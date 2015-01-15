/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
// default package
// Generated Jan 6, 2011 10:24:45 AM by Hibernate Tools 3.4.0.Beta1

package org.irdresearch.tbreach2.shared.model;

/**
 * XrayResultsId generated by hbm2java
 */
public class XrayResultsId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3528906464370487265L;
	private int					irs;
	private String				patientId;

	public XrayResultsId ()
	{
		// Not implemented
	}

	public XrayResultsId (int xrayId, String patientId)
	{
		this.irs = xrayId;
		this.patientId = patientId;
	}

	public int getIrs ()
	{
		return this.irs;
	}

	public void setIrs (int irs)
	{
		this.irs = irs;
	}

	public String getPatientId ()
	{
		return this.patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}

	@Override
	public String toString ()
	{
		return irs + ", " + patientId;
	}
}
