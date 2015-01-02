package org.irdresearch.tbreachtajikistan.sms;

import java.io.IOException;
import java.text.ParseException;

import org.irdresearch.tbreachtajikistan.sms.ReminderSystem;
import org.quartz.SchedulerException;

public class TajikistanSMSMain {
	
	public static void main(String[] args)
	{
		try {
			ReminderSystem.instantiateReminderSystem();
			ReminderSystem.getScheduler().start();
			
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
