
package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:01:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Treatmentrefusal generated by hbm2java
 */
public class TreatmentRefusal implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4046829996787578862L;
	private String				patientId;
	private String				elementRefused;
	private Integer				monthOfSputum;
	private String				reason;
	private Date				caseClosedOnDate;

	public TreatmentRefusal ()
	{
	}

	public TreatmentRefusal (String patientId)
	{
		this.patientId = patientId;
	}

	public TreatmentRefusal (String patientId, String elementRefused, Integer monthOfSputum, String reason, Date caseClosedOnDate)
	{
		this.patientId = patientId;
		this.elementRefused = elementRefused;
		this.monthOfSputum = monthOfSputum;
		this.reason = reason;
		this.caseClosedOnDate = caseClosedOnDate;
	}

	public String getPatientId ()
	{
		return this.patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}

	public String getElementRefused ()
	{
		return this.elementRefused;
	}

	public void setElementRefused (String elementRefused)
	{
		this.elementRefused = elementRefused;
	}

	public Integer getMonthOfSputum ()
	{
		return this.monthOfSputum;
	}

	public void setMonthOfSputum (Integer monthOfSputum)
	{
		this.monthOfSputum = monthOfSputum;
	}

	public String getReason ()
	{
		return this.reason;
	}

	public void setReason (String reason)
	{
		this.reason = reason;
	}

	public Date getCaseClosedOnDate ()
	{
		return this.caseClosedOnDate;
	}

	public void setCaseClosedOnDate (Date caseClosedOnDate)
	{
		this.caseClosedOnDate = caseClosedOnDate;
	}

}
