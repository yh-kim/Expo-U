package com.expou.tab.t2expo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expou.R;
import com.expou.util.Config;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Kim on 2015-08-16.
 */
public class ExpoDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    ImageView imgBack;
    public static ImageView expo_detail_img;

    String youtubeCode;

    static public TextView expo_detail_title,
            expo_detail_name,
            expo_detail_clicknum,
            expo_detail_type,
            expo_detail_startdate,expo_detail_enddate,
            expo_detail_opentime,expo_detail_closetime,
            expo_detail_fee,
            expo_detail_locationword,
            expo_detail_website,
            expo_detail_phone,
            expo_detail_fax,
            expo_detail_email,
            expo_detail_host,expo_detail_supervisor,expo_detail_sponsor,
            expo_detail_intro;



    // YouTube player view
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo_detail);

        Intent intent = getIntent();
        youtubeCode = intent.getExtras().getString("youtubeCode");

        expo_detail_title = (TextView)findViewById(R.id.expo_detail_title);
        expo_detail_name = (TextView)findViewById(R.id.expo_detail_name);
        expo_detail_clicknum = (TextView)findViewById(R.id.expo_detail_clicknum);
        expo_detail_type = (TextView)findViewById(R.id.expo_detail_type);
        expo_detail_startdate = (TextView)findViewById(R.id.expo_detail_startdate);
        expo_detail_enddate = (TextView)findViewById(R.id.expo_detail_enddate);
        expo_detail_opentime = (TextView)findViewById(R.id.expo_detail_opentime);
        expo_detail_closetime = (TextView)findViewById(R.id.expo_detail_closetime);
        expo_detail_fee = (TextView)findViewById(R.id.expo_detail_fee);
        expo_detail_locationword = (TextView)findViewById(R.id.expo_detail_locationword);
        expo_detail_website = (TextView)findViewById(R.id.expo_detail_website);
        expo_detail_phone = (TextView)findViewById(R.id.expo_detail_phone);
        expo_detail_fax = (TextView)findViewById(R.id.expo_detail_fax);
        expo_detail_email = (TextView)findViewById(R.id.expo_detail_email);
        expo_detail_host = (TextView)findViewById(R.id.expo_detail_host);
        expo_detail_supervisor = (TextView)findViewById(R.id.expo_detail_supervisor);
        expo_detail_sponsor = (TextView)findViewById(R.id.expo_detail_sponsor);
        expo_detail_intro = (TextView)findViewById(R.id.expo_detail_intro);

        expo_detail_img = (ImageView)findViewById(R.id.expo_detail_img);



        youTubeView = (YouTubePlayerView) findViewById(R.id.expo_youtube);

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

        imgBack = (ImageView)findViewById(R.id.expo_detail_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically

//            //자동재생
//            player.loadVideo(Config.youtubeCode);

            //선택재생
            player.cueVideo(youtubeCode);

            //컨트롤 숨기기
//            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.expo_youtube);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
