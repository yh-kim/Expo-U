package com.expou.tab.t1home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.expou.R;
import com.expou.activity.MainActivity;
import com.expou.exception.ServiceException;
import com.expou.serverconnect.dao.ServiceDAOImpl;
import com.expou.util.SetFont;

/**
 * Created by Kim on 2015-07-02.
 */
public class HomeFragment extends Fragment {
    View rootView;

    TextView txt_plus_expo;
    TextView txt_plus_booth;
    TextView txt_plus_contents;


    static public TextView home_expo1_hitnum,
            home_expo1_lovenum,
            home_expo1_name;
    static public TextView home_expo2_hitnum,
            home_expo2_lovenum,
            home_expo2_name;
    static public TextView home_expo3_hitnum,
            home_expo3_lovenum,
            home_expo3_name;
    static public TextView home_expo4_hitnum,
            home_expo4_lovenum,
            home_expo4_name;
    static public ImageView home_expo1_img,
            home_expo2_img,
            home_expo3_img,
            home_expo4_img;

    static public TextView home_booth1_hitnum,
            home_booth1_lovenum;
    static public TextView home_booth2_hitnum,
            home_booth2_lovenum;
    static public TextView home_booth3_hitnum,
            home_booth3_lovenum;
    static public TextView home_booth4_hitnum,
            home_booth4_lovenum;
    static public TextView home_booth5_hitnum,
            home_booth5_lovenum;
    static public TextView home_booth6_hitnum,
            home_booth6_lovenum;

    static public ImageView home_booth1_img,
            home_booth2_img,
            home_booth3_img,
            home_booth4_img,
            home_booth5_img,
            home_booth6_img;

    static public TextView home_content1_exponame,
            home_content1_hitnum,
            home_content1_lovenum,
            home_content1_name,
            home_content1_intro;
    static public TextView home_content2_exponame,
            home_content2_hitnum,
            home_content2_lovenum,
            home_content2_name,
            home_content2_intro;
    static public TextView home_content3_exponame,
            home_content3_hitnum,
            home_content3_lovenum,
            home_content3_name,
            home_content3_intro;
    static public TextView home_content4_exponame,
            home_content4_hitnum,
            home_content4_lovenum,
            home_content4_name,
            home_content4_intro;

    static public ImageView home_content1_img,
            home_content2_img,
            home_content3_img,
            home_content4_img;

    public static Fragment newInstance() {
        Fragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home,container,false);

        //frgment 폰트 설정
        SetFont.setGlobalFont(rootView.getContext(), rootView);

        findName();

        init();

        try {
            new ServiceDAOImpl().getHome();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        txt_plus_expo = (TextView)rootView.findViewById(R.id.txt_plus_expo);
        txt_plus_expo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.pager.setCurrentItem(1);
            }
        });

        txt_plus_booth = (TextView)rootView.findViewById(R.id.txt_plus_booth);
        txt_plus_booth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.pager.setCurrentItem(2);
            }
        });

        txt_plus_contents = (TextView)rootView.findViewById(R.id.txt_plus_contents);
        txt_plus_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.pager.setCurrentItem(3);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void findName(){
        home_expo1_hitnum = (TextView)rootView.findViewById(R.id.home_expo1_hitnum);
        home_expo1_lovenum = (TextView)rootView.findViewById(R.id.home_expo1_lovenum);
        home_expo1_name = (TextView)rootView.findViewById(R.id.home_expo1_name);
        home_expo2_hitnum = (TextView)rootView.findViewById(R.id.home_expo2_hitnum);
        home_expo2_lovenum = (TextView)rootView.findViewById(R.id.home_expo2_lovenum);
        home_expo2_name = (TextView)rootView.findViewById(R.id.home_expo2_name);
        home_expo3_hitnum = (TextView)rootView.findViewById(R.id.home_expo3_hitnum);
        home_expo3_lovenum = (TextView)rootView.findViewById(R.id.home_expo3_lovenum);
        home_expo3_name = (TextView)rootView.findViewById(R.id.home_expo3_name);
        home_expo4_hitnum = (TextView)rootView.findViewById(R.id.home_expo4_hitnum);
        home_expo4_lovenum = (TextView)rootView.findViewById(R.id.home_expo4_lovenum);
        home_expo4_name = (TextView)rootView.findViewById(R.id.home_expo4_name);

        home_expo1_img = (ImageView)rootView.findViewById(R.id.home_expo1_img);
        home_expo2_img = (ImageView)rootView.findViewById(R.id.home_expo2_img);
        home_expo3_img = (ImageView)rootView.findViewById(R.id.home_expo3_img);
        home_expo4_img = (ImageView)rootView.findViewById(R.id.home_expo4_img);

        home_booth1_hitnum = (TextView)rootView.findViewById(R.id.home_booth1_hitnum);
        home_booth1_lovenum = (TextView)rootView.findViewById(R.id.home_booth1_lovenum);
        home_booth2_hitnum = (TextView)rootView.findViewById(R.id.home_booth2_hitnum);
        home_booth2_lovenum = (TextView)rootView.findViewById(R.id.home_booth2_lovenum);
        home_booth3_hitnum = (TextView)rootView.findViewById(R.id.home_booth3_hitnum);
        home_booth3_lovenum = (TextView)rootView.findViewById(R.id.home_booth3_lovenum);
        home_booth4_hitnum = (TextView)rootView.findViewById(R.id.home_booth4_hitnum);
        home_booth4_lovenum = (TextView)rootView.findViewById(R.id.home_booth4_lovenum);
        home_booth5_hitnum = (TextView)rootView.findViewById(R.id.home_booth5_hitnum);
        home_booth5_lovenum = (TextView)rootView.findViewById(R.id.home_booth5_lovenum);
        home_booth6_hitnum = (TextView)rootView.findViewById(R.id.home_booth6_hitnum);
        home_booth6_lovenum = (TextView)rootView.findViewById(R.id.home_booth6_lovenum);

        home_booth1_img = (ImageView)rootView.findViewById(R.id.home_booth1_img);
        home_booth2_img = (ImageView)rootView.findViewById(R.id.home_booth2_img);
        home_booth3_img = (ImageView)rootView.findViewById(R.id.home_booth3_img);
        home_booth4_img = (ImageView)rootView.findViewById(R.id.home_booth4_img);
        home_booth5_img = (ImageView)rootView.findViewById(R.id.home_booth5_img);
        home_booth6_img = (ImageView)rootView.findViewById(R.id.home_booth6_img);

        home_content1_exponame = (TextView)rootView.findViewById(R.id.home_content1_exponame);
        home_content1_hitnum = (TextView)rootView.findViewById(R.id.home_content1_hitnum);
        home_content1_lovenum = (TextView)rootView.findViewById(R.id.home_content1_lovenum);
        home_content1_name = (TextView)rootView.findViewById(R.id.home_content1_name);
        home_content1_intro = (TextView)rootView.findViewById(R.id.home_content1_intro);
        home_content2_exponame = (TextView)rootView.findViewById(R.id.home_content2_exponame);
        home_content2_hitnum = (TextView)rootView.findViewById(R.id.home_content2_hitnum);
        home_content2_lovenum = (TextView)rootView.findViewById(R.id.home_content2_lovenum);
        home_content2_name = (TextView)rootView.findViewById(R.id.home_content2_name);
        home_content2_intro = (TextView)rootView.findViewById(R.id.home_content2_intro);
        home_content3_exponame = (TextView)rootView.findViewById(R.id.home_content3_exponame);
        home_content3_hitnum = (TextView)rootView.findViewById(R.id.home_content3_hitnum);
        home_content3_lovenum = (TextView)rootView.findViewById(R.id.home_content3_lovenum);
        home_content3_name = (TextView)rootView.findViewById(R.id.home_content3_name);
        home_content3_intro = (TextView)rootView.findViewById(R.id.home_content3_intro);
        home_content4_exponame = (TextView)rootView.findViewById(R.id.home_content4_exponame);
        home_content4_hitnum = (TextView)rootView.findViewById(R.id.home_content4_hitnum);
        home_content4_lovenum = (TextView)rootView.findViewById(R.id.home_content4_lovenum);
        home_content4_name = (TextView)rootView.findViewById(R.id.home_content4_name);
        home_content4_intro = (TextView)rootView.findViewById(R.id.home_content4_intro);


        home_content1_img = (ImageView)rootView.findViewById(R.id.home_content1_img);
        home_content2_img = (ImageView)rootView.findViewById(R.id.home_content2_img);
        home_content3_img = (ImageView)rootView.findViewById(R.id.home_content3_img);
        home_content4_img = (ImageView)rootView.findViewById(R.id.home_content4_img);

    }

    public void init(){
        home_expo1_img.setImageResource(0);
        home_expo2_img.setImageResource(0);
        home_expo3_img.setImageResource(0);
        home_expo4_img.setImageResource(0);
        home_booth1_img.setImageResource(0);
        home_booth2_img.setImageResource(0);
        home_booth3_img.setImageResource(0);
        home_booth4_img.setImageResource(0);
        home_booth5_img.setImageResource(0);
        home_booth6_img.setImageResource(0);
        home_content1_img.setImageResource(0);
        home_content2_img.setImageResource(0);
        home_content3_img.setImageResource(0);
        home_content4_img.setImageResource(0);
    }

}
