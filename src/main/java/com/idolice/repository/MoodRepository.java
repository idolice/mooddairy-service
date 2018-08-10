package com.idolice.repository;

import com.idolice.entity.Mood;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodRepository extends PagingAndSortingRepository<Mood, Integer> {
    List<Mood> findByOpenId(String openId);
    List<Mood> findByYearAndMonthAndDay(int year, int month, int day);
    List<Mood> findByMonthAndYearAndOpenId(int month, int year, String openId);
    List<Mood> findByYearAndMonthAndDayAndOpenId(int year, int month, int day, String openId);
    List<Mood> findByYear(int year);
}
