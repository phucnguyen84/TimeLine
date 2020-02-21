package com.volio.model.entity2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot implements Serializable {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("pest_id")
    @Expose
    private Integer pestId;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getPestId() {
        return pestId;
    }

    public void setPestId(Integer pestId) {
        this.pestId = pestId;
    }

}