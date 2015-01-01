
package com.ihsinformatics.tbreach3tanzania.mobile.shared;

public class TBRT
{
	/* Version must be same as of web app */
	public static final String	VERSION						= "1.0";
	public static final String	BASE_URL					= "http://41.188.174.250:8080/tbreach3tanzania/mobileservice";
	//public static final String	BASE_URL 				= "http://localhost:8888/mobileservice";
	public static final String	DEFAULT_SCAN_DELAY			= "2000";
	public static final String	DEFAULT_CONNECTION_TIMEOUT	= "360000";
	public static final String	DEFAULT_PHONE_NUMBER		= String.valueOf (99999999999l);

	public static final String	XML_SUCCESS					= "success";
	public static final String	XML_ERROR					= "error";
	public static final String	XML_RESULT					= "result";

	public static final int		ID_LENGTH					= 11;
	public static final int		KEY_LENGTH					= 10;
	public static final int		VALUE_LENGTH				= 50;
	public static final int		OPENTEXT_LENGTH				= 255;

	private static String		currentUserName				= "";
	private static String		currentUserRole				= "";
	
	private static String		lastFormSubmitted			= "";
	private static long			lastOperationTimestamp		= 0;

	public static String getCurrentUserName ()
	{
		return currentUserName;
	}

	public static void setCurrentUserName (String currentUserName)
	{
		TBRT.currentUserName = currentUserName;
	}

	public static String getCurrentUserRole ()
	{
		return currentUserRole;
	}

	public static void setCurrentUserRole (String currentUserRole)
	{
		TBRT.currentUserRole = currentUserRole;
	}

	public static String getLastFormSubmitted ()
	{
		return lastFormSubmitted;
	}

	public static void setLastFormSubmitted (String lastFormSubmitted)
	{
		TBRT.lastFormSubmitted = lastFormSubmitted;
	}
	
	public static long getLastOperationTimestamp ()
	{
		return lastOperationTimestamp;
	}

	public static void setLastOperationTimestamp (long lastOperationTimestamp)
	{
		TBRT.lastOperationTimestamp = lastOperationTimestamp;
	}
}
