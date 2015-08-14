package com.expou.item;

/**
 * Created by Kim on 2015-07-19.
 */
public class ContentItem {
    private String name;
    private String text;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
