/**
 * 
 */

package org.irdresearch.tbr3mobile.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.irdresearch.tbr3mobile.App;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class Observation extends AbstractModel
{
	public static final String	FIELDS	= "uuid,encounterDatetime,patient,encounterType,location,provider,obs";

	String						personUuid;
	Date						obsDateTime;
	String						conceptUuid;
	String						locationUuid;
	boolean						isValueConcept;
	String						value;

	public Observation (String uuid)
	{
		super (uuid);
	}

	public Observation (String uuid, String personUuid, Date obsDateTime, String conceptUuid, String locationUuid,
			boolean isValueConcept, String value)
	{
		super (uuid);
		this.personUuid = personUuid;
		this.obsDateTime = obsDateTime;
		this.conceptUuid = conceptUuid;
		this.locationUuid = locationUuid;
		this.isValueConcept = isValueConcept;
		this.value = value;
	}

	public JSONObject getJSONObject ()
	{
		JSONObject jsonObject = new JSONObject ();
		try
		{
			jsonObject.put ("uuid", super.getUuid ());
			jsonObject.put ("personUuid", personUuid);
			jsonObject.put ("obsDateTime", App.getSqlDate (obsDateTime));
			jsonObject.put ("conceptUuid", conceptUuid);
			jsonObject.put ("locationUuid", locationUuid);
			jsonObject.put ("value", value);
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			jsonObject = null;
		}
		return jsonObject;
	}

	public static Observation parseJSONObject (JSONObject json)
	{
		Observation observation;
		String uuid = "";
		String personUuid = null;
		Date obsDateTime = null;
		String locationUuid = null;
		String conceptUuid = null;
		boolean isValueConcept = false;
		String value = null;
		try
		{
			uuid = json.getString ("uuid");
			personUuid = json.getString ("personUuid");
			// TODO: Change the locale based on preferences
			SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss", Locale.getDefault ());
			obsDateTime = format.parse (json.getString ("obsDateTime"));
			locationUuid = json.getString ("locationUuid");
			conceptUuid = json.getString ("conceptUuid");
			// If the value can be parsed (mazeed), then turn it into another nested JSON object
			try
			{
				JSONObject obj = json.getJSONObject ("value");
				isValueConcept = true;
				value = obj.toString ();
			}
			catch (JSONException e)
			{
				isValueConcept = false;
				value = json.getString ("value");
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace ();
			observation = null;
		}
		catch (ParseException e)
		{
			e.printStackTrace ();
			observation = null;
		}
		observation = new Observation (uuid, personUuid, obsDateTime, conceptUuid, locationUuid, isValueConcept, value);
		return observation;
	}

	public String getPersonUuid ()
	{
		return personUuid;
	}

	public void setPersonUuid (String personUuid)
	{
		this.personUuid = personUuid;
	}

	public Date getObsDateTime ()
	{
		return obsDateTime;
	}

	public void setObsDateTime (Date obsDateTime)
	{
		this.obsDateTime = obsDateTime;
	}

	public String getConceptUuid ()
	{
		return conceptUuid;
	}

	public void setConceptUuid (String conceptUuid)
	{
		this.conceptUuid = conceptUuid;
	}

	public String getLocationUuid ()
	{
		return locationUuid;
	}

	public void setLocationUuid (String locationUuid)
	{
		this.locationUuid = locationUuid;
	}

	public boolean isValueConcept ()
	{
		return isValueConcept;
	}

	public void setValueConcept (boolean isValueConcept)
	{
		this.isValueConcept = isValueConcept;
	}

	public String getValue ()
	{
		return value;
	}

	public void setValue (String value)
	{
		this.value = value;
	}

	@Override
	public String toString ()
	{
		return super.toString () + ", " + personUuid + ", " + obsDateTime + ", " + conceptUuid + ", " + locationUuid
				+ ", " + isValueConcept + ", " + value;
	}
}
