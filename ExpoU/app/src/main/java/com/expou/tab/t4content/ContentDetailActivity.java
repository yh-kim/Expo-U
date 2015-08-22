package com.expou.tab.t4content;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.expou.R;
import com.expou.exception.ServiceException;
import com.expou.serverconnect.dao.ServiceDAOImpl;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;

/**
 * Created by Kim on 2015-08-16.
 */
public class ContentDetailActivity extends Activity {
    ImageView imgBack;
    static public TextView content_detail_title;
    static public TextView content_detail_name;
    static public TextView content_detail_clicknum;
static public int plusheart;
    static public TextView content_detail_heartnum;
    static public TextView content_detail_intro;
    public static ImageView content_detail_img;
 static int heart= 0;

    public static CheckBox love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        content_detail_title = (TextView) findViewById(R.id.content_detail_title);
        content_detail_name = (TextView) findViewById(R.id.content_detail_name);
        content_detail_clicknum = (TextView) findViewById(R.id.content_detail_clicknum);
        content_detail_intro = (TextView) findViewById(R.id.content_detail_intro);
        content_detail_img = (ImageView) findViewById(R.id.content_detail_img);
        content_detail_heartnum = (TextView) findViewById(R.id.content_detail_love_num);


        imgBack = (ImageView) findViewById(R.id.content_detail_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        love = (CheckBox) findViewById(R.id.content_detail_love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("set heart before");
                    new ServiceDAOImpl().setHeart();
                    new ServiceDAOImpl().setBookMarkInCon();

                    content_detail_heartnum = (TextView) findViewById(R.id.content_detail_love_num);
                    System.out.println("set heart after");
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}