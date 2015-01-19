package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Question implements Serializable {
	
	public static int BEFORE = 0, AFTER = 1, BOTH = 2, NONE = 3;
	public static final String COLUMN_QUESTION_ID = "question_id";
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -3272071716646871272L;
	private int questionId;
	private int videoId;
	private int questionType;
	int questionOccurance;
	private  String conseptName;
	private String englishText; 
	
	public String getEnglishText() {
		return englishText;
	}

	public void setEnglishText(String englishText) {
		this.englishText = englishText;
	}

	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int questionId, int videoId, int questionType, int questionOccurance, String englishText, String conseptName) {
		super();
		this.questionId = questionId;
		this.videoId = videoId;
		this.questionType = questionType;
		this.questionOccurance = questionOccurance;
		this.conseptName = conseptName;
		this.englishText = englishText;
	}

	public String getConseptName() {
		return conseptName;
	}

	public void setConseptName(String conseptName) {
		this.conseptName = conseptName;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getQuestionOccurance() {
		return questionOccurance;
	}

	public void setQuestionOccurance(int questionOccurance) {
		this.questionOccurance = questionOccurance;
	}
	
}
