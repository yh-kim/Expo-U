package com.expou.tab.t3booth;


import android.graphics.Bitmap;

/**
 * Created by Kim on 2015-07-14.
 */
public class BoothItem {
    private Bitmap img;
    private String objectId;
    private String txtTitle,txtExplain;
    private String hit,love,contents;
    private String youtubeCode;

    public BoothItem() {
    }

    public BoothItem(String txtTitle, String txtExplain){
        this.txtExplain = txtExplain;
        this.txtTitle = txtTitle;
    }

    public BoothItem(String objectId, String txtTitle, String txtExplain, String hit, String love, String contents){
        this.objectId = objectId;
        this.txtTitle = txtTitle;
        this.txtExplain = txtExplain;
        this.hit = hit;
        this.love = love;
        this.contents = contents;

    }


    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtExplain() {
        return txtExplain;
    }

    public void setTxtExplain(String txtExplain) {
        this.txtExplain = txtExplain;
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getYoutubeCode() {
        return youtubeCode;
    }

    public void setYoutubeCode(String youtubeCode) {
        this.youtubeCode = youtubeCode;
    }
}
