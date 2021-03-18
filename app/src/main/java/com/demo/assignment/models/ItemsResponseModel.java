package com.demo.assignment.models;

import com.google.gson.annotations.SerializedName;

public class ItemsResponseModel {

    @SerializedName("title")
    private String title;

    @SerializedName("owner")
    private UserResponseModel user;

    public String getTitle() {
        return title;
    }

    public UserResponseModel getUser() {
        return user;
    }
}
