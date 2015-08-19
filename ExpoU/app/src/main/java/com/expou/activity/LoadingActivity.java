package com.expou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.expou.R;
import com.expou.util.DataUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kim on 2015-08-08.
 */
public class LoadingActivity  extends Activity {
    private long splashDelay = 1000;



    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //Logo를 보여주는 쓰레드 (타이머)
        //2초 후에 run메소드가 실행됨
        TimerTask task = new TimerTask(){

            @Override
            public void run() {




                //register값이 저장되있는 회원이 있나 파일에서가져옴
                String register = DataUtil.getAppPreferences(getApplicationContext(), "user_email");

                //회원이 없을때
                if(register.equals("")){
                    //로그인 activity 이동
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                //회원가입이 되어있을 때
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

                finish();


				/*
				while(true){
					//여기다 서버에 연결하는 함수(refresh)를 넣으면
					try {
						//1초마다 refresh 되는거지
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
				*/
            }

        };

        //Timer 객체 생성
        Timer timer = new Timer();
        //몇초 후에 실행해라(splashDelay 초 후에 task를 실행해라)
        timer.schedule(task, splashDelay);


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
}
