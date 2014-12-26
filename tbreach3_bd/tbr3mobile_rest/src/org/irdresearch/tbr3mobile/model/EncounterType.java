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
public class EncounterType extends AbstractModel
{
	public static final String	FIELDS		= "uuid,name,description";
	private String				name		= "";
	private String				description	= "";

	public EncounterType (String uuid, String name, String description)
	{
		super (uuid);
		this.name = name;
		this.description = description;
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("uuid", super.getUuid ());
			jsonObject.put ("name", name);
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static EncounterType parseJSONObject (JSONObject json)
	{
		EncounterType encounterType = null;
		String uuid = "";
		String name = "";
		String description = "";
		try
		{
			uuid = json.getString ("uuid");
			name = json.getString ("name");
			description = json.getString ("description");
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			encounterType = null;
		}
		encounterType = new EncounterType (uuid, name, description);
		return encounterType;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String name)
	{
		this.name = name;
	}

	public String getDescription ()
	{
		return description;
	}

	public void setDescription (String description)
	{
		this.description = description;
	}

	@Override
	public String toString ()
	{
		return super.toString () + ", " + name + ", " + description;
	}
}
