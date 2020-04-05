package com.mhr.entiy;
import java.util.Date;


public class VideoBean {

    private String sid;
    private String text;    //内容简介
    private String type;    //类型
    private String thumbnail;   //缩略图
    private String video;   //视频
    private String images;  //null
    private String up;
    private String down;
    private String forward;
    private String comment;
    private String uid;
    private String name;    //标题
    private String header;
    private String top_comments_content;
    private String top_comments_voiceuri;
    private String top_comments_uid;
    private String top_comments_name;
    private String top_comments_header;
    private Date passtime;
    public void setSid(String sid) {
         this.sid = sid;
     }
     public String getSid() {
         return sid;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setThumbnail(String thumbnail) {
         this.thumbnail = thumbnail;
     }
     public String getThumbnail() {
         return thumbnail;
     }

    public void setVideo(String video) {
         this.video = video;
     }
     public String getVideo() {
         return video;
     }

    public void setImages(String images) {
         this.images = images;
     }
     public String getImages() {
         return images;
     }

    public void setUp(String up) {
         this.up = up;
     }
     public String getUp() {
         return up;
     }

    public void setDown(String down) {
         this.down = down;
     }
     public String getDown() {
         return down;
     }

    public void setForward(String forward) {
         this.forward = forward;
     }
     public String getForward() {
         return forward;
     }

    public void setComment(String comment) {
         this.comment = comment;
     }
     public String getComment() {
         return comment;
     }

    public void setUid(String uid) {
         this.uid = uid;
     }
     public String getUid() {
         return uid;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setHeader(String header) {
         this.header = header;
     }
     public String getHeader() {
         return header;
     }

    public void setTop_comments_content(String top_comments_content) {
         this.top_comments_content = top_comments_content;
     }
     public String getTop_comments_content() {
         return top_comments_content;
     }

    public void setTop_comments_voiceuri(String top_comments_voiceuri) {
         this.top_comments_voiceuri = top_comments_voiceuri;
     }
     public String getTop_comments_voiceuri() {
         return top_comments_voiceuri;
     }

    public void setTop_comments_uid(String top_comments_uid) {
         this.top_comments_uid = top_comments_uid;
     }
     public String getTop_comments_uid() {
         return top_comments_uid;
     }

    public void setTop_comments_name(String top_comments_name) {
         this.top_comments_name = top_comments_name;
     }
     public String getTop_comments_name() {
         return top_comments_name;
     }

    public void setTop_comments_header(String top_comments_header) {
         this.top_comments_header = top_comments_header;
     }
     public String getTop_comments_header() {
         return top_comments_header;
     }

    public void setPasstime(Date passtime) {
         this.passtime = passtime;
     }
     public Date getPasstime() {
         return passtime;
     }

    @Override
    public String toString() {
        return "VideoBean{" +
                "sid='" + sid + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", video='" + video + '\'' +
                ", images='" + images + '\'' +
                ", up='" + up + '\'' +
                ", down='" + down + '\'' +
                ", forward='" + forward + '\'' +
                ", comment='" + comment + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", top_comments_content='" + top_comments_content + '\'' +
                ", top_comments_voiceuri='" + top_comments_voiceuri + '\'' +
                ", top_comments_uid='" + top_comments_uid + '\'' +
                ", top_comments_name='" + top_comments_name + '\'' +
                ", top_comments_header='" + top_comments_header + '\'' +
                ", passtime=" + passtime +
                '}';
    }
}