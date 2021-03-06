/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.utils;

import java.util.Random;

import org.quartz.Scheduler;

//import org.smslib.smsserver.SMSServer;

public class Utils {
	
	private static Random rnd=new Random();

	public static int getRandomNumber(int range) {
		int num=rnd.nextInt(range);
		return num+1;
	}
	
	public static boolean isNumberBetween(String number,int min,int max){
		try{
			int num=Integer.parseInt(number);
			
			if(num<min || num>max){
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	/*public static String getSMSServerInfo(SMSServer server){
		String infoString="\n-------------------------------SMS SERVER INFO---------------------------";

		infoString+="\nModem/gateway Information:";

		try
		{
			infoString += "\nSignal Level: "
				+ ((SerialModemGateway) server.getService().getGateway(
							"modem1")).getSignalLevel() + "%";
		}
		catch (Exception e)
		{
			infoString += e.getMessage();
		}
		try
		{
			infoString += "\nBattery Level: "
					+ ((SerialModemGateway) server.getService().getGateway(
							"modem1")).getBatteryLevel() + "%";
		}
		catch (Exception e)
		{
			infoString += e.getMessage();
		}
		try
		{
			infoString += "\nGateway Status: "
					+ ((SerialModemGateway) server.getService().getGateway(
							"modem1")).getStatus().name();
		}
		catch (Exception e) 
		{
			infoString+=e.getMessage();
		} 

		infoString+="\nRestart count :"+((SerialModemGateway)server.getService().getGateway("modem1")).getRestartCount();
		infoString+="\nInbound msg count :"+server.getService().getInboundMessageCount();
		infoString+="\nOutbound msg count :"+server.getService().getOutboundMessageCount();
		infoString+="\nService Status :"+server.getService().getServiceStatus().name();
		infoString+="\n------------------------------------------------------------------------------";
		
		return infoString;
	}*/
	public static String getJVMInfo(){
		String infoString="\n----------------------------------JVM INFO------------------------------------";
		final long  MEGABYTE = 1024L * 1024L;

		infoString += "\nMEMEORY INFO IN MBs:" + "\nAVAILABLE MEMORY  : "
				+ (Runtime.getRuntime().freeMemory() / MEGABYTE)
				+ "\nTOTAL MEMORY   : "
				+ (Runtime.getRuntime().totalMemory() / MEGABYTE)
				+ "\nMAXIMUM MEMORY : "
				+ (Runtime.getRuntime().maxMemory() / MEGABYTE);

		infoString+="\n**THREAD GROUP**";
		
		ThreadGroup ti = ThreadUtilities.getRootThreadGroup();
		
		infoString+="\n	active count: "+ti.activeCount();
		infoString+="\n	active group count: "+ti.activeGroupCount();
		/*infoString+="\n**THREAD INFOS**";
			
		ThreadInfo[] t2 = ThreadUtilities.getAllThreadInfos();
		for (ThreadInfo thread : t2) {
			infoString+="\nthread--------";
			infoString+="\n	thread name:"+thread.getThreadName();
			infoString+=",	lock name:"+thread.getLockName();
			infoString+="\n	lock owner:"+thread.getLockOwnerName();
			infoString+="\n	blocked count:"+thread.getBlockedCount();
			infoString+=",	blocked time (sec):"+(thread.getBlockedTime()/(1000));
			infoString+="\n	waited count:"+thread.getWaitedCount();
			infoString+=",	waited time:"+(thread.getWaitedTime()/(1000));
			infoString+="\n	thread state:"+thread.getThreadState().toString();
			infoString+="\n:: Stack traces :: ";
			for (StackTraceElement st : thread.getStackTrace()) {
				infoString+="\nclass name:"+st.getClassName();
				infoString+=" : file name:"+st.getFileName();
				infoString+=" : method name:"+st.getMethodName();
			}
		}*/
		infoString+="\n----------------------------------------------------------------------------";
		
		return infoString;
	}
	public static StringBuilder getSchedulerJobDetailedDesc(Scheduler sch) {
		StringBuilder sb=new StringBuilder();
		sb.append("\n*********************SCHEDULER DETAILED DESCRIPTION*******************\n");
		try{
			for (String gp : sch.getTriggerGroupNames()) {
				for (String tnm : sch.getTriggerNames(gp)) {
					sb.append("\n--Trigger name : "+sch.getTrigger(tnm, gp).getFullName());
					sb.append("\n--Job name : "+sch.getTrigger(tnm, gp).getFullJobName());
					sb.append("\n--Prev Fire Time : "+sch.getTrigger(tnm, gp).getPreviousFireTime());
					sb.append("\n--Next Fire Time : "+sch.getTrigger(tnm, gp).getNextFireTime());
				}
			}
		}catch (Exception e) {
			sb.append("An error occurred while reading scheduler metadata. Error message is :"+e.getMessage());
		}
		return sb;
	}
}
