package org.irdresearch.tbreach.shared.model;

// default package
// Generated Dec 21, 2010 3:45:59 PM by Hibernate Tools 3.4.0.Beta1

/**
 * UserRights generated by hbm2java
 */
public class UserRights implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6761475086504029148L;
	private UserRightsId		id;
	private boolean				searchAccess;
	private boolean				insertAccess;
	private boolean				updateAccess;
	private boolean				deleteAccess;
	private boolean				printAccess;

	public UserRights()
	{
		// Not implemented
	}

	public UserRights(UserRightsId id, boolean searchAccess, boolean insertAccess, boolean updateAccess, boolean deleteAccess, boolean printAccess)
	{
		this.id = id;
		this.searchAccess = searchAccess;
		this.insertAccess = insertAccess;
		this.updateAccess = updateAccess;
		this.deleteAccess = deleteAccess;
		this.printAccess = printAccess;
	}

	public UserRightsId getId()
	{
		return this.id;
	}

	public void setId(UserRightsId id)
	{
		this.id = id;
	}

	public boolean isSearchAccess()
	{
		return this.searchAccess;
	}

	public void setSearchAccess(boolean searchAccess)
	{
		this.searchAccess = searchAccess;
	}

	public boolean isInsertAccess()
	{
		return this.insertAccess;
	}

	public void setInsertAccess(boolean insertAccess)
	{
		this.insertAccess = insertAccess;
	}

	public boolean isUpdateAccess()
	{
		return this.updateAccess;
	}

	public void setUpdateAccess(boolean updateAccess)
	{
		this.updateAccess = updateAccess;
	}

	public boolean isDeleteAccess()
	{
		return this.deleteAccess;
	}

	public void setDeleteAccess(boolean deleteAccess)
	{
		this.deleteAccess = deleteAccess;
	}

	public boolean isPrintAccess()
	{
		return this.printAccess;
	}

	public void setPrintAccess(boolean printAccess)
	{
		this.printAccess = printAccess;
	}

	@Override
	public String toString()
	{
		return id + ", " + searchAccess + ", " + insertAccess + ", " + updateAccess + ", " + deleteAccess + ", " + printAccess;
	}

}
