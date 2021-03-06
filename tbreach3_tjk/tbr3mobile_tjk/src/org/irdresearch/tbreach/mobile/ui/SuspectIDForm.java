/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;


import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class SuspectIDForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	private static final String LESS_THAN_string = "less than";
	private static final String GREATER_THAN_string = "greater than";

	/** 'is TB MEDICATION taken?' question choiceGroup/stringItem INDEX on form */
	//private static final int TB_MEDindex = 11;
	/**
	 * 'what was TB TREATMENT DURATION?' question choiceGroup/stringItem INDEX
	 * on form
	 */
	//private static final int TB_TRT_DURindex = 12;
	/** 'what was COUGH DURATION?' question choiceGroup/stringItem INDEX on form */
	private static final int COUGH_DURindex = 5;
	/** 'is PRODUCTIVE COUGH?' question choiceGroup/stringItem INDEX on form */
	private static final int PROD_COUGHindex = 6;

	private boolean IS_SUSPECT = false;
	private boolean isPatientInfoFieldsVisible = false;
	private int SUSPECT_INDEX = 0;
	private int NOT_SUSPECT_INDEX = 1;
	private ChoiceGroup list;
	// private TBReachMainMIDlet tbrMidlet;
	DateField dateField;
	TextField chwIdField;
	//TextField labField;

	TextField idField;
	TextField idConfirm;
	TextField gpIdField;
	TextField firstNameField;
	TextField lastNameField;
	TextField ageField;
	// TextField phoneField;
	//TextField labTestOtherField;
	TextField currentTreatmentDurField;
	//TextField otherField;

	//ChoiceGroup currentlyOnTreatmentChoice;

	//StringItem tbMedicationString;
	//StringItem tbTreatmentString;
	StringItem coughDurationString;
	StringItem productiveCoughString;
	//ChoiceGroup tbMedicationChoice;
	//ChoiceGroup tbTreatmentChoice;
	ChoiceGroup sexGroup;
	/*ChoiceGroup howHearGroup;
	ChoiceGroup whoToldGroup;
	ChoiceGroup someoneElseAdvertGroup;
	ChoiceGroup adFormGroup;*/
	ChoiceGroup coughGroup;
	ChoiceGroup coughDurationGroup;
	//ChoiceGroup labTestName;
	ChoiceGroup productiveCoughGroup;
	ChoiceGroup tbHistoryGroup;
	ChoiceGroup tbFamilyHistoryGroup;
	ChoiceGroup locationGroup;
	ChoiceGroup feverGroup;
	ChoiceGroup nightSweatGroup;
	ChoiceGroup weightLossGroup;
	ChoiceGroup haemoptysisGroup;
	TextField conclusionGroup;
	/*ChoiceGroup gpConfirmed;
	ChoiceGroup consentRead;*/
	Command cmdOK;
	Command cmdBack;
	Item[]		formItems;	
	TextField doctorName;
	TextField indexHospital;
	TextField districtregistration;
	ChoiceGroup treatmentquestion;
	//boolean symptoms;
	
	public SuspectIDForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		dateField = null;           
		chwIdField= null;          
		//labField = null;            
		         list = null;            
		idField = null;             
		idConfirm = null;           
		gpIdField = null;           
		firstNameField= null;      
		lastNameField= null;       
		ageField = null;            
		//phoneField= null;          
		//labTestOtherField= null; 
		//tbMedicationChoice= null;        
		//tbTreatmentChoice= null;   
		//tbMedicationString= null;        
		//tbTreatmentString= null;         
		             
		/*howHearGroup = null;  
		whoToldGroup = null;  
		adFormGroup = null;   */
		//otherField = null;
		sexGroup = null;            
		coughGroup = null;               
		coughDurationGroup= null;  
		coughDurationString = null;
		//labTestName= null;         
		productiveCoughGroup= null;     
		productiveCoughString = null;
		tbHistoryGroup = null;           
		tbFamilyHistoryGroup= null;     
		feverGroup = null;               
		nightSweatGroup= null;          
		weightLossGroup= null;          
		haemoptysisGroup= null;         
		conclusionGroup= null; 
		/*gpConfirmed = null;
		consentRead = null;*/
		locationGroup = null;
		cmdOK = null;
		cmdBack = null;
		doctorName =null;
		indexHospital = null;
		districtregistration = null;
		treatmentquestion = null;
		
	}
	
	public void init() {
		IS_SUSPECT = false;
		isPatientInfoFieldsVisible = false;
		
		//TODO is it OK or needs to be NUMERIC
		chwIdField = new TextField("????????????????????:", tbrMidlet.getCurrentUserId(), IdValidateUtil.MAX_CHWID_LENGTH, TextField.ANY);
		
		//gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		/*if(tbrMidlet.getSettings().getActiveUserType()==UserType.USER_TYPE_GP) {
			gpIdField.setString(tbrMidlet.getCurrentUserId());
			chwIdField.setConstraints(TextField.UNEDITABLE);
		}*/
		dateField = new DateField("?????????? ????????????????", DateField.DATE);
		dateField.setDate(new Date());
		 list = new ChoiceGroup("????-???? ??????????????", Choice.EXCLUSIVE);
		 list.append( "??????" , null);
		 list.append( "??????" , null);
		 list.append( "??????" , null);
		 list.setSelectedIndex(2, true );
		 
		/* doctorName = new TextField( "Doctor's Name" , "" , 25 ,  TextField.ANY) ;
		 indexHospital = new TextField( "Index # of Hospital" , ""  , 15 , TextField.ANY);
		 districtregistration = new TextField( "District Registration on TB03" , "" , 15 , TextField.ANY);
*/
		firstNameField = new TextField("??????:", "", 25, TextField.ANY);
		lastNameField = new TextField("??????????:", "", 25, TextField.ANY);
		//labTestOtherField = new TextField("If other, specify:", "", 15, TextField.ANY);

		sexGroup = new ChoiceGroup("????????:", Choice.POPUP);
		sexGroup.append("M",null);
		sexGroup.append("??", null);
		
		ageField = new TextField("???????? ??????:", "", 3, TextField.NUMERIC);
		//phoneField = new TextField("Phone:" , "", 20, TextField.ANY);	
		
		treatmentquestion = new ChoiceGroup( "???????????????? ?????????? ???????? ???????????????" , ChoiceGroup.POPUP);
		treatmentquestion.append("????",null);
		treatmentquestion.append("Xa",null);
		treatmentquestion.setSelectedIndex(0, true);

		coughGroup = new ChoiceGroup("?????????? ???????????", ChoiceGroup.POPUP);
		coughGroup.append("????",null);
		coughGroup.append("Xa",null);
		coughGroup.setSelectedIndex(0, true);
		
		coughDurationGroup=  new ChoiceGroup("???????? ?????????? ???????? ?????????????????", ChoiceGroup.POPUP);
		coughDurationGroup.append("???????????? ???? 2 ", null);
		coughDurationGroup.append("2-3 ??????????", null);
		coughDurationGroup.append("???????? ???? 3 ??????????", null);
		coughDurationGroup.append("??????????????????", null);
		coughDurationGroup.append("?????????? ????????", null);
		coughDurationGroup.setSelectedIndex(0,true);
		
		coughDurationString=new StringItem("", "");
		
		productiveCoughGroup = new ChoiceGroup("?????????????? ???????????????? ???????????? ???????????", ChoiceGroup.POPUP);
		productiveCoughGroup.append("????", null);
		productiveCoughGroup.append("????",null);
		productiveCoughGroup.append("?????????? ????????", null);
		//productiveCough.append("Don't know", null);
		productiveCoughGroup.setSelectedIndex(0, true);
		
		productiveCoughString=new StringItem("", "");
		
		tbHistoryGroup = new ChoiceGroup("?????????????? ?????? ?????????????", ChoiceGroup.POPUP);
		tbHistoryGroup.append("????", null);
		tbHistoryGroup.append("????",null);
		tbHistoryGroup.append("?????????? ????????", null);
		//tbHistory.append("Don't Know", null);
		tbHistoryGroup.setSelectedIndex(0, true);
		
		
		tbFamilyHistoryGroup = new ChoiceGroup("?????? ???????? ???????????? ?????? ?????????", ChoiceGroup.POPUP);
		tbFamilyHistoryGroup.append("????", null);
		tbFamilyHistoryGroup.append("????",null);
		tbFamilyHistoryGroup.append("?????????? ????????", null);
		//tbFamilyHistory.append("Don't Know", null);
		tbFamilyHistoryGroup.setSelectedIndex(0, true);
		
		/*gpConfirmed = new ChoiceGroup("Did the GP confirm that this person is an eptb suspect?", Choice.POPUP);
		gpConfirmed.append("No", null);
		gpConfirmed.append("Yes",null);*/
		
		conclusionGroup = new TextField("?????????? ???? ?????? ???????? ?? ?????","",15, TextField.UNEDITABLE);
		//conclusionGroup.setString("Suspect");
		//
		SUSPECT_INDEX=0;
		NOT_SUSPECT_INDEX=1;
		//conclusionGroup.setSelectedIndex(NOT_SUSPECT_INDEX, true);
		conclusionGroup.setString("?????? ????????");
	//	idField = new TextField("???????????? ???????????? ??????????????????:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
	//	idConfirm = new TextField("???????????? ???????????? ??????????????????:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		
		locationGroup = new ChoiceGroup("????????????????????:" , Choice.POPUP);
		locationGroup.append("Poly Clinic Dushanbe 1" , null);
		locationGroup.append("Poly Clinic Dushanbe 2" , null);
		locationGroup.append("Poly Clinic Dushanbe 3" , null);
		locationGroup.append("Poly Clinic Dushanbe 4" , null);
		locationGroup.append("Poly Clinic Dushanbe 5" , null);
		locationGroup.append("Poly Clinic Dushanbe 6" , null);
		locationGroup.append("Poly Clinic Dushanbe 7" , null);
		locationGroup.append("Poly Clinic Dushanbe 8" , null);
		locationGroup.append("Poly Clinic Dushanbe 9" , null);
		locationGroup.append("Poly Clinic Dushanbe 10" , null);
		locationGroup.append("Poly Clinic Dushanbe 11" , null);
		locationGroup.append("Poly Clinic Dushanbe 12" , null);
		locationGroup.append("Poly Clinic Dushanbe 13" , null);
		locationGroup.append("Poly Clinic Dushanbe 14" , null);
		locationGroup.append("Poly Clinic Rudaki", null);
		locationGroup.append("Poly Clinic Tursunzade" , null);
		locationGroup.append("Diabetes center Dushanbe" , null);
		locationGroup.append("Prison system" , null);
				
		
		feverGroup = new ChoiceGroup("???? ???????????? ???????? ???????? ???????????", Choice.POPUP);
		feverGroup.append("????", null);
		feverGroup.append("????",  null);
		feverGroup.append("?????????? ????????", null);
		//fever.append("Don't know", null);
		
		nightSweatGroup = new ChoiceGroup("???? ???????????? ???????? ?????????? \n???????? ???????????????", Choice.POPUP);
		nightSweatGroup.append("????", null);
		nightSweatGroup.append("????", null);
		nightSweatGroup.append("?????????? ????????", null);
		//nightSweat.append("Don't know", null);
		
		weightLossGroup = new ChoiceGroup("?????????????????? ???????????? ???? ???????? ?????????", Choice.POPUP);
		weightLossGroup.append("????", null);
		weightLossGroup.append("????", null);
		weightLossGroup.append("?????????? ????????", null);
		//weightLoss.append("Don't know", null);
		
		haemoptysisGroup = new ChoiceGroup("???????????????????? ?????????????? ?????????? ?????????", Choice.POPUP);
		haemoptysisGroup.append("????", null);
		haemoptysisGroup.append("????", null);
		haemoptysisGroup.append("?????????? ????????", null);
		//haemoptysis.append("Don't know", null);
	
		
		cmdOK = new Command("????????", Command.OK, 1);
		cmdBack = new Command("???? ??????????", Command.BACK, 2);

		
		Item items[] = {/*dateField,*//*chwIdField,*//*list,*/ageField, firstNameField,lastNameField,sexGroup,treatmentquestion, coughGroup,coughDurationGroup,productiveCoughGroup,tbHistoryGroup,tbFamilyHistoryGroup,conclusionGroup
				,feverGroup,nightSweatGroup,weightLossGroup,haemoptysisGroup};
		formItems = items;
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		initialShow ();
		setCommandListener(this);
		setItemStateListener(this);
		
	}
	
	private int indexOf (Item item)
	{
		for (int i = 0; i < formItems.length; i++)
			if (formItems[i] == item)
				return i;
		return -1;
	}
	
	private void show (Item item)
	{
		int i = indexOf (item);
		delete (i);
		insert (i, item);
	}
	
	private void hide (Item item)
	{
		int i = indexOf (item);
		delete (i);
		StringItem tmp = new StringItem ("", "");
		insert (i, tmp);
	}
	
	private void initialShow ()
	{
		// Add Items to form
		for (int i = 0; i < formItems.length; i++)
			append (formItems[i]);
		hide (firstNameField);
		hide (lastNameField);
		hide (coughDurationGroup);
		hide (productiveCoughGroup);
		//hide (idField);
		//hide (idConfirm);
		//hide (locationGroup);
		hide (feverGroup);
		hide (nightSweatGroup);
		hide (weightLossGroup);
		hide (haemoptysisGroup);
	}




	public void commandAction(Command c, Displayable d) {
	
		if(c==cmdOK) {
			if(validate()) {
				/*if(!IS_SUSPECT){
					tbrMidlet.showAlert(ErrMsg.SUSPECT_IS_UNSAVEABLE,null);
					return;
				}*/
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				Hashtable model = tbrMidlet.sendToServer(request);
				
				if(model!=null) {
					String status = (String)model.get("status");
					String uniqueID = (String)model.get("uniqueID");
					
					
					if(status.equals(XmlStrings.SUCCESS)) {
						System.out.println("success");
						if(IS_SUSPECT){
							deleteAll();
							removeCommand(cmdOK);
							removeCommand(cmdBack);
							init();
							tbrMidlet.showAlert("???????????????????? ???????? ??????????????. ?????????? ?????????????? ???? ?????????? ?????????? ???????????????? Done-???? ?????? ????????????. ???????????? ?????????????????????? ???? ???????? "+uniqueID+" ??????",null);
							
						}
						else{
							deleteAll();
							removeCommand(cmdOK);
							removeCommand(cmdBack);
							init();
							tbrMidlet.showAlert("???? ???????? ???????????????? ???? ?????? ????????. ???????????????????? ???????? ??????????????. ?????????? ?????????????? ???? ?????????? ?????????? ???????????????? Done-???? ?????? ????????????",null);
							
						}
						
						
					}
					else if(status.equals(XmlStrings.ERROR)) {
						System.out.println((String)model.get("msg"));
						tbrMidlet.showAlert("ERROR: " + (String)model.get("msg"),null);
					}
				}
			}
		}
		
		else if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			tbrMidlet.setDisplay(prevDisplayable);
		}
	}

	public String createRequestPayload() {
		String request = "";
		
		//request for suspect and not suspect both getting handled by only one method
		request = "type="+FormType.SUSPECT_ID;

		if(IS_SUSPECT) {
			//request = "type=sif";
			//request = "type=" + FormType.SUSPECT_ID;
			//request += "&id=" + idField.getString();
			request += "&fn=" + firstNameField.getString();
	    	request += "&ln=" + lastNameField.getString();
	    	//request += "&gpid=" + gpIdField.getString();
    		request += "&fev=" + feverGroup.getString(feverGroup.getSelectedIndex());
    		request += "&ns=" + nightSweatGroup.getString(nightSweatGroup.getSelectedIndex());
    		request += "&wl=" + weightLossGroup.getString(weightLossGroup.getSelectedIndex());
    		request += "&ha=" + haemoptysisGroup.getString(haemoptysisGroup.getSelectedIndex());

		}
		//request += "&v=01";
    	//request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&chwid=" +   chwIdField.getString();
    	//request += "&labid=" +   labField.getString();
    	//request += "&labtest=" +   labTestName.getString(labTestName.getSelectedIndex());

    	//if(labTestName.getString(labTestName.getSelectedIndex()).toLowerCase().equals("other")){
    	//	request += "&labother=" +   labTestOtherField.getString();
    	//}
    	//request += "&gpid=" + gpIdField.getString();
    	/*request += "&fn=" + firstNameField.getString();
    	request += "&ln=" + lastNameField.getString();*/
    	request += "&sex=" + sexGroup.getString(sexGroup.getSelectedIndex());
    	request += "&age=" + ageField.getString();
    	//request += "&loc=" + locationGroup.getString(locationGroup.getSelectedIndex());
    	int coughIndex = coughGroup.getSelectedIndex();
    	request += "&agetype=" + list.getString( list.getSelectedIndex());
    	
    	request += "&cough=" + coughGroup.getString(coughIndex);
    	if(coughIndex==1) {//if cough is yes
    		int coughDurationIndex = coughDurationGroup.getSelectedIndex();
    		String coughDurString = coughDurationGroup.getString(coughDurationIndex);
/*    		if(coughDurString.trim().toLowerCase().startsWith(LESS_THAN_string.toLowerCase())){
    			coughDurString = StringUtil.replace( LESS_THAN_string.toLowerCase(),  "<",  coughDurString.toLowerCase());
    		}else if(coughDurString.trim().toLowerCase().startsWith(GREATER_THAN_string.toLowerCase())){
    			coughDurString = StringUtil.replace( GREATER_THAN_string.toLowerCase(),  ">",  coughDurString.toLowerCase());
    		}*/
    		request += "&cd=" + coughDurString;
    			
    		request += "&pc=" + productiveCoughGroup.getString(productiveCoughGroup.getSelectedIndex());
    	}
    	request += "&tbh=" + tbHistoryGroup.getString(tbHistoryGroup.getSelectedIndex());
    	
    	/*if(tbHistory.getString(tbHistory.getSelectedIndex()).toLowerCase().equals("yes")){
        	
    		request += "&tbmed=" + tbMedicationChoice.getString(tbMedicationChoice.getSelectedIndex());
        	
    		if(tbMedicationChoice.getString(tbMedicationChoice.getSelectedIndex()).toLowerCase().equals("yes")){
        		String tbTrtString = tbTreatmentChoice.getString(tbTreatmentChoice.getSelectedIndex());
        		if(tbTrtString.trim().toLowerCase().startsWith(LESS_THAN_string.toLowerCase())){
        			tbTrtString = StringUtil.replace( LESS_THAN_string.toLowerCase(),  "<",  tbTrtString.toLowerCase());
        		}else if(tbTrtString.trim().toLowerCase().startsWith(GREATER_THAN_string.toLowerCase())){
        			tbTrtString = StringUtil.replace( GREATER_THAN_string.toLowerCase(),  ">",  tbTrtString.toLowerCase());
        		}
        		
        		request += "&tbtrt=" + tbTrtString;
        	}
    	}*/
    	request += "&ftbh=" + tbFamilyHistoryGroup.getString(tbFamilyHistoryGroup.getSelectedIndex());
		//request += "&eptb=" + gpConfirmed.getString(gpConfirmed.getSelectedIndex());
    	//request += "&conc=" + conclusionGroup.getString(conclusionGroup.getSelectedIndex());
    	request += "&conc=" + conclusionGroup.getString();

		
    	//request += "&pc=" + productiveCough.getString(productiveCough.getSelectedIndex());
    	/*if(size()>10) {
    		request += "&fev=" + fever.getString(fever.getSelectedIndex());
    		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
    		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
    		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());
    		
    	}*/
    	
    	/*if(symptoms==true)
    	{
    		request += "&fev=" + fever.getString(fever.getSelectedIndex());
    		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
    		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
    		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());
    	}
*/    	
    	//if(tbrMidlet.getSettings().getActiveUserType()==UserType.USER_TYPE_GP) {
    	//	request += "&conc=" + conclusion.getString(conclusion.getSelectedIndex());
    	//}
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDate(dateField.getDate());
    	
    	return request;
	}
	
	private boolean validate() {
		/*if(!IS_SUSPECT){
			tbrMidlet.showAlert(ErrMsg.SUSPECT_IS_UNSAVEABLE,null);
			return false;
		}*/
		boolean result = true;
		int check = -1;
		
		if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING, null);
			result = false;
		}
		else if(chwIdField.getString().length()<IdValidateUtil.MIN_CHWID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.CHW_ID_INVALID, null);
			result = false;
		}
		else if(ageField.getString()==null || ageField.getString().length() ==0) {
			tbrMidlet.showAlert(ErrMsg.AGE_MISSING, null);
			result = false;	
		}
		
		else if(dateField.getDate()==null)
		{
			tbrMidlet.showAlert( ErrMsg.DATE_NOT_ENTERED , null );
			result = false;
		}
		else if(treatmentquestion.getSelectedIndex()==1)
		{
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			init();
			tbrMidlet.showAlert( "???? ?????????? ?????? ?????????????? ?????????? ??????????, ???????????????? ????????. ?????????? ?????????????? ???? ?????????? ?????????? ???????????????? Done-???? ?????? ????????????" , null );
			result = false;
		}
		//TODO id validations not implemented
		
		else if(IS_SUSPECT){
/*			 if(idField.getString()==null || idField.getString().length()==0) {
				tbrMidlet.showAlert(ErrMsg.ID_MISSING,null);
				result = false;
			 }*/
/*			 else if((check=IdValidateUtil.validateId(idField.getString()))!=IdValidateUtil.ID_VALID) {
				if(check==IdValidateUtil.BAD_ID_LENGTH) {
					tbrMidlet.showAlert(ErrMsg.INVALID_ID_LENGTH,null);
				}				
				else if(check==IdValidateUtil.BAD_GP_NUMBER) {
					tbrMidlet.showAlert(ErrMsg.INVALID_GP_NUMBER_IN_ID,null);
				}				
				else if(check==IdValidateUtil.BAD_YEAR) {
					tbrMidlet.showAlert(ErrMsg.INVALID_YEAR_IN_ID,null);
				}	
				result = false;
			}*/
/*			 else if(!idField.getString().equals(idConfirm.getString())) {
				tbrMidlet.showAlert(ErrMsg.ID_MISMATCH,null);
				result = false;
			}*/
			 
			 
			  if(firstNameField.getString()==null || firstNameField.getString().length()==0) {
					tbrMidlet.showAlert(ErrMsg.FIRST_NAME_MISSING, null);
					result = false;
				}
				else if(lastNameField.getString()==null || lastNameField.getString().length()==0) {
					tbrMidlet.showAlert(ErrMsg.LAST_NAME_MISSING, null);
					result = false;	
				}
/*				else if (gpIdField.getString()==null || gpIdField.getString().length()==0) {
					tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
					result = false;
				}*/
			/* else if (howHearGroup.getString( howHearGroup.getSelectedIndex()).toLowerCase().equals("other") && (otherField.getString()==null || otherField.getString().length()==0)) {
				 tbrMidlet.showAlert(ErrMsg.MISSING_HOW_HEAR_OTHER,null);
					result = false;
			 }*/
		}
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		

		return result;
	}
	
	public void itemStateChanged(Item i) {
		/*if(i == labTestName){
			int labtestIndex=labTestName.getSelectedIndex();
			
			if(labTestName.getString(labtestIndex).toLowerCase().equals("other")){
				labTestOtherField.setConstraints(TextField.ANY);
			}
			else{
				labTestOtherField.setString("");
				labTestOtherField.setConstraints(TextField.UNEDITABLE);
			}
			return;
		}
		else*/ if(i == coughGroup) {
			//int coughIndex = coughGroup.getSelectedIndex();
			int indexx=1;
				
				/*Collator collator = Collator.getInstance();
				collator.setStrength(Collator.SECONDARY);
				int compResult = collator.compare(coughGroup.getString(coughIndex).toLowerCase(), "Xa");
				tbrMidlet.showAlert(Integer.toString(compResult)  , null);*/
				//String st1=new String(coughGroup.getString(coughIndex));
				//tbrMidlet.showAlert(st1,null);
				//tbrMidlet.showAlert( s+"Ser" , null ); 
				
					//tbrMidlet.showAlert(Integer.toString(coughIndex),null);
					//tbrMidlet.showAlert(b1[bray] , null);
				
			//tbrMidlet.showAlert(coughGroup.getString(coughIndex),null);
			if(coughGroup.getSelectedIndex()==indexx) {
/*				if(get(COUGH_DURindex)!=coughDurationString){
					if(get(COUGH_DURindex) == coughDurationGroup) delete(COUGH_DURindex);
					
					insert(COUGH_DURindex, coughDurationString);
				}
				if(get(PROD_COUGHindex)!=productiveCoughString){
					if(get(PROD_COUGHindex) == productiveCoughGroup) delete(PROD_COUGHindex);
					
					insert(PROD_COUGHindex, productiveCoughString);
				}*/
				show(coughDurationGroup);
				show(productiveCoughGroup);
				
			}
			else
			{
				hide(coughDurationGroup);
				hide(productiveCoughGroup);
			}
			setSuspect();
		}
		else if(i==coughDurationGroup) {
			setSuspect();
		}
		
		else if(i==productiveCoughGroup) {
			setSuspect();
		}
		
		else if(i == tbHistoryGroup){
			setSuspect();
		}
		
		
		else if(i==tbFamilyHistoryGroup) {
/*			if(tbFamilyHistory.getSelectedIndex()==0) {
				if(tbrMidlet.getSettings().getActiveUserType()!=UserType.USER_TYPE_GP) {
					tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP, null);
					suspect = 0;
				}
			}	*/
			setSuspect();
		}
	}
	public void setSuspect(){
		IS_SUSPECT=false;
		int localIndex = 1;
		//if(coughGroup.getString(coughGroup.getSelectedIndex()).toLowerCase().equals("Xa")){
		if(coughGroup.getSelectedIndex()==localIndex){
			if(coughDurationGroup.getSelectedIndex()==2){
				IS_SUSPECT=true;
			}
			else if(coughDurationGroup.getSelectedIndex() == 1 && productiveCoughGroup.getSelectedIndex() == 1){
				IS_SUSPECT=true;
			}
			
		}
		/*if(tbHistoryGroup.getString(tbHistoryGroup.getSelectedIndex()).toLowerCase().equals("yes")
				|| tbFamilyHistoryGroup.getString(tbFamilyHistoryGroup.getSelectedIndex()).toLowerCase().equals("yes")){
			IS_SUSPECT=true;
		}*/
		if(tbHistoryGroup.getSelectedIndex()==1
				|| (tbFamilyHistoryGroup.getSelectedIndex()==1)){
			IS_SUSPECT=true;
		}
		/*if(gpConfirmed.getString(gpConfirmed.getSelectedIndex()).toLowerCase().equals("yes")){
			IS_SUSPECT=true;
		}*/
	
		if(IS_SUSPECT){
		//	conclusionGroup.setSelectedIndex(SUSPECT_INDEX, true);
			conclusionGroup.setString( "?????????? ???? ??????" );
			if(!isPatientInfoFieldsVisible){
				//idField.setString( "" );
				//idConfirm.setString( "" );
				//howHearGroup.setSelectedIndex( 0 , true );
				//otherField.setString( "" );
				//otherField.setConstraints( TextField.UNEDITABLE );
				//append(idField);
				//append(idConfirm);
				//append(gpIdField);
				append(firstNameField);
				append(lastNameField);
				//append(locationGroup);
				append(feverGroup);
				append(nightSweatGroup);
				append(weightLossGroup);
				append(haemoptysisGroup);
				//append( howHearGroup );
				//append(otherField);
				//append( whoToldGroup );
			//	append(consentRead);
				
				isPatientInfoFieldsVisible=true;
			}
		}
		else{
			//conclusionGroup.setSelectedIndex(NOT_SUSPECT_INDEX, true);
			conclusionGroup.setString( "?????? ????????" );
			if(isPatientInfoFieldsVisible){
				while (true)
				{//delete all till conclusion group
					if(get( size()-1 ) != conclusionGroup){
						delete(size() - 1);
					}
					else{
						break;
					}
				}
				isPatientInfoFieldsVisible=false;
			}
		}
	}
}
