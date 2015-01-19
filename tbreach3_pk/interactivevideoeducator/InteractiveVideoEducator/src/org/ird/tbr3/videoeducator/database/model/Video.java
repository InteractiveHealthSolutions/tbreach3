package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Video implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 8121045194942187940L;
	private int videoId;
	private String videoTitle;
	
	public  String encounterName;
	public String getEncounterName() {
		return encounterName;
	}

	public void setEncounterName(String encounterName) {
		this.encounterName = encounterName;
	}

	public Video() {
		// TODO Auto-generated constructor stub
	}
	
	public Video(int videoId, String videoTitle, String encounterName) {
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.encounterName = encounterName;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
}
