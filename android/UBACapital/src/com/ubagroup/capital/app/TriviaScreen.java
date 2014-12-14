package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

/**
 * The Trivia screen
 * 
 * @author saladthieves
 */
public class TriviaScreen extends Screen {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_screen, container, false);
		
		return root;
	}
}
