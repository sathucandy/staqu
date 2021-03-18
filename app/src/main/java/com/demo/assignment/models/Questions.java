package com.demo.assignment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {

    @SerializedName("items")
    private List<ItemsResponseModel> itemsList;

    public List<ItemsResponseModel> getItemsList() {
        return itemsList;
    }
}
