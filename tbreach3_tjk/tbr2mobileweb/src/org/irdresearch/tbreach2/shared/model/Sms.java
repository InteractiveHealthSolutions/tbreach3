/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:01:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Sms generated by hbm2java
 */
public class Sms implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4034960933289789675L;
	private Integer				smsid;
	private String				targetNumber;
	private String				messageText;
	private Date				dueDateTime;
	private Date				sentDateTime;
	private String				status;
	private String				errorMessage;
	private String				failureCause;

	public Sms ()
	{
	}

	public Sms (String targetNumber, Date dueDateTime)
	{
		this.targetNumber = targetNumber;
		this.dueDateTime = dueDateTime;
	}

	public Sms (String targetNumber, String messageText, Date dueDateTime, Date sentDateTime, String status, String errorMessage, String failureCause)
	{
		this.targetNumber = targetNumber;
		this.messageText = messageText;
		this.dueDateTime = dueDateTime;
		this.sentDateTime = sentDateTime;
		this.status = status;
		this.errorMessage = errorMessage;
		this.failureCause = failureCause;
	}

	public Integer getSmsid ()
	{
		return this.smsid;
	}

	public void setSmsid (Integer smsid)
	{
		this.smsid = smsid;
	}

	public String getTargetNumber ()
	{
		return this.targetNumber;
	}

	public void setTargetNumber (String targetNumber)
	{
		this.targetNumber = targetNumber;
	}

	public String getMessageText ()
	{
		return this.messageText;
	}

	public void setMessageText (String messageText)
	{
		this.messageText = messageText;
	}

	public Date getDueDateTime ()
	{
		return this.dueDateTime;
	}

	public void setDueDateTime (Date dueDateTime)
	{
		this.dueDateTime = dueDateTime;
	}

	public Date getSentDateTime ()
	{
		return this.sentDateTime;
	}

	public void setSentDateTime (Date sentDateTime)
	{
		this.sentDateTime = sentDateTime;
	}

	public String getStatus ()
	{
		return this.status;
	}

	public void setStatus (String status)
	{
		this.status = status;
	}

	public String getErrorMessage ()
	{
		return this.errorMessage;
	}

	public void setErrorMessage (String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getFailureCause ()
	{
		return this.failureCause;
	}

	public void setFailureCause (String failureCause)
	{
		this.failureCause = failureCause;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return smsid + ", " + targetNumber + ", " + messageText + ", " + dueDateTime + ", " + sentDateTime + ", " + status + ", " + errorMessage + ", " + failureCause;
	}

}
