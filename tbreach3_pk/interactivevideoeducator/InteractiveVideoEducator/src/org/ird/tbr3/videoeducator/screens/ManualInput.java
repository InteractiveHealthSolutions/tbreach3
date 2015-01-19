package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.Tools;
import org.ird.tbr3.videoeducator.network_tasks.ServerService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class ManualInput extends Activity implements OnClickListener, OnTouchListener {

	EditText etValue;
	Button btnCancel, btnOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_input);
		setThreadSafety(false);
		etValue = (EditText) findViewById(R.id.etValue);
		// etValue.setText("101130800001-3");

		btnOK = (Button) findViewById(R.id.btnOK);
		btnCancel = (Button) findViewById(R.id.btnCancel);

		btnOK.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnOK.setOnTouchListener(this);
		btnCancel.setOnTouchListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == btnOK) {
			/*if (Tools.getInstance().isValidId(etValue.getText().toString())) {
				Intent intent = new Intent(ManualInput.this, LanguageSelecter.class);
				startActivity(intent);
			}*/
			if (Tools.getInstance().isValidId(etValue.getText().toString())) {
				
				String patientId = new ServerService(this).getPatientId(etValue.getText().toString());
				if(patientId != null){
					App.PATIENT_MR_NUMBER = patientId;
					Intent intent = new Intent(ManualInput.this, LanguageSelecter.class);
					startActivity(intent);
					finish();
				} else {
					new AlertDialog.Builder(this)
					.setTitle("Error!")
					.setMessage("Patient does not exists")
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
							
						}
					}).show();
				}
			} else {
				new AlertDialog.Builder(this)
						.setTitle("Error!")
						.setMessage("Please Enter Valid Patient ID")
						.setNeutralButton("OK", null).show();
			}
		} else if (v == btnCancel) {
			finish();
		}
		
		

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Tools.getInstance().runButtonTouchEffect(ManualInput.this, v, event);

		return false;
	}
	
	public static void setThreadSafety (boolean state)
    {
            StrictMode.ThreadPolicy policy = StrictMode.getThreadPolicy ();
            if (state)
            {
                    policy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
            }
            else
            {
                    policy =  new StrictMode.ThreadPolicy.Builder().detectAll ().build();
            }
            StrictMode.setThreadPolicy(policy);
    }

}
