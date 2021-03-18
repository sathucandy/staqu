package com.demo.assignment.models;

import com.google.gson.annotations.SerializedName;

public class UserResponseModel {

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("link")
    private String link;

    public String getProfileImage() {
        return profileImage;
    }

    public String getLink() {
        return link;
    }
}
