package com.ubagroup.capital.app.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ubagroup.capital.app.NewsScreen;
import com.ubagroup.capital.app.PortfolioScreen;
import com.ubagroup.capital.app.ProfileActivity;
import com.ubagroup.capital.app.TradeScreen;
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
		case 1: return new TriviaScreen();
		case 2: return new TradeScreen();
		case 3: return new NewsScreen();
		default: return new TriviaScreen();	// no history page design provided!
		}
	}
	
	@Override
	public int getCount() {
		return ProfileActivity.FRAGMENT_COUNT;
	}
}
