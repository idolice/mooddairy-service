package com.idolice.service;

import com.idolice.Model.MoodIndex;
import com.idolice.entity.Mood;
import com.idolice.repository.MoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoodRepository moodRepository;

    public List<MoodIndex> getMoodForMonth(String openId) {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        List<Mood> moods = moodRepository.findByMonthAndOpenId(month, openId);
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
