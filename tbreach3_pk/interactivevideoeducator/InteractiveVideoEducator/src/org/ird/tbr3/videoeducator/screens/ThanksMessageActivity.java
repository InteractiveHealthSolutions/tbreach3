package org.ird.tbr3.videoeducator.screens;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.common.OnAudioCompletionListener;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ThanksMessageActivity extends Activity implements OnAudioCompletionListener {

	AnimationDrawable frameAnimation;
	ImageView ivThanks;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_thanks_message);
		
		ivThanks = (ImageView) findViewById(R.id.ivThanks);
//		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Images/Resources/thank_you.png");
//		Drawable drawable = new BitmapDrawable(getResources(), Tools.getInstance().resizeBitmap(bitmap, 620));
//		ivThanks.setBackgroundDrawable(drawable);
		ivThanks.setScaleType(ScaleType.FIT_CENTER);
		ivThanks.setBackgroundResource(R.drawable.waving_character);
		frameAnimation = (AnimationDrawable) ivThanks.getBackground();
		
		AudioPlayer.getInstance().playAudio(Environment.getExternalStorageDirectory().getPath()+App.LANGUAGE.getThanksAudio(), this, 0);
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS", Locale.ENGLISH);
		DataCollector.obsList.add(new String[] {App.ENDING_DATE_TIME_UUID, df.format(new Date())});
	
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		frameAnimation.start();
	}

	@Override
	public void onCompletion(int requestId) {
		startActivity(new Intent(ThanksMessageActivity.this, DataHandler.class));
		finish();
		
	}

}
