
package org.irdresearch.tbr3web.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4000334616739602381L;
	private String				pid;
	private String				userName;
	private char				currentStatus;
	private String				password;
	private String				secretQuestion;
	private String				secretAnswer;
	private Boolean				loggedIn;

	public User ()
	{
	}

	public User (String pid, String userName, char currentStatus, String password, String secretQuestion, String secretAnswer)
	{
		this.pid = pid;
		this.userName = userName;
		this.currentStatus = currentStatus;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public User (String pid, String userName, char currentStatus, String password, String secretQuestion, String secretAnswer, Boolean loggedIn)
	{
		this.pid = pid;
		this.userName = userName;
		this.currentStatus = currentStatus;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.loggedIn = loggedIn;
	}

	public String getPid ()
	{
		return this.pid;
	}

	public void setPid (String pid)
	{
		this.pid = pid;
	}

	public String getUserName ()
	{
		return this.userName;
	}

	public void setUserName (String userName)
	{
		this.userName = userName;
	}

	public char getCurrentStatus ()
	{
		return this.currentStatus;
	}

	public void setCurrentStatus (char currentStatus)
	{
		this.currentStatus = currentStatus;
	}

	public String getPassword ()
	{
		return this.password;
	}

	public void setPassword (String password)
	{
		this.password = password;
	}

	public String getSecretQuestion ()
	{
		return this.secretQuestion;
	}

	public void setSecretQuestion (String secretQuestion)
	{
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer ()
	{
		return this.secretAnswer;
	}

	public void setSecretAnswer (String secretAnswer)
	{
		this.secretAnswer = secretAnswer;
	}

	public Boolean getLoggedIn ()
	{
		return this.loggedIn;
	}

	public void setLoggedIn (Boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString ()
	{
		return pid + ", " + userName + ", " + currentStatus + ", " + secretQuestion + ", " + loggedIn;
	}

}
