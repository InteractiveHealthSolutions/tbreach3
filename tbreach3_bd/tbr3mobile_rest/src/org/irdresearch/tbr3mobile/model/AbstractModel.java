/**
 * Abstract class to be inherited by other model persistance classes
 */

package org.irdresearch.tbr3mobile.model;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public abstract class AbstractModel
{
	private String	uuid;

	public AbstractModel (String uuid)
	{
		this.uuid = uuid;
	}

	public String getUuid ()
	{
		return uuid;
	}

	public void setUuid (String uuid)
	{
		this.uuid = uuid;
	}

	@Override
	public String toString ()
	{
		return uuid;
	}
}
