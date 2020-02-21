package com.volio.model.entity2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot__ implements Serializable {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("product_type_id")
    @Expose
    private Integer productTypeId;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

}