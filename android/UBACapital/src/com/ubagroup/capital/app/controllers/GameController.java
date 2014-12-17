package com.ubagroup.capital.app.controllers;

import java.util.ArrayList;

import com.ubagroup.capital.app.models.GameQuestion;

/**
 * Controller that manages all the game objects in the trivial game
 * @author saladthieves
 *
 */
public class GameController {
	
	public ArrayList<GameQuestion> questions;
	public GameQuestion currentQuestion;
	
	public GameController(ArrayList<GameQuestion> questions) {
		this.questions = questions;
		currentQuestion = null;
	}
	
	public void addQuestion(GameQuestion question) {
		if (questions.contains(question)) return;
		questions.add(question);
	}
	
	public GameQuestion getNextQuestion() {
		GameQuestion nextQuestion;
		if (currentQuestion == null) {
			nextQuestion = questions.get(0);
		} else {
			if (currentQuestion.questionIndex == (questions.size() - 1)) return null;
			nextQuestion = questions.get(currentQuestion.questionIndex + 1);
		}
		currentQuestion = nextQuestion;
		return currentQuestion;
	}
	
	public int getCorrectAnswerCount() {
		int count = 0;
		for (GameQuestion question : questions) {
			if (question.userAnswerIndex == -1) continue;
			count += question.answerIndex == question.userAnswerIndex ? 1 : 0;
		}
		return count;
	}
}
