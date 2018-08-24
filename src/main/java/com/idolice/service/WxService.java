package com.idolice.service;

import com.idolice.entity.Mood;
import com.idolice.entity.User;
import com.idolice.domain.MoodTag;
import com.idolice.domain.request.WxUserMoodInfoVO;
import com.idolice.repository.MoodRepository;
import com.idolice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@Service
public class WxService {
    private final static String WX_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MoodRepository moodRepository;
    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> getOpenId(String jsCode) {
        MultiValueMap<String, String> queryParamMap = new LinkedMultiValueMap<>();
        queryParamMap.add("appid", "wx94ca369c922f9c59");
        queryParamMap.add("secret", "af00fddb9218bcc514d3c3c4bf05dc15");
        queryParamMap.add("js_code", jsCode);
        queryParamMap.add("grant_type", "authorization_code");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> wxOpenIdVOResponseEntity;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WX_URL).queryParams(queryParamMap);
        wxOpenIdVOResponseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, httpEntity, String.class);
        return wxOpenIdVOResponseEntity;

    }

    @Transactional
    public HttpStatus saveUserMood(WxUserMoodInfoVO wxUserMoodInfoVO) {
        try {
            LocalDate localDate = LocalDate.now();
            Mood mood = new Mood();
            if (wxUserMoodInfoVO.getYear() == 0) {
                mood.setYear(localDate.getYear());
            } else {
                mood.setYear(wxUserMoodInfoVO.getYear());
            }
            if (wxUserMoodInfoVO.getMonth() == 0) {
                mood.setMonth(localDate.getMonthValue());
            } else {
                mood.setMonth(wxUserMoodInfoVO.getMonth());
            }

            if (wxUserMoodInfoVO.getDay() == 0) {
                mood.setDay(localDate.getDayOfMonth());
            } else {
                mood.setDay(wxUserMoodInfoVO.getDay());
            }
            if (userRepository.findByOpenId(wxUserMoodInfoVO.getOpenId()) == null) {
                User user = new User();
                user.setOpenId(wxUserMoodInfoVO.getOpenId());
                user.setGender(wxUserMoodInfoVO.getUserInfo().getGender());
                user.setNickName(wxUserMoodInfoVO.getUserInfo().getNickName());
                user.setProvince(wxUserMoodInfoVO.getUserInfo().getProvince());
                logger.info("insert user to db: " + user);
                userRepository.save(user);
            }
            List<Mood> moods = moodRepository.findByYearAndMonthAndDayAndOpenId(mood.getYear(), mood.getMonth(), mood.getDay(), wxUserMoodInfoVO.getOpenId());
            if (moods.size() != 0) {
                Mood moodExisted = moods.get(0);
                moodExisted.setOpenId(wxUserMoodInfoVO.getOpenId());
                moodExisted.setMoodTag(MoodTag.getIndex(wxUserMoodInfoVO.getMood()));
                moodExisted.setReason(wxUserMoodInfoVO.getReason());
                logger.info("insert mood to db: " + moodExisted);
                moodRepository.save(moodExisted);
            } else {
                mood.setOpenId(wxUserMoodInfoVO.getOpenId());
                mood.setMoodTag(MoodTag.getIndex(wxUserMoodInfoVO.getMood()));
                mood.setReason(wxUserMoodInfoVO.getReason());
                logger.info("insert user to db: " + mood);
                moodRepository.save(mood);

            }
        } catch (RuntimeException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.ACCEPTED;
    }
}
