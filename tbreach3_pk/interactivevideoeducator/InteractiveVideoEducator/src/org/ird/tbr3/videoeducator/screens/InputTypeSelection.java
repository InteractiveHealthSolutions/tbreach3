package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.Tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class InputTypeSelection extends Activity implements OnClickListener, OnTouchListener {

	Button btnManualEntry, btnReadFromQR, btnContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pid);
		
		btnManualEntry = (Button) findViewById(R.id.btnManualEntry);
		btnReadFromQR = (Button) findViewById(R.id.btnReadFromQR);
		btnContinue = (Button) findViewById(R.id.btnContinue);
		
		btnManualEntry.setOnClickListener(this);
		btnReadFromQR.setOnClickListener(this);
		btnContinue.setOnClickListener(this);
		
		btnManualEntry.setOnTouchListener(this);
		btnReadFromQR.setOnTouchListener(this);
		btnContinue.setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==btnReadFromQR) {
			App.APP_MODE = App.MODE_ONLINE_STORAGE;
			Intent intent = new Intent(InputTypeSelection.this, QRReader.class);
			startActivity(intent);
		} else if(v==btnManualEntry) {
			App.APP_MODE = App.MODE_ONLINE_STORAGE;
			Intent intent = new Intent(InputTypeSelection.this, ManualInput.class);
			startActivity(intent);
		} else if(v==btnContinue) {
			App.APP_MODE = App.MODE_OFFLINE_STORAGE;
			Intent intent = new Intent(InputTypeSelection.this, PatientRegistration.class);
			startActivity(intent);
		}
		
		finish();
		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Tools.getInstance().runButtonTouchEffect(InputTypeSelection.this, v, event);

		return false;
	}

}
