package com.ubagroup.capital.app.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ubagroup.capital.app.PortfolioScreen;
import com.ubagroup.capital.app.ProfileActivity;
import com.ubagroup.capital.app.TriviaScreen;
import com.ubagroup.capital.app.base.Act;
import com.ubagroup.capital.app.base.Screen;

public class ScreenPagerAdapter extends FragmentPagerAdapter {
	
	private Act a;
	
	public ScreenPagerAdapter(FragmentManager fm, Act a) {
		super(fm);
		this.a = a;
	}
	
	@Override
	public Screen getItem(int position) {
		switch(position) {
		case 0: return new PortfolioScreen();
		default: return new TriviaScreen();
		}
	}
	
	@Override
	public int getCount() {
		return ProfileActivity.FRAGMENT_COUNT;
	}
}
