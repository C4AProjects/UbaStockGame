package com.ubagroup.capital.app.adapters;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ubagroup.capital.app.R;
import com.ubagroup.capital.app.base.Act;
import com.ubagroup.capital.app.models.NewsItem;

/**
 * ArrayAdapter for displaying and managing the news items that appear on the ListView inside the 
 * NewsListScreen.
 * 
 * @author saladthieves
 *
 */
public class NewsListAdapter extends ArrayAdapter<NewsItem>{
	private Act a;
	private ArrayList<NewsItem> items;
	private int layoutId;
	
	public NewsListAdapter(Act a, int layoutId, ArrayList<NewsItem> items) {
		super(a, layoutId, items);
		this.a = a;
		this.layoutId = layoutId;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View root = convertView;
		Holder holder;
		
		if (root == null) {
			root = a.getLayoutInflater().inflate(layoutId, parent, false);
			
			holder = new Holder();
			holder.title = (TextView)root.findViewById(R.id.label_news_item_title);
			
			root.setTag(holder);
		} else {
			holder = (Holder)root.getTag();
		}
		
		NewsItem item = items.get(position);
		holder.title.setText(item.title);
		
		return root;
	}
	
	/**
	 * Holder for the content on each row, used as a way to cache some data for reuse.
	 * @author saladthieves
	 *
	 */
	private class Holder {
		TextView title;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}
}
