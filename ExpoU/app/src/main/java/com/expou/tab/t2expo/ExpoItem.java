package com.expou.tab.t2expo;


import android.graphics.Bitmap;

/**
 * Created by Kim on 2015-07-14.
 */
public class ExpoItem {
    private String name;
    private Bitmap img;
    private String hit;
    private String love;
    private String objectId;

    public ExpoItem(String objectId,String name, String hit, String love){
        this.name = name;
        this.hit = hit;
        this.love = love;
    }




    public Bitmap getImgExpo() {
        return img;
    }

    public void setImgExpo(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
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

    public String getObjectId() {
        return objectId;
    }
}
