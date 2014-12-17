package com.ubagroup.capital.app;

import java.util.Locale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ubagroup.capital.app.base.Screen;
import com.ubagroup.capital.app.controllers.GameController;
import com.ubagroup.capital.app.models.GameQuestion;

public class TriviaQuestionScreen extends Screen {
	
	public static final String TRIVIA_QUESTION_DATA = "QUESTION_DATA";
	private GameQuestion gameQuestion;
	
	private LinearLayout profStatement;
	private TextView textOutcome;
	private TextView textStatement;
	private ImageView leftArrow;
	
	private ProgressBar triviaProgressBar;
	private TextView triviaQuestionNumber;
	
	private TextView triviaQuestion;
	
	private LinearLayout triviaButtonContainer;
	
	private Button nextButton;
	private GameController gameController;
	private TriviaScreen triviaScreen;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_question_multiplechoice_professor, container, false);
		
		gameQuestion = (GameQuestion)getArguments().getSerializable(TRIVIA_QUESTION_DATA);
		triviaScreen = (TriviaScreen)getParentFragment();
		gameController = triviaScreen.getGameController();
		
		profStatement = (LinearLayout)getView(R.id.profStatement);
		textOutcome = (TextView)getView(R.id.textOutcome);
		textStatement = (TextView)getView(R.id.textStatement);
		leftArrow = (ImageView)getView(R.id.leftArrow);
		
		triviaProgressBar = (ProgressBar)getView(R.id.triviaProgressBar);
		triviaQuestionNumber = (TextView)getView(R.id.triviaQuestionNumber);
		
		triviaQuestion = (TextView)getView(R.id.triviaQuestion);
		
		triviaButtonContainer = (LinearLayout)getView(R.id.triviaButtonContainer);
		
		nextButton = (Button)getView(R.id.actionNext);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Screen nextScreen = triviaScreen.getNextTrivialQuestionScreen();
				if (nextScreen instanceof TriviaQuestionScreen) {
					TriviaQuestionScreen screen = (TriviaQuestionScreen)nextScreen;
					triviaScreen.replaceScreen(screen, false);
				} else if (nextScreen instanceof TriviaResultScreen) {
					TriviaResultScreen screen = (TriviaResultScreen)nextScreen;
					triviaScreen.replaceScreen(screen, false);
				}
			}
		});
		
		initialize();
		
		return root;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void initialize() {
		toggleProfStatementHolder(false);
		updateProgress();
		triviaQuestion.setText(gameQuestion.question);
		createButtons();
	}
	
	private void updateProgress() {
		int index = gameQuestion.questionIndex;
		triviaProgressBar.setMax(gameController.questions.size());
		triviaProgressBar.setProgress(index + 1);
		triviaQuestionNumber.setText(String.format(Locale.getDefault(), "%d of %d", index + 1, gameController.questions.size()));
	}
	
	private void toggleProfStatementHolder(boolean visible) {
		textStatement.setText(visible ? gameQuestion.message : "");
		profStatement.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
		leftArrow.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
	}
	
	private void createButtons() {
		for (String s : gameQuestion.choices) {
			getParent().getLayoutInflater().inflate(R.layout.trivia_question_button_layout, triviaButtonContainer, true);
			Button button = (Button)triviaButtonContainer.getChildAt(triviaButtonContainer.getChildCount() - 1);
			button.setOnClickListener(buttonListener);
			button.setText(s);
		}
	}
	
	private View.OnClickListener buttonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (gameQuestion.userAnswerIndex != -1) return;
			int userAnswerIndex = getButtonIndex((Button)v);
			gameQuestion.userAnswerIndex = userAnswerIndex;
			processUserAnswer();
		}
	};
	
	private void processUserAnswer() {
		toggleProfStatementHolder(true);
		boolean correctAnswer = gameQuestion.answerIndex == gameQuestion.userAnswerIndex;
		textOutcome.setText(getResources().getString(correctAnswer ? R.string.textCorrectAnswer : R.string.textWrongAnswer));
		textOutcome.setTextColor(getResources().getColor(correctAnswer ? R.color.green : R.color.red));
		for (int i = 0; i < triviaButtonContainer.getChildCount(); i++) {
			Button button = (Button)triviaButtonContainer.getChildAt(i);
			if (correctAnswer) button.setTextColor(getResources().getColor(i == gameQuestion.answerIndex ? R.color.green : R.color.black));
			else button.setTextColor(getResources().getColor(i == gameQuestion.answerIndex ? R.color.green : R.color.red));
		}
	}
	
	private int getButtonIndex(Button clicked) {
		String textClicked = clicked.getText().toString().trim();
		for (int i = 0; i < triviaButtonContainer.getChildCount(); i++) {
			Button button = (Button)triviaButtonContainer.getChildAt(i);
			String text = button.getText().toString().trim();
			if (text.equals(textClicked)) return i;
		}
		return 0;
	}
	
	public static TriviaQuestionScreen newInstance(GameQuestion question) {
		TriviaQuestionScreen screen = new TriviaQuestionScreen();
		Bundle args = new Bundle();
		args.putSerializable(TRIVIA_QUESTION_DATA, question);
		screen.setArguments(args);
		return screen;
	}
}
