
package com.volio.model.entityLiked;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("refer_id")
    @Expose
    private String referId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("liked")
    @Expose
    private Boolean liked;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
