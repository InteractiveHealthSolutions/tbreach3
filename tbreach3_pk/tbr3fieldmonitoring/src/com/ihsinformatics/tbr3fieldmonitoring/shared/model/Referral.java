
package com.ihsinformatics.tbr3fieldmonitoring.shared.model;

// Generated Nov 20, 2012 3:57:28 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Referral generated by hbm2java
 */
public class Referral implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8402215983814049042L;
	private String	patientId;
	private String	referredBy;
	private String	referredTo;
	private String	reason;
	private Date	dateReferred;

	public Referral ()
	{
	}

	public Referral (String patientId)
	{
		this.patientId = patientId;
	}

	public Referral (String patientId, String referredBy, String referredTo, String reason, Date dateReferred)
	{
		this.patientId = patientId;
		this.referredBy = referredBy;
		this.referredTo = referredTo;
		this.reason = reason;
		this.dateReferred = dateReferred;
	}

	public String getPatientId ()
	{
		return this.patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}

	public String getReferredBy ()
	{
		return this.referredBy;
	}

	public void setReferredBy (String referredBy)
	{
		this.referredBy = referredBy;
	}

	public String getReferredTo ()
	{
		return this.referredTo;
	}

	public void setReferredTo (String referredTo)
	{
		this.referredTo = referredTo;
	}

	public String getReason ()
	{
		return this.reason;
	}

	public void setReason (String reason)
	{
		this.reason = reason;
	}

	public Date getDateReferred ()
	{
		return this.dateReferred;
	}

	public void setDateReferred (Date dateReferred)
	{
		this.dateReferred = dateReferred;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return patientId + ", " + referredBy + ", " + referredTo + ", " + reason + ", " + dateReferred;
	}
}
