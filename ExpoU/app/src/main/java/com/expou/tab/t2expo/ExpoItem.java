package com.expou.tab.t2expo;


/**
 * Created by Kim on 2015-07-14.
 */
public class ExpoItem {
    private String imgExpo;
    private String txtExplain;

    public ExpoItem(){
        imgExpo = "@drawable/poster";
        txtExplain = "test";
    }

    public ExpoItem(String imgExpo, String txtExplain){
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
