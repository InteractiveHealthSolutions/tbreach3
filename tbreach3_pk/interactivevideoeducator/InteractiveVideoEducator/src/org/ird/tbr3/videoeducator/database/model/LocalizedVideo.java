package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class LocalizedVideo implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -4110985150333241689L;
	private int id;
	private int videoId;
	private String languageId;
	private String videoName;
	private String videoDescription;
	private String videoPath;

	public LocalizedVideo() {
		// TODO Auto-generated constructor stub
	}
	
	public LocalizedVideo(int id, int videoId, String languageId, String videoName, String videoDescription, String videoPath) {
		super();
		this.id = id;
		this.videoId = videoId;
		this.languageId = languageId;
		this.videoName = videoName;
		this.videoDescription = videoDescription;
		this.videoPath = videoPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
}
