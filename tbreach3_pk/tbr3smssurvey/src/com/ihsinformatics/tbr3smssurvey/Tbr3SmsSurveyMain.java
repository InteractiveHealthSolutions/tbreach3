/**
 * Utility to perform SMS based survey, using SMS Tarseel
 */

package com.ihsinformatics.tbr3smssurvey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.management.InstanceAlreadyExistsException;

import net.jmatrix.eproperties.EProperties;

import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.InboundMessage;
import org.irdresearch.smstarseel.data.InboundMessage.InboundStatus;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;

import com.ihsinformatics.tbr3smssurvey.model.Participant;
import com.ihsinformatics.tbr3smssurvey.model.Transaction;
import com.ihsinformatics.tbr3smssurvey.util.HibernateUtil;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class Tbr3SmsSurveyMain
{
	private static TarseelServices	tarseelService;
	public static CommonDao			commonDao		= new CommonDao ();
	public static SmsSurvey			smsSurvey;
	public static final String		TARSEEL_CFG		= "smstarseel.cfg.xml";
	public static final String		TARSEEL_PROP	= "smstarseel.properties";
	public static final Long		DELAY			= 100L;

	/**
	 * @param args
	 * @throws InstanceAlreadyExistsException
	 */
	public static void main (String[] args)
	{
		// The argument for survey ID should be passed, like -s 3
		int surveyId = 1;
		if (args.length == 2)
		{
			if (args[0].equals ("-s"))
			{
				surveyId = Integer.parseInt (args[1]);
			}
		}
		smsSurvey = new SmsSurvey (surveyId);
		try
		{
			InputStream f = Thread.currentThread ().getContextClassLoader ().getResourceAsStream (TARSEEL_PROP);
			EProperties root = new EProperties ();
			root.load (f);
			Properties prop = new Properties ();
			prop.putAll (convertEntrySetToMap (root.entrySet ()));
			TarseelContext.instantiate (prop, TARSEEL_CFG);
			tarseelService = TarseelContext.getServices ();
		}
		catch (InstanceAlreadyExistsException e)
		{
			e.printStackTrace ();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace ();
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		System.out.println ("Interactive SMS Survey Service is running...");
		while (true)
		{
			try
			{
				List<InboundMessage> newMessages = tarseelService.getSmsService ().findInbound (null, null, InboundStatus.UNREAD, null, null, null, null, false);
				// Check for new incoming messages
				for (InboundMessage message : newMessages)
				{
					System.out.println ("Processing " + message.getText () + " from " + message.getOriginator ());
					tarseelService = TarseelContext.getServices ();
					Participant participant = (Participant) HibernateUtil.util.findObject ("from Participant where contactNo='" + message.getOriginator () + "'");
					// If the contact does not exist in DB, then create
					// a participant
					if (participant == null)
					{
						participant = new Participant (0, message.getOriginator (), "", new Date (), false, UUID.randomUUID ().toString ());
						commonDao.saveParticipant (participant);
						// Send a Welcome message with detail of how to
						// initiate survey
						sendMessage (participant.getContactNo (), smsSurvey.getWelcomeMessage ());
					}
					// If the contact exists, then check message
					else
					{
						String text = message.getText ().trim ().toUpperCase ();
						// If the message is initiator, then initiate survey
						if (smsSurvey.isInitText (text))
						{
							String newMessage = smsSurvey.initiateSurvey (participant);
							sendMessage (participant.getContactNo (), newMessage);
						}
						else
						{
							
							String newMessage = smsSurvey.parseAnswer (participant, text);
							sendMessage (participant.getContactNo (), newMessage);
						}
					}
					message.setStatus (InboundStatus.READ);
					tarseelService.getSmsService ().updateInbound (message);
					tarseelService.commitTransaction ();
				}
				Thread.sleep (DELAY);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace ();
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}

	public static Map<Object, Object> convertEntrySetToMap (Set<Entry<Object, Object>> entrySet)
	{
		Map<Object, Object> mapFromSet = new HashMap<Object, Object> ();
		for (Entry<Object, Object> entry : entrySet)
		{
			mapFromSet.put (entry.getKey (), entry.getValue ());
		}
		return mapFromSet;
	}

	public static void sendMessage (String target, String text)
	{
		Calendar c = Calendar.getInstance ();
		c.add (Calendar.HOUR_OF_DAY, -1);
		c.add (Calendar.MINUTE, 1);
		String message = new String ((text).getBytes (), Charset.forName ("UTF-8"));
		tarseelService.getSmsService ().createNewOutboundSms (target, message, c.getTime (), Priority.MEDIUM, 24, PeriodType.HOUR, 1, null);
	}
}
