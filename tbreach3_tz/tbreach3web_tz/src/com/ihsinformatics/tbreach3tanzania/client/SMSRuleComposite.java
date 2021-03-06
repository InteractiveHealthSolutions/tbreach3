/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.client;

import java.util.Date;
import java.util.Iterator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.SmsRules;
import com.ihsinformatics.tbreach3tanzania.shared.model.User;

public class SMSRuleComposite extends Composite implements IForm, ClickHandler
{
	private static ServerServiceAsync	service				= GWT.create (ServerService.class);
	private static LoadingWidget		loading				= new LoadingWidget ();
	private static final String			menuName			= "SMS";

	private UserRightsUtil				rights				= new UserRightsUtil ();

	private FlexTable					flexTable										= new FlexTable ();
	private FlexTable					topFlexTable									= new FlexTable ();
	private FlexTable					rightFlexTable									= new FlexTable ();
	private FlexTable					bottomFlexTable									= new FlexTable ();
	
	
	private Grid						grid											= new Grid (1, 3);
	private Grid						districtGrid									= new Grid (2, 10);
	
	private Button						saveButton			= new Button ("Save");
	private Button						closeButton			= new Button ("Close");

	private Label						lblTbReachSms		= new Label (TBRT.getProjectTitle () + " Sms Rules Setup");
	
	private CheckBox					tbSputumResultsAlertToClientCheckBox					= new CheckBox ("Enable alert on GeneXpert Results to Client");
	private CheckBox					tbSputumResultsAlertToRegionalCordinatorCheckBox		= new CheckBox ("Enable alert on GeneXpert Results to  Regional TB & Leprosy Coordinator");
	private	CheckBox					tbSputumResultsAlertToMECordinatorCheckBox				= new CheckBox ("Enable alert on GeneXpert Results to M & E Coordinator");
	private	CheckBox					tbSputumResultsAlertToDistrictCordinatorCheckBox		= new CheckBox ("Enable alert on GeneXpert Results to District Coordinators");
	
	public SMSRuleComposite ()
	{
		initWidget (flexTable);
		flexTable.setSize ("90%", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		lblTbReachSms.setStyleName ("title");
		topFlexTable.setWidget (0, 0, lblTbReachSms);
		flexTable.setWidget (1, 0, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
			
		tbSputumResultsAlertToClientCheckBox.setWordWrap (false);
		rightFlexTable.setWidget (4, 0, tbSputumResultsAlertToClientCheckBox);
		tbSputumResultsAlertToRegionalCordinatorCheckBox.setWordWrap (false);
		rightFlexTable.setWidget (5, 0, tbSputumResultsAlertToRegionalCordinatorCheckBox);
		tbSputumResultsAlertToMECordinatorCheckBox.setWordWrap (false);
		rightFlexTable.setWidget (6, 0, tbSputumResultsAlertToMECordinatorCheckBox);
		tbSputumResultsAlertToDistrictCordinatorCheckBox.setWordWrap (false);
		rightFlexTable.setWidget (7, 0, tbSputumResultsAlertToDistrictCordinatorCheckBox);
		rightFlexTable.setWidget (8, 0, districtGrid);
		
		tbSputumResultsAlertToClientCheckBox.setName ("1");	 
		tbSputumResultsAlertToRegionalCordinatorCheckBox.setName ("2");
		tbSputumResultsAlertToMECordinatorCheckBox.setName ("3");
		tbSputumResultsAlertToDistrictCordinatorCheckBox.setName ("4");
			
		rightFlexTable.setWidget (12, 0 , bottomFlexTable);
		bottomFlexTable.setWidget (3, 20, grid);
		grid.setSize ("100%", "100%");
		grid.setWidget (0, 0, saveButton);
		grid.setWidget (0, 1, closeButton);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (0, 0, HasVerticalAlignment.ALIGN_TOP);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (2, 0, HasVerticalAlignment.ALIGN_TOP);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getRowFormatter ().setVerticalAlign (1, HasVerticalAlignment.ALIGN_TOP);


		saveButton.addClickHandler (this);
		closeButton.addClickHandler (this);
		
		setCurrent();
		refreshList ();
		setRights (menuName);
	}

	
	
	
	public void refreshList ()
	{
		// Not implemented
	}

	/**
	 * Display/Hide main panel and loading widget
	 * 
	 * @param status
	 */
	public void load (boolean status)
	{
		flexTable.setVisible (!status);
		if (status)
			loading.show ();
		else
			loading.hide ();
	}

	public void clearControls (Widget w)
	{
		if (w instanceof Panel)
		{
			Iterator<Widget> iter = ((Panel) w).iterator ();
			while (iter.hasNext ())
				clearControls (iter.next ());
		}
		else if (w instanceof TextBoxBase)
		{
			((TextBoxBase) w).setText ("");
		}
		else if (w instanceof RichTextArea)
		{
			((RichTextArea) w).setText ("");
		}
		else if (w instanceof ListBox)
		{
			((ListBox) w).setSelectedIndex (0);
		}
		else if (w instanceof DatePicker)
		{
			((DatePicker) w).setValue (new Date ());
		}
	}
	
	public void setCurrent()
	{
		
			try
			{
				service.findSmsRule ("1",  new AsyncCallback<SmsRules> ()
				{

					@Override
					public void onFailure (Throwable caught)
					{
						
						
					}

					@Override
					public void onSuccess (SmsRules result)
					{
						tbSputumResultsAlertToClientCheckBox.setValue (result.getEnable ());
						
					}
					
				});
			
	
				service.findSmsRule ("2",  new AsyncCallback<SmsRules> ()
				{

					@Override
					public void onFailure (Throwable caught)
					{
						
						
					}

					@Override
					public void onSuccess (SmsRules result)
					{
						tbSputumResultsAlertToRegionalCordinatorCheckBox.setValue (result.getEnable ());
					}
					
				});
				
				service.findSmsRule ("3",  new AsyncCallback<SmsRules> ()
				{

						@Override
						public void onFailure (Throwable caught)
						{
								
								
						}

						@Override
						public void onSuccess (SmsRules result)
						{
							tbSputumResultsAlertToMECordinatorCheckBox.setValue (result.getEnable ());
						}
							
				});
				
				service.findSmsRule ("4",  new AsyncCallback<SmsRules> ()
				{

						@Override
						public void onFailure (Throwable caught)
						{
								
								
						}

						@Override
						public void onSuccess (SmsRules result)
						{
							tbSputumResultsAlertToDistrictCordinatorCheckBox.setValue (result.getEnable ());
						}
							
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			} 		
	}

	
	

	public void clearUp ()
	{
		clearControls (flexTable);
	}



	public void updateData ()
	{
		// Not implemented
	}

	public void deleteData ()
	{
		// Not implemented
	}

	public void setRights (String menuName)
	{
		try
		{
			load (true);
			service.getUserRgihts (TBRT.getCurrentUserName (), TBRT.getCurrentRole (), menuName, new AsyncCallback<Boolean[]> ()
			{

				public void onSuccess (Boolean[] result)
				{
					final Boolean[] userRights = result;
					try
					{
						service.findUser (TBRT.getCurrentUserName (), new AsyncCallback<User> ()
						{

							public void onSuccess (User result)
							{
								rights.setRoleRights (TBRT.getCurrentRole (), userRights);
								saveButton.setEnabled (rights.getAccess (AccessType.UPDATE));
								load (false);
							}

							public void onFailure (Throwable caught)
							{
								load (false);
							}
						});
					}
					catch (Exception e)
					{
						e.printStackTrace ();
						load (false);
					}
				}

				public void onFailure (Throwable caught)
				{
					load (false);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			load (false);
		}
	}

	public void onClick (ClickEvent event)
	{
		Widget sender = (Widget) event.getSource ();
		
		if (sender == saveButton)
		{
			load (true);
			saveData ();
			load(false);
		}
		if (sender == closeButton)
		{
			load(false);
		}	
		
	}




	@Override
	public boolean validate ()
	{
	 
		return true;
	}


	@Override
	public void saveData ()
	{

		if(validate()){
		
			SmsRules[] rules = new SmsRules[4];
		
			rules[0] = new SmsRules();
			rules[0].setSmsRuleId (Integer.parseInt (tbSputumResultsAlertToClientCheckBox.getName ()));
			rules[0].setDescription (tbSputumResultsAlertToClientCheckBox.getText ());
			rules[0].setEnable (tbSputumResultsAlertToClientCheckBox.getValue ());

			rules[1] = new SmsRules();
			rules[1].setSmsRuleId (Integer.parseInt (tbSputumResultsAlertToRegionalCordinatorCheckBox.getName ()));
			rules[1].setDescription (tbSputumResultsAlertToRegionalCordinatorCheckBox.getText ());
			rules[1].setEnable (tbSputumResultsAlertToRegionalCordinatorCheckBox.getValue ());
			
			rules[2] = new SmsRules();
			rules[2].setSmsRuleId (Integer.parseInt (tbSputumResultsAlertToMECordinatorCheckBox.getName ()));
			rules[2].setDescription (tbSputumResultsAlertToMECordinatorCheckBox.getText ());
			rules[2].setEnable (tbSputumResultsAlertToMECordinatorCheckBox.getValue ());

			rules[3] = new SmsRules();
			rules[3].setSmsRuleId (Integer.parseInt (tbSputumResultsAlertToDistrictCordinatorCheckBox.getName ()));
			rules[3].setDescription (tbSputumResultsAlertToDistrictCordinatorCheckBox.getText ());
			rules[3].setEnable (tbSputumResultsAlertToDistrictCordinatorCheckBox.getValue ());	
		
			try
			{
				service.updateSmsRule (rules, new AsyncCallback<Boolean> ()
				{

					@Override
					public void onFailure (Throwable caught)
					{
						load(false);				
					}

					@Override
					public void onSuccess (Boolean result)
					{				
						Window.alert ("SMS Rules updated Successfully!.");
					}
				
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		Window.alert ("Please Enter Valid Recipient's Mobile Number.");
	}




	@Override
	public void fillData ()
	{
		
	}	
	
	
}

	
	
