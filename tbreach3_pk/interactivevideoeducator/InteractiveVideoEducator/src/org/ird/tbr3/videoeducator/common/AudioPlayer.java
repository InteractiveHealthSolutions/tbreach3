package org.ird.tbr3.videoeducator.common;

import android.media.MediaPlayer;

public class AudioPlayer {
	MediaPlayer mp;
	private static AudioPlayer instance;
	private AudioPlayer() {
		mp = new MediaPlayer();
	}
	
	public static AudioPlayer getInstance() {
		if(instance == null) {
			return instance =  new AudioPlayer();
		}
		
		return instance;
	}
	
	public void playAudio(String path){
	    stop();
	    if (mp.isPlaying()) {
			mp.stop();
		}
	    mp = new MediaPlayer();
	    try {
	        mp.setDataSource(path);
	        mp.prepare();
	        mp.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void playAudio(String path, final OnAudioCompletionListener arg1, final int requestId){
	    stop();
	    mp = new MediaPlayer();
	    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
		        arg1.onCompletion(requestId);
			}
		});
	    try {
	        mp.setDataSource(path);
	        mp.prepare();
	        mp.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	        arg1.onCompletion(requestId);
	    }
	}
	
	public void stop() {
		if (mp.isPlaying()) {
			mp.stop();
		}
	}
	
	public void stop(OnAudioCompletionListener arg1, int requestId) {
		if (mp.isPlaying()) {
			mp.stop();
			arg1.onCompletion(requestId);
		}
	}
}
