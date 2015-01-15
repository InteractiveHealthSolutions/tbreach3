package org.irdresearch.tbreach.shared.model;

// default package
// Generated Dec 21, 2010 3:45:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * LogUserLogin generated by hbm2java
 */
public class LogUserLogin implements java.io.Serializable
{

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.loginNo + ", " + this.userId + ", " + this.loginTime + ", " + this.logoutTime + ", " + this.remarks;
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3353487827782511036L;
	private Long				loginNo;
	private String				userId;
	private Date				loginTime;
	private Date				logoutTime;
	private String				remarks;

	public LogUserLogin()
	{
		// Not implemented
	}

	public LogUserLogin(String userId)
	{
		this.userId = userId;
	}

	public LogUserLogin(String userId, Date loginTime, Date logoutTime, String remarks)
	{
		this.userId = userId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.remarks = remarks;
	}

	public Long getLoginNo()
	{
		return this.loginNo;
	}

	public void setLoginNo(Long loginNo)
	{
		this.loginNo = loginNo;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public Date getLoginTime()
	{
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}

	public Date getLogoutTime()
	{
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime)
	{
		this.logoutTime = logoutTime;
	}

	public String getRemarks()
	{
		return this.remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

}
