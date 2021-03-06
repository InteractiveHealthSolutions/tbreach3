/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Hashtable;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import com.ihsinformatics.tbreach3tanzania.mobile.model.MessageEntry;
import com.ihsinformatics.tbreach3tanzania.mobile.model.Settings;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.HttpSender;

public class TBReach3TanzaniaMain extends MIDlet
{
	private HttpSender			httpSender;

	private Display				display;
	private Settings			settings;
	private Metadata			metadata;
	private Command				exit;

	public MainList				mainList;
	public LoginForm			loginForm;
    public ReportList			reportList;
    public RegistrationForm		registrationForm;
	public ScreeningForm		screeningForm;
//	public SmearResultsForm		smearResultsForm;
	public TreatmentForm		treatmentForm;
	public DiagnosisForm		diagnosisForm;
//	public SitesDataForm		sitesDataForm;
//	public TreatmentReviewForm	treatmentReviewForm;
//	public LabReviewForm		labReviewForm;
//	public ComparisonReviewForm	comparisonReviewForm;
//	public FeedbackForm			feedbackForm;
//	public UndoForm				undoForm;
	

	public TBReach3TanzaniaMain ()
	{
		this.display = Display.getDisplay (this);
		mainList = new MainList ("Main Menu", this);
		reportList = new ReportList ("Report List", this);
		loginForm = new LoginForm ("Login", this);
		registrationForm = new RegistrationForm ("Registration", this);
		screeningForm = new ScreeningForm ("Initial Screening Form", this);
		//smearResultsForm = new SmearResultsForm ("Smear Results Form", this);
		diagnosisForm = new DiagnosisForm ("Diagnosis Form", this);
		treatmentForm = new TreatmentForm ("Treatment Form", this);
		//sitesDataForm = new SitesDataForm ("AMPATH Sites Data", this);
		//treatmentReviewForm = new TreatmentReviewForm ("FC-Treatment Review Form", this);
		//labReviewForm = new LabReviewForm ("FC-Lab Review Form", this);
		//comparisonReviewForm = new ComparisonReviewForm ("FC-Comparison Review Form", this);
		//feedbackForm = new FeedbackForm ("Feedback submission", this);
		//undoForm = new UndoForm ("Undo Last Form", this);

		settings = new Settings ();
		metadata = new Metadata ();
		httpSender = new HttpSender ();
		
	}

	/*
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp () throws MIDletStateChangeException
	{
		
		this.exit = new Command ("Exit", Command.EXIT, 0x01);
		System.out.println ("Starting...");
		Displayable main = this.getMainScreen ();
		main.addCommand (this.exit);

		if (display == null)
			display = Display.getDisplay (this);
		
		loginForm.init ();
		setDisplay (loginForm);
		
	}

	/**
	 * Gets the application main screen.
	 * 
	 * @return main screen.
	 */
	private Displayable getMainScreen ()
	{
		return new Canvas ()
		{
			protected void paint (Graphics g)
			{
			}
		};
	}

	/*
	 * @see
	 * javax.microedition.lcdui.CommandListener#commandAction(javax.microedition
	 * .lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction (Command command, Displayable displayable)
	{
		if (command == this.exit)
		{
			try
			{
				this.destroyApp (true);
			}
			catch (MIDletStateChangeException e)
			{
				e.printStackTrace ();
			}
			finally
			{
				this.notifyDestroyed ();
			}
		}
	}

	/*
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp (boolean arg0) throws MIDletStateChangeException
	{
		System.out.println ("Entering the app");
		mainList = null;
		loginForm = null;
		System.out.println ("Leaving the app");
	}

	/*
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp ()
	{
	}

	public Hashtable sendToServer (String payload)
	{
		payload += "&appver=" + TBRT.VERSION;
		try
		{
			System.out.println ("POSTing");
			MessageEntry messageEntry = new MessageEntry (TBRT.BASE_URL, payload, true, MessageEntry.REQUEST_METHOD_HTTP_POST, false);
			System.out.println (messageEntry.toString ());
			httpSender.doPost (TBRT.BASE_URL, messageEntry);
		}
		catch (Exception e)
		{   
			System.out.println ("ERROR!");
			e.printStackTrace ();
			return null;
		}
		System.out.println (httpSender.model.toString ());
		return httpSender.model;
	}

	public void showGauge (String msg, String formDescription)
	{
		Gauge gauge = new Gauge (null, false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);
		Form frm;
		frm = new Form (null);
		frm.append (msg);
		frm.append (gauge);
		frm.setTicker (new Ticker (".. WAIT .."));
		setDisplay (frm);
	}

	public void showTimedAlert (String msg, String imgName, int timeout)
	{
		this.display.vibrate (1000);
		Alert alert = new javax.microedition.lcdui.Alert ("ALERT!");
		alert.setString (msg);
		alert.setTimeout (timeout);
		setDisplay (alert);
	}

	public void showAlert (String msg, String imgName)
	{
		this.display.vibrate (1000);
		showTimedAlert (msg, imgName, Alert.FOREVER);
	}

	public void startMainMenu ()
	{
		System.out.println ("User name:" + TBRT.getCurrentUserName () + ", Role: " + TBRT.getCurrentUserRole ());
		mainList.init ();
		setDisplay (mainList);
	}

	public void startBaseForm (BaseForm form)
	{
		form.init ();
		setDisplay (form);
	}

	public void setDisplay (Displayable displayable)
	{
		display.setCurrent (displayable);
	}

	public Display getDisplay ()
	{
		return display;
	}

	public Settings getSettings ()
	{
		return settings;
	}

	public Metadata getMetadata ()
	{
		return metadata;
	}

	public void setMetadata (Metadata metadata)
	{
		this.metadata = metadata;
	}
}
