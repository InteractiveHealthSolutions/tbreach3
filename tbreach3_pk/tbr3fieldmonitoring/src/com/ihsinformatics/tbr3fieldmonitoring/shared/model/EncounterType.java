package com.ihsinformatics.tbr3fieldmonitoring.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * EncounterType generated by hbm2java
 */
public class EncounterType implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2402212672773278229L;
	private String encounterType;
	private String description;

	public EncounterType()
	{
	}

	public EncounterType(String encounterType)
	{
		this.encounterType = encounterType;
	}

	public EncounterType(String encounterType, String description)
	{
		this.encounterType = encounterType;
		this.description = description;
	}

	public String getEncounterType()
	{
		return this.encounterType;
	}

	public void setEncounterType(String encounterType)
	{
		this.encounterType = encounterType;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return encounterType + ", " + description;
	}

}