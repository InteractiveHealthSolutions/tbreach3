package com.ihsinformatics.tbr3fieldmonitoring.shared.model;

// Generated Nov 19, 2012 12:18:02 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Patient generated by hbm2java
 */
public class Patient implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8851753522319779552L;
	private String patientId;
	private String mrNo;
	private Date dateScreened;
	private String team;
	private String suspectedBy;
	private String diseaseSuspected;
	private Date dateSuspected;
	private Boolean diseaseConfirmed;
	private Date dateConfirmed;
	private String confirmationSource;
	private String confirmationRemarks;
	private String diseaseType;
	private String patientType;
	private String patientStatus;
	private String hivStatus;
	private String treatmentSite;
	private Date dateClosed;

	public Patient()
	{
	}

	public Patient(String patientId)
	{
		this.patientId = patientId;
	}

	public Patient(String patientId, String mrNo, Date dateScreened,
			String team, String suspectedBy, String diseaseSuspected,
			Date dateSuspected, Boolean diseaseConfirmed, Date dateConfirmed,
			String confirmationSource, String confirmationRemarks,
			String diseaseType, String patientType, String patientStatus,
			String hivStatus, String treatmentSite, Date dateClosed)
	{
		this.patientId = patientId;
		this.mrNo = mrNo;
		this.dateScreened = dateScreened;
		this.team = team;
		this.suspectedBy = suspectedBy;
		this.diseaseSuspected = diseaseSuspected;
		this.dateSuspected = dateSuspected;
		this.diseaseConfirmed = diseaseConfirmed;
		this.dateConfirmed = dateConfirmed;
		this.confirmationSource = confirmationSource;
		this.confirmationRemarks = confirmationRemarks;
		this.diseaseType = diseaseType;
		this.patientType = patientType;
		this.patientStatus = patientStatus;
		this.hivStatus = hivStatus;
		this.treatmentSite = treatmentSite;
		this.dateClosed = dateClosed;
	}

	public String getPatientId()
	{
		return this.patientId;
	}

	public void setPatientId(String patientId)
	{
		this.patientId = patientId;
	}

	public String getMrNo()
	{
		return this.mrNo;
	}

	public void setMrNo(String mrNo)
	{
		this.mrNo = mrNo;
	}

	public Date getDateScreened()
	{
		return this.dateScreened;
	}

	public void setDateScreened(Date dateScreened)
	{
		this.dateScreened = dateScreened;
	}

	public String getTeam()
	{
		return this.team;
	}

	public void setTeam(String team)
	{
		this.team = team;
	}

	public String getSuspectedBy()
	{
		return this.suspectedBy;
	}

	public void setSuspectedBy(String suspectedBy)
	{
		this.suspectedBy = suspectedBy;
	}

	public String getDiseaseSuspected()
	{
		return this.diseaseSuspected;
	}

	public void setDiseaseSuspected(String diseaseSuspected)
	{
		this.diseaseSuspected = diseaseSuspected;
	}

	public Date getDateSuspected()
	{
		return this.dateSuspected;
	}

	public void setDateSuspected(Date dateSuspected)
	{
		this.dateSuspected = dateSuspected;
	}

	public Boolean getDiseaseConfirmed()
	{
		return this.diseaseConfirmed;
	}

	public void setDiseaseConfirmed(Boolean diseaseConfirmed)
	{
		this.diseaseConfirmed = diseaseConfirmed;
	}

	public Date getDateConfirmed()
	{
		return this.dateConfirmed;
	}

	public void setDateConfirmed(Date dateConfirmed)
	{
		this.dateConfirmed = dateConfirmed;
	}

	public String getConfirmationSource()
	{
		return this.confirmationSource;
	}

	public void setConfirmationSource(String confirmationSource)
	{
		this.confirmationSource = confirmationSource;
	}

	public String getConfirmationRemarks()
	{
		return this.confirmationRemarks;
	}

	public void setConfirmationRemarks(String confirmationRemarks)
	{
		this.confirmationRemarks = confirmationRemarks;
	}

	public String getDiseaseType()
	{
		return this.diseaseType;
	}

	public void setDiseaseType(String diseaseType)
	{
		this.diseaseType = diseaseType;
	}

	public String getPatientType()
	{
		return this.patientType;
	}

	public void setPatientType(String patientType)
	{
		this.patientType = patientType;
	}

	public String getPatientStatus()
	{
		return this.patientStatus;
	}

	public void setPatientStatus(String patientStatus)
	{
		this.patientStatus = patientStatus;
	}

	public String getHivStatus()
	{
		return this.hivStatus;
	}

	public void setHivStatus(String hivStatus)
	{
		this.hivStatus = hivStatus;
	}

	public String getTreatmentSite()
	{
		return treatmentSite;
	}

	public void setTreatmentSite(String treatmentSite)
	{
		this.treatmentSite = treatmentSite;
	}

	public Date getDateClosed()
	{
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed)
	{
		this.dateClosed = dateClosed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return patientId + ", " + mrNo + ", " + dateScreened + ", " + team
				+ ", " + suspectedBy + ", " + diseaseSuspected + ", "
				+ dateSuspected + ", " + diseaseConfirmed + ", "
				+ dateConfirmed + ", " + confirmationSource + ", "
				+ confirmationRemarks + ", " + diseaseType + ", " + patientType
				+ ", " + patientStatus + ", " + hivStatus + ", "
				+ treatmentSite + ", " + dateClosed;
	}
}
