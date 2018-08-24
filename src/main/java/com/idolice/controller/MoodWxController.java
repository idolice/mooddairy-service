package com.idolice.controller;

import com.idolice.Model.MoodIndex;
import com.idolice.Model.MoodSituation;
import com.idolice.domain.request.WxUserMoodInfoVO;
import com.idolice.domain.response.RecordResponseDTO;
import com.idolice.domain.response.ReportDTO;
import com.idolice.service.ExtraTalentService;
import com.idolice.service.MoodService;
import com.idolice.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class MoodWxController {
    @Autowired
    WxService wxService;
    @Autowired
    MoodService moodService;
    @Autowired
    ExtraTalentService extraTalentService;

    @RequestMapping(value = "/openId")
    public ResponseEntity<String> getOpenId(@RequestParam String jsCode) {
        return wxService.getOpenId(jsCode);
    }

    @RequestMapping(value = "/userMood", method = RequestMethod.POST)
    public HttpStatus saveUserMood(@RequestBody WxUserMoodInfoVO wxUserMoodInfoVO) {
        return wxService.saveUserMood(wxUserMoodInfoVO);
    }

    @RequestMapping(value = "/{year}/{month}/{openId}", method = RequestMethod.GET)
    public ResponseEntity<MoodSituation> getMoodOfMonth(@PathVariable int year, @PathVariable int month, @PathVariable String openId) {
        List<MoodIndex> moodIndices = moodService.getMoodForMonth(year, month, openId);
        MoodSituation moodSituation = new MoodSituation();
        moodSituation.setMoodIndexList(moodIndices);
        LocalDate localDate = LocalDate.now();
        boolean ifNotHappyMoreThanWeek = extraTalentService.ifNotHappyMoreThanWeek(openId, localDate.getMonth().getValue(), localDate.getYear());
        boolean ifHappyMoreThanWeek = extraTalentService.ifHappyMoreThanWeek(openId, localDate.getMonth().getValue(), localDate.getYear());
        moodSituation.setIfNotHappyMoreThanWeek(ifNotHappyMoreThanWeek);
        moodSituation.setIfHappyMoreThanWeek(ifHappyMoreThanWeek);
        ResponseEntity<MoodSituation> responseEntity =
                new ResponseEntity<>(moodSituation, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @RequestMapping(value = "/{openId}/record", method = RequestMethod.GET)
    public RecordResponseDTO getRecordOfSpecificDay(@PathVariable String openId, @PathParam("year") int year,
                                                    @PathParam("month") int month, @PathParam("day") int day) {
        return moodService.getMoodRecordForADay(year, month, day, openId);
    }

    @RequestMapping(value = "/report/{openId}/{year}", method = RequestMethod.GET)
    public ReportDTO getReport(@PathVariable String openId, @PathVariable int year) {
        return moodService.getYearReport(year, openId);
    }


}
