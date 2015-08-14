package com.expou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expou.BuildConfig;
import com.expou.R;
import com.expou.util.DataUtil;
import com.expou.util.SetFont;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Kim on 2015-08-09.
 */
public class LoginActivity   extends Activity {
    ImageView btnLogin;
    TextView tvSignup;
    EditText e_log_email;
    EditText e_log_passwd;
    String user_email;
    String user_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        btnLogin = (ImageView)findViewById(R.id.btn_login);
        tvSignup = (TextView)findViewById(R.id.tv_signUp);



        e_log_email = (EditText)findViewById(R.id.e_log_email);
        e_log_passwd = (EditText)findViewById(R.id.e_log_passwd);





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = e_log_email.getText().toString().trim();
                user_passwd = e_log_passwd.getText().toString().trim();

                if(user_email.length() == 0){
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user_passwd.length() == 0){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                //서버로 데이터 보냄
                onLogin();

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signup);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

    }



    protected void onLogin() {
        ParseUser.logInInBackground(user_email, user_passwd, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e != null) {
                    if (BuildConfig.DEBUG) e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "잘못 입력하셨습니다", Toast.LENGTH_LONG).show();
                    return;
                }

                //유저가 없을때
                if (parseUser == null) {
                    Toast.makeText(getApplicationContext(), "잘못된 이메일을 입력하셨습니다", Toast.LENGTH_LONG).show();
                    return;
                }


                DataUtil.setAppPreferences(LoginActivity.this,"user_email",user_email);
                Intent login = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(login);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }
}
