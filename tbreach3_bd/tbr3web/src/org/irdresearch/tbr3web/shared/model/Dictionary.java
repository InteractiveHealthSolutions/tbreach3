
package org.irdresearch.tbr3web.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Dictionary generated by hbm2java
 */
public class Dictionary implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1540158347615195010L;
	private Integer				termId;
	private String				term;
	private String				definition;
	private String				definedBy;
	private Date				enteredOn;
	private Date				lastRevised;
	private String				revisedBy;
	private String				previousDefinition;
	private String				originalDefinition;

	public Dictionary ()
	{
	}

	public Dictionary (String definition, String definedBy, Date enteredOn)
	{
		this.definition = definition;
		this.definedBy = definedBy;
		this.enteredOn = enteredOn;
	}

	public Dictionary (String term, String definition, String definedBy, Date enteredOn, Date lastRevised, String revisedBy, String previousDefinition, String originalDefinition)
	{
		this.term = term;
		this.definition = definition;
		this.definedBy = definedBy;
		this.enteredOn = enteredOn;
		this.lastRevised = lastRevised;
		this.revisedBy = revisedBy;
		this.previousDefinition = previousDefinition;
		this.originalDefinition = originalDefinition;
	}

	public Integer getTermId ()
	{
		return this.termId;
	}

	public void setTermId (Integer termId)
	{
		this.termId = termId;
	}

	public String getTerm ()
	{
		return this.term;
	}

	public void setTerm (String term)
	{
		this.term = term;
	}

	public String getDefinition ()
	{
		return this.definition;
	}

	public void setDefinition (String definition)
	{
		this.definition = definition;
	}

	public String getDefinedBy ()
	{
		return this.definedBy;
	}

	public void setDefinedBy (String definedBy)
	{
		this.definedBy = definedBy;
	}

	public Date getEnteredOn ()
	{
		return this.enteredOn;
	}

	public void setEnteredOn (Date enteredOn)
	{
		this.enteredOn = enteredOn;
	}

	public Date getLastRevised ()
	{
		return this.lastRevised;
	}

	public void setLastRevised (Date lastRevised)
	{
		this.lastRevised = lastRevised;
	}

	public String getRevisedBy ()
	{
		return this.revisedBy;
	}

	public void setRevisedBy (String revisedBy)
	{
		this.revisedBy = revisedBy;
	}

	public String getPreviousDefinition ()
	{
		return this.previousDefinition;
	}

	public void setPreviousDefinition (String previousDefinition)
	{
		this.previousDefinition = previousDefinition;
	}

	public String getOriginalDefinition ()
	{
		return this.originalDefinition;
	}

	public void setOriginalDefinition (String originalDefinition)
	{
		this.originalDefinition = originalDefinition;
	}

	@Override
	public String toString ()
	{
		return termId + ", " + term + ", " + definition + ", " + definedBy + ", " + enteredOn + ", " + lastRevised + ", " + revisedBy + ", " + previousDefinition + ", " + originalDefinition;
	}

}
