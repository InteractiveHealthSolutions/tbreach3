package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class LocalizedOption implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -1365518454592156704L;
	private int id;
	private int optionId;
	private String languageId;
	private String text;
	private String audioPath;
	private String imageFilePath;
	
	public LocalizedOption() {
		// TODO Auto-generated constructor stub
	}

	public LocalizedOption(int id, int optionId, String languageId,
			String text, String imageFilePath, String audioPath) {
		super();
		this.id = id;
		this.optionId = optionId;
		this.languageId = languageId;
		this.text = text;
		this.audioPath = audioPath;
		this.imageFilePath = imageFilePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
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

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
}
