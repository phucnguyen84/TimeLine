
package com.volio.model.entityPeopleLiked;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPeopleLiked {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private List<Datum4> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Datum4> getData() {
        return data;
    }

    public void setData(List<Datum4> data) {
        this.data = data;
    }

}
