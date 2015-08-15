package com.expou.tab.t4content;

import android.graphics.Bitmap;

/**
 * Created by Kim on 2015-07-19.
 */
public class ContentItem {
    private String name;
    private String text;
    private Bitmap img;

    private String objectId;
    private int position;
    private String date;
    private String detail;

    public ContentItem(){
    }

    public ContentItem(String name, String text){
        this.name = name;
        this.text = text;

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

    public String getDetail() {
        return detail;
    }

    public String getDate() {
        return date;
    }

}
