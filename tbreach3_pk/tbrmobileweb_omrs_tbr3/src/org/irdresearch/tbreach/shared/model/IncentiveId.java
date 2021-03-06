package org.irdresearch.tbreach.shared.model;

// default package
// Generated Dec 21, 2010 3:45:59 PM by Hibernate Tools 3.4.0.Beta1

/**
 * IncentiveId generated by hbm2java
 */
public class IncentiveId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2683775144588331417L;
	private String				pid;
	private String				incentiveId;
	private long				transactionNo;

	public IncentiveId()
	{
		// Not implemented
	}

	public IncentiveId(String pid, String incentiveId, long transactionNo)
	{
		this.pid = pid;
		this.incentiveId = incentiveId;
		this.transactionNo = transactionNo;
	}

	public String getPid()
	{
		return this.pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getIncentiveId()
	{
		return this.incentiveId;
	}

	public void setIncentiveId(String incentiveId)
	{
		this.incentiveId = incentiveId;
	}

	public long getTransactionNo()
	{
		return this.transactionNo;
	}

	public void setTransactionNo(long transactionNo)
	{
		this.transactionNo = transactionNo;
	}

	@Override
	public String toString()
	{
		return pid + ", " + incentiveId + ", " + transactionNo;
	}

}
