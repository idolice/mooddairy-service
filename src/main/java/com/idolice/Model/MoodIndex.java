package com.idolice.Model;

import java.io.Serializable;

public class MoodIndex implements Serializable {
    private int index;
    private int moodTag;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getMoodTag() {
        return moodTag;
    }

    public void setMoodTag(int moodTag) {
        this.moodTag = moodTag;
    }
}
