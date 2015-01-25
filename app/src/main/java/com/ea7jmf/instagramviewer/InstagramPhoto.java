package com.ea7jmf.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by jesusft on 1/22/15.
 */
public class InstagramPhoto {

    private String username;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount = 0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private long createdTime;

    public InstagramPhoto(String username, String caption, String imageUrl, int imageHeight, int likesCount, long createdTime) {
        this.username = username;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
        this.createdTime = createdTime;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public static InstagramPhoto parse(JSONObject photoJSON) throws JSONException {
        InstagramPhoto ip = new InstagramPhoto(
                photoJSON.getJSONObject("user").getString("username"),
                photoJSON.getJSONObject("caption").getString("text"),
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"),
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"),
                photoJSON.getJSONObject("likes").getInt("count"),
                Long.parseLong(photoJSON.getString("created_time")));

        if (!photoJSON.isNull("location")) {
            ip.setLatitude(photoJSON.getJSONObject("location").getDouble("latitude"));
            ip.setLongitude(photoJSON.getJSONObject("location").getDouble("longitude"));
        }

        return ip;
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" +
                "username='" + username + '\'' +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", likesCount=" + likesCount +
                '}';
    }
}
