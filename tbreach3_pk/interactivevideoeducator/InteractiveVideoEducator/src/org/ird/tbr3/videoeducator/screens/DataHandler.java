package org.ird.tbr3.videoeducator.screens;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.JSONParser;
import org.ird.tbr3.videoeducator.common.NetworkProgressDialog;
import org.ird.tbr3.videoeducator.database.data_access.AnswerDAO;
import org.ird.tbr3.videoeducator.database.data_access.EncounterDAO;
import org.ird.tbr3.videoeducator.database.model.Answer;
import org.ird.tbr3.videoeducator.database.model.Encounter;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

public class DataHandler extends Activity {
 NetworkProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_sender);
		
		dialog = new NetworkProgressDialog(this, "Submitting data...");
		dialog.show();
		new Operation().execute();
	}

	private class Operation extends AsyncTask<String, Void, String> {
       @Override
       protected String doInBackground(String... params) {
    	  DataCollector dataCollector = new DataCollector(DataHandler.this);
    		if(App.APP_MODE == App.MODE_ONLINE_STORAGE) {
    			return dataCollector.send();
    		} else if(App.APP_MODE == App.MODE_OFFLINE_STORAGE) {
    			JSONObject jsonData = dataCollector.getJsonData();
    			long encounterId = new EncounterDAO(DataHandler.this).insert(new Encounter(App.ENCOUNTER_TYPE_ID, new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(new Date()), App.PATIENT_MR_NUMBER, App.PATIENT_NAME, App.PATIENT_AGE));
    			List<Answer> answers = new JSONParser().parseAnswers(jsonData, encounterId);
    			new AnswerDAO(DataHandler.this).insert(answers);
    			return "successful";
    		}
    		
    	return null;
	  }

	       @Override
	       protected void onPostExecute(String result) {
	           dialog.dismiss();
	           AlertDialog.Builder b = new AlertDialog.Builder(DataHandler.this);
	           if(result.contains("SUCCESS")) {
	        	   b.setMessage("Operation Successful");
	           } else {
	        	   b.setMessage(result);
	           }
	           b.setTitle("Response").setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					
				}
			});
	           b.show();
	       }

	       @Override
	       protected void onPreExecute() {}

	       @Override
	       protected void onProgressUpdate(Void... values) {}
	   }
	 

}
