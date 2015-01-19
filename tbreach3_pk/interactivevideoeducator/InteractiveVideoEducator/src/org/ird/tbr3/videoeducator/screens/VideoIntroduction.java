package org.ird.tbr3.videoeducator.screens;

import java.util.Timer;
import java.util.TimerTask;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.common.OnAudioCompletionListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoIntroduction extends Activity implements OnCompletionListener, OnAudioCompletionListener {

	AnimationDrawable frameAnimation;
	VideoView videoHolder;
	TextView tvIntroduction;
	Timer introVideoTimer, starAnimationTimer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_video_introduction);
		
		setFinishOnTouchOutside(false);
		
		introVideoTimer = new Timer();
		starAnimationTimer = new Timer();
		tvIntroduction = (TextView) findViewById(R.id.tvHeading);
		tvIntroduction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// startScreen();
				
			}
		});
		
		
		
		videoHolder = (VideoView) findViewById(R.video_viewer.videoView);
		videoHolder.setOnCompletionListener(this);
		setupVideoView(Environment.getExternalStorageDirectory().getPath()+"/DCIM/TBR3/Videos/intro_english.mp4", true);		
		
		
		tvIntroduction.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.zooming_star), null, null, null);
		frameAnimation = (AnimationDrawable) tvIntroduction.getCompoundDrawables()[0];
		startScreen();
		
//		startActivity(new Intent(VideoIntroduction.this, PatientStart.class));
//		finish();
	}

	public void setupVideoView(String path, boolean autoplay) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoHolder.setBackgroundResource(R.drawable.intro_video_frame1_640_480);
        //videoHolder.setMediaController(new MediaController(this));
        videoHolder.setVideoURI(Uri.parse(path));
        videoHolder.requestFocus();
        
     }
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		
		
	}

	@Override
	public void onCompletion(int requestId) {
		//tvIntroduction.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.star_normal), null, null, null);
		//frameAnimation.stop();
		
		startActivity(new Intent(VideoIntroduction.this, PatientStart.class));
		finish();
	}
	
	private void playAudio() {
		
		//tvIntroduction.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.star_animated), null, null, null);
		//frameAnimation.start();
		
		
		AudioPlayer.getInstance().playAudio(Environment.getExternalStorageDirectory().getPath()+App.LANGUAGE.getIntroAudio(), this, 0);
		
	}

	private void startScreen() {
		playAudio();
		if(videoHolder.isPlaying()) {
			videoHolder.pause();
		}
		
		introVideoTimer.cancel();
		introVideoTimer = new Timer();
		introVideoTimer.schedule(new TimerTask() {
			public void run() {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						videoHolder.setBackgroundResource(0);
			            videoHolder.start();
			            
					}
				});
				
			}

		}, App.LANGUAGE.getIntroVideoInAudioArrival());
		
		starAnimationTimer.cancel();
		starAnimationTimer = new Timer();
		starAnimationTimer.schedule(new TimerTask() {
			public void run() {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
			            frameAnimation.start();
			            new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								frameAnimation.stop();
								
							}
						}, 3000);
			            
					}
				});
				
			}

		}, App.LANGUAGE.getStarInAudioArrival());
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
}
