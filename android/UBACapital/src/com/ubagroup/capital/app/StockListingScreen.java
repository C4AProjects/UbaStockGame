package com.ubagroup.capital.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ubagroup.capital.app.adapters.StockItemAdapter;
import com.ubagroup.capital.app.base.Screen;
import com.ubagroup.capital.app.models.StockItem;

/**
 * Stock listing screen
 * @author saladthieves
 *
 */
public class StockListingScreen extends Screen {
	
	private ListView stockListView;
	private StockItemAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.stock_listing, container, false);
		stockListView = (ListView)getView(R.id.list_browse_stock);
		adapter = new StockItemAdapter(getParent(), R.layout.stock_listing_item, createStockItems());
		stockListView.setAdapter(adapter);
		stockListView.setOnItemClickListener(itemClickListener);
		
		return root;
	}
	
	private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TradeScreen screen = (TradeScreen)getParentFragment();
			screen.replaceScreen(new TradeMarketDataScreen(), true);
		}
	};
	
	private ArrayList<StockItem> createStockItems() {
		ArrayList<StockItem> items = new ArrayList<StockItem>();
		for (int i = 0; i < 20; i++) {
			items.add(new StockItem("STOCK TRADING HEADLING"));
		}
		return items;
	}
}
