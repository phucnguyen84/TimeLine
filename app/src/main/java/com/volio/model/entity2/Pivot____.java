package com.volio.model.entity2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot____ implements Serializable {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("packaged_id")
    @Expose
    private Integer packagedId;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getPackagedId() {
        return packagedId;
    }

    public void setPackagedId(Integer packagedId) {
        this.packagedId = packagedId;
    }

}