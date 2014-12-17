package com.ubagroup.capital.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;
import com.ubagroup.capital.app.controllers.GameController;
import com.ubagroup.capital.app.models.GameQuestion;

/**
 * The Trivia screen
 * 
 * @author saladthieves
 */
public class TriviaScreen extends Screen {
	
	private GameController controller;
	private boolean showedSplash;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.trivia_screen, container, false);
		controller = new GameController(createGameQuestions());
//		replaceScreen(new TriviaSplashScreen(), false);
		return root;
	}
	
	public GameController getGameController() {
		return controller;
	}
	
	@Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
        	if (showedSplash) return;
        	showedSplash = true;
    		replaceScreen(new TriviaSplashScreen(), false);
        }
    }
	
	public Screen getNextTrivialQuestionScreen() {
		GameQuestion nextQuestion = controller.getNextQuestion();
		if (nextQuestion == null) return new TriviaResultScreen();
		Screen screen = TriviaQuestionScreen.newInstance(nextQuestion);
		return screen;
	}
	
	public void resetAllScreens() {
		resetGame();
		FragmentManager manager = getChildFragmentManager();
		manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		replaceScreen(new TriviaSplashScreen(), false);
	}
	
	public void resetGame() {
		controller = new GameController(createGameQuestions());
	}
	
	private ArrayList<GameQuestion> createGameQuestions() {
		ArrayList<GameQuestion> questions = new ArrayList<GameQuestion>();
		questions.add(new GameQuestion(0, "Which of the following forms of investment gives me an ownership right in a company?", 
				new String[] 
						{
							"Stock investment",
							"Bond investment",
							"Treasury bills"
						}, 0, "A stock is a partial ownership in a company, with rights to shares in its profits. " +
								"When an investor buys a stock of a company he is called a shareholder or a stockholder " +
								"of that company"));
		
		questions.add(new GameQuestion(1, "What is the difference between bullish and bearish market?",
				new String[] 
						{
							"In a bullish market stock prices are generally falling; in a bearish market stock prices are generally rising",
							"In a bullish market stock prices are generally rising; in a bearish market stock prices are generally falling",
							"Stock prices generally fall in both bullish and bearish market."
						}, 1,
						"A bull market is when everything in the economy is great, people are finding jobs, gross domestic product (GDP) is growing " +
						"and stocks are rising. Things are just plain rosy!"));
		
		questions.add(new GameQuestion(2, "Your friend Catherine keeps persuading you to participate in the Transcorp Hotels Plc or IPO. What does it really mean?",
				new String[] 
						{
							"Initial Public Offer",
							"Initial Private Offer",
							"Initial Public Organization"
						}, 0,
						"Companies come up with an initial price, mostly with premium for the face value of the shares, " +
						"which will be distributed to the investors. This is called the Initial Public Offer or the IPO."));
		
		questions.add(new GameQuestion(3, "Ijeoma wants to invest in the stock market. How can she make money from investing?",
				new String[] 
						{
							"Insider trading",
							"Seling at the highest market price",
							"Dividends and capital gains"
						}, 2, "Remember that in the stock market, you're buying ownership of businesses. So you make money the same way its business owners make money"));
		
		return questions;
	}
	
	public void replaceScreen(Screen screen, boolean addToStack) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.triviaScreenFragmentContainer, screen);
		if (addToStack) transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void removeScreen(Screen screen) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.remove(screen);
		transaction.commit();
	}
}
