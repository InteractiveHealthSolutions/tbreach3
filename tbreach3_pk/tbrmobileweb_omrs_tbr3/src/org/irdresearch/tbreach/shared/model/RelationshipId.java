package org.irdresearch.tbreach.shared.model;

// default package
// Generated Dec 21, 2010 3:45:59 PM by Hibernate Tools 3.4.0.Beta1

/**
 * RelationshipId generated by hbm2java
 */
public class RelationshipId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4961544198287449542L;
	private String				pid;
	private String				relativePid;

	public RelationshipId()
	{
		// Not implemented
	}

	public RelationshipId(String pid, String relativePid)
	{
		this.pid = pid;
		this.relativePid = relativePid;
	}

	public String getPid()
	{
		return this.pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getRelativePid()
	{
		return this.relativePid;
	}

	public void setRelativePid(String relativePid)
	{
		this.relativePid = relativePid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.pid + ", " + this.relativePid;
	}

}
