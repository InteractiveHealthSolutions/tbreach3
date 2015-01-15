// default package
// Generated Dec 30, 2010 3:31:23 PM by Hibernate Tools 3.4.0.Beta1
package org.irdresearch.tbreach.shared.model;

/**
 * GpmappingId generated by hbm2java
 */
public class GpMappingId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -955853666272232701L;
	private String				gpid;
	private String				monitorId;

	public GpMappingId()
	{
		// Not implemented
	}

	public GpMappingId(String gpid, String monitorId)
	{
		this.gpid = gpid;
		this.monitorId = monitorId;
	}

	public String getGpid()
	{
		return this.gpid;
	}

	public void setGpid(String gpid)
	{
		this.gpid = gpid;
	}

	public String getMonitorId()
	{
		return this.monitorId;
	}

	public void setMonitorId(String monitorId)
	{
		this.monitorId = monitorId;
	}

	@Override
	public String toString()
	{
		return gpid + ", " + monitorId;
	}
}
