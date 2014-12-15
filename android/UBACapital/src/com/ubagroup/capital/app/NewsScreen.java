package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

/**
 * News screen
 * @author saladthieves
 *
 */
public class NewsScreen extends Screen {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.news_screen, container, false);
		replaceScreen(new NewsListScreen(), false);
		return root;
	}
	
	public void replaceScreen(Screen screen, boolean addToStack) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.news_fragment_container, screen);
		if (addToStack) transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void removeScreen(Screen screen) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.remove(screen);
		transaction.commit();
	}
}
