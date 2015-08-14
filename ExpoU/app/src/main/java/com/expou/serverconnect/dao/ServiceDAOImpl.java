package com.expou.serverconnect.dao;

import android.support.v7.appcompat.BuildConfig;

import com.expou.exception.ServiceException;
import com.expou.item.ContentItem;
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
    static ArrayList<ContentItem> items = new ArrayList<>();
    @Override
    public ArrayList<ContentItem> getContent() throws ServiceException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Contents");
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
                items.clear();

                for (ParseObject obj : list) {
                    items.add(new ContentItem(obj.get("Contents_name").toString() , obj.get("Contents_text").toString()));
                }
            }
        });
        return items;
    }
}