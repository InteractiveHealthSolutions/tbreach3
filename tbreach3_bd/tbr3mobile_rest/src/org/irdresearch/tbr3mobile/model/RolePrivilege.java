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
public class RolePrivilege extends AbstractModel
{
	public static final String	FIELDS	= "uuid,name";

	public RolePrivilege (String uuid)
	{
		super (uuid);
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("uuid", super.getUuid ());
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static RolePrivilege parseJSONObject (JSONObject json)
	{
		RolePrivilege rolePrivilege = null;
		String uuid = "";
		try
		{
			uuid = json.getString ("uuid");
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			rolePrivilege = null;
		}
		rolePrivilege = new RolePrivilege (uuid);
		return rolePrivilege;
	}

	@Override
	public String toString ()
	{
		return super.toString ();
	}

}
