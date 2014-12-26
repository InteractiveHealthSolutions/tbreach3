/**
 * This class reads and writes JSON objects/arrays
 */

package org.irdresearch.tbr3mobile.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class JSONParser
{
	private static final String	TAG	= "JSONParser";

	public static JSONObject getJSONObject (String jsonText)
	{
		// try parse the string to a JSON object
		try
		{
			JSONObject jsonObj = new JSONObject (jsonText);
			return jsonObj;
		}
		catch (JSONException e)
		{
			Log.e (TAG, "Error parsing data. " + e.getMessage ());
			return null;
		}
	}

	public static JSONObject[] getJSONArrayFromObject (JSONObject jsonObj, String arrayElement)
	{
		try
		{
			JSONArray jsonArray = jsonObj.getJSONArray (arrayElement);
			JSONObject[] jsonObjects = new JSONObject[jsonArray.length ()];
			for (int i = 0; i < jsonArray.length (); i++)
			{
				jsonObjects[i] = JSONParser.getJSONObject (jsonArray.getString (i));
			}
			return jsonObjects;
		}
		catch (JSONException e)
		{
			Log.e (TAG,
					"Error parsing array from JSON Object using element \'" + arrayElement + "\'. " + e.getMessage ());
			return null;
		}
	}
}
