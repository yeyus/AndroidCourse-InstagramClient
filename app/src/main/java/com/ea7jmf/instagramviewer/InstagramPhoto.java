package com.ea7jmf.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jesusft on 1/22/15.
 */
public class InstagramPhoto {

    private String username;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount = 0;

    public InstagramPhoto(String username, String caption, String imageUrl, int imageHeight, int likesCount) {
        this.username = username;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public static InstagramPhoto parse(JSONObject photoJSON) throws JSONException {
        return new InstagramPhoto(
                photoJSON.getJSONObject("user").getString("username"),
                photoJSON.getJSONObject("caption").getString("text"),
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"),
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"),
                photoJSON.getJSONObject("likes").getInt("count"));
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" +
                "username='" + username + '\'' +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageHeight=" + imageHeight +
                ", likesCount=" + likesCount +
                '}';
    }
}
