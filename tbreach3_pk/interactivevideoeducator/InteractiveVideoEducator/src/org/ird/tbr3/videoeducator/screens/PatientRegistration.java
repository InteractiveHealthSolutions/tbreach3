package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.Tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegistration extends Activity {

	EditText etPatientName, etPatientAge, etMRNumber;
	Button btnContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_registration);
		
		etPatientName = (EditText) findViewById(R.id.etPatientName);
		etPatientAge = (EditText) findViewById(R.id.etPatientAge);
		etMRNumber = (EditText) findViewById(R.id.etPatientMRNumber);
		
		btnContinue = (Button) findViewById(R.id.btnContinue);
		btnContinue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*if(Tools.getInstance().isValidId(etMRNumber.getText().toString())) {*/
				if(etPatientName.getText()!=null && etPatientAge.getText()!=null ) {
					App.PATIENT_NAME = etPatientName.getText().toString();
					App.PATIENT_MR_NUMBER = etMRNumber.getText().toString();
					try{
						App.PATIENT_AGE = Integer.parseInt(etPatientAge.getText().toString());
						Intent intent = new Intent(PatientRegistration.this, LanguageSelecter.class);
						startActivity(intent);
						finish();
					} catch(NumberFormatException nfe) {
						Toast.makeText(PatientRegistration.this, "Age should be a number", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(PatientRegistration.this, "Please fill the form with valid data", Toast.LENGTH_LONG).show();
				}
			/*} else {
				Toast.makeText(PatientRegistration.this, "Invalid MR Number", Toast.LENGTH_LONG).show();
			}*/
				}
		});
		
	}

}
