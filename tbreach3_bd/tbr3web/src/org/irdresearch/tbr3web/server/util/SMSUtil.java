/**
 * Utility to send SMS alerts
 */

package org.irdresearch.tbr3web.server.util;

import java.util.Date;
import org.irdresearch.tbr3web.shared.model.Feedback;
import org.irdresearch.tbr3web.shared.model.Sms;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class SMSUtil
{
	private static final String	status	= "PENDING";
	public static SMSUtil		util	= new SMSUtil ();

	public void sendGenericSMSAlert (String targetNumber, String messageText)
	{
		Sms sms = new Sms (targetNumber, messageText, new Date (), null, status, null, null);
		if (!sms.getTargetNumber ().equals (""))
			HibernateUtil.util.save (sms);
	}

	/**
	 * Send alerts to Technical correspondents on feedback
	 * 
	 * @param feedback
	 */
	public void sendAlertsOnFeedback (Feedback feedback)
	{
		if (feedback.getFeedbackType ().equalsIgnoreCase ("Error/Bug"))
		{
			sendGenericSMSAlert ("03453174270", feedback.toString ());
		}
	}
}