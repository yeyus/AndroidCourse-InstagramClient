package com.ea7jmf.instagramviewer.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jesusft on 1/22/15.
 */
public class InstagramPhoto {

    private InstagramUser user;
    private ArrayList<InstagramComment> comments;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount = 0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String locationName;
    private long createdTime;

    public InstagramPhoto(String caption, String imageUrl, int imageHeight, int likesCount, long createdTime) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
        this.createdTime = createdTime;
        this.comments = new ArrayList<>();

    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public ArrayList<InstagramComment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<InstagramComment> comments) {
        this.comments = comments;
    }

    public static InstagramPhoto parse(JSONObject photoJSON) throws JSONException {
        String caption = "";

        if(!photoJSON.isNull("caption")) {
            caption = photoJSON.getJSONObject("caption").getString("text");
        }

        InstagramPhoto ip = new InstagramPhoto(
                caption,
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"),
                photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"),
                photoJSON.getJSONObject("likes").getInt("count"),
                Long.parseLong(photoJSON.getString("created_time")));

        if (!photoJSON.isNull("location")) {
            JSONObject locationJSON = photoJSON.getJSONObject("location");
            if (!locationJSON.isNull("latitude") ||
                    !locationJSON.isNull("longitude")) {
                ip.setLatitude(locationJSON.getDouble("latitude"));
                ip.setLongitude(locationJSON.getDouble("longitude"));
            }

            if(!locationJSON.isNull("name")) {
                ip.setLocationName(locationJSON.getString("name"));
            }
        }

        return ip;
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", likesCount=" + likesCount +
                '}';
    }
}
