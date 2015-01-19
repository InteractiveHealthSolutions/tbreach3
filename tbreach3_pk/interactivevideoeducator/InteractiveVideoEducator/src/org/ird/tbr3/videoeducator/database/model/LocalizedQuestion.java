package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class LocalizedQuestion implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 2326277662271964302L;
	private int id;
	private int questionId;
	private String languageId;
	private String text;
	private String audioPath;
	
	public LocalizedQuestion() {
		// TODO Auto-generated constructor stub
	}

	public LocalizedQuestion(int id, int questionId, String languageId,
			String text, String audioPath) {
		super();
		this.id = id;
		this.questionId = questionId;
		this.languageId = languageId;
		this.text = text;
		this.audioPath = audioPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	
	
}
