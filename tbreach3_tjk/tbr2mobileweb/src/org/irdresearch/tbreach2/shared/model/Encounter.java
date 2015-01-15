/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:45:30 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Encounter generated by hbm2java
 */
public class Encounter implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4452319133072055384L;
	private EncounterId			id;
	private String				encounterType;
	private String				locationId;
	private Date				dateEncounterEntered;
	private Date				dateEncounterStart;
	private Date				dateEncounterEnd;
	private String				detail;

	public Encounter ()
	{
	}

	public Encounter (EncounterId id, String encounterType)
	{
		this.id = id;
		this.encounterType = encounterType;
	}

	public Encounter (EncounterId id, String encounterType, String locationId, Date dateEncounterEntered, Date dateEncounterStart, Date dateEncounterEnd, String detail)
	{
		this.id = id;
		this.encounterType = encounterType;
		this.locationId = locationId;
		this.dateEncounterEntered = dateEncounterEntered;
		this.dateEncounterStart = dateEncounterStart;
		this.dateEncounterEnd = dateEncounterEnd;
		this.detail = detail;
	}

	public EncounterId getId ()
	{
		return this.id;
	}

	public void setId (EncounterId id)
	{
		this.id = id;
	}

	public String getEncounterType ()
	{
		return this.encounterType;
	}

	public void setEncounterType (String encounterType)
	{
		this.encounterType = encounterType;
	}

	public String getLocationId ()
	{
		return this.locationId;
	}

	public void setLocationId (String locationId)
	{
		this.locationId = locationId;
	}

	public Date getDateEncounterEntered ()
	{
		return this.dateEncounterEntered;
	}

	public void setDateEncounterEntered (Date dateEncounterEntered)
	{
		this.dateEncounterEntered = dateEncounterEntered;
	}

	public Date getDateEncounterStart ()
	{
		return this.dateEncounterStart;
	}

	public void setDateEncounterStart (Date dateEncounterStart)
	{
		this.dateEncounterStart = dateEncounterStart;
	}

	public Date getDateEncounterEnd ()
	{
		return this.dateEncounterEnd;
	}

	public void setDateEncounterEnd (Date dateEncounterEnd)
	{
		this.dateEncounterEnd = dateEncounterEnd;
	}

	public String getDetail ()
	{
		return this.detail;
	}

	public void setDetail (String detail)
	{
		this.detail = detail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return id + ", " + encounterType + ", " + locationId + ", " + dateEncounterEntered + ", " + dateEncounterStart + ", " + dateEncounterEnd + ", " + detail;
	}

}
