package com.doubleclick.snapchatclone.ReciclerViewReciever;

public class RecieverObject {

//    private String email;
    private String uid;
    private Boolean receive;

    public RecieverObject(String username, String uid, Boolean receive){
        this.username = username;
        this.uid = uid;
        this.receive = receive;
    }

    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }

//    public String getEmail(){
//        return username;
//    }
//    public void setEmail(String email){
//        this.username = email;
//    }

    public Boolean getReceive(){
        return receive;
    }
    public void setReceive(Boolean receive){
        this.receive = receive;
    }

    private String username;
//    private String uid;
    private String profileImageUrl;
//    private boolean receive;
//
    public RecieverObject(String username, String uid, String profileImageUrl, boolean receive) {
        this.username = username;
        this.uid = uid;
        this.receive = receive;
        this.profileImageUrl = profileImageUrl;
    }
//
//    public String getUid() {
//        return uid;
//    }
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
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
//
//    public boolean getReceive() {
//        return receive;
//    }
//    public void setReceive(boolean receive) {
//        this.receive = receive;
//    }
}
