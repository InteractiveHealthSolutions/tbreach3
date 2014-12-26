
package org.irdresearch.tbr3web.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * EncounterElementId generated by hbm2java
 */
public class EncounterElementId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -760553787507850602L;
	private String				encounterType;
	private String				element;

	public EncounterElementId ()
	{
	}

	public EncounterElementId (String encounterType, String element)
	{
		this.encounterType = encounterType;
		this.element = element;
	}

	public String getEncounterType ()
	{
		return this.encounterType;
	}

	public void setEncounterType (String encounterType)
	{
		this.encounterType = encounterType;
	}

	public String getElement ()
	{
		return this.element;
	}

	public void setElement (String element)
	{
		this.element = element;
	}

	public boolean equals (Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EncounterElementId))
			return false;
		EncounterElementId castOther = (EncounterElementId) other;

		return ((this.getEncounterType () == castOther.getEncounterType ()) || (this.getEncounterType () != null && castOther.getEncounterType () != null && this.getEncounterType ().equals (
				castOther.getEncounterType ())))
				&& ((this.getElement () == castOther.getElement ()) || (this.getElement () != null && castOther.getElement () != null && this.getElement ().equals (castOther.getElement ())));
	}

	public int hashCode ()
	{
		int result = 17;

		result = 37 * result + (getEncounterType () == null ? 0 : this.getEncounterType ().hashCode ());
		result = 37 * result + (getElement () == null ? 0 : this.getElement ().hashCode ());
		return result;
	}

	@Override
	public String toString ()
	{
		return encounterType + ", " + element;
	}

}
