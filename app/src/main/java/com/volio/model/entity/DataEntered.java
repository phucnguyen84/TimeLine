package com.volio.model.entity;

public class DataEntered {
    private String username,password,tenantcode,language;

    public DataEntered(String username, String password, String tenantcode, String language) {
        this.username = username;
        this.password = password;
        this.tenantcode = tenantcode;
        this.language = language;
    }
}
