package org.ird.tbr3.videoeducator.network_tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DataCollector {
	
	public static ArrayList<String[]> obsList;
	
	Context context;
	JSONObject jsonData;
	
	public DataCollector(Context context) {
		this.context = context;
		SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);	
		
		try{
			jsonData = new JSONObject();
			
			jsonData.put("location", App.LOCATION_NAME);
			jsonData.put("username", App.USERNAME);
			jsonData.put("patient_id", App.PATIENT_MR_NUMBER);
			jsonData.put("encounter_type", FontManager.video.getEncounterName());
			jsonData.put("form_date", df.format(new Date()));
			jsonData.put("encounter_location", App.LOCATION_NAME);
			jsonData.put("provider", App.USERNAME);
			jsonData.put("app_ver", sharedPrefs.getString("tbr3appversion", "1.2.2"));
			jsonData.put("form_name", FontManager.video.getEncounterName());
			
			JSONArray jsonArray = new JSONArray();
			for (String[] observations : DataCollector.obsList) {
				JSONObject temp = new JSONObject();
				
				temp.put ("concept", observations[0]);
				temp.put ("value", observations[1]);
				
				jsonArray.put(temp);
			}
			
			jsonData.put("obs", jsonArray.toString());
		} catch (JSONException exception) {
			exception.printStackTrace();
		}
		
	}
	
	public JSONObject getJsonData() {
		return jsonData;
	}
	
	public String send() {
		return new ServerService(context).postEncounter(jsonData);
	}
	
}
