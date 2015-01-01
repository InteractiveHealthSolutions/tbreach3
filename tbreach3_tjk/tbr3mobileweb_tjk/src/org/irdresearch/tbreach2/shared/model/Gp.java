
package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:01:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Gp generated by hbm2java
 */
public class Gp implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3299807626161470643L;
	private String				gpid;
	private String				qualification;
	private String				specialization;
	private String				workplaceId;
	private Date				dateRegistered;

	public Gp ()
	{
	}

	public Gp (String gpid)
	{
		this.gpid = gpid;
	}

	public Gp (String gpid, String qualification, String specialization, String workplaceId, Date dateRegistered)
	{
		this.gpid = gpid;
		this.qualification = qualification;
		this.specialization = specialization;
		this.workplaceId = workplaceId;
		this.dateRegistered = dateRegistered;
	}

	public String getGpid ()
	{
		return this.gpid;
	}

	public void setGpid (String gpid)
	{
		this.gpid = gpid;
	}

	public String getQualification ()
	{
		return this.qualification;
	}

	public void setQualification (String qualification)
	{
		this.qualification = qualification;
	}

	public String getSpecialization ()
	{
		return this.specialization;
	}

	public void setSpecialization (String specialization)
	{
		this.specialization = specialization;
	}

	public String getWorkplaceId ()
	{
		return this.workplaceId;
	}

	public void setWorkplaceId (String workplaceId)
	{
		this.workplaceId = workplaceId;
	}

	public Date getDateRegistered ()
	{
		return this.dateRegistered;
	}

	public void setDateRegistered (Date dateRegistered)
	{
		this.dateRegistered = dateRegistered;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return gpid + ", " + qualification + ", " + specialization + ", " + workplaceId + ", " + dateRegistered;
	}

}
