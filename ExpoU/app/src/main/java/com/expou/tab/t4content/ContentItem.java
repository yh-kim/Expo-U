package com.expou.tab.t4content;

import android.graphics.Bitmap;

/**
 * Created by Kim on 2015-07-19.
 */
public class ContentItem {
    private String name;
    private String text;
    private Bitmap img;
    private String hit;
    private String love;

    private String objectId;
    private int position;
    private String date;
    private String detail;
    private String youtubeCode;

    private String expo;
    private String booth;

    public String getExpo() {
        return expo;
    }

    public void setExpo(String expo) {
        this.expo = expo;
    }

    public String getBooth() {
        return booth;
    }

    public void setBooth(String booth) {
        this.booth = booth;
    }

    public ContentItem(String objectId, String name, String detail, String hit, String love){
        this.objectId =objectId;
        this.name = name;
        this.detail = detail;
        this.hit = hit;
        this.love = love;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public ContentItem(String objectId, String name, String date, String detail){
        this.objectId =objectId;
        this.name = name;
        this.date = date;
        this.detail = detail;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getYoutubeCode() {
        return youtubeCode;
    }

    public void setYoutubeCode(String youtubeCode) {
        this.youtubeCode = youtubeCode;
    }
}
