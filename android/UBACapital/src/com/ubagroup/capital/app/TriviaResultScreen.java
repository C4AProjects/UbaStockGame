package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ubagroup.capital.app.base.Screen;
import com.ubagroup.capital.app.controllers.GameController;

public class TriviaResultScreen extends Screen{
	
	private ProgressBar progressBar;
	private TextView triviaScore;
	private int score, totalQuestions;
	private Button actionBackToTrivia;
	private Button actionCheckPortfolio;
	private Button actionStartTrading;
	
	private TriviaScreen triviaScreen;
	private GameController gameController;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_results_screen, container, false);
		
		triviaScreen = (TriviaScreen)getParentFragment();
		gameController = triviaScreen.getGameController();
		
		totalQuestions = gameController.questions.size();
		score = gameController.getCorrectAnswerCount();
		
		progressBar = (ProgressBar)getView(R.id.circularProgressBar);
		triviaScore = (TextView)getView(R.id.triviaScore);
		actionBackToTrivia = (Button)getView(R.id.actionBackToTrivia);
		actionBackToTrivia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				triviaScreen.resetAllScreens();
			}
		});
		
		actionCheckPortfolio = (Button)getView(R.id.actionCheckPortfolio);
		actionCheckPortfolio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ProfileActivity)getParent()).activateViewPagerScreen(0);
			}
		});
		actionStartTrading = (Button)getView(R.id.actionStartTrading);
		actionStartTrading.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ProfileActivity)getParent()).activateViewPagerScreen(2);
			}
		});
		
		progressBar.setMax(totalQuestions);
		progressBar.setSecondaryProgress(score);
		
		triviaScore.setText(this.getString(R.string.triviaScore, score,totalQuestions));

		return root;
	}
}
