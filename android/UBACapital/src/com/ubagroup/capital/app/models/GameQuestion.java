package com.ubagroup.capital.app.models;

import java.io.Serializable;

/**
 * Represents a single question within a trivia game
 * @author saladthieves
 *
 */
public class GameQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int questionIndex;
	public String question;
	public String message;
	public String correctMessage;
	public String[] choices;
	public int answerIndex;
	public int userAnswerIndex;
	
	public GameQuestion() {
		this(0, "", new String[] {""}, 0, "");
	}
	
	public GameQuestion(int questionIndex, String question, String[] choices, int answerIndex, String message) {
		this.questionIndex = questionIndex;
		this.question = question;
		this.choices = choices;
		this.answerIndex = answerIndex;
		this.userAnswerIndex = -1;
		this.message = message;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GameQuestion)) return false;
		GameQuestion gameQuestion = (GameQuestion)o;
		return gameQuestion.question.equals(question);
	}
}
