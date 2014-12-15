package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

public class NewsDetailScreen extends Screen {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.news_listing_item_details, container, false);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ProfileActivity activity = (ProfileActivity)getParent();
		activity.setNewsDetailScreen(this);
		activity.setActionBarBackButtonBehavior(ProfileActivity.BEHAVIOR_REMOVE_NESTED_FRAGMENT);
		
		return root;
	}
}
