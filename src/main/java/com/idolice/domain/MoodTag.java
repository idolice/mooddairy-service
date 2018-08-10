package com.idolice.domain;

public enum  MoodTag {
    EXCITING("exciting",1,"兴奋"),
    SAD("sad", 2,"悲伤"),
    CRY("cry",3,"哭泣"),
    INLOVE("inlove",4,"甜蜜"),
    ANGRY("angry",5,"生气"),
    HAPPY("happy",6,"开心"),
    NORMAL("normal",7,"平常"),
    MENGBI("mengbi",8,"懵逼");
    private Integer index;
    private String name;
    private String pieTag;
    private MoodTag(String name, Integer index, String pieTag){
        this.name = name;
        this.index = index;
        this.pieTag = pieTag;
    }
    public String getName() {
        return name;
    }
    public String getPieTag() {return pieTag;}
    private int getIndex() {return index;}
    public static Integer getIndex(String name) {
        for (MoodTag c : MoodTag.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.index;
            }
        }
        return null;
    }

    public static String getPieTag(int moodTag) {
        for (MoodTag c : MoodTag.values()) {
            if (c.getIndex() == moodTag) {
                return c.pieTag;
            }
        }
        return null;
    }

}
