package com.ubagroup.capital.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

public class TriviaSplashScreen extends Screen {
	
	private TriviaScreen triviaScreen;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_start_quiz_splash, container, false);
		triviaScreen = (TriviaScreen)getParentFragment();
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				triviaScreen.resetGame();
				triviaScreen.replaceScreen(triviaScreen.getNextTrivialQuestionScreen(), false);
			}
		}, 750);
		return root;
	}
}
