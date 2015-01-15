package org.irdresearch.tbreach.shared.model;

// default package
// Generated Dec 21, 2010 3:45:59 PM by Hibernate Tools 3.4.0.Beta1

/**
 * Feedback generated by hbm2java
 */
public class Feedback implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6170013251205552269L;
	private Long				feedbackId;
	private String				userName;
	private String				feedbackType;
	private String				detail;

	public Feedback()
	{
		// Not implemented
	}

	public Feedback(String userName, String feedbackType, String detail)
	{
		this.userName = userName;
		this.feedbackType = feedbackType;
		this.detail = detail;
	}

	public Long getFeedbackId()
	{
		return this.feedbackId;
	}

	public void setFeedbackId(Long feedbackId)
	{
		this.feedbackId = feedbackId;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFeedbackType()
	{
		return this.feedbackType;
	}

	public void setFeedbackType(String feedbackType)
	{
		this.feedbackType = feedbackType;
	}

	public String getDetail()
	{
		return this.detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	@Override
	public String toString()
	{
		return feedbackId + ", " + userName + ", " + feedbackType + ", " + detail;
	}
}