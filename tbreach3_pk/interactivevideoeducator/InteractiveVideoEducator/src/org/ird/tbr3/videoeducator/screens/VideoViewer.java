package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewer extends Activity implements OnCompletionListener {

	VideoView videoHolder;
	DataProvider dataProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_video_viewer);
		dataProvider = DataProvider.getInstance();
		
		videoHolder = (VideoView) findViewById(R.video_viewer.videoView);
		
		videoHolder.setOnCompletionListener(this);
		//videoHolder.
		playVideo(Environment.getExternalStorageDirectory().getPath()+dataProvider.getLocalizedVideos(FontManager.video.getVideoId(), App.LANGUAGE.getLanguageId()).getVideoPath(), true);
		
		String title = FontManager.video.getVideoTitle();
		if(title != null) {
			setTitle(title);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void playVideo(String path, boolean autoplay){
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoHolder.setMediaController(new MediaController(this));
        videoHolder.setVideoURI(Uri.parse(path));
        videoHolder.requestFocus();
        if(autoplay){
            videoHolder.start();
        }
     }
    
	@Override
	public void onCompletion(MediaPlayer mp) {
		Intent intent = new Intent(VideoViewer.this, QuestionDisplayer.class);

		intent.putExtra("pre", "false");
		startActivity(intent);
		finish();
		
	}
	
	@Override
	public void onBackPressed() {
		
		//super.onBackPressed();
	}
	
}
