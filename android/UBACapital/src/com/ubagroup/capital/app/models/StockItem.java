package com.ubagroup.capital.app.models;

import java.util.Locale;

/**
 * Class representing a single trade item
 * @author saladthieves
 *
 */
public class StockItem {
	public String title;
	
	public StockItem() {
		this("");
	}
	
	public StockItem(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.getDefault(), "Title: %s", title);
	}
}
