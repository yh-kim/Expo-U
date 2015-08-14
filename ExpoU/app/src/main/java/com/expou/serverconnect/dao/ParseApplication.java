package com.expou.serverconnect.dao;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


/**
 * Created by Kim on 2015-07-19.
 */
public class ParseApplication extends Application {

//    private static final String APPLICATION_ID = "UGdXkZQyXBy5hJBIjoDMIRUTty9SJB2Etj04hzah";
//    private static final String CLIENT_KEY = "ZGEp3T0BWadiSnm2d0P2mfrAYLkc2ZvqJy4FJdQ6";
    private static final String APPLICATION_ID = "tn9d02bCQ4bzBg0rIWdck4335XFmzv3rROCXyRZI";
    private static final String CLIENT_KEY = "5BLewJqbqY7EttsOC04r2MgQnLGoYtFM0QqHzOWg";

    @Override
    public void onCreate() {
        super.onCreate();

        // Parse.enableLocalDatastore(this);
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(false);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
