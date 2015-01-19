package org.ird.tbr3.videoeducator.common;

import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Language;

public class App {
	
	public static int ENCOUNTER_TYPE_ID;
	public static String PATIENT_NAME;
	public static int PATIENT_AGE;
	public static String USERNAME;
	
	
	private static String PASSWORD;
	public static String USER_UUID;
	public static String PATIENT_MR_NUMBER;
	public static String LOCATION_NAME = "";
	public static final String GET_USER_FORM = "Get User";
	public static final String GET_PATIENT_FORM	= "Get Patient";
	public static String LANGUAGE_UUID = "Language";
	public static String MOTHER_TOUNGE_UUID = "Mother Tounge";
	public static String USER_STARTING_DATE_TIME_UUID = "User Starting Time";
	public static String PATIENT_STARTING_DATE_TIME_UUID = "Patient Starting Time";
	public static String ENDING_DATE_TIME_UUID = "Video App Ending Time";
	public static Language LANGUAGE = DataProvider.getInstance().getLanguage(Language.ENGLISH_US);
	public static final String HTTPS_URI = "https://202.141.249.106:8932/tbreach3web/";
	public static final String HTTP_URI = "http://202.141.249.106:8931/tbreach3web/";
	public static String CURRENT_URI = HTTPS_URI;
	
	
	//"https://202.141.249.106:8932/openmrs/ws/rest/v1/";
	
	public static int APP_MODE;
	public static final int MODE_ONLINE_STORAGE = 0;
	public static final int MODE_OFFLINE_STORAGE = 1;
	
	public static void setPassword(String password) {
		PASSWORD = password;
	}
	
	public static String getPassword() {
		return PASSWORD;
	}
	
	
}
