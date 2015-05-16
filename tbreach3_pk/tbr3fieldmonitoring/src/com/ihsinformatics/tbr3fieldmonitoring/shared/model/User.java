
package com.ihsinformatics.tbr3fieldmonitoring.shared.model;

// Generated Nov 14, 2012 2:47:13 PM by Hibernate Tools 3.4.0.Beta1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4598862828504997256L;
	private String	pid;
	private String	userName;
	private char	currentStatus;
	private String	password;
	private String	secretQuestion;
	private String	secretAnswer;
	private Boolean	loggedIn;
	private String	role;
	private String	location;

	public User ()
	{
	}

	public User (String pid, String userName, char currentStatus, String password, String secretQuestion, String secretAnswer, String role)
	{
		this.pid = pid;
		this.userName = userName;
		this.currentStatus = currentStatus;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.role = role;
	}

	public User (String pid, String userName, char currentStatus, String password, String secretQuestion, String secretAnswer, Boolean loggedIn, String role, String location)
	{
		this.pid = pid;
		this.userName = userName;
		this.currentStatus = currentStatus;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.loggedIn = loggedIn;
		this.role = role;
		this.location = location;
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

	public String getRole ()
	{
		return this.role;
	}

	public void setRole (String role)
	{
		this.role = role;
	}

	public String getLocation ()
	{
		return this.location;
	}

	public void setLocation (String location)
	{
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return pid + ", " + userName + ", " + currentStatus + ", " + secretQuestion + ", " + loggedIn + ", " + role + ", " + location;
	}
}
