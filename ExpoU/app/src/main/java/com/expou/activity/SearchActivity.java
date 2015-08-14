package com.expou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.expou.R;
import com.expou.util.SetFont;

/**
 * Created by Kim on 2015-08-09.
 */
public class SearchActivity extends Activity {
    ImageView imgBack;
    EditText e_txt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imgBack = (ImageView)findViewById(R.id.search_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        e_txt_search = (EditText)findViewById(R.id.e_txt_search);

        e_txt_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getApplicationContext(), e_txt_search.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    e_txt_search.setText("");
                    return true;
                }
                return false;
            }
            });


    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}