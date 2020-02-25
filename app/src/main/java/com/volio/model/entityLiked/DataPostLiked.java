package com.volio.model.entityLiked;

import androidx.annotation.NonNull;

public class DataPostLiked {
    private String refer_id,type_id;

    public DataPostLiked(String refer_id, String type_id) {
        this.refer_id = refer_id;
        this.type_id = type_id;
    }

    @NonNull
    @Override
    public String toString() {
        return refer_id+"-"+type_id;
    }
}
