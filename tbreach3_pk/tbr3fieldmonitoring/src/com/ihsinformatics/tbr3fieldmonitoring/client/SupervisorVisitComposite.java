/**
 * Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
 * You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html
 * Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors.
 * Contributors: Tahira Niazi
*/
package com.ihsinformatics.tbr3fieldmonitoring.client;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.summatech.gwt.client.HourMinutePicker;
import com.summatech.gwt.client.HourMinutePicker.PickerFormat;

/**
 * @author Tahira
 *
 */
public class SupervisorVisitComposite extends Composite implements ClickHandler
{
	private FlexTable mainFlexTable = new FlexTable();
	private FlexTable userProfileFlexTable = new FlexTable();
	private FlexTable headerFlexTable = new FlexTable();
	private FlexTable supervisorVisitFlexTable = new FlexTable();
	
	private FlowPanel userNamePanel = new FlowPanel();
	
	private Label loginAsLabel = new Label("Login As:");
	private Label usernameLabel = new Label("user");
	private Label leftBraceLabel = new Label("(");
	private Label rightBraceLabel = new Label(")");
	private Hyperlink logoutHyperlink = new Hyperlink("Logout,", "");
	private Hyperlink mainMenuHyperlink = new Hyperlink("Back to Menu", "");
	
	private Label formHeadingLabel = new Label("SUPERVISOR VISIT FORM");
	
	private Label formDateLabel = new Label("Form Date   ");
	private DateBox formDateBox = new DateBox();
	
	private Label locationIDLabel = new Label("Location ID");
	private ListBox locationIDListBox = new ListBox();
	
	private TextBox locationNameTextBox = new TextBox();	
	private Label locationNameLabel = new Label("Location Name   ");
	
	private Label visitDateLabel = new Label("Visit Date");
	private DateBox visitDateBox = new DateBox();
	
	private Label visitTimeLabel = new Label("Visit Time");
	private HourMinutePicker visitHourMinutePicker = new HourMinutePicker(PickerFormat._12_HOUR);

	private Label townLabel = new Label("Town");
	private ListBox townListBox = new ListBox();	
	
	private Label gpPotentialLabel = new Label("GP Potential");
	private ListBox gpPotentialListBox = new ListBox();
	
	private Label mrLastVisitLabel = new Label("MR Last Visit");
	private DateBox mrLastVisitDateBox = new DateBox();
	
	private Label relationshipLabel = new Label("Relationship of MR/GP");
	private TextArea relationshipTextArea = new TextArea();
	
	private Button submitButton = new Button("Submit");
	
	MainMenuComposite mainMenu = new MainMenuComposite();
	
	
	public SupervisorVisitComposite()
	{
initWidget(mainFlexTable);
		
	    //mainFlexTable.setStyleName("verticalPanel");
	    mainFlexTable.setSize("100%", "100%");
	    
	    userNamePanel.add(loginAsLabel);
	    userNamePanel.add(usernameLabel);
	    userNamePanel.add(leftBraceLabel);
	    userNamePanel.add(logoutHyperlink);
	    userNamePanel.add(mainMenuHyperlink);

	    logoutHyperlink.addStyleName("hyperlink");
	    mainMenuHyperlink.addStyleName("hyperlink");
	    
	    userNamePanel.add(rightBraceLabel);
	    loginAsLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    usernameLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    logoutHyperlink.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    mainMenuHyperlink.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    leftBraceLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    rightBraceLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
	    
	    userNamePanel.addStyleName("flowPanel");
	    
	    userProfileFlexTable.setWidget(0, 0, userNamePanel);
	    userProfileFlexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
	    
	    
		headerFlexTable.setWidget(0, 1, formHeadingLabel);
		headerFlexTable.getRowFormatter().addStyleName(0, "Tbr3Header");
		headerFlexTable.setSize("100%", "");
		
		
//		loginFlexTable.setBorderWidth(2);
		supervisorVisitFlexTable.setWidget(1, 0, formDateLabel);
		formDateLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(1, 1, formDateBox);
		formDateBox.setFormat (new DefaultFormat (DateTimeFormat.getFormat ("yyyy-MM-dd")));
		
		supervisorVisitFlexTable.setWidget(2, 0, locationIDLabel);
		locationIDLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(2, 1, locationIDListBox);
		
		supervisorVisitFlexTable.setWidget(3, 0, locationNameLabel);
		locationNameLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(3, 1, locationNameTextBox);
		locationNameTextBox.addStyleName("textbox");
		
		
		supervisorVisitFlexTable.setWidget(4, 0, townLabel );
		townLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(4, 1, townListBox);
		
		supervisorVisitFlexTable.setWidget(5, 0, visitDateLabel);
		visitDateLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(5, 1, visitDateBox);
		visitDateBox.addStyleName("textbox");
		
		supervisorVisitFlexTable.setWidget(6, 0, visitTimeLabel);
		visitTimeLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(6, 1, visitHourMinutePicker);
		
		supervisorVisitFlexTable.setWidget(7, 0, gpPotentialLabel);
		gpPotentialLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(7, 1, gpPotentialListBox);
		
		supervisorVisitFlexTable.setWidget(8, 0, mrLastVisitLabel);
		mrLastVisitLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(8, 1, mrLastVisitDateBox);
		
		supervisorVisitFlexTable.setWidget(9, 0, relationshipLabel);
		relationshipLabel.addStyleName("text");
		
		supervisorVisitFlexTable.setWidget(9, 1, relationshipTextArea);
		relationshipTextArea.addStyleName("textbox");
		
		
		supervisorVisitFlexTable.setWidget(10, 1, submitButton);
		submitButton.setStyleName("submitButton");
		submitButton.setSize("169", "30");
		
		//loginFlexTable.setStyleName("flexTableCell");

		mainFlexTable.setWidget(0, 0, userProfileFlexTable);
		mainFlexTable.setWidget(1, 0, headerFlexTable);
		mainFlexTable.setWidget(2, 0, supervisorVisitFlexTable);
		
		
		supervisorVisitFlexTable.setSize("100%", "");
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(8, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(10, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(11, 1, HasHorizontalAlignment.ALIGN_LEFT);
		supervisorVisitFlexTable.getCellFormatter().setHorizontalAlignment(12, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		

		mainFlexTable.setBorderWidth(1);
		
//		mainFlexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
//		mainFlexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event)
	{
		Widget sender = (Widget) event.getSource();
		if(sender == submitButton)
		{
//			Tbr3fieldmonitoring.verticalPanel.clear();
//			Tbr3fieldmonitoring.verticalPanel.add(firstVisit);
		}
		else if(sender == mainMenuHyperlink)
		{
			Tbr3fieldmonitoring.verticalPanel.clear();
			Tbr3fieldmonitoring.verticalPanel.add(mainMenu);
		}
	}
}
