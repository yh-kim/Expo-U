package com.expou.tab.t3booth;

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
public class BoothDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    ImageView imgBack;
    String youtubeCode;
    public static ImageView booth_detail_img;
    static public TextView booth_detail_title,
            booth_detail_name,
            booth_detail_clicknum,
            booth_detail_intro;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_detail);

        Intent intent = getIntent();
        youtubeCode = intent.getExtras().getString("youtubeCode");

        booth_detail_title = (TextView)findViewById(R.id.booth_detail_title);
        booth_detail_name = (TextView)findViewById(R.id.booth_detail_name);
        booth_detail_clicknum = (TextView)findViewById(R.id.booth_detail_clicknum);
        booth_detail_intro = (TextView)findViewById(R.id.booth_detail_intro);

        booth_detail_img = (ImageView)findViewById(R.id.booth_detail_img);

        youTubeView = (YouTubePlayerView) findViewById(R.id.booth_youtube);

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

        imgBack = (ImageView)findViewById(R.id.booth_detail_back);
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

            // Hiding player controls
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
        return (YouTubePlayerView) findViewById(R.id.booth_youtube);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
