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
 * Screening generated by hbm2java
 */
public class Screening implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3702249700127786490L;
	private Integer				screeningId;
	private String				patientId;
	private String				chwid;
	private String				screenLocation;
	private Integer				age;
	private Character			gender;
	private String				cough;
	private String				coughDuration;
	private String				productiveCough;
	private String				fever;
	private String				nightSweat;
	private String				weightLoss;
	private String				haemoptysis;
	private String				tbhistory;
	private String				familyTbhistory;
	private Boolean				suspect;
	private Date				dateEntered;
	private Date				dateStarted;
	private Date				dateEnded;
	private String 				firstName;
	private String				lastName;
	 				
	
	public Screening ()
	{
	}

	public Screening (String patientId, String chwid, String screenLocation, Integer age, Character gender, String cough, String coughDuration, String productiveCough,
			String fever, String nightSweat, String weightLoss, String haemoptysis, String tbhistory, String familyTbhistory, String firstName, String lastName,
			Boolean suspect, Date dateEntered, Date dateStarted, Date dateEnded)
	{
		this.patientId = patientId;
		this.chwid = chwid;
		this.screenLocation = screenLocation;
		this.age = age;
		this.gender = gender;
		this.firstName = firstName;
		this.cough = cough;
		this.coughDuration = coughDuration;
		this.productiveCough = productiveCough;
		this.fever = fever;
		this.nightSweat = nightSweat;
		this.weightLoss = weightLoss;
		this.haemoptysis = haemoptysis;
		this.lastName = lastName;
		this.tbhistory = tbhistory;
		this.familyTbhistory = familyTbhistory;
		this.suspect = suspect;
		this.dateEntered = dateEntered;
		this.dateStarted = dateStarted;
		this.dateEnded = dateEnded;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getScreeningId ()
	{
		return this.screeningId;
	}

	public void setScreeningId (Integer screeningId)
	{
		this.screeningId = screeningId;
	}

	public String getPatientId ()
	{
		return this.patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}

	public String getChwid ()
	{
		return this.chwid;
	}

	public void setChwid (String chwid)
	{
		this.chwid = chwid;
	}

	public String getScreenLocation ()
	{
		return this.screenLocation;
	}

	public void setScreenLocation (String screenLocation)
	{
		this.screenLocation = screenLocation;
	}

	public Integer getAge ()
	{
		return this.age;
	}

	public void setAge (Integer age)
	{
		this.age = age;
	}

	public Character getGender ()
	{
		return this.gender;
	}

	public void setGender (Character gender)
	{
		this.gender = gender;
	}

	

	public String getCough ()
	{
		return this.cough;
	}

	public void setCough (String cough)
	{
		this.cough = cough;
	}

	public String getCoughDuration ()
	{
		return this.coughDuration;
	}

	public void setCoughDuration (String coughDuration)
	{
		this.coughDuration = coughDuration;
	}

	public String getProductiveCough ()
	{
		return this.productiveCough;
	}

	public void setProductiveCough (String productiveCough)
	{
		this.productiveCough = productiveCough;
	}

	public String getFever ()
	{
		return this.fever;
	}

	public void setFever (String fever)
	{
		this.fever = fever;
	}

	public String getNightSweat ()
	{
		return this.nightSweat;
	}

	public void setNightSweat (String nightSweat)
	{
		this.nightSweat = nightSweat;
	}

	public String getWeightLoss ()
	{
		return this.weightLoss;
	}

	public void setWeightLoss (String weightLoss)
	{
		this.weightLoss = weightLoss;
	}

	public String getHaemoptysis ()
	{
		return this.haemoptysis;
	}

	public void setHaemoptysis (String haemoptysis)
	{
		this.haemoptysis = haemoptysis;
	}

	

	public String getTbhistory ()
	{
		return this.tbhistory;
	}

	public void setTbhistory (String tbhistory)
	{
		this.tbhistory = tbhistory;
	}

	
	public String getFamilyTbhistory ()
	{
		return this.familyTbhistory;
	}

	public void setFamilyTbhistory (String familyTbhistory)
	{
		this.familyTbhistory = familyTbhistory;
	}

	public Boolean getSuspect ()
	{
		return this.suspect;
	}

	public void setSuspect (Boolean suspect)
	{
		this.suspect = suspect;
	}

	public Date getDateEntered ()
	{
		return this.dateEntered;
	}

	public void setDateEntered (Date dateEntered)
	{
		this.dateEntered = dateEntered;
	}

	public Date getDateStarted ()
	{
		return this.dateStarted;
	}

	public void setDateStarted (Date dateStarted)
	{
		this.dateStarted = dateStarted;
	}

	public Date getDateEnded ()
	{
		return this.dateEnded;
	}

	public void setDateEnded (Date dateEnded)
	{
		this.dateEnded = dateEnded;
	}

	
	@Override
	public String toString ()
	{
		return screeningId + patientId + chwid + screenLocation + age + gender +  cough + coughDuration + productiveCough + fever + nightSweat + weightLoss + haemoptysis
				+  tbhistory +   familyTbhistory + suspect + dateEntered + dateStarted + dateEnded;
	}

}
