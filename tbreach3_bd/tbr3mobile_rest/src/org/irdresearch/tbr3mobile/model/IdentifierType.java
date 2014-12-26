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
public class IdentifierType extends AbstractModel
{
	public static final String	FIELDS	= "uuid,name";
	private String				name;

	public IdentifierType (String uuid, String name)
	{
		super (uuid);
		this.name = name;
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

	public static IdentifierType parseJSONObject (JSONObject json)
	{
		IdentifierType identifierType = null;
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
			identifierType = null;
		}
		identifierType = new IdentifierType (uuid, name);
		return identifierType;
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
