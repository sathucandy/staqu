package com.demo.assignment.models;

import com.google.gson.annotations.SerializedName;

public class ItemsResponseModel {

    @SerializedName("link")
    private String link;

    @SerializedName("title")
    private String title;

    @SerializedName("owner")
    private UserResponseModel user;

}
