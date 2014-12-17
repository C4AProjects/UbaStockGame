package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ubagroup.capital.app.base.Screen;

/**
 * Experiment Screen!!
 * @author johnadamsy
 * A fork from saladthieves Screen. For UI experiments only
 *
 */
public class ExperimentScreen extends Screen {
	
	private ProgressBar progressBar;
	private TextView triviaScore;
	private int score,totalQuestions;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_results_screen, container, false);
		
		getActionBar().setTitle("Experiment");
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		//for debug mode purposes
		totalQuestions=10;
		score=7;
		
		/*hijack the default ui screen to check out the progress ring so far :)*/
		progressBar = (ProgressBar)getView (R.id.circularProgressBar);
		
		triviaScore=(TextView)getView(R.id.triviaScore);
        
		progressBar.setMax(totalQuestions); //total number of questions to be attempted
		progressBar.setSecondaryProgress( score ); //the total questions answered correctly
		
		// display the score at last ;)
		triviaScore.setText(this.getString(R.string.triviaScore, score,totalQuestions)); // The text at the centre
		
		return root;
	}
}
