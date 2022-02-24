package com.doubleclick.snapchatclone.recyclerViewFollow;

public class FollowObject {

//    private String email;
//    private String uid;

    public FollowObject(String username, String uid){
        this.username = username;
        this.uid = uid;
    }
//
//    public String getUid(){
//        return uid;
//    }
//    public void setUid(String uid){
//        this.uid = uid;
//    }
//
//    public String getEmail(){
//        return email;
//    }
//    public void setEmail(String email){
//        this.email = email;
//    }

    private String username;
    private String uid;
    private String profileImageUrl;

    public FollowObject(String username, String uid, String profileImageUrl) {
        this.username = username;
        this.uid = uid;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
