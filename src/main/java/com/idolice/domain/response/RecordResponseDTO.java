package com.idolice.domain.response;

import java.io.Serializable;

public class RecordResponseDTO implements Serializable {
    private boolean ifHasNoRecord;
    private String record;

    public boolean isIfHasNoRecord() {
        return ifHasNoRecord;
    }

    public void setIfHasNoRecord(boolean ifHasNoRecord) {
        this.ifHasNoRecord = ifHasNoRecord;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
