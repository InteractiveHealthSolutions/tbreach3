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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

/**
 * @author Tahira
 * 
 */
public class TestEntryComposite extends Composite implements ClickHandler
{

	private FlexTable mainFlexTable = new FlexTable();
	private FlexTable userProfileFlexTable = new FlexTable();
	private FlexTable headerFlexTable = new FlexTable();
	private FlexTable dailyVisitFlexTable = new FlexTable();

	private FlowPanel userNamePanel = new FlowPanel();

	private Label loginAsLabel = new Label("Login As:");
	private Label usernameLabel = new Label("user");
	private Label leftBraceLabel = new Label("(");
	private Label rightBraceLabel = new Label(")");
	private Hyperlink logoutHyperlink = new Hyperlink("Logout,", "");
	private Hyperlink mainMenuHyperlink = new Hyperlink("Back to Menu", "");

	private Label formHeadingLabel = new Label("DAILY VISIT FORM");

	private Label formDateLabel = new Label("Form Date   ");
	private DateBox formDateBox = new DateBox();

	private Label locationIDLabel = new Label("Location ID");
	private ListBox locationIDListBox = new ListBox();

	private TextBox locationNameTextBox = new TextBox();
	private Label locationNameLabel = new Label("Location Name   ");

	private Label patientAgeLabel = new Label("Patient Age");
	private TextBox patientAgeTextBox = new TextBox();

	private Label patientGenderLabel = new Label("Patient Gender");
	private FlowPanel patientGenderFlowPanel = new FlowPanel();
	private RadioButton genderMaleRadioButton = new RadioButton(
			"genderRadioGroup", "Male");
	private RadioButton genderFemaleRadioButton = new RadioButton(
			"genderRadioGroup", "Female");

	private Label testTypeLabel = new Label("Test Type");
	private FlowPanel testTypeFlowPanel = new FlowPanel();
	private RadioButton testDiabetesRadioButton = new RadioButton(
			"testTypeRadioGroup", "Diabetes");
	private RadioButton testSpirometryRadioButton = new RadioButton(
			"testTypeRadioGroup", "Spirometry");

	private Button submitButton = new Button("Submit");

	MainMenuComposite mainMenu = new MainMenuComposite();

	public TestEntryComposite()
	{
		initWidget(mainFlexTable);
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
		logoutHyperlink.getElement().getStyle()
				.setDisplay(Display.INLINE_BLOCK);
		mainMenuHyperlink.getElement().getStyle()
				.setDisplay(Display.INLINE_BLOCK);
		leftBraceLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		rightBraceLabel.getElement().getStyle()
				.setDisplay(Display.INLINE_BLOCK);

		userNamePanel.addStyleName("flowPanel");

		userProfileFlexTable.setWidget(0, 0, userNamePanel);
		userProfileFlexTable.getCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);

		headerFlexTable.setWidget(0, 1, formHeadingLabel);
		headerFlexTable.getRowFormatter().addStyleName(0, "Tbr3Header");
		headerFlexTable.setSize("100%", "");

		// loginFlexTable.setBorderWidth(2);
		dailyVisitFlexTable.setWidget(1, 0, formDateLabel);
		formDateLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(1, 1, formDateBox);
		formDateBox.setFormat(new DefaultFormat(DateTimeFormat
				.getFormat("yyyy-MM-dd")));

		dailyVisitFlexTable.setWidget(2, 0, locationIDLabel);
		locationIDLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(2, 1, locationIDListBox);

		dailyVisitFlexTable.setWidget(3, 0, locationNameLabel);
		locationNameLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(3, 1, locationNameTextBox);
		locationNameTextBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(4, 0, patientAgeLabel);
		patientAgeLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(4, 1, patientAgeTextBox);
		patientAgeTextBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(5, 0, patientGenderLabel);
		patientGenderLabel.addStyleName("text");

		patientGenderFlowPanel.add(genderMaleRadioButton);
		patientGenderFlowPanel.add(genderFemaleRadioButton);

		dailyVisitFlexTable.setWidget(5, 1, patientGenderFlowPanel);

		dailyVisitFlexTable.setWidget(6, 0, testTypeLabel);
		testTypeLabel.addStyleName("text");

		testTypeFlowPanel.add(testDiabetesRadioButton);
		testTypeFlowPanel.add(testSpirometryRadioButton);

		dailyVisitFlexTable.setWidget(6, 1, testTypeFlowPanel);

		dailyVisitFlexTable.setWidget(7, 1, submitButton);
		submitButton.setStyleName("submitButton");
		submitButton.setSize("169", "30");

		// loginFlexTable.setStyleName("flexTableCell");

		mainFlexTable.setWidget(0, 0, userProfileFlexTable);
		mainFlexTable.setWidget(1, 0, headerFlexTable);
		mainFlexTable.setWidget(2, 0, dailyVisitFlexTable);

		dailyVisitFlexTable.setSize("80%", "");
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(2, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(3, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(4, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(5, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(6, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(7, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(8, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(9, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(10, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(11, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(12, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(13, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		dailyVisitFlexTable.getCellFormatter().setHorizontalAlignment(14, 1,
				HasHorizontalAlignment.ALIGN_LEFT);

		mainFlexTable.setBorderWidth(1);

		// mainMenuHyperlink.addClickHandler(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event
	 * .dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event)
	{
		Widget sender = (Widget) event.getSource();
		if (sender == submitButton)
		{
			// Tbr3fieldmonitoring.verticalPanel.clear();
			// Tbr3fieldmonitoring.verticalPanel.add(firstVisit);
		}
		// else if(sender == mainMenuHyperlink)
		// {
		// Tbr3fieldmonitoring.verticalPanel.clear();
		// Tbr3fieldmonitoring.verticalPanel.add(mainMenu);
		// }

	}

}
