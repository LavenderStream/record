package org.record.tiny.demo.model;

@SuppressWarnings("All")
public class StoryItem {
    private String userImage;
    private String nickName;
    private String title;
    private String subTitle;
    private int like;
    private int comment;
    private String image;
    private String link;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "StoryItem{" +
                "userImage='" + userImage + '\'' +
                ", nickName='" + nickName + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", like=" + like +
                ", comment=" + comment +
                '}';
    }
}
