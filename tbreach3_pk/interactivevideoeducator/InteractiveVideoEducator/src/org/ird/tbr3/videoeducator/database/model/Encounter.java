package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Encounter implements Serializable {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 4402280693811518694L;

	public static final String COLUMN_ENCOUNTER_ID = "encounter_id";
	public static final String COLUMN_ENCOUNTER_TYPE_ID = EncounterType.COLUMN_TYPE_ID;
	public static final String COLUMN_ENCOUNTER_DATE = "encounter_date";
	public static final String COLUMN_PATIENT_NAME = "patient_name";
	public static final String COLUMN_PATIENT_MR_NUMBER = "patient_mr_number";
	public static final String COLUMN_PATIENT_AGE = "patient_age";

	private int typeId;
	private int encounterId;
	private String date;
	private String patientName;
	private String patientMRNumber;
	private int patienAge;

	public Encounter(int typeId, String date, String patientMRNumber, String patientName, int patientAge) {
		this.typeId = typeId;
		this.date = date;
		this.patientName = patientName;
		this.patienAge = patientAge;
		this.patientMRNumber = patientMRNumber;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}

	public String getPatientMrNumber() {
		return patientMRNumber;
	}

	public void setPatientMrNumber(String patientMrNumber) {
		this.patientMRNumber = patientMrNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatienAge() {
		return patienAge;
	}

	public void setPatienAge(int patienAge) {
		this.patienAge = patienAge;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
