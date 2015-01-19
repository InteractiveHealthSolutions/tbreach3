package org.ird.tbr3.videoeducator.screens;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.R.layout;
import org.ird.tbr3.videoeducator.R.menu;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class PatientStart extends Activity {
ImageView ivStart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_patient_start);
		
		ivStart = (ImageView) findViewById(R.id.tvUserStart);
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/start.png");
		Drawable drawable = new BitmapDrawable(getResources(), bitmap);
		ivStart.setBackgroundDrawable(drawable);
		
		ivStart.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/start_touched.png");
					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
					ivStart.setBackgroundDrawable(drawable);
					
				} else if(event.getAction() == MotionEvent.ACTION_UP) {
					Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/start.png");
					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
					ivStart.setBackgroundDrawable(drawable);
					
				}
				return false;
			}
		});
		
		ivStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS", Locale.ENGLISH);
				DataCollector.obsList.add(new String[] {App.PATIENT_STARTING_DATE_TIME_UUID, df.format(new Date())});
				Intent intent = new Intent(PatientStart.this, QuestionDisplayer.class);
				intent.putExtra("pre", "true");
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == 0 && resultCode == RESULT_OK) {
			finish();
		}
	}

}
