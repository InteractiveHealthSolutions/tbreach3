/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbr3web.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * DefinitionId generated by hbm2java
 */
public class DefinitionId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3827462399699826943L;
	private String				definitionType;
	private String				definitionKey;

	public DefinitionId ()
	{
	}

	public DefinitionId (String definitionType, String definitionKey)
	{
		this.definitionType = definitionType;
		this.definitionKey = definitionKey;
	}

	public String getDefinitionType ()
	{
		return this.definitionType;
	}

	public void setDefinitionType (String definitionType)
	{
		this.definitionType = definitionType;
	}

	public String getDefinitionKey ()
	{
		return this.definitionKey;
	}

	public void setDefinitionKey (String definitionKey)
	{
		this.definitionKey = definitionKey;
	}

	public boolean equals (Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DefinitionId))
			return false;
		DefinitionId castOther = (DefinitionId) other;

		return ((this.getDefinitionType () == castOther.getDefinitionType ()) || (this.getDefinitionType () != null && castOther.getDefinitionType () != null && this.getDefinitionType ().equals (
				castOther.getDefinitionType ())))
				&& ((this.getDefinitionKey () == castOther.getDefinitionKey ()) || (this.getDefinitionKey () != null && castOther.getDefinitionKey () != null && this.getDefinitionKey ().equals (
						castOther.getDefinitionKey ())));
	}

	public int hashCode ()
	{
		int result = 17;

		result = 37 * result + (getDefinitionType () == null ? 0 : this.getDefinitionType ().hashCode ());
		result = 37 * result + (getDefinitionKey () == null ? 0 : this.getDefinitionKey ().hashCode ());
		return result;
	}

	@Override
	public String toString ()
	{
		return definitionType + ", " + definitionKey;
	}

}
