package com.ea7jmf.instagramviewer.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yeyus on 1/24/15.
 */
public class InstagramComment {

    private long createdTime;
    private String text;
    private InstagramUser user;

    public InstagramComment(long createdTime, String text, InstagramUser user) {
        this.createdTime = createdTime;
        this.text = text;
        this.user = user;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
    }

    public static InstagramComment parse(JSONObject commentJSON) throws JSONException {
        InstagramComment ic = new InstagramComment(
                commentJSON.getLong("created_time"),
                commentJSON.getString("text"),
                InstagramUser.parse(commentJSON.getJSONObject("from"))
        );

        return ic;
    }

    @Override
    public String toString() {
        return "InstagramComment{" +
                "createdTime=" + createdTime +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
