package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubagroup.capital.app.base.Screen;

public class TradeMarketDataScreen extends Screen {
	
	private LinearLayout tabContainer;
	
	private TextView actionBuy;
	private TextView actionSell;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trade_marketdata, container, false);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		tabContainer = (LinearLayout)getView(R.id.tradeTabContainer);
		initializeTabContainer();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ProfileActivity activity = (ProfileActivity)getParent();
		activity.setStockDetailScreen(this);
		activity.setActionBarBackButtonBehavior(ProfileActivity.BEHAVIOR_REMOVE_NESTED_FRAGMENT);
		
		actionBuy = (TextView)getView(R.id.actionBuy);
		actionBuy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TradeScreen screen = (TradeScreen)getParentFragment();
				screen.replaceScreen(new BuyStockScreen(), true);
			}
		});
		
		actionSell = (TextView)getView(R.id.actionSell);
		
		replaceScreen(new MarketDataScreen(), false);
		
		return root;
	}
	
	public void replaceScreen(Screen screen, boolean addToStack) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.trade_fragment_container, screen);
		if (addToStack) transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void removeScreen(Screen screen) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.remove(screen);
		transaction.commit();
	}
	
	private void initializeTabContainer() {
		for (int i = 0; i < tabContainer.getChildCount(); i++) {
			TextView tab = (TextView)tabContainer.getChildAt(i);
			tab.setOnClickListener(tabListener);
		}
	}
	
	private View.OnClickListener tabListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			TextView tab = (TextView)v;
			selectTab(tab);
		}
	};
	
	private void selectTab(TextView tab) {
		int index = getTabIndex(tab.getText().toString().trim());
		activateTab(index);
		replaceMarketContent(index);
	}
	
	private void replaceMarketContent(int index) {
		if (index == 0) {
			replaceScreen(new TradeCompanyProfileScreen(), false);
			return;
		}
		if (index == 1) {
			replaceScreen(new MarketDataScreen(), false);
			return;
		}
	}
	
	private int getTabIndex(String text) {
		if (text.equals("Profile")) return 0;
		else if (text.equals("Market Data")) return 1;
		else return 2;
	}
	
	private void activateTab(int index) {
		for (int i = 0; i < tabContainer.getChildCount(); i++) {
			TextView tab = (TextView)tabContainer.getChildAt(i);
			tab.setTextColor(getResources().getColor(index == i ? R.color.white : R.color.black));
			tab.setBackgroundColor(getResources().getColor(index == i ? R.color.navy_blue : R.color.white));
		}
	}
}

