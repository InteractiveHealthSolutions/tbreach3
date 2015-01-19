package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Answer implements Serializable {

	
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -7843093689131069489L;
	
	public static final String COLUMN_QUESTION_ID = Question.COLUMN_QUESTION_ID;
	public static final String COLUMN_ENCOUNTER_ID = Encounter.COLUMN_ENCOUNTER_ID;
	public static final String COLUMN_ANSWER_ID = "answer_id";
	public static final String COLUMN_TEXT = "text";
	
	private int questionId;
	private long encounterId;
	private int answerId;
	private String text;

	public Answer(int questionId, long encounterId, String text) {
		super();
		this.questionId = questionId;
		this.encounterId = encounterId;
		this.text = text;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public long getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(long encounterId) {
		this.encounterId = encounterId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
