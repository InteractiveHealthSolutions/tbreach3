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

import java.awt.Choice;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.ihsinformatics.tbr3fieldmonitoring.server.ServerServiceImpl;
import com.ihsinformatics.tbr3fieldmonitoring.shared.CustomMessage;
import com.ihsinformatics.tbr3fieldmonitoring.shared.ErrorType;
import com.ihsinformatics.tbr3fieldmonitoring.shared.RegexUtil;
import com.ihsinformatics.tbr3fieldmonitoring.shared.TBR3;
import com.summatech.gwt.client.HourMinutePicker;
import com.summatech.gwt.client.HourMinutePicker.PickerFormat;

/**
 * @author Tahira
 * 
 */
public class DailyVisitComposite extends Composite implements IForm, ClickHandler, ValueChangeHandler<Boolean>
{
	private static ServerServiceAsync service = GWT.create(ServerService.class);
	private static final String formName = "DAILY_VIS";
	
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
	private IntegerBox locationIdIntegerBox = new IntegerBox();

	private TextBox locationNameTextBox = new TextBox();
	private Label locationNameLabel = new Label("Location Name   ");

	private Label townLabel = new Label("Town");
	private ListBox townListBox = new ListBox();

	private Label visitDateLabel = new Label("Visit Date");
	private DateBox visitDateBox = new DateBox();

	private Label arrivalTimeLabel = new Label("Arrival Time");
	private HourMinutePicker arrivalHourMinutePicker = new HourMinutePicker(
			PickerFormat._12_HOUR);

	private Label departureTimeLabel = new Label("Departure Time");
	private HourMinutePicker departureHourMinutePicker = new HourMinutePicker(
			PickerFormat._12_HOUR);

	private Label metGpLabel = new Label("Met GP");
	private FlowPanel metGpFlowPanel = new FlowPanel();
	private RadioButton metGpYesRadioButton = new RadioButton(
			"metGpRadioGroup", "Yes");
	private RadioButton metGpNoRadioButton = new RadioButton("metGpRadioGroup",
			"No");

	private Label givenCouponsLabel = new Label("Given Coupons/Rx Pads");
	private FlowPanel givenCouponsFlowPanel = new FlowPanel();
	private RadioButton givenCouponsYesRadioButton = new RadioButton(
			"givenCouponsRadioGroup", "Yes");
	private RadioButton givenCouponsNoRadioButton = new RadioButton(
			"givenCouponsRadioGroup", "No");

	private Label marketingActivityLabel = new Label("Marketing Activity");
	private FlowPanel marketingActivityFlowPanel = new FlowPanel();
	private RadioButton marketingYesRadioButton = new RadioButton(
			"marketingRadioGroup", "Yes");
	private RadioButton marketingNoRadioButton = new RadioButton(
			"marketingRadioGroup", "No");

	private Label marketingDescriptionLabel = new Label(
			"Marketing Activity Description");
	private TextBox marketingDescriptionTextBox = new TextBox();

	private Label marketingBudgetItemsLabel = new Label(
			"MR Marketing Budget Items");
	private TextBox marketingBudgetItemsTextBox = new TextBox();

	private Label amountLabel = new Label("Amount");
	private IntegerBox amountIntegerBox = new IntegerBox(); // takes only
															// integer

	private Label institutionalMarketingItemsLabel = new Label(
			"Institutional Marketing Items");
	private ListBox institutionalMarketingItemsListBox = new ListBox();

	private Label countLabel = new Label("Count");
	private IntegerBox countIntegerBox = new IntegerBox();

	private Button addMoreButton = new Button("(Add More...)");

	private Label gpPotentialLabel = new Label("GP Potential");
	private ListBox gpPotentialListBox = new ListBox();

	private Label commentsLabel = new Label("Comments");
	private TextArea commentsTextArea = new TextArea();
	
	private Anchor validateLocationIdAnchor = new Anchor(
			"Validate Location ID", false);

	private Button submitButton = new Button("Submit");

	MainMenuComposite mainMenu = new MainMenuComposite();

	public DailyVisitComposite()
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

		dailyVisitFlexTable.setWidget(2, 1, locationIdIntegerBox);
		locationIdIntegerBox.setStyleName("text");

		dailyVisitFlexTable.setWidget(3, 0, locationNameLabel);
		locationNameLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(3, 1, locationNameTextBox);
		locationNameTextBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(4, 0, townLabel);
		townLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(4, 1, townListBox);

		dailyVisitFlexTable.setWidget(5, 0, visitDateLabel);
		visitDateLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(5, 1, visitDateBox);
		visitDateBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(6, 0, arrivalTimeLabel);
		arrivalTimeLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(6, 1, arrivalHourMinutePicker);

		dailyVisitFlexTable.setWidget(7, 0, departureTimeLabel);
		departureTimeLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(7, 1, departureHourMinutePicker);

		dailyVisitFlexTable.setWidget(8, 0, metGpLabel);
		metGpLabel.addStyleName("text");

		metGpFlowPanel.add(metGpYesRadioButton);
		metGpFlowPanel.add(metGpNoRadioButton);

		dailyVisitFlexTable.setWidget(8, 1, metGpFlowPanel);

		dailyVisitFlexTable.setWidget(9, 0, givenCouponsLabel);
		givenCouponsLabel.addStyleName("text");

		givenCouponsFlowPanel.add(givenCouponsYesRadioButton);
		givenCouponsFlowPanel.add(givenCouponsNoRadioButton);

		dailyVisitFlexTable.setWidget(9, 1, givenCouponsFlowPanel);

		dailyVisitFlexTable.setWidget(10, 0, marketingActivityLabel);
		marketingActivityLabel.addStyleName("text");

		marketingActivityFlowPanel.add(marketingYesRadioButton);
		marketingActivityFlowPanel.add(marketingNoRadioButton);

		dailyVisitFlexTable.setWidget(10, 1, marketingActivityFlowPanel);

		dailyVisitFlexTable.setWidget(11, 0, marketingDescriptionLabel);
		marketingDescriptionLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(11, 1, marketingDescriptionTextBox);
		marketingDescriptionTextBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(12, 0, marketingBudgetItemsLabel);
		marketingBudgetItemsLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(12, 1, marketingBudgetItemsTextBox);
		marketingBudgetItemsTextBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(13, 0, amountLabel);
		amountLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(13, 1, amountIntegerBox);
		amountIntegerBox.addStyleName("textbox");
		amountIntegerBox.setMaxLength(8);
		amountIntegerBox.setText("0");

		dailyVisitFlexTable.setWidget(14, 0, institutionalMarketingItemsLabel);
		institutionalMarketingItemsLabel.addStyleName("text");

		dailyVisitFlexTable
				.setWidget(14, 1, institutionalMarketingItemsListBox);

		dailyVisitFlexTable.setWidget(15, 0, countLabel);
		countLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(15, 1, countIntegerBox);
		countIntegerBox.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(15, 2, addMoreButton);

		dailyVisitFlexTable.setWidget(16, 0, gpPotentialLabel);
		gpPotentialLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(16, 1, gpPotentialListBox);
		gpPotentialListBox.setName("GP_POTENTIAL_RATING");

		dailyVisitFlexTable.setWidget(17, 0, commentsLabel);
		commentsLabel.addStyleName("text");

		dailyVisitFlexTable.setWidget(17, 1, commentsTextArea);
		commentsTextArea.addStyleName("textbox");

		dailyVisitFlexTable.setWidget(18, 1, submitButton);
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
		
		validateLocationIdAnchor.addClickHandler(this);

		// mainFlexTable.getCellFormatter().setHorizontalAlignment(0, 0,
		// HasHorizontalAlignment.ALIGN_CENTER);
		// mainFlexTable.getCellFormatter().setHorizontalAlignment(1, 0,
		// HasHorizontalAlignment.ALIGN_RIGHT);

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
		else if (sender == mainMenuHyperlink)
		{
			Tbr3fieldmonitoring.verticalPanel.clear();
			Tbr3fieldmonitoring.verticalPanel.add(mainMenu);
		}
		else if(sender == validateLocationIdAnchor)
		{
			locationNameTextBox.setText("");
			if (RegexUtil.isLocationID(TBR3Client.get(locationIdIntegerBox)))
			{
				service.getLocation(TBR3.getCurrentUserName(),
						TBR3.getPassword(),
						TBR3Client.get(locationIdIntegerBox),
						new AsyncCallback<String>()
						{
							@Override
							public void onSuccess(String result)
							{
								if (!result.contains("FAILED"))
								{
									locationNameTextBox.setText("");
									locationNameTextBox.setText(result);
									Window.alert("Loacation found: " + result);
								}
								else
								{
									Window.alert("Location "
											+ TBR3Client
													.get(locationIdIntegerBox)
											+ ":"
											+ CustomMessage
													.getErrorMessage(ErrorType.ITEM_NOT_FOUND));
								}
							}

							@Override
							public void onFailure(Throwable caught)
							{
								// TODO Auto-generated method stub

							}
						});
			}
			else
			{
				Window.alert("Enter 6-digit Location ID.");
			}

		}
		
		
	}

	@Override
	public void clearUp()
	{
		
	}

	@Override
	public boolean validate()
	{
		
		return false;
	}

	@Override
	public void saveData()
	{
		
	}

	@Override
	public void fillData()
	{
		
	}

	@Override
	public void setCurrent()
	{
		
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)
	 */
	@Override
	public void onValueChange(ValueChangeEvent<Boolean> event)
	{
		Widget sender = (Widget) event.getSource();
		if(sender == marketingYesRadioButton)
		{
			boolean marketing = marketingYesRadioButton.getValue();
			marketingBudgetItemsTextBox.setEnabled(marketing);
			
		}
	}

}
