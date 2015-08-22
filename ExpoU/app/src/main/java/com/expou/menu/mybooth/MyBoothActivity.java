package com.expou.menu.mybooth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.expou.R;
import com.expou.exception.ServiceException;
import com.expou.serverconnect.dao.ServiceDAOImpl;
import com.expou.tab.t4content.ContentItem;
import com.expou.util.SetFont;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-08-08.
 */
public class MyBoothActivity extends Activity {

    ImageView imgBack;
    ArrayList<ContentItem> bookCon = new ArrayList<>();
    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooth);

        imgBack = (ImageView)findViewById(R.id.mybooth_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        try {
            bookCon = new ServiceDAOImpl().getBookMarkInCon();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    //onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
