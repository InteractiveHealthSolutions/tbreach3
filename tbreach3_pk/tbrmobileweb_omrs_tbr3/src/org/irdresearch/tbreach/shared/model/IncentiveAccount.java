// default package
// Generated Jan 2, 2011 1:23:51 PM by Hibernate Tools 3.4.0.Beta1
package org.irdresearch.tbreach.shared.model;

import java.math.BigDecimal;

/**
 * IncentiveAccount generated by hbm2java
 */
public class IncentiveAccount implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3779610276662695814L;
	private String				pid;
	private BigDecimal			totalCredit;
	private BigDecimal			totalTransferred;
	private String				details;

	public IncentiveAccount()
	{
		// Not implemented
	}

	public IncentiveAccount(String pid, BigDecimal totalCredit, BigDecimal totalTransferred)
	{
		this.pid = pid;
		this.totalCredit = totalCredit;
		this.totalTransferred = totalTransferred;
	}

	public IncentiveAccount(String pid, BigDecimal totalCredit, BigDecimal totalTransferred, String details)
	{
		this.pid = pid;
		this.totalCredit = totalCredit;
		this.totalTransferred = totalTransferred;
		this.details = details;
	}

	public String getPid()
	{
		return this.pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public BigDecimal getTotalCredit()
	{
		return this.totalCredit;
	}

	public void setTotalCredit(BigDecimal totalCredit)
	{
		this.totalCredit = totalCredit;
	}

	public BigDecimal getTotalTransferred()
	{
		return this.totalTransferred;
	}

	public void setTotalTransferred(BigDecimal totalTransferred)
	{
		this.totalTransferred = totalTransferred;
	}

	public String getDetails()
	{
		return this.details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	@Override
	public String toString()
	{
		return pid + ", " + totalCredit + ", " + totalTransferred + ", " + details;
	}
}
