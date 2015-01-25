package com.ea7jmf.instagramviewer.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yeyus on 1/24/15.
 */
public class InstagramUser {

    private String username;
    private String profilePicUrl;
    private String fullName;

    public InstagramUser(String username, String profilePicUrl, String fullName) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static InstagramUser parse(JSONObject userJSON) throws JSONException {
        InstagramUser iu = new InstagramUser(
                userJSON.getString("username"),
                userJSON.getString("profile_picture"),
                userJSON.getString("full_name"));

        return iu;
    }

    @Override
    public String toString() {
        return "InstagramUser{" +
                "username='" + username + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
