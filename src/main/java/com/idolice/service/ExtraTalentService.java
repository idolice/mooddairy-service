package com.idolice.service;

import com.idolice.domain.MoodTag;
import com.idolice.entity.Mood;
import com.idolice.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.idolice.domain.MoodTag.*;

@Service
public class ExtraTalentService {
    @Autowired
    private MoodRepository moodRepository;

    public boolean ifNotHappyMoreThanWeek(String openId, int month, int year) {
        List<Mood> moods = moodRepository.findByMonthAndYearAndOpenIdOrderByDayDesc(month, year, openId);
        return ifMoodMoreThanWeek(moods, SAD, CRY, ANGRY);
    }

    public boolean ifHappyMoreThanWeek(String openId, int month, int year) {
        List<Mood> moods = moodRepository.findByMonthAndYearAndOpenIdOrderByDayDesc(month, year, openId);
        return ifMoodMoreThanWeek(moods, INLOVE, HAPPY, EXCITING);
    }

    private boolean ifMoodMoreThanWeek(List<Mood> moods, MoodTag tag1, MoodTag tag2, MoodTag tag3) {
        if (moods.size() >= 7) {
            List<Mood> subMoods = moods.subList(0, 6);
            return subMoods.stream().allMatch(mood -> mood.getMoodTag().equals(tag1.getIndex()) ||
                    mood.getMoodTag().equals(tag2.getIndex()) ||
                    mood.getMoodTag().equals(tag3.getIndex()));
        }
        return false;
    }
}
