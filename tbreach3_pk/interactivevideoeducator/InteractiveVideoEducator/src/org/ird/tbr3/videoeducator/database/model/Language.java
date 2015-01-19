package org.ird.tbr3.videoeducator.database.model;

import java.io.Serializable;

public class Language implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 3186484682821781014L;
	private String languageId;
	private String languageName;
	private String scriptType;
	private String audioPath;
	private String introAudioPath;
	private String thanksAudioPath;
	private String[] motherLanguages;
	private int starArrival;
	private int introVideoArrival;
	
	public static String ENGLISH_US = "en-US";
	public static String URDU_PAK = "ur-PK";
	public static String BENGLA_BAN = "bn-BN";
	public static String RTL_SCRIPT = "rtl";
	public static String LTR_SCRIPT = "ltr";
	
	
	public Language() {
		// TODO Auto-generated constructor stub
	}

	public Language(String languageId, String languageName, String audioPath, String scriptType, String introAudioPath, String thanksAudioPath, int introVideoArrival, int starArrival, String... motherLanguages) {
		super();
		this.languageId = languageId;
		this.languageName = languageName;
		this.audioPath = audioPath;
		this.scriptType = scriptType;
		this.introAudioPath = introAudioPath;
		this.thanksAudioPath = thanksAudioPath;
		this.introVideoArrival = introVideoArrival;
		this.starArrival = starArrival;
		this.motherLanguages = motherLanguages;
	}

//////////////////////////////////setters//////////////////////////////////////
	public Language setLanguageId(String languageId) {
		this.languageId = languageId;
		return this;
	}

	public Language setLanguageName(String languageName) {
		this.languageName = languageName;
		return this;
	}

	public Language setAudioPath(String audioPath) {
		this.audioPath = audioPath;
		return this;
	}

	public Language setScriptType(String scriptType) {
		this.scriptType = scriptType;
		return this;
	}

	public Language setIntroAudio(String introAudioPath) {
		this.introAudioPath = introAudioPath;
		return this;
	}

	public Language setThanksAudio(String thanksAudioPath) {
		this.thanksAudioPath = thanksAudioPath;
		return this;
	}

	public Language setStarInAudioArrival(int starArrival) {
		this.starArrival = starArrival;
		return this;
	}

	public Language setIntroVideoInAudioArrival(int introVideoArrival) {
		this.introVideoArrival = introVideoArrival;
		return this;
	}

	public Language setmotherLanguages(String... motherLanguages) {
		this.motherLanguages = motherLanguages;
		return this;
	}

	//////////////////////////////////getters//////////////////////////////////////
	public String getScriptType() {
		return scriptType;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getLanguageId() {
		return languageId;
	}

	public String getIntroAudio() {
		return introAudioPath;
	}

	public String getThanksAudio() {
		return thanksAudioPath;
	}

	public int getStarInAudioArrival() {
		return starArrival;
	}

	public int getIntroVideoInAudioArrival() {
		return introVideoArrival;
	}

	public String[] getmotherLanguages() {
		return motherLanguages;
	}

}
