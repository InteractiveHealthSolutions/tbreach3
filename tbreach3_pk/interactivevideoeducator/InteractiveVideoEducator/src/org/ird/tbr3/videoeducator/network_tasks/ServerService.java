package org.ird.tbr3.videoeducator.network_tasks;

import java.io.UnsupportedEncodingException;

import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.HttpsClient;
import org.ird.tbr3.videoeducator.common.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ServerService {
	private String appVersion;
	HttpsClient httpsClient;
	public ServerService(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);	
		appVersion = sharedPrefs.getString("tbr3appversion", "1.3.2");
		httpsClient = new HttpsClient (context);
	}

	public Boolean validateUser() {
		JSONObject json = new JSONObject ();
		try {
			json.put ("app_ver", appVersion);
			json.put ("form_name", App.GET_USER_FORM);
			json.put ("username", App.USERNAME);
			String response = null;
			try {
				response = httpsClient.clientGet(App.CURRENT_URI + "?username=" + App.USERNAME + "&password=" + App.getPassword() + "&content=" + JsonUtil.getEncodedJson (json));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (response != null) {
				
				JSONObject userObj = JsonUtil.getJSONObject (response);
				String userName = userObj.getString ("name");
				
				if (userName.equalsIgnoreCase (App.USERNAME)) {	
	
					return true;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace ();
		}
		
		return false;
	}
	
	public String getPatientId (String patientId) {
		try {
			JSONObject json = new JSONObject ();
			json.put ("app_ver", appVersion);
			json.put ("form_name", App.GET_PATIENT_FORM);
			json.put ("patient_id", patientId);
			String uri = App.CURRENT_URI + "?username=" + App.USERNAME + "&password=" + App.getPassword() + "&content=" + JsonUtil.getEncodedJson (json);
			String response = httpsClient.clientGet (uri);
			JSONObject jsonResponse = JsonUtil.getJSONObject (response);
			if (response != null) {
				if (jsonResponse == null) {
					
					return null;
				}
				if (jsonResponse.has ("id")) {
					
					// return jsonResponse.getString ("id");
					return patientId;
				}
				
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*public String getPatientUuid (String patientId) {
		String uuid = null;
		try
		{
			String response = httpsClient.clientGet (App.HTTPS_URI + "patient?v=custom:(uuid)&q=" + patientId);
			JSONObject json = JSONParser.getJSONObject (response);
			JSONObject[] uuids = JSONParser.getJSONArrayFromObject (json, "results");
			if (uuids.length > 0)
				uuid = uuids[0].getString ("uuid");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return uuid;
	}*/
	
	
	
	public String postEncounter(JSONObject jsonData) {
		String uri = null;
		try {
			uri = App.CURRENT_URI + "?username=" + App.USERNAME + "&password=" + App.getPassword() + "&content=" + JsonUtil.getEncodedJson(jsonData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpsClient.clientPost (uri, jsonData.toString());
		// return "Successful";
	}
}
