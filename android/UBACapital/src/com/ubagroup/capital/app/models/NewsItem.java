package com.ubagroup.capital.app.models;

import java.util.Locale;

/**
 * Class representing a single news item on the news screen.<br>
 * The rest of the attributes (rating, category etc) will come in later :)
 * 
 * @author saladthieves
 */
public class NewsItem {
	public String title;
	
	public NewsItem() {
		this("");
	}
	
	public NewsItem(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.getDefault(), "Title: %s", title);
	}
}
