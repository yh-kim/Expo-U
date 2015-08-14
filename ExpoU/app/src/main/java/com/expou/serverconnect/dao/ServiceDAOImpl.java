package com.expou.serverconnect.dao;

import android.support.v7.appcompat.BuildConfig;

import com.expou.exception.ServiceException;
import com.expou.tab.t2expo.ExpoItem;
import com.expou.tab.t3booth.BoothItem;
import com.expou.tab.t4content.ContentItem;
import com.expou.tab.t5market.MarketItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim on 2015-07-19.
 */
public class ServiceDAOImpl implements ServiceDAO{


    static ArrayList<ContentItem> ConItems = new ArrayList<>();
    static ArrayList<BoothItem> BoothItems = new ArrayList<>();
    static ArrayList<ExpoItem> ExpoItems = new ArrayList<>();
    static ArrayList<MarketItem> MarketItems = new ArrayList<>();
    @Override
    public ArrayList<ContentItem> getContent() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("c_Contents");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e != null) {
                    if (BuildConfig.DEBUG) e.printStackTrace();
                    return;
                }

                if (list == null || list.size() < 1) {
                    new ServiceException("err");
                    return;
                }
                ConItems.clear();

                for (ParseObject obj : list) {
                    ConItems.add(new ContentItem(obj.get("contents_name").toString(), obj.get("contents_name").toString(), obj.get("contents_clickNum").toString(), obj.get("contents_clickNum").toString()));
                }
            }
        });
        return ConItems;
    }

    @Override
    public ArrayList<BoothItem> getBooth() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("b_Booth");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e != null) {
                    if (BuildConfig.DEBUG) e.printStackTrace();
                    return;
                }

                if (list == null || list.size() < 1) {
                    new ServiceException("err");
                    return;
                }
                BoothItems.clear();

                for (ParseObject obj : list) {

                    //items.add(new ContentItem(obj.get("Contents_name").toString() , obj.get("Contents_text").toString()));
                    BoothItems.add(new BoothItem(obj.get("booth_name").toString(), obj.get("objectId").toString()));

                }
            }
        });

        return BoothItems;
    }

    @Override
    public ArrayList<ExpoItem> getExpo() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("a_Expo");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e != null) {
                    if (BuildConfig.DEBUG) e.printStackTrace();
                    return;
                }

                if (list == null || list.size() < 1) {
                    new ServiceException("err");
                    return;
                }
                ExpoItems.clear();

                for (ParseObject obj : list) {
                    //items.add(new ContentItem(obj.get("Contents_name").toString() , obj.get("Contents_text").toString()));
                    ExpoItems.add(new ExpoItem(obj.get("expo_name").toString(), obj.get("expo_intro").toString()));
                }
            }
        });

        return ExpoItems;
    }

    @Override
    public ArrayList<MarketItem> getMarket() throws ServiceException {
        return null;
    }
}