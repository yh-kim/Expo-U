package com.expou;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.expou.fragment.CtBooth;
import com.expou.fragment.CtContent;
import com.expou.fragment.CtExpo;
import com.expou.fragment.CtHome;
import com.expou.fragment.CtMarket;

/**
 * Created by Kim on 2015-07-21.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private int tabIcons[] = {R.drawable.home_tab, R.drawable.expo_tab, R.drawable.booth_tab,R.drawable.content_tab,R.drawable.market_tab};
    private int switch_Icons[] = {R.drawable.selector_tab_home, R.drawable.selector_tab_expo, R.drawable.selector_tab_booth,R.drawable.selector_tab_content,R.drawable.selector_tab_market};
    Fragment frag =null;
    private Resources res;
    final int PAGE_COUNT = 5;

    public SampleFragmentPagerAdapter(FragmentManager fm){
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
                return CtHome.newInstance();
            case 1:
                return CtExpo.newInstance();
            case 2:
                return CtBooth.newInstance();
            case 3:
                return CtContent.newInstance();
            case 4:
                return CtMarket.newInstance();

        }
        return null;
    }


    @Override
    public int getPageIconResId(int position) {
//        return tabIcons[position];

        return switch_Icons[position];
    }
}