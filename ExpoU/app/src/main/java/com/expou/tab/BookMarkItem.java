package com.expou.tab;

/**
 * Created by cho on 2015-08-22.
 */
public class BookMarkItem {
    public String type;

    public String getOtherObj() {
        return otherObj;
    }

    public void setOtherObj(String otherObj) {
        this.otherObj = otherObj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String otherObj;

    public BookMarkItem(String type, String otherObj) {
        this.type = type;
        this.otherObj = otherObj;
    }
}
