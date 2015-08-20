package com.expou.util;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.expou.R;
import com.expou.tab.t1home.HomeFragment;
import com.expou.tab.t2expo.ExpoFragment;
import com.expou.tab.t3booth.BoothFragment;
import com.expou.tab.t4content.ContentFragment;
import com.expou.tab.t5market.MarketFragment;

/**
 * Created by Kim on 2015-07-21.
 */
public class ExpoFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private int tabIcons[] = {R.drawable.home_tab, R.drawable.expo_tab, R.drawable.booth_tab,R.drawable.content_tab,R.drawable.market_tab};
    private int switch_Icons[] = {R.drawable.selector_tab_home, R.drawable.selector_tab_expo, R.drawable.selector_tab_booth,R.drawable.selector_tab_content,R.drawable.selector_tab_market};
    Fragment frag =null;
    private Resources res;
    final int PAGE_COUNT = 5;


    public ExpoFragmentPagerAdapter(FragmentManager fm){
        super(fm);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return ExpoFragment.newInstance();
            case 2:
                return BoothFragment.newInstance();
            case 3:
                return ContentFragment.newInstance();
            case 4:
                return MarketFragment.newInstance();

        }
        return null;
    }


    @Override
    public int getPageIconResId(int position) {
//        return tabIcons[position];

        return switch_Icons[position];
    }
}