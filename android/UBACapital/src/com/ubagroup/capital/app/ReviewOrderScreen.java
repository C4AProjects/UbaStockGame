package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ubagroup.capital.app.base.Screen;

public class ReviewOrderScreen extends Screen {
	
	private Button actionCancelOrder;
	private Button actionPlaceOrder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trade_order_review, container, false);
		
		actionPlaceOrder = (Button)getView(R.id.actionPlaceOrder);
		actionPlaceOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		actionCancelOrder = (Button)getView(R.id.actionCancelOrder);
		actionCancelOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getChildFragmentManager().beginTransaction().remove(ReviewOrderScreen.this).commit();
			}
		});
		return root;
	}
}
