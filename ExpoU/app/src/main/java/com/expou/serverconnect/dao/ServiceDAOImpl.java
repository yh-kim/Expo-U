package com.expou.serverconnect.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;

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
     static ArrayList<MarketItem> MarketItems = new ArrayList<>();
    public static final ArrayList<ExpoItem> ExpoItems = new ArrayList<>();

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

                    ContentItem conValue = new ContentItem(obj.getObjectId(),
                            obj.get("contents_name").toString(),
                            obj.get("contents_detail").toString(),
                            obj.get("contents_clickNum").toString(),
                            obj.get("contents_heartNum").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("contents_img").getData(),
                                0,
                                obj.getParseFile("contents_img").getData().length);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    conValue.setImg(bitmap);

                    ConItems.add(conValue);
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
                    BoothItems.add(new BoothItem(obj.get("booth_name").toString(),
                            obj.get("objectId").toString()));

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
                    ExpoItem conValue = new ExpoItem(obj.getObjectId(),
                            obj.get("expo_name").toString(),
                            obj.get("expo_clickNum").toString(),
                            obj.get("expo_heartNum").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("expo_poster").getData(),
                                0,
                                obj.getParseFile("expo_poster").getData().length);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    conValue.setImg(bitmap);


                    getExpoItems().add(conValue);

                }
            }
        });

        Log.e("bingle : ", getExpoItems().size()+"..");
        return getExpoItems();
    }

    @Override
    public ArrayList<MarketItem> getMarket() throws ServiceException {
        return null;
    }

    public ArrayList<ExpoItem> getExpoItems() {
        return ExpoItems;
    }
}