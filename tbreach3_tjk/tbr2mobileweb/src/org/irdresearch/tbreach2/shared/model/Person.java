/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:01:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Person generated by hbm2java
 */
public class Person implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3604445892858867805L;
	private String				pid;
	private String				salutation;
	private String				firstName;
	private String				middleName;
	private String				lastName;
	private String				surName;
	private String				guardianName;
	private char				gender;
	private Date				dob;
	private String				maritalStatus;
	private String				domicile;
	private String				nic;
	private String				nicownerName;
	private String				religion;
	private String				caste;
	private String				roleInSystem;
	private Boolean				alive;
	private byte[]				picture;

	public Person ()
	{
	}

	public Person (String pid, String firstName, char gender)
	{
		this.pid = pid;
		this.firstName = firstName;
		this.gender = gender;
	}

	public Person (String pid, String salutation, String firstName, String middleName, String lastName, String surName, String guardianName, char gender, Date dob, String maritalStatus,
			String domicile, String nic, String nicownerName, String religion, String caste, String roleInSystem, Boolean alive, byte[] picture)
	{
		this.pid = pid;
		this.salutation = salutation;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.surName = surName;
		this.guardianName = guardianName;
		this.gender = gender;
		this.dob = dob;
		this.maritalStatus = maritalStatus;
		this.domicile = domicile;
		this.nic = nic;
		this.nicownerName = nicownerName;
		this.religion = religion;
		this.caste = caste;
		this.roleInSystem = roleInSystem;
		this.alive = alive;
		this.picture = picture;
	}

	public String getPid ()
	{
		return this.pid;
	}

	public void setPid (String pid)
	{
		this.pid = pid;
	}

	public String getSalutation ()
	{
		return this.salutation;
	}

	public void setSalutation (String salutation)
	{
		this.salutation = salutation;
	}

	public String getFirstName ()
	{
		return this.firstName;
	}

	public void setFirstName (String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName ()
	{
		return this.middleName;
	}

	public void setMiddleName (String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName ()
	{
		return this.lastName;
	}

	public void setLastName (String lastName)
	{
		this.lastName = lastName;
	}

	public String getSurName ()
	{
		return this.surName;
	}

	public void setSurName (String surName)
	{
		this.surName = surName;
	}

	public String getGuardianName ()
	{
		return this.guardianName;
	}

	public void setGuardianName (String guardianName)
	{
		this.guardianName = guardianName;
	}

	public char getGender ()
	{
		return this.gender;
	}

	public void setGender (char gender)
	{
		this.gender = gender;
	}

	public Date getDob ()
	{
		return this.dob;
	}

	public void setDob (Date dob)
	{
		this.dob = dob;
	}

	public String getMaritalStatus ()
	{
		return this.maritalStatus;
	}

	public void setMaritalStatus (String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	public String getDomicile ()
	{
		return this.domicile;
	}

	public void setDomicile (String domicile)
	{
		this.domicile = domicile;
	}

	public String getNic ()
	{
		return this.nic;
	}

	public void setNic (String nic)
	{
		this.nic = nic;
	}

	public String getNicownerName ()
	{
		return this.nicownerName;
	}

	public void setNicownerName (String nicownerName)
	{
		this.nicownerName = nicownerName;
	}

	public String getReligion ()
	{
		return this.religion;
	}

	public void setReligion (String religion)
	{
		this.religion = religion;
	}

	public String getCaste ()
	{
		return this.caste;
	}

	public void setCaste (String caste)
	{
		this.caste = caste;
	}

	public String getRoleInSystem ()
	{
		return this.roleInSystem;
	}

	public void setRoleInSystem (String roleInSystem)
	{
		this.roleInSystem = roleInSystem;
	}

	public Boolean getAlive ()
	{
		return this.alive;
	}

	public void setAlive (Boolean alive)
	{
		this.alive = alive;
	}

	public byte[] getPicture ()
	{
		return this.picture;
	}

	public void setPicture (byte[] picture)
	{
		this.picture = picture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return pid + ", " + salutation + ", " + firstName + ", " + middleName + ", " + lastName + ", " + surName + ", " + guardianName + ", " + gender + ", " + dob + ", " + maritalStatus + ", "
				+ domicile + ", " + nic + ", " + nicownerName + ", " + religion + ", " + caste + ", " + roleInSystem + ", " + alive;
	}

}
