package com.example.kjh_stock01.ui.home.post;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("body")
    private String userId;
    private String id;
    private String title;
    private String text;

    public Post(String userId, String id, String title, String text) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getText() {

        return text;
    }
}
