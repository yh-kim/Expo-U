package com.expou.serverconnect.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;

import com.expou.exception.ServiceException;
import com.expou.tab.t2expo.ExpoDetailActivity;
import com.expou.tab.t2expo.ExpoItem;
import com.expou.tab.t3booth.BoothDetailActivity;
import com.expou.tab.t3booth.BoothFragment;
import com.expou.tab.t3booth.BoothItem;
import com.expou.tab.t4content.ContentDetailActivity;
import com.expou.tab.t4content.ContentFragment;
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


      static public ArrayList<ContentItem> contentItems = ContentFragment.arr_list;
      static public ArrayList<BoothItem> boothItems = BoothFragment.arr_list;
      static ArrayList<MarketItem> marketItems = new ArrayList<>();
      static public ArrayList<ExpoItem> expoItems = new ArrayList<>();
    static public int count1 = 0;
    static public int count2 = 0;

    @Override
    public ArrayList<ContentItem> getContent(int offset) throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("c_Contents");
        query.setLimit(7);
        query.setSkip(offset);
        query.orderByDescending("contents_name");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                count2 = 0;

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
                        options.inSampleSize = 1;
                        if(obj.getParseFile("contents_img").getData().length > 106700){
                            options.inSampleSize = 2;
                        }
                        if(obj.getParseFile("contents_img").getData().length > 500000){
                            options.inSampleSize = 4;
                        }
                        if(obj.getParseFile("contents_img").getData().length > 1000000){
                            options.inSampleSize = 8;
                        }
                            bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("contents_img").getData(),
                                    0,
                                    obj.getParseFile("contents_img").getData().length,
                                    options);

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    conValue.setImg(bitmap);
                    count2++;

                    contentItems.add(conValue);
                    ContentFragment.adapter.notifyDataSetChanged();
                }
            }
        });
        return contentItems;
    }

    @Override
    public ArrayList<BoothItem> getBooth(int offset) throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("b_Booth");
        query.setLimit(6);
        query.setSkip(offset);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                count1 = 0;

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

                    boothValue.setYoutubeCode(obj.get("booth_youtube").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 1;

                        if(obj.getParseFile("booth_logo").getData().length > 106700){
                            options.inSampleSize = 2;
                        }

                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("booth_logo").getData(),
                                0,
                                obj.getParseFile("booth_logo").getData().length,
                                options);

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    boothValue.setImg(bitmap);

                    count1++;


                    boothItems.add(boothValue);
                    BoothFragment.adapter.notifyDataSetChanged();
                }
            }
        });

        return boothItems;
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


                    conValue.setYoutubeCode(obj.get("expo_youtube").toString());


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


                    expoItems.add(conValue);

                }
            }
        });

        return expoItems;
    }

    @Override
    public ArrayList<MarketItem> getMarket() throws ServiceException {
        return null;
    }

    @Override
    public void getDetailExpo(String objectId) throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("a_Expo");
        query.whereEqualTo("objectId", objectId);
        query.setLimit(1);
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
                    ExpoDetailActivity.expo_detail_title.setText(obj.get("expo_name").toString());
                    ExpoDetailActivity.expo_detail_name.setText(obj.get("expo_name").toString());
                    ExpoDetailActivity.expo_detail_clicknum.setText(obj.get("expo_clickNum").toString());
                    ExpoDetailActivity.expo_detail_type.setText(obj.get("expo_type").toString());
                    ExpoDetailActivity.expo_detail_startdate.setText(obj.get("expo_startDate").toString());
                    ExpoDetailActivity.expo_detail_enddate.setText(obj.get("expo_endDate").toString());
                    ExpoDetailActivity.expo_detail_opentime.setText(obj.get("expo_openTime").toString());
                    ExpoDetailActivity.expo_detail_closetime.setText(obj.get("expo_closeTime").toString());
                    ExpoDetailActivity.expo_detail_fee.setText(obj.get("expo_fee").toString());
                    ExpoDetailActivity.expo_detail_locationword.setText(obj.get("expo_locationWord").toString());
                    ExpoDetailActivity.expo_detail_website.setText(obj.get("expo_website").toString());
                    ExpoDetailActivity.expo_detail_phone.setText(obj.get("expo_phone").toString());
                    ExpoDetailActivity.expo_detail_fax.setText(obj.get("expo_fax").toString());
                    ExpoDetailActivity.expo_detail_email.setText(obj.get("expo_email").toString());
                    ExpoDetailActivity.expo_detail_host.setText(obj.get("expo_host").toString());
                    ExpoDetailActivity.expo_detail_supervisor.setText(obj.get("expo_supervisor").toString());
                    ExpoDetailActivity.expo_detail_sponsor.setText(obj.get("expo_sponsor").toString());
                    ExpoDetailActivity.expo_detail_intro.setText(obj.get("expo_intro").toString());

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

                    ExpoDetailActivity.expo_detail_img.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    public void getDetailBooth(String objectId) throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("b_Booth");
        query.whereEqualTo("objectId", objectId);
        query.setLimit(1);
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
                    BoothDetailActivity.booth_detail_title.setText(obj.get("booth_name").toString());
                    BoothDetailActivity.booth_detail_name.setText(obj.get("booth_name").toString());
                    BoothDetailActivity.booth_detail_clicknum.setText(obj.get("booth_clickNum").toString());
                    BoothDetailActivity.booth_detail_intro.setText(obj.get("booth_intro").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options  = new BitmapFactory.Options();
                        options.inSampleSize = 1;

                        if(obj.getParseFile("booth_logo").getData().length > 106700){
                            options.inSampleSize = 2;
                        }

                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("booth_logo").getData(),
                                0,
                                obj.getParseFile("booth_logo").getData().length,
                                options);

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    BoothDetailActivity.booth_detail_img.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    public void getDetailContent(String objectId) throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("c_Contents");
        query.whereEqualTo("objectId", objectId);
        query.setLimit(1);
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
                    ContentDetailActivity.content_detail_title.setText(obj.get("contents_name").toString());
                    ContentDetailActivity.content_detail_name.setText(obj.get("contents_name").toString());
                    ContentDetailActivity.content_detail_clicknum.setText(obj.get("contents_clickNum").toString());
                    ContentDetailActivity.content_detail_intro.setText(obj.get("contents_detail").toString());

                    //이미지 받아오는 부분
                    Bitmap bitmap = null;
                    try {
                        BitmapFactory.Options options  = new BitmapFactory.Options();
                        options.inSampleSize = 1;
                        Log.e("vvvv : ",obj.getParseFile("contents_img").getData().length+".");
                        if(obj.getParseFile("contents_img").getData().length > 100000){
                            options.inSampleSize = 2;
                        }
//                        if(obj.getParseFile("contents_img").getData().length > 500000){
//                            options.inSampleSize = 4;
//                        }
//                        if(obj.getParseFile("contents_img").getData().length > 1000000){
//                            options.inSampleSize = 8;
//                        }
                        bitmap = BitmapFactory.decodeByteArray(obj.getParseFile("contents_img").getData(),
                                0,
                                obj.getParseFile("contents_img").getData().length,
                                options);

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    ContentDetailActivity.content_detail_img.setImageBitmap(bitmap);
                }
            }
        });
    }

}