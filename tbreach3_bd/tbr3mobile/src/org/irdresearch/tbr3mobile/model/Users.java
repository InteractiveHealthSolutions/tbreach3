/**
 * 
 */

package org.irdresearch.tbr3mobile.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class Users extends AbstractModel
{
	public static final String	FIELDS	= "uuid,username";
	private String				name;

	public Users (String uuid, String username)
	{
		super (uuid);
		this.name = username;
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("uuid", super.getUuid ());
			jsonObject.put ("username", name);
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static Users parseJSONObject (JSONObject json)
	{
		Users user = null;
		String uuid = "";
		String username = "";
		try
		{
			uuid = json.getString ("uuid");
			username = json.getString ("username");
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			user = null;
		}
		user = new Users (uuid, username);
		return user;
	}

	public String getUsername ()
	{
		return name;
	}

	public void setUsername (String username)
	{
		this.name = username;
	}

	@Override
	public String toString ()
	{
		return super.toString () + ", " + name;
	}
}
