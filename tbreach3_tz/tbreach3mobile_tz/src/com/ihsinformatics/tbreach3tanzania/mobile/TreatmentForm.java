/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Treatment Form for confirmed Patients for Cough Monitors 
 */

package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Date;
import java.util.Hashtable;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.ErrorProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.InfoProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class TreatmentForm extends BaseForm implements CommandListener, ItemStateListener
{
	String		formType	= FormType.TREATMENT;
	// Mobile fields
	TextField	patientScreenId;
	DateField	dateEntered;
	TextField	districtTbTreatmentNumber;
	TextField	nameTreatmentCenter;
	DateField	dateTreatmentStarted;
	DateField	dateTreatmentCompleted;
	ChoiceGroup	treatmentOutcome;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public TreatmentForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		patientScreenId = new TextField ("*Patient Screening ID Number: ", "", TBRT.ID_LENGTH, TextField.ANY);
		dateEntered = new DateField ("*TB Diagnosis Date: ", DateField.DATE);
		dateEntered.setDate (new Date ());
		districtTbTreatmentNumber = new TextField ("*District TB Treatment Number: ", "", 7, TextField.NUMERIC);
		nameTreatmentCenter = new TextField ("*Name of Treatment Center (DOT): ",  "", TBRT.ID_LENGTH, TextField.ANY);
		dateTreatmentStarted = new DateField ("*Date TB Treatment Started: ", DateField.DATE);
		dateTreatmentStarted.setDate (new Date ());
		dateTreatmentCompleted = new DateField ("Date TB Treatment Completed: ", DateField.DATE);
		dateTreatmentCompleted.setDate (new Date ());
		treatmentOutcome = new ChoiceGroup ("Result of TB treatment: ", ChoiceGroup.POPUP);


		treatmentOutcome.append ("Cured", null);
		treatmentOutcome.append ("Treatment Completed", null);
		treatmentOutcome.append ("Treatment Failure", null);
		treatmentOutcome.append ("Died", null);
		treatmentOutcome.append ("Other", null);

		formItems = new Item[] {dateEntered, patientScreenId, districtTbTreatmentNumber, nameTreatmentCenter, dateTreatmentStarted, dateTreatmentCompleted, treatmentOutcome};
		startTimestamp = new Date ();
		initialShow ();
		// Add commands
		addCommand (okCommand);
		addCommand (backCommand);
		// Add listeners
		setCommandListener (this);
		setItemStateListener (this);
	}

	/**
	 * Shows/Hides form items initially
	 */
	private void initialShow ()
	{
		deleteAll ();
		// Add Items to form
		for (int i = 0; i < formItems.length; i++)
			append (formItems[i]);
	}

	/**
	 * Validation method before submitting form
	 * 
	 * @return
	 */

	private boolean validate ()
	{
		
		StringBuffer sb = new StringBuffer ();

		// Date should not be of future
		if (DateTimeUtil.isDateInFuture (dateEntered.getDate ()))
			sb.append ("TB Diagnosis Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		if (DateTimeUtil.isDateInFuture (dateTreatmentStarted.getDate ()))
			sb.append ("Date TB Treatment Started: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		
		
		if (DateTimeUtil.isDateInFuture (dateTreatmentCompleted.getDate ()))
			sb.append ("Date TB Treatment Ended: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		
		// Treatment Started and Completion Date validation
		if(DateTimeUtil.isDateAfter (dateTreatmentStarted.getDate (),dateTreatmentCompleted.getDate ()))
			sb.append ("Inavlid Date: 'Treatment Started/Completed'" + "\n");
		else if(dateTreatmentStarted.getDate ().equals (dateTreatmentCompleted.getDate ()))
			sb.append ("Inavlid Date: 'Treatment Started/Completed'" + "\n");
		
	
		
		// Check for empty fields
		if (StringUtil.getString (districtTbTreatmentNumber).equals (""))
			sb.append ("District TB Treatment Number: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		else if (StringUtil.getString (districtTbTreatmentNumber).length () != districtTbTreatmentNumber.getMaxSize ())
			sb.append ("District TB Treatment Number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		if (StringUtil.getString (nameTreatmentCenter).equals (""))
			sb.append ("Treatment Center Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		
		if (sb.length () != 0)
		{
			tbreach3tanzaniaMidlet.showAlert (sb.toString (), null);
			return false;
		}
		return true;
	}

	/**
	 * Item change Listener method
	 */
	public void itemStateChanged (Item i)
	{
		// no need?
		if (i instanceof TextField)
			return;
	}

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{
			if (validate ())
			{
				String request = createRequestPayload ();
				System.out.println (request);
				Hashtable model = tbreach3tanzaniaMidlet.sendToServer (request);

				if (model != null)
				{
					String status = (String) model.get ("status");
					if (status.equals (TBRT.XML_SUCCESS))
					{
						System.out.println ("success");
						tbreach3tanzaniaMidlet.showAlert (InfoProvider.getInfo (InfoProvider.OPERATION_SUCCESSFUL), null);
						TBRT.setLastFormSubmitted (formType);
						TBRT.setLastOperationTimestamp (new Date ().getTime ());
						init ();
					}
					else if (status.equals (TBRT.XML_ERROR))
					{
						System.out.println ((String) model.get ("msg"));
						tbreach3tanzaniaMidlet.showAlert ("ERROR: " + (String) model.get ("msg"), null);
					}
				}
			}
		}

		else if (command == backCommand)
		{
			deleteAll ();
			removeCommand (okCommand);
			removeCommand (backCommand);
			tbreach3tanzaniaMidlet.setDisplay (prevDisplayable);
		}
	}

	protected String createRequestPayload ()
	{
		StringBuffer request = new StringBuffer ();
		request.append ("appver=" + TBRT.VERSION);
		request.append ("&form=" + formType);
		request.append ("&startTimestamp=" + startTimestamp.getTime ());
		request.append ("&endTimestamp=" + new Date ().getTime ());
		request.append ("&userName=" + TBRT.getCurrentUserName ());
		request.append ("&dateEntered=" + dateEntered.getDate ().getTime ());
		request.append ("&patientId=" + StringUtil.getString (patientScreenId));

		request.append ("&results=[");
		request.append ("DIST_TB_TREAT_NO=" + StringUtil.getString (districtTbTreatmentNumber) + ";");
		request.append ("N_TREATMENT_CENTER=" + StringUtil.getString (nameTreatmentCenter) + ";");
		request.append ("DATE_TREAT_STARTED=" + DateTimeUtil.getDateTime (dateTreatmentStarted.getDate ()) + ";");
		request.append ("DATE_TREAT_COMPLETED=" + DateTimeUtil.getDateTime (dateTreatmentCompleted.getDate ()) + ";");
		request.append ("TREATMENT_OUTCOME=" + StringUtil.getString (treatmentOutcome));
		request.append ("]");
		return request.toString ();
	}
}
