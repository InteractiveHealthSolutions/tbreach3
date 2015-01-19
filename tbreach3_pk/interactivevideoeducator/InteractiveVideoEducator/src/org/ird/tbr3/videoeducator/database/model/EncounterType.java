package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class EncounterType implements Serializable {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -517797855887154421L;
	
	public static final String COLUMN_TYPE_NAME = "type_name";
	public static final String COLUMN_TYPE_ID = "type_id";
	
	private String typeName;
	private int typeId;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String patientName) {
		this.typeName = patientName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public EncounterType(String typeName) {
		super();
		this.typeName = typeName;
	}
}
