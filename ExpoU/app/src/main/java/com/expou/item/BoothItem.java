package com.expou.item;


/**
 * Created by Kim on 2015-07-14.
 */
public class BoothItem {
    private String imgBooth;
    private String txtTitle,txtExplain;

    public BoothItem(){
        imgBooth = "@drawable/booth_img";
        txtExplain = "testExplain";
        txtTitle = "testTitle";
    }

    public BoothItem(String imgExpo,String txtTitle, String txtExplain){
        this.imgBooth = "@drawable/"+imgExpo;
        this.txtExplain = txtExplain;
        this.txtTitle = txtTitle;
    }

    public String getImgBooth() {
        return imgBooth;
    }

    public void setImgBooth(String imgBooth) {
        this.imgBooth = imgBooth;
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
}
