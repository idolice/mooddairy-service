package com.idolice.repository;

import com.idolice.Model.Mood;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodRepository extends PagingAndSortingRepository<Mood, Integer> {
    List<Mood> findByOpenId(String openId);
    List<Mood> findByYearAndMonthAndDay(int year, int month, int day);
}
