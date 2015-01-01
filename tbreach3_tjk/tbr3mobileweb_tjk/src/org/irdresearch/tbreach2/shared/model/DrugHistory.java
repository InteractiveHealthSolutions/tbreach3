
package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:57:18 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * DrugHistory generated by hbm2java
 */
public class DrugHistory implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5980260469246436013L;
	private DrugHistoryId		id;
	private Date				dateDispensed;
	private String				chwid;
	private String				locationDeliveredTo;
	private String				regimen;
	private Integer				pillsQuotaDelivered;
	private Integer				pillsDelivered;
	private Integer				pillsRemaining;
	private Integer				pillsLost;
	private Integer				streptomycinQuotaDelivered;
	private Integer				streptomycinDelivered;
	private Integer				streptomycinRemaining;
	private Integer				streptomycinLost;
	private Boolean				pillContainerShown;
	private Boolean				emptyStreptomycinCollected;
	private String				remarks;

	public DrugHistory ()
	{
	}

	public DrugHistory (DrugHistoryId id)
	{
		this.id = id;
	}

	public DrugHistory (DrugHistoryId id, Date dateDispensed, String chwid, String locationDeliveredTo, String regimen, Integer pillsQuotaDelivered, Integer pillsDelivered, Integer pillsRemaining,
			Integer pillsLost, Integer streptomycinQuotaDelivered, Integer streptomycinDelivered, Integer streptomycinRemaining, Integer streptomycinLost, Boolean pillContainerShown,
			Boolean emptyStreptomycinCollected, String remarks)
	{
		this.id = id;
		this.dateDispensed = dateDispensed;
		this.chwid = chwid;
		this.locationDeliveredTo = locationDeliveredTo;
		this.regimen = regimen;
		this.pillsQuotaDelivered = pillsQuotaDelivered;
		this.pillsDelivered = pillsDelivered;
		this.pillsRemaining = pillsRemaining;
		this.pillsLost = pillsLost;
		this.streptomycinQuotaDelivered = streptomycinQuotaDelivered;
		this.streptomycinDelivered = streptomycinDelivered;
		this.streptomycinRemaining = streptomycinRemaining;
		this.streptomycinLost = streptomycinLost;
		this.pillContainerShown = pillContainerShown;
		this.emptyStreptomycinCollected = emptyStreptomycinCollected;
		this.remarks = remarks;
	}

	public DrugHistoryId getId ()
	{
		return this.id;
	}

	public void setId (DrugHistoryId id)
	{
		this.id = id;
	}

	public Date getDateDispensed ()
	{
		return this.dateDispensed;
	}

	public void setDateDispensed (Date dateDispensed)
	{
		this.dateDispensed = dateDispensed;
	}

	public String getChwid ()
	{
		return this.chwid;
	}

	public void setChwid (String chwid)
	{
		this.chwid = chwid;
	}

	public String getLocationDeliveredTo ()
	{
		return this.locationDeliveredTo;
	}

	public void setLocationDeliveredTo (String locationDeliveredTo)
	{
		this.locationDeliveredTo = locationDeliveredTo;
	}

	public String getRegimen ()
	{
		return this.regimen;
	}

	public void setRegimen (String regimen)
	{
		this.regimen = regimen;
	}

	public Integer getPillsQuotaDelivered ()
	{
		return this.pillsQuotaDelivered;
	}

	public void setPillsQuotaDelivered (Integer pillsQuotaDelivered)
	{
		this.pillsQuotaDelivered = pillsQuotaDelivered;
	}

	public Integer getPillsDelivered ()
	{
		return this.pillsDelivered;
	}

	public void setPillsDelivered (Integer pillsDelivered)
	{
		this.pillsDelivered = pillsDelivered;
	}

	public Integer getPillsRemaining ()
	{
		return this.pillsRemaining;
	}

	public void setPillsRemaining (Integer pillsRemaining)
	{
		this.pillsRemaining = pillsRemaining;
	}

	public Integer getPillsLost ()
	{
		return this.pillsLost;
	}

	public void setPillsLost (Integer pillsLost)
	{
		this.pillsLost = pillsLost;
	}

	public Integer getStreptomycinQuotaDelivered ()
	{
		return this.streptomycinQuotaDelivered;
	}

	public void setStreptomycinQuotaDelivered (Integer streptomycinQuotaDelivered)
	{
		this.streptomycinQuotaDelivered = streptomycinQuotaDelivered;
	}

	public Integer getStreptomycinDelivered ()
	{
		return this.streptomycinDelivered;
	}

	public void setStreptomycinDelivered (Integer streptomycinDelivered)
	{
		this.streptomycinDelivered = streptomycinDelivered;
	}

	public Integer getStreptomycinRemaining ()
	{
		return this.streptomycinRemaining;
	}

	public void setStreptomycinRemaining (Integer streptomycinRemaining)
	{
		this.streptomycinRemaining = streptomycinRemaining;
	}

	public Integer getStreptomycinLost ()
	{
		return this.streptomycinLost;
	}

	public void setStreptomycinLost (Integer streptomycinLost)
	{
		this.streptomycinLost = streptomycinLost;
	}

	public Boolean getPillContainerShown ()
	{
		return this.pillContainerShown;
	}

	public void setPillContainerShown (Boolean pillContainerShown)
	{
		this.pillContainerShown = pillContainerShown;
	}

	public Boolean getEmptyStreptomycinCollected ()
	{
		return this.emptyStreptomycinCollected;
	}

	public void setEmptyStreptomycinCollected (Boolean emptyStreptomycinCollected)
	{
		this.emptyStreptomycinCollected = emptyStreptomycinCollected;
	}

	public String getRemarks ()
	{
		return this.remarks;
	}

	public void setRemarks (String remarks)
	{
		this.remarks = remarks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return id + ", " + dateDispensed + ", " + chwid + ", " + locationDeliveredTo + ", " + regimen + ", " + pillsQuotaDelivered + ", " + pillsDelivered + ", " + pillsRemaining + ", " + pillsLost
				+ ", " + streptomycinQuotaDelivered + ", " + streptomycinDelivered + ", " + streptomycinRemaining + ", " + streptomycinLost + ", " + pillContainerShown + ", "
				+ emptyStreptomycinCollected + ", " + remarks;
	}

}
