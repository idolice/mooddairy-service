package com.idolice.domain;

public enum  MoodTag {
    EXCITING("exciting",1),
    SAD("sad", 2),
    CRY("cry",3),
    INLOVE("inlove",4),
    ANGRY("angry",5),
    HAPPY("happy",6),
    NORMAL("normal",7),
    MENGBI("mengbi",8);
    private Integer index;
    private String name;
    private MoodTag(String name, Integer index){
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public static Integer getIndex(String name) {
        for (MoodTag c : MoodTag.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.index;
            }
        }
        return null;
    }

}
