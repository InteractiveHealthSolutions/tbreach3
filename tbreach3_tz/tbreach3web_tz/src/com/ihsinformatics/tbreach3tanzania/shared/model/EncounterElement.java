
package com.ihsinformatics.tbreach3tanzania.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * EncounterElement generated by hbm2java
 */
public class EncounterElement implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1039693088057924110L;
	private EncounterElementId	id;
	private String				validator;
	private String				description;

	public EncounterElement ()
	{
	}

	public EncounterElement (EncounterElementId id)
	{
		this.id = id;
	}

	public EncounterElement (EncounterElementId id, String validator, String description)
	{
		this.id = id;
		this.validator = validator;
		this.description = description;
	}

	public EncounterElementId getId ()
	{
		return this.id;
	}

	public void setId (EncounterElementId id)
	{
		this.id = id;
	}

	public String getValidator ()
	{
		return this.validator;
	}

	public void setValidator (String validator)
	{
		this.validator = validator;
	}

	public String getDescription ()
	{
		return this.description;
	}

	public void setDescription (String description)
	{
		this.description = description;
	}

	@Override
	public String toString ()
	{
		return id + ", " + validator + ", " + description;
	}

}
