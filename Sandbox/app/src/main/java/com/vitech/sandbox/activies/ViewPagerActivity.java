package com.vitech.sandbox.activies;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vitech.sandbox.R;
import com.vitech.sandbox.views.CustomViewPager;
import com.vitech.sandbox.views.ScreenSlidePageFragment;

public class ViewPagerActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 3;

    private CustomViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        pager = (CustomViewPager) findViewById(R.id.viewpager);
        pager.setSwipingEnabled(false);  // start first page (not selected)
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Log.d("ViewPagerActivity", "onPageSelected: " + position);
                pager.setSwipingEnabled(position!=0);  // disable swiping on first page
                super.onPageSelected(position);
            }
        } );
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        // TODO: replace above line to the following line
//                        invalidateOptionsMenu();
                    }
                }
        );
        TabLayout tabLayout = (TabLayout) findViewById(R.id.viewpager_tab);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            // first page
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
