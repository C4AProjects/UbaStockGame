package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.ubagroup.capital.app.adapters.ScreenPagerAdapter;
import com.ubagroup.capital.app.base.Act;

public class ProfileActivity extends Act {
	
	public static final int FRAGMENT_COUNT = 5;
	
	private ViewPager viewPager;
	private ScreenPagerAdapter screenPagerAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		viewPager = (ViewPager)getView(R.id.viewPager);
		screenPagerAdapter = new ScreenPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(screenPagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		@Override public void onPageScrollStateChanged(int arg0) {}
		@Override public void onPageScrolled(int arg0, float arg1, int arg2) {}
		@Override
		public void onPageSelected(int position) {
    		setActionBarTitle(position);
		}
    };
    
    @Override
    public void onBackPressed() {
    	int currentItem = viewPager.getCurrentItem();
    	if (currentItem == 0) {
    		super.onBackPressed();
    	} else {
    		viewPager.setCurrentItem(currentItem - 1);
    	}
    }
    
    private void setActionBarTitle(int position) {
    	String title;
    	switch (position) {
    	case 0 : title = "Profile"; break;
    	case 1 : title = "Trivia"; break;
    	case 2 : title = "Trade"; break;
    	case 3 : title = "News"; break;
    	default : title = "History";
    	}
    	getActionBar().setTitle(title);
    }
}
