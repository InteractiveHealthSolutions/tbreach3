
package org.irdresearch.tbr3web.server;

import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class TBRKMain
{
	public static void main (String[] args)
	{
		try
		{
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public static String sendPost (String url, String content)
	{
		HttpClient httpClient = new DefaultHttpClient ();

		try
		{
			HttpPost request = new HttpPost (url);
			StringEntity params = new StringEntity (content);
			request.addHeader ("content-type", "application/x-www-form-urlencoded");
			request.setEntity (params);
			HttpResponse response = httpClient.execute (request);

			return response.toString ();
		}
		catch (Exception ex)
		{
			ex.getMessage ();
		}
		httpClient.getConnectionManager ().shutdown ();
		return "FAIL";
	}

	@SuppressWarnings("deprecation")
	public static Date parseDate (String str)
	{
		try
		{
			String[] parts = str.split (" ");
			String[] dateParts = parts[0].split ("/");
			int date, month, year, hour = 0, min = 0;
			date = Integer.parseInt (dateParts[0]);
			month = Integer.parseInt (dateParts[1]);
			year = Integer.parseInt (dateParts[2]);

			try
			{
				String[] timeParts = parts[1].split (":");
				hour = Integer.parseInt (timeParts[0]);
				min = Integer.parseInt (timeParts[1]);
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}

			Date dt = new Date (year - 1900, month - 1, date, hour, min, 0);
			return dt;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static int findIndex (String[] array, String str)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equalsIgnoreCase (str))
				return i;
		return -1;
	}
}
