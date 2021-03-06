/**
 * This class provides hashing functionality
 */

package com.ihsinformatics.tbr3smssurvey.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public final class MDHashUtil
{
	/**
	 * Get Hash code of given string
	 * 
	 * @param String
	 *            to get the hash code of
	 * @return byte[] hash code of input string
	 */
	private static byte[] getHashCode (String string)
	{
		try
		{
			// For now, we will be using SHA; alternatives are SHA2, SHA256, etc.
			MessageDigest md = MessageDigest.getInstance ("SHA");
			md.reset ();
			return md.digest (string.getBytes ());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace ();
			return null;
		}
	}

	/**
	 * Match a string with a hash code
	 * 
	 * @param String
	 *            string to match with the hash code
	 * @param byte[] hash code to match with the string
	 * @return true if string matches with the hash code
	 */
	public static boolean match (String string, String hashString)
	{
		String generatedString = getHashString (string);
		// In cases where all data is stored in CAPS, we should ignore the case
		if (generatedString.equalsIgnoreCase (hashString))
			return true;
		return false;
	}

	/**
	 * Get hash code in a proper string format
	 * 
	 * @param byte[] hash code to convert into string
	 * @return String string form of hash code
	 */
	public static String getHashString (String string)
	{
		StringBuffer hexString = new StringBuffer ();
		byte[] hashCode = getHashCode (string);
		for (int i = 0; i < hashCode.length; i++)
		{
			String hex = Integer.toString (0xFF & hashCode[i]);
			if (hex.length () == 0)
				hexString.append ('0');
			else
				hexString.append (hex);
		}
		return hexString.toString ();
	}
}
