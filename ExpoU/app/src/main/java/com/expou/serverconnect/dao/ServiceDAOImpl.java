package com.expou.serverconnect.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


      static public ArrayList<ContentItem> ConItems = new ArrayList<>();
      static public ArrayList<BoothItem> BoothItems = new ArrayList<>();
      static ArrayList<MarketItem> MarketItems = new ArrayList<>();
      static public ArrayList<ExpoItem> ExpoItems = new ArrayList<>();

    @Override
    public ArrayList<ContentItem> getContent() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("c_Contents");
        query.setLimit(2);
        query.orderByDescending("contents_name");
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


                for (ParseObject obj : list) {

                    ContentItem conValue = new ContentItem(obj.getObjectId(),
                            obj.get("contents_name").toString(),
                            obj.get("contents_detail").toString(),
                            obj.get("contents_clickNum").toString(),
                            obj.get("contents_heartNum").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options  = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                            bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("contents_img").getData(),
                                    0,
                                    obj.getParseFile("contents_img").getData().length,
                                    options);

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
        query.setLimit(4);
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

                for (ParseObject obj : list) {
                    BoothItem boothValue = new BoothItem(obj.getObjectId(),
                            obj.get("booth_name").toString(),
                            obj.get("booth_intro").toString(),
                            obj.get("booth_clickNum").toString(),
                            obj.get("booth_heartNum").toString(),
                            obj.get("booth_heartNum").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("booth_logo").getData(),
                                0,
                                obj.getParseFile("booth_logo").getData().length,
                                options);

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    boothValue.setImg(bitmap);


                    BoothItems.add(boothValue);

                }
            }
        });

        return BoothItems;
    }

    @Override
    public ArrayList<ExpoItem> getExpo() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("a_Expo");
        query.setLimit(4);
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


                for (ParseObject obj : list) {
                    ExpoItem conValue = new ExpoItem(obj.getObjectId(),
                            obj.get("expo_name").toString(),
                            obj.get("expo_clickNum").toString(),
                            obj.get("expo_heartNum").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options  = new BitmapFactory.Options();
                        options.inSampleSize = 1;
                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("expo_poster").getData(),
                                0,
                                obj.getParseFile("expo_poster").getData().length,
                                options);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    conValue.setImg(bitmap);


                    ExpoItems.add(conValue);

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