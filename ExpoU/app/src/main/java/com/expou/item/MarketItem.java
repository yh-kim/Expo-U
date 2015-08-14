package com.expou.item;


/**
 * Created by Kim on 2015-07-14.
 */
public class MarketItem {
    private String imgExpo;
    private String txtExplain;

    public MarketItem(){
//        imgExpo = "@drawable/poster";
//        txtExplain = "test";
    }

    public MarketItem(String imgExpo, String txtExplain){
        this.imgExpo = "@drawable/"+imgExpo;
        this.txtExplain = txtExplain;
    }


    public String getImgExpo() {
        return imgExpo;
    }

    public void setImgExpo(String imgExpo) {
        this.imgExpo = imgExpo;
    }

    public String getTxtExplain() {
        return txtExplain;
    }

    public void setTxtExplain(String txtExplain) {
        this.txtExplain = txtExplain;
    }
}
