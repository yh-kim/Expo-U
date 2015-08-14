package com.expou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.expou.BuildConfig;
import com.expou.R;
import com.expou.util.SetFont;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Kim on 2015-08-08.
 */
public class SignUpActivity extends Activity {

    ImageView btn_sighup;
    EditText e_signup_email,e_signup_passwd,e_signup_passwd_confirm;
    String email,passwd,passwd_confirm;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        e_signup_email = (EditText)findViewById(R.id.e_signup_email);
        e_signup_passwd = (EditText)findViewById(R.id.e_signup_passwd);
        e_signup_passwd_confirm = (EditText)findViewById(R.id.e_signup_passwd_confirm);




        btn_sighup = (ImageView)findViewById(R.id.btn_sighup);
        btn_sighup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = e_signup_email.getText().toString().trim();
                passwd = e_signup_passwd.getText().toString().trim();
                passwd_confirm = e_signup_passwd_confirm.getText().toString().trim();

                if(email.length() == 0){
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwd.length() == 0){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwd.length() <6){
                    Toast.makeText(getApplicationContext(),"비밀번호가 너무 짧습니다",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwd.length() >12){
                    Toast.makeText(getApplicationContext(),"비밀번호가 너무 깁니다",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwd_confirm.length() == 0){
                    Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(!passwd.equals(passwd_confirm)){
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                onSignUp();
            }
        });


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



    protected void onSignUp() {
        final ParseUser user = new ParseUser();

        user.setUsername(email);
        user.setPassword(passwd);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //오류났을때
                    if (BuildConfig.DEBUG) e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    e_signup_email.setText("");
                    e_signup_passwd.setText("");
                    e_signup_passwd_confirm.setText("");
                    return;
                }

                Toast.makeText(getApplicationContext(), "Registered! - " + user.getUsername(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


}
