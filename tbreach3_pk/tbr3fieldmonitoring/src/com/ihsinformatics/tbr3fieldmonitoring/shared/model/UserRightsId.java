
package com.ihsinformatics.tbr3fieldmonitoring.shared.model;

// Generated Jun 13, 2012 3:47:17 PM by Hibernate Tools 3.4.0.CR1

/**
 * UserRightsId generated by hbm2java
 */
public class UserRightsId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -585916800817454555L;
	private String	userRole;
	private String	menuName;

	public UserRightsId ()
	{
	}

	public UserRightsId (String userRole, String menuName)
	{
		this.userRole = userRole;
		this.menuName = menuName;
	}

	public String getUserRole ()
	{
		return this.userRole;
	}

	public void setUserRole (String userRole)
	{
		this.userRole = userRole;
	}

	public String getMenuName ()
	{
		return this.menuName;
	}

	public void setMenuName (String menuName)
	{
		this.menuName = menuName;
	}

	public boolean equals (Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRightsId))
			return false;
		UserRightsId castOther = (UserRightsId) other;

		return ((this.getUserRole () == castOther.getUserRole ()) || (this.getUserRole () != null && castOther.getUserRole () != null && this.getUserRole ().equals (castOther.getUserRole ())))
				&& ((this.getMenuName () == castOther.getMenuName ()) || (this.getMenuName () != null && castOther.getMenuName () != null && this.getMenuName ().equals (castOther.getMenuName ())));
	}

	public int hashCode ()
	{
		int result = 17;

		result = 37 * result + (getUserRole () == null ? 0 : this.getUserRole ().hashCode ());
		result = 37 * result + (getMenuName () == null ? 0 : this.getMenuName ().hashCode ());
		return result;
	}

	@Override
	public String toString ()
	{
		return userRole + ", " + menuName;
	}

}
