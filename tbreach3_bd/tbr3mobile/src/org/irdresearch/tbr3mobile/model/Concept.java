/**
 * 
 */

package org.irdresearch.tbr3mobile.model;

import org.irdresearch.tbr3mobile.util.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class Concept extends AbstractModel
{
	public static final String	FIELDS		= "uuid,name,datatype";
	private String				name		= "";
	private String				dataType	= "";

	public Concept (String uuid, String name, String dataType)
	{
		super (uuid);
		this.name = name;
		this.dataType = dataType;
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("uuid", super.getUuid ());
			jsonObject.put ("name", name);
			jsonObject.put ("datatype", dataType);
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static Concept parseJSONObject (JSONObject json)
	{
		Concept concept = null;
		String uuid = "";
		String name = "";
		String dataType = "";
		try
		{
			uuid = json.getString ("uuid");
			name = json.getString ("name");
			// Since name itself is JSON Object, parse it more
			JSONObject nameObj = JSONParser.getJSONObject (name);
			name = nameObj.getString ("name");
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			concept = null;
		}
		concept = new Concept (uuid, name, dataType);
		return concept;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String name)
	{
		this.name = name;
	}

	public String getDataType ()
	{
		return dataType;
	}

	public void setDataType (String dataType)
	{
		this.dataType = dataType;
	}

	@Override
	public String toString ()
	{
		return super.toString () + ", " + name + ", " + dataType;
	}
}
