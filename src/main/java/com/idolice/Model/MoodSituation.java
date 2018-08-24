package com.idolice.Model;

import java.io.Serializable;
import java.util.List;

public class MoodSituation implements Serializable {
    private List<MoodIndex> moodIndexList;
    private boolean ifNotHappyMoreThanWeek;
    private boolean ifHappyMoreThanWeek;

    public List<MoodIndex> getMoodIndexList() {
        return moodIndexList;
    }

    public void setMoodIndexList(List<MoodIndex> moodIndexList) {
        this.moodIndexList = moodIndexList;
    }

    public boolean isIfNotHappyMoreThanWeek() {
        return ifNotHappyMoreThanWeek;
    }

    public void setIfNotHappyMoreThanWeek(boolean ifNotHappyMoreThanWeek) {
        this.ifNotHappyMoreThanWeek = ifNotHappyMoreThanWeek;
    }

    public boolean isIfHappyMoreThanWeek() {
        return ifHappyMoreThanWeek;
    }

    public void setIfHappyMoreThanWeek(boolean ifHappyMoreThanWeek) {
        this.ifHappyMoreThanWeek = ifHappyMoreThanWeek;
    }
}
