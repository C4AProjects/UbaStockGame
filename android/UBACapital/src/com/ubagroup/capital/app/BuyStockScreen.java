package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ubagroup.capital.app.base.Screen;

public class BuyStockScreen extends Screen {
	
	private Button actionReviewOrder;
	private Button actionCancelOrder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trade_start_order, container, false);
		
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		actionReviewOrder = (Button)getView(R.id.actionReviewOrder);
		actionReviewOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TradeScreen screen = (TradeScreen)getParentFragment();
				screen.replaceScreen(new ReviewOrderScreen(), true);
			}
		});
		
		actionCancelOrder = (Button)getView(R.id.actionCancel);
		actionCancelOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getChildFragmentManager().beginTransaction().remove(BuyStockScreen.this).commit();
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		});
		
		return root;
	}
}
