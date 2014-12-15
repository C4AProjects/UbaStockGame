package com.ubagroup.capital.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ubagroup.capital.app.adapters.NewsListAdapter;
import com.ubagroup.capital.app.base.Screen;
import com.ubagroup.capital.app.models.NewsItem;

public class NewsListScreen extends Screen {
	
	private ListView newsListView;
	private NewsListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.news_listing, container, false);
		
		getActionBar().setDisplayHomeAsUpEnabled(false);

		newsListView = (ListView)getView(R.id.list_browse_news);
		adapter = new NewsListAdapter(getParent(), R.layout.news_listing_item, createNewsItems());
		newsListView.setAdapter(adapter);
		newsListView.setOnItemClickListener(itemClickListener);
		
		return root;
	}
	
	private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			NewsScreen screen = (NewsScreen)getParentFragment();
			screen.replaceScreen(new NewsDetailScreen(), true);
		}
	};
	
	private ArrayList<NewsItem> createNewsItems() {
		ArrayList<NewsItem> items = new ArrayList<NewsItem>();
		for (int i = 0; i < 20; i++) {
			items.add(new NewsItem("Stock market headline"));
		}
		return items;
	}
}
