package com.expou.tab.t4content;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expou.R;

/**
 * Created by Kim on 2015-08-16.
 */
public class ContentDetailActivity extends Activity {
    ImageView imgBack;
    static public TextView content_detail_title,
            content_detail_name,
            content_detail_clicknum,
            content_detail_intro;
    public static ImageView content_detail_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        content_detail_title = (TextView)findViewById(R.id.content_detail_title);
        content_detail_name = (TextView)findViewById(R.id.content_detail_name);
        content_detail_clicknum = (TextView)findViewById(R.id.content_detail_clicknum);
        content_detail_intro = (TextView)findViewById(R.id.content_detail_intro);

        content_detail_img = (ImageView)findViewById(R.id.content_detail_img);

        imgBack = (ImageView)findViewById(R.id.content_detail_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }



}