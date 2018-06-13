package com.idolice.service;

import com.idolice.Model.MoodIndex;
import com.idolice.domain.response.RecordResponseDTO;
import com.idolice.entity.Mood;
import com.idolice.repository.MoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoodRepository moodRepository;

    public RecordResponseDTO getMoodRecordForADay(int year, int month, int day, String openId){
        List<Mood> moods = moodRepository.findByYearAndMonthAndDayAndOpenId(year, month, day, openId);
        RecordResponseDTO recordResponseDTO = new RecordResponseDTO();
        if(moods == null || moods.size() == 0){
            recordResponseDTO.setIfHasNoRecord(true);
            recordResponseDTO.setRecord("你还没有记录当天的心情哦~是否要添加？");
        } else {
            recordResponseDTO.setIfHasNoRecord(false);
            recordResponseDTO.setRecord(moods.get(0).getReason());
        }
        return recordResponseDTO;
    }

    public List<MoodIndex> getMoodForMonth(int year, int month, String openId) {
        List<Mood> moods = moodRepository.findByMonthAndYearAndOpenId(month, year, openId);
        logger.info("moods size: {}", moods.size());
        return mapToMoodIndex(moods);
    }

    private List<MoodIndex> mapToMoodIndex(List<Mood> moods) {
       return moods.stream().map(item -> {
            MoodIndex moodIndex = new MoodIndex();
            moodIndex.setIndex(item.getDay());
            moodIndex.setMoodTag(item.getMoodTag());
            return moodIndex;
        }).collect(Collectors.toList());
    }

}
