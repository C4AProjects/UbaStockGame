package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubagroup.capital.app.adapters.ScreenPagerAdapter;
import com.ubagroup.capital.app.base.Act;

public class ProfileActivity extends Act {
	
	public static final int FRAGMENT_COUNT = 5;
	
	private ViewPager viewPager;
	private ScreenPagerAdapter screenPagerAdapter;
	
	private LinearLayout tabContainer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		viewPager = (ViewPager)getView(R.id.viewPager);
		screenPagerAdapter = new ScreenPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(screenPagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
		
		tabContainer = (LinearLayout)getView(R.id.tabContainer);
		initializeTabContainer();
		activateTab(0);
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		@Override public void onPageScrollStateChanged(int arg0) {}
		@Override public void onPageScrolled(int arg0, float arg1, int arg2) {}
		@Override
		public void onPageSelected(int position) {
    		setActionBarTitle(position);
    		activateTab(position);
		}
    };
    
    private void initializeTabContainer() {
    	for (int i = 0; i < tabContainer.getChildCount(); i++) {
    		LinearLayout tabItemContainer = (LinearLayout)tabContainer.getChildAt(i);
    		tabItemContainer.setOnClickListener(tabSelectionListener);
    	}
    }
    
    private View.OnClickListener tabSelectionListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int position = getTabPosition((LinearLayout)v);
			viewPager.setCurrentItem(position);
			activateTab(position);
		}
	};
	
	private int getTabPosition(LinearLayout tabItemContainer) {
		String title = ((TextView)tabItemContainer.getChildAt(1)).getText().toString().trim();
		if (title.equals("Portfolio")) return 0;
		else if (title.equals("Trivia")) return 1;
		else if (title.equals("Trade")) return 2;
		else if (title.equals("News")) return 3;
		else return 4;
	}
	
	private void activateTab(int position) {
    	for (int i = 0; i < tabContainer.getChildCount(); i++) {
    		LinearLayout tabItemContainer = (LinearLayout)tabContainer.getChildAt(i);
    		ImageView image = (ImageView)tabItemContainer.getChildAt(0);
    		image.setImageDrawable(getResources().getDrawable(getDrawableFromPosition(i, i == position)));
    		TextView title = (TextView)tabItemContainer.getChildAt(1);
    		title.setTextColor(getResources().getColor(i == position ? R.color.white : R.color.red));
    	}
	}
	
	private int getDrawableFromPosition(int position, boolean activated) {
		if (position == 0) 
			return activated ? R.drawable.tab_icon_portfolio_active : R.drawable.tab_icon_portfolio_inactive;
		if (position == 1) 
			return activated ? R.drawable.tab_icon_trivia_acitve : R.drawable.tab_icon_trivia_inacitve;
		if (position == 2) 
			return activated ? R.drawable.tab_icon_trade_acitve : R.drawable.tab_icon_trade_inacitve;
		if (position == 3) 
			return activated ? R.drawable.tab_icon_news_acitve : R.drawable.tab_icon_news_inacitve;
		return activated ? R.drawable.tab_icon_history_acitve : R.drawable.tab_icon_history_inacitve;
	}
    
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
