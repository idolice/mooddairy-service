package com.idolice.domain.response;

import com.idolice.entity.MoodMatrix;

import java.util.List;

public class ReportDTO {
  private List<MoodMatrix> moodMatrices;

    public List<MoodMatrix> getMoodMatrices() {
        return moodMatrices;
    }

    public void setMoodMatrices(List<MoodMatrix> moodMatrices) {
        this.moodMatrices = moodMatrices;
    }
}
