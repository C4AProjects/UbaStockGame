package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

/**
 * The long-ass Trade screen
 * @author saladthieves
 *
 */
public class TradeScreen extends Screen {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trade_marketdata, container, false);
		return root;
	}
}
