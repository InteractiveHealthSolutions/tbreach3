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
public class Location extends AbstractModel
{
	public static final String	FIELDS	= "uuid,name";
	private String				name;

	public Location (String uuid, String name)
	{
		super (uuid);
		this.name = name;
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("name", name);
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static Location parseJSONObject (JSONObject json)
	{
		Location location = null;
		String uuid = "";
		String name = "";
		try
		{
			uuid = json.getString ("uuid");
			name = json.getString ("name");
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			location = null;
		}
		location = new Location (uuid, name);
		return location;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String name)
	{
		this.name = name;
	}

	@Override
	public String toString ()
	{
		return super.toString () + ", " + name;
	}
}
