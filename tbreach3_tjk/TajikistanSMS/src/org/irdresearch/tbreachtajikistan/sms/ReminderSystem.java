/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import ird.xoutTB.dataValidation.DataValidation;
import ird.xoutTB.dataValidation.REG_EX;
import ird.xoutTB.db.entity.Patient;
import ird.xoutTB.emailer.exception.EmailException;
import org.irdresearch.tbreach2.shared.model.PatientReminder;
import org.irdresearch.tbreachtajikistan.utils.EmailEngine;
import org.irdresearch.tbreachtajikistan.utils.LoggerUtil;
//import ird.xoutTB.reminderSys.context.RemServiceContext;
//import ird.xoutTB.reminderSys.db.entity.ScheduledFlags;
//import ird.xoutTB.reminderSys.db.entity.ScheduledReminders;
import ird.xoutTB.reporting.ExceptionUtil;
import org.irdresearch.tbreachtajikistan.utils.Utils;
import org.irdresearch.tbreachtajikistan.sms.HibernateUtil;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
//import org.smslib.smsserver.SMSServer;


public class ReminderSystem {
	
	public static Calendar SMS_SENDING_FAILED_NOTIFY_TIME;

//	private static SMSServer server;
	private static SchedulerFactory schedFact = new StdSchedulerFactory();
	private static Scheduler sched ; 
	private static List<ScheduledJob> scheduledReminders =new ArrayList<ScheduledJob>();
//	private static List<ScheduledFlagedData> scheduledFlag=new ArrayList<ScheduledFlagedData>();
	private static Properties props;
//	private static EmailServer emailer;
	private static Timer customsmstimer;
	private static Timer irsettingsupdater;
	private static RescheduleReminders resch;
	private static FlaggedPatient fpsch;
	private static MonthlyPatientReminder mpsch;
	private static DoctorsReminder docsch;

	private static Object remlogger;

/*	public static void emailErrorReportToAdmin(String subject,String message){
		try {
			String ir_setting=Context.getIRSetting("admin.email-address","interactive.reminder@gmail.com");
			
			if(ir_setting==null){
				LoggerUtil.logIt("Ir Setting was not retrieved while sending email to admin :");
			}else{
				getEmailer().postSimpleMail(new String[]{ir_setting}, subject, message, "admin@interactiveReminder.com");
			}
		} catch (MessagingException e) {
			LoggerUtil.logIt("Error report was not emailed to admin :");
			LoggerUtil.logIt("Stack trace is :"+ExceptionUtil.getStackTrace(e));
		}
	}*/
	
	public static void emailErrorReportToAdminAsASeparateThread(String subject,String message){
		final String msg=message;
		final String sub=subject;
		
		Runnable emailr = new Runnable() {
			@Override
			public void run() {
				//ReminderSystem.emailErrorReportToAdmin(sub, msg);
			}
		};
		
		new Thread(emailr).start();  
	}
	
	
	public static ScheduledJob getRunningScheduledJobDetails(Patient patient){
		for (ScheduledJob job : getScheduledReminders()) {
			if(job.getPatientId().compareTo(patient.getPatientId())==0){
				return job;
			}
		}
		return null;
	}
			
	
	public static Properties getProperties(){
		return props;
	}
	
	public static List<ScheduledJob> getScheduledReminders(){
		return scheduledReminders;
	}
	
/*	public static List<ScheduledFlagedData> getScheduledFlags() {
		return scheduledFlag;
	}*/
	
	public static Scheduler getScheduler() {
		return sched;
	}
	
/*	public static EmailServer getEmailer(){
		return emailer;
	}*/
	
	//public static Scheduler instantiateReminderSystem(SMSServer smsServer) 
				//throws SchedulerException, ParseException, IOException, EmailException{
		public static Scheduler instantiateReminderSystem() 
				throws SchedulerException, ParseException, IOException/*, EmailException*/{
		SMS_SENDING_FAILED_NOTIFY_TIME=Calendar.getInstance();
		SMS_SENDING_FAILED_NOTIFY_TIME.set(Calendar.YEAR, 1975);
		
	//	loadProperties();
		
	//	emailer=new EmailServer(props);
		//server=smsServer;
		
		instantiateScheduler();
		scheduleReminders();
	//	scheduleFlaggedData();
		
		LoggerUtil.logIt("......SCHEDULER SCHEDULED SUCCESSFULLY......");
		LoggerUtil.logIt(Utils.getJVMInfo());
	//	LoggerUtil.logIt(Utils.getSMSServerInfo(ReminderSystem.getServer()));
		
		instantiateEmailer(); //Email properties
		
		return sched;
	}
	private static void loadProperties() throws ParseException, IOException{
		FileInputStream f = null;
		props = new Properties();
		f = new FileInputStream("ReminderSystem.properties");
		
		getProperties().load(f);

		String[] e = getProperties().getProperty("system-shutdown.notification.email-addresses").split(",");
		for (String string : e)
		{
			if(!DataValidation.validate(REG_EX.EMAIL, string.trim())){
				throw new ParseException("invalid email was found in property 'system-shutdown.notification.email-addresses'",0);
			}
		}
		LoggerUtil.logIt("......PROPERTIES LOADED SUCCESSFULLY......");
	}
	private static void instantiateScheduler() 
					throws SchedulerException, ParseException{
		sched=schedFact.getScheduler();
		JobDetail jd = null;
		SimpleTrigger st = null;
		
		JobDetail jd2= null;
		SimpleTrigger st2= null;
/*		
		JobDetail jd3= null;
		SimpleTrigger st3= null;
		
		JobDetail jd = null;
		SimpleTrigger st = null;
		
		JobDetail jd2= null;
		SimpleTrigger st2= null;
		
		JobDetail jd4= null;
		SimpleTrigger st4= null;
		try{
		jd=new JobDetail("jobname","jobgp",LoggerCheckJob.class);
		st=new SimpleTrigger("mytrigger","myGroup",new Date(),  null,SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L); 
		sched.scheduleJob(jd, st);
		}
		catch(Exception e){LoggerUtil.logIt("Job not deleted,because it was already there");
		e.printStackTrace();
		sched.deleteJob("jobname", "jobgp");
		sched.scheduleJob(jd, st);
		}
		
		try
		{
			jd2=new JobDetail("inboundjobname", "inboundjobgroup", StatusCheckJob.class);
			st2=new SimpleTrigger("inboundtriggername", "inboundtriggergroup", new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L);
			sched.scheduleJob(jd2, st2);
		}
		catch(Exception e){
			e.printStackTrace();
			sched.deleteJob("inboundjobname","inboundjobgroup");
			sched.scheduleJob(jd2, st2);
		}
		
		try
		{
			jd3=new JobDetail("customJob", "customjobgroup", CustomCheckJob.class);
			st3=new SimpleTrigger("customtriggername", "customtriggergroup", new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L);
			sched.scheduleJob(jd3, st3);
		}
		catch(Exception e){
			e.printStackTrace();
			sched.deleteJob("customJob","customjobgroup");
			sched.scheduleJob(jd3, st3);
		}
		
		try
		{
			jd4=new JobDetail("NewRecordJob", "NewRecordGroup", CheckingThread.class);
			st4=new SimpleTrigger("NewRecordtriggername", "newrecordtriggergroup", new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,60L*60L*1000L);
			sched.scheduleJob(jd4, st4);
		}
		catch(Exception e){
			e.printStackTrace();
			sched.deleteJob("NewRecordJob","NewRecordGroup");
			sched.scheduleJob(jd4, st4);
		}
		resch=new RescheduleReminders(sched);
		reschf=new RescheduleFlaggedUpdates(sched);
		dailyremrespupdater=new DailyRemRespUpdater(sched);
		
		customsmstimer=new Timer();
		customsmstimer.schedule(new SendCustomSMSJob(), 1*60*1000, 1*60*1000);
		
		
		
	try{
		jd3=new JobDetail("FlaggedPatient","emailFlaggedPatient",FlagPatientJob.class);
		st3=new SimpleTrigger("flaggedPatientTrigger","myGroupTrigger",new Date(),  null,SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L); 
		sched.scheduleJob(jd3, st3);
		}
	catch(Exception e){LoggerUtil.logIt("Job not deleted,because it was already there");
		e.printStackTrace();
		sched.deleteJob("FlaggedPatient", "emailFlaggedPatient");
		sched.scheduleJob(jd3, st3);
		}
			
*/		
		try{
	jd=new JobDetail("jobname","jobgp",LoggerCheckJob.class);
	st=new SimpleTrigger("mytrigger","myGroup",new Date(),  null,SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L); 
	sched.scheduleJob(jd, st);
	}
	catch(Exception e){LoggerUtil.logIt("Job not deleted,because it was already there");
	e.printStackTrace();
	sched.deleteJob("jobname", "jobgp");
	sched.scheduleJob(jd, st);
	}

	try
	{
		jd2=new JobDetail("inboundjobname", "inboundjobgroup", StatusCheckJob.class);
		st2=new SimpleTrigger("inboundtriggername", "inboundtriggergroup", new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,60L*5000L);
		sched.scheduleJob(jd2, st2);
	}
	catch(Exception e){
		e.printStackTrace();
		sched.deleteJob("inboundjobname","inboundjobgroup");
		sched.scheduleJob(jd2, st2);
	}
	
	resch=new RescheduleReminders(sched);  //re-schedule cron job
	fpsch=new FlaggedPatient(sched);       //flagging patient cron job
	mpsch=new MonthlyPatientReminder(sched); //monthly reminder cron job
	docsch=new DoctorsReminder(sched);   //doctors reminder cron job
	
		irsettingsupdater=new Timer();
		irsettingsupdater.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					try{
				//		Context.reloadCurrentIrSettings();
					}catch (Exception e) {
						//LoggerUtil.logIt("Error occurred while reloading irsettings. Trace is:"+ExceptionUtil.getStackTrace(e));
					}
				}
			}
			, 1000*60*60*8, 1000*60*60*24);
	}
	private synchronized static void scheduleReminders() throws ParseException, SchedulerException{
		getScheduledReminders().clear();
	/*	long g= 1394697600000L;
		Date d = new Date(g);
		System.out.println(d);
*/		LoggerUtil.logIt(".......SCHEDULING REMINDERS......");
		
		List<PatientReminder> preminders=new ArrayList<PatientReminder>();
		
//		 ServiceContext sc = new ServiceContext();
		 
		 
		HibernateUtil util = new HibernateUtil();
		preminders=util.getAllPatientReminderRecord();
		
/*		sched.scheduleJob(new JobDetail("TESTTRIGG", "TESTTRIGG", SendReminderJob.class), new CronTrigger("TESTTRIGG", "TESTTRIGG", "TESTTRIGG", "TESTTRIGG", "0 0/5 * * * ?"));
*/		
		/*List<PatientDetails>  pdetail = new ArrayList<PatientDetails>()	;
		pdetail=util.findPatients();*/
		int index = 0;
		for (PatientReminder r : preminders) {
		 getScheduledReminders().add(index, new ScheduledJob(r));
		 try{
		 sched.addJob(getScheduledReminders().get(index).getJob(), true);
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		 try{
		 sched.scheduleJob(getScheduledReminders().get(index).getTrigger());
		 }
		 catch (Exception e) {
			// e.printStackTrace();
				sched.deleteJob(getScheduledReminders().get(index).getJobName(),getScheduledReminders().get(index).getJobGroup());
				sched.addJob(getScheduledReminders().get(index).getJob(), true);
				sched.scheduleJob(getScheduledReminders().get(index).getTrigger());
				//System.out.println(sched.getTrigger(getScheduledReminders().get(index).getTriggerName(), getScheduledReminders().get(index).getTriggerGroup()).getNextFireTime());
				((CronTrigger)sched.getTrigger(getScheduledReminders().get(index).getTriggerName(),
					getScheduledReminders().get(index).getTriggerGroup())).setCronExpression(getScheduledReminders().get(index).getCronExpression());
			sched.rescheduleJob(getScheduledReminders().get(index).getJobName()
					, getScheduledReminders().get(index).getJobGroup(), getScheduledReminders().get(index).getTrigger());
		}
		 index++;
		}
/*		 int index=0;
		 
		 for (PatientReminder r : preminders) {
			 if(r.getPatient().isUnderTreatment()){
				 if(r.getPatient().getDateOfCompletion()!=null&&r.getPatient().getDateOfCompletion().compareTo(new Date())<0){
					 try {
						sc.getPatientService().setPatientCompletedTreatment(r.getPatient().getPatientId());
						
					 
					 } catch (PatientDataException e) {
						LoggerUtil.logIt("Scheduled Patient record was not found. check if database connections are active and database is consistent.");
					 }
					 
					 try{
						 sc.commitTransaction();
					 }catch (Exception e) {
						 LoggerUtil.logIt("Patient treatment status updation on reaching date of completion failed.\nerror is:"+ExceptionUtil.getStackTrace(e));
						// emailErrorReportToAdmin("Patient treatment status updation", "During scheduling reminders patient with id "+r.getPatient().getPatientId()+" treatment status updation on reaching date of completion failed.\nerror is:"+e.getMessage()+"\ncheck log for stack trace");
					}
					 finally{
						 sc.closeSession();
					 }
					 
				 }  else {System.out.println("Patient ID "+r.getPatient().getPatientId());
				 		getScheduledReminders().add(index,new ScheduledJob(r));//37th index, patientid(10960001), reminderid(1), trigname="rem_trig",triggp="reminder"jobname="rem_job_"
					//jobgp="reminder";
					try{
						 sched.addJob(getScheduledReminders().get(index).getJob(), true);//adding the job to the scheduler,"rem_job_88250011"
					}catch (Exception e) {
						//System.out.println(e.getMessage());
						LoggerUtil.logIt("An error occurred while adding job "+getScheduledReminders().get(index).getJob().getFullName()+" to scheduler."+ExceptionUtil.getStackTrace(e));
					}
					try{
						sched.scheduleJob(getScheduledReminders().get(index).getTrigger());//get the trigger for the job at index,'30 45 11,13,15 * * ?'"
						//System.out.println(sched.getTrigger(getScheduledReminders().get(index).getTriggerName(), getScheduledReminders().get(index).getTriggerGroup()).getNextFireTime());
					}catch (Exception e) {
							//System.out.println(e.getMessage());
							//System.out.println("Updating Trigger and rescheduling job");
							
							try{
								sched.deleteJob(getScheduledReminders().get(index).getJobName(),getScheduledReminders().get(index).getJobGroup());
								sched.addJob(getScheduledReminders().get(index).getJob(), true);
								sched.scheduleJob(getScheduledReminders().get(index).getTrigger());
								//System.out.println(sched.getTrigger(getScheduledReminders().get(index).getTriggerName(), getScheduledReminders().get(index).getTriggerGroup()).getNextFireTime());
								((CronTrigger)sched.getTrigger(getScheduledReminders().get(index).getTriggerName(),
									getScheduledReminders().get(index).getTriggerGroup())).setCronExpression(getScheduledReminders().get(index).getCronExpression());
							sched.rescheduleJob(getScheduledReminders().get(index).getJobName()
									, getScheduledReminders().get(index).getJobGroup(), getScheduledReminders().get(index).getTrigger());
							}catch (Exception e1) {
								//System.out.println("Updating trigger throws Exception...");
								//System.out.println(e1.getMessage());
								LoggerUtil.logIt("Updating trigger throws Exception..."+ExceptionUtil.getStackTrace(e1));
							}
							finally{
								sc.closeSession();
							}
						}
						index++;
				 }
			 }
		 }
*/		 
		//find in quartz triggers by trigger group ='reminder' for 
		 
		 for (String s : sched.getJobNames("reminder")) {//quartz database, sched.getJobNames("reminder") returns all reminders in quartz db
			// System.out.println(s+" THis is the string ");
			boolean found=false;
			
			for (ScheduledJob job : getScheduledReminders()) {//this returns all the patients reminders
				if(s.equalsIgnoreCase(job.getJobName())){
					//System.out.println(s.equalsIgnoreCase(job.getJobName())+""+job.getJobName()+job.getJobGroup());
					found=true;
					break;
				}
			}
				if(!found){
					try{
						sched.deleteJob(s, "reminder");
						
						
					}catch (Exception e) {
						LoggerUtil.logIt("Error deleting job from schedular...(JobName,JobGp):("+s+","+"reminder"+")");
						LoggerUtil.logIt(ExceptionUtil.getStackTrace(e));	
					}
				}
		 }
	}

	
	public static void rescheduleReminders() throws ParseException, SchedulerException{
		scheduleReminders();
	}
	
	public static void instantiateEmailer(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("/home/rtbc/Tajikistan_SMS_System/emailer.properties");	 
			// load a properties file
			prop.load(input);
			EmailEngine.instantiateEmailEngine(prop, "ird.tajikistan@gmail.com");
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		}catch (EmailException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
