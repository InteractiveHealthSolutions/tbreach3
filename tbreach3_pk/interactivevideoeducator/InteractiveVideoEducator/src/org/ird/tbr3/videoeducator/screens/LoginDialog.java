package org.ird.tbr3.videoeducator.screens;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.MainPreferences;
import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.NetworkProgressDialog;
import org.ird.tbr3.videoeducator.network_tasks.ServerService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginDialog extends Activity {

	EditText etUsername, etPassword;
	Button btnLogin;
	ServerService serverService;
	Spinner spLocations;
	List<String> locationsList;
	NetworkProgressDialog dialog;
	Boolean fromUserStart;
	TextView tvVersion;
	ImageView btnSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login_dialog);
		dialog = new  NetworkProgressDialog(this, "Verifying user, please wait");
		serverService = new ServerService(this);
		locationsList = new ArrayList<String>();
		locationsList.add("101000 Sehatmand Zindagi Centre Korangi");
		locationsList.add("102000 Screening Centre 2");
		locationsList.add("103000 Screening Centre 3");
		fromUserStart = Boolean.valueOf(getIntent().getStringExtra("from_userstart"));
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		tvVersion = (TextView) findViewById(R.id.tvVersion);
		btnSettings = (ImageView) findViewById(R.id.ivSettings);
		btnSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginDialog.this, MainPreferences.class));
			}
		});
		
		PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			tvVersion.setText(pInfo.versionName);
		} catch (NameNotFoundException e) {
			tvVersion.setText("Unidentified");
			e.printStackTrace();
		}
		
//		etUsername.setText("nvidia");
//		etPassword.setText("GeForce540");
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		spLocations = (Spinner) findViewById(R.id.spLocations);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				App.USERNAME = etUsername.getText().toString();
				App.setPassword(etPassword.getText().toString());
				App.LOCATION_NAME = locationsList.get(spLocations.getSelectedItemPosition());
				
				dialog.show();
				
				new Operation().execute();
				 // startActivity(new Intent(LoginDialog.this, MainActivity.class));
			}
		});
	}
	
	private class Operation extends AsyncTask<String, Void, Boolean> {

	       @Override
	       protected Boolean doInBackground(String... params) {
	    	   
	    	   return serverService.validateUser();
	       }

	       @Override
	       protected void onPostExecute(Boolean result) {
	           dialog.dismiss();
	           if(result) {
	        	   setResult(RESULT_OK);
					if(!fromUserStart) {
						startActivity(new Intent(LoginDialog.this, MainActivity.class));
					}
					finish();
	           } else {
	        	   AlertDialog.Builder b = new AlertDialog.Builder(LoginDialog.this);
	        	   b.setTitle("Login Failed").setMessage("Invalid username or password").setNegativeButton("Retry", null);
	        	   b.show();
	           }
	       }

	       @Override
	       protected void onPreExecute() {}

	       @Override
	       protected void onProgressUpdate(Void... values) {}
	   }
}
