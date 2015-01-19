package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Option implements Serializable {

	
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -3641081075651587031L;
	private int optionId;
	private int questionId;
	private Boolean correct;
	private  String conceptName;
	private String englishText;
	
	public Option() {
		// TODO Auto-generated constructor stub
	}

	public Option(int optionId, int questionId, String conceptName, String englishText) {
		super();
		this.optionId = optionId;
		this.questionId = questionId;
		this.conceptName = conceptName;
		this.englishText = englishText;
	}
	
	public String getEnglishText() {
		return englishText;
	}

	public void setEnglishText(String englishText) {
		this.englishText = englishText;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public Option(int optionId, int questionId, String conceptName, String englishText, Boolean isCorrect) {
		super();
		this.optionId = optionId;
		this.questionId = questionId;
		this.correct = isCorrect;
		this.conceptName = conceptName;
		this.englishText = englishText;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setCorrect(Boolean isCorrect) {
		this.correct = isCorrect;
	}
	
	public Boolean isCorrect() {
		return correct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conceptName == null) ? 0 : conceptName.hashCode());
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result
				+ ((englishText == null) ? 0 : englishText.hashCode());
		result = prime * result + optionId;
		result = prime * result + questionId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Option other = (Option) obj;
		if (conceptName == null) {
			if (other.conceptName != null)
				return false;
		} else if (!conceptName.equals(other.conceptName))
			return false;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (englishText == null) {
			if (other.englishText != null)
				return false;
		} else if (!englishText.equals(other.englishText))
			return false;
		if (optionId != other.optionId)
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	
}
