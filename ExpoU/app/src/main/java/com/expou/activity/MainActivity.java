package com.expou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.expou.R;
import com.expou.menu.search.SearchActivity;
import com.expou.util.ExpoFragmentPagerAdapter;
import com.expou.menu.MenuAdapter;
import com.expou.exception.ServiceException;
import com.expou.menu.MenuListItem;
import com.expou.menu.mybooth.MyBoothActivity;
import com.expou.menu.myinfo.MyInfoActivity;
import com.expou.menu.notice.NoticeActivity;
import com.expou.menu.setting.SettingActivity;
import com.expou.serverconnect.dao.ServiceDAOImpl;
import com.expou.util.BackPressCloseHandler;
import com.expou.util.BaseActivity;
import com.expou.util.DataUtil;
import com.expou.util.SetFont;
import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public ViewPager pager;
    PagerSlidingTabStrip tabsStrip;
    ImageView drawerImageView,drawerclose, drawerSearch;
    MenuAdapter adapters;
    ArrayList<MenuListItem> dataList;
    ListView menu_list;
    Toolbar mToolBar;
    TextView tv_drawer_name;
    CircularImageView circulr_img;

    DrawerLayout mDrawerLayout;
    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Activity 한번에 제거하기위해 리스트에 추가
        BaseActivity.actList.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_main);

        //취소버튼 눌렀을 때 핸들러
        backPressCloseHandler = new BackPressCloseHandler(this);

        //드로워 레이아웃 사진
        circulr_img = (CircularImageView) findViewById(R.id.circulr_img);


        //이름설정정
       tv_drawer_name = (TextView)findViewById(R.id.tv_drawer_name);
        tv_drawer_name.setText(DataUtil.getAppPreferences(getApplicationContext(),"user_email"));


        drawerSearch = (ImageView) findViewById(R.id.drawer_imageview_done);
        drawerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,R.anim.slide_out_left);
            }
        });


        //TextView 폰트 지정
        SetFont.setGlobalFont(this, getWindow().getDecorView());

        //Toolbar 생성
        initToolbar();

        //아이템가져오는거 테스트
        parseConnect();

        //탭 생성
        initTab();

        //Drawer 생성
        initNaviDrawer();

        //menu list
        initMenuList();


    }

    //취소버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        //drawer가 열려있으면
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        //핸들러 작동
        backPressCloseHandler.onBackPressed();
//        super.onBackPressed();
    }

    private void initToolbar(){
        //액션바 객체 생성
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //액션바 설정
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //액션바 숨김
        actionBar.hide();

        //툴바 설정
        mToolBar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolBar.setContentInsetsAbsolute(0, 0);
    }

    private void parseConnect(){
        try {
            new ServiceDAOImpl().getContent();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void initTab(){
        pager = (ViewPager) this.findViewById(R.id.pager);
        pager.setAdapter(new ExpoFragmentPagerAdapter(getSupportFragmentManager()));

        /* 큰아이콘 탭
        */
        tabsStrip = (PagerSlidingTabStrip)this.findViewById(R.id.tabsStrip);
        tabsStrip.setViewPager(pager);

        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
    }

    private void initNaviDrawer(){

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //메뉴버튼
        drawerImageView = (ImageView) findViewById(R.id.drawer_imageview);

        drawerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //닫기버튼
        drawerclose = (ImageView) findViewById(R.id.menu_close);
        drawerclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });


    }

    private void initMenuList(){
        addItemsMenu();

        menu_list = (ListView)findViewById(R.id.menu_list);

        //Adapter 생성
        adapters = new MenuAdapter(this, R.layout.row_menu, dataList);

        //Adapter와 GirdView를 연결
        menu_list.setAdapter(adapters);


        adapters.notifyDataSetChanged();

        //이미지 터치 못하게
        ImageView imageView99 = (ImageView)findViewById(R.id.imageView99);
        imageView99.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        //menu button 눌렀을 때
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent myInfo = new Intent(getApplicationContext(),MyInfoActivity.class);
                        startActivity(myInfo);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 1:
                        Intent myBooth = new Intent(getApplicationContext(),MyBoothActivity.class);
                        startActivity(myBooth);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 2:
                        Intent notice = new Intent(getApplicationContext(),NoticeActivity.class);
                        startActivity(notice);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case 3:
                        Intent setting = new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(setting);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                }
//                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void addItemsMenu(){
        dataList = new ArrayList<>();

        dataList.add(new MenuListItem(R.drawable.menu_my,"내 정보"));
        dataList.add(new MenuListItem(R.drawable.menu_hart,"내 리스트"));
        dataList.add(new MenuListItem(R.drawable.menu_alarm,"알림"));
        dataList.add(new MenuListItem(R.drawable.menu_setting, "설정"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

}
