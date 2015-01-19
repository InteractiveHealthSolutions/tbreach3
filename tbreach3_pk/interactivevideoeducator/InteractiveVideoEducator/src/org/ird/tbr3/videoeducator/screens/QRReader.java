package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.network_tasks.ServerService;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;

public class QRReader extends CaptureActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capture);
	}

	@Override
	public void handleDecode(Result rawResult, Bitmap barcode) {	
		String uuid = new ServerService(this).getPatientId(rawResult.getText());
		if(uuid != null){
			App.PATIENT_MR_NUMBER = uuid;
			Intent intent = new Intent(QRReader.this, LanguageSelecter.class);
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

	}

}
