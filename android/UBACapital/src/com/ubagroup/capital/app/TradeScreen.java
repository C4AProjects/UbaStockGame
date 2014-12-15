package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

/**
 * Trade screen
 * @author saladthieves
 *
 */
public class TradeScreen extends Screen {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trade_screen, container, false);
		replaceScreen(new StockListingScreen(), false);
		return root;
	}
	
	public void replaceScreen(Screen screen, boolean addToStack) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.trade_fragment_container, screen);
		if (addToStack) transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void removeScreen(Screen screen) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.remove(screen);
		transaction.commit();
	}
}
