package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class QuestionType implements Serializable {

	public static int SINGLE_SELECT_IMAGE = 0;
	public static int MULTI_SELECT_IMAGE = 1;
	public static int SINGLE_SELECT_TEXT = 2;
	public static int MULTI_SELECT_TEXT = 3;
	public static int OTHER = 4;
	
	/**
	 * Genereted serial version UID 
	 */
	private static final long serialVersionUID = 5584575753894893429L;
	private int typeId;
	private String typeName;

	public QuestionType() {
		// TODO Auto-generated constructor stub
	}
	
	public QuestionType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
