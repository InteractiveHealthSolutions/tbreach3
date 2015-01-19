package org.ird.tbr3.videoeducator.screens;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.Tools;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class UserStart extends Activity {

	ImageView ivStart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_user_start);
		
		ivStart = (ImageView) findViewById(R.id.tvUserStart);
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/user_start.png");
		Drawable drawable = new BitmapDrawable(getResources(), bitmap);
		ivStart.setBackgroundDrawable(drawable);
		
		ivStart.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/user_start_touched.png");
					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
					ivStart.setBackgroundDrawable(drawable);
					
				} else if(event.getAction() == MotionEvent.ACTION_UP) {
					Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/user_start.png");
					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
					ivStart.setBackgroundDrawable(drawable);
					
				}
				return false;
			}
		});
		
		ivStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Tools.getInstance().isNetworkAvailable(UserStart.this)) {
					DataCollector.obsList = new ArrayList<String[]>();
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS", Locale.ENGLISH);
					DataCollector.obsList.add(new String[]{App.USER_STARTING_DATE_TIME_UUID, df.format(new Date())});
					Intent intent = new Intent(UserStart.this, InputTypeSelection.class);
					startActivity(intent);
				} else {
					new AlertDialog.Builder(UserStart.this)
						.setTitle("No Internet Access")
						.setMessage("This tablet is not connected to internet, please connect to internet.")
						.setNeutralButton("Close", null)
						.show();
				}
			}
		});
	}

	
	@Override
	public void onBackPressed() {
		Intent intent =new Intent(UserStart.this, LoginDialog.class);
		intent.putExtra("from_userstart", "true");
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == 0 && resultCode == RESULT_OK) {
			finish();
		}
	}

}
