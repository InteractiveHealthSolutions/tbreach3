/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.MenuItem;
import org.irdresearch.tbreach.mobile.constants.Tbr2Version;
import org.irdresearch.tbreach.mobile.model.ListItem;

public class MainList extends List implements CommandListener {
	
	private TBReachMainMIDlet tbrMidlet;
	private ListItem[] itemList;
	private Command cmdOK;
	private Command cmdExit;
	private Display display;
	private Tbr2Version mobileVersion;
	
	public MainList(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, Choice.IMPLICIT);
		this.tbrMidlet = tbrMidlet;
		cmdOK = new Command("Ха", Command.OK, 0);
		cmdExit = new Command("Баромад", Command.EXIT, 1);
	}


	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		
		if(c==cmdOK) {
			int listIndex;
		   //determine what option has been selected and change display accordingly	
			listIndex = tbrMidlet.getSettings().getItemIndex(getString(this.getSelectedIndex()));
			
			switch(listIndex) {
			
			case MenuItem.MENU_SUSPECT_ID:
				tbrMidlet.sif.setPrevDisplayable(this);
				display = tbrMidlet.getDisplay();
				Alert alert = new Alert("");
				
				alert.setTimeout (Alert.FOREVER);
				int currentVersion = mobileVersion.VERSION;
				alert.setString("Current Verison: "+Integer.toString(currentVersion));
				tbrMidlet.sif.init();
				display.setCurrent( alert,tbrMidlet.sif );
				break;
				
			case MenuItem.MENU_PATIENT_REGISTRATION:
				tbrMidlet.preg.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.preg);
				break;
				
			case MenuItem.MENU_BASELINEFORM:
				tbrMidlet.qf.setFormType("bslfrm");
				tbrMidlet.bslfrm.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_MONITORINGFORM:
				tbrMidlet.qf.setFormType("mtrfrm");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_SURVEYFORM:
				tbrMidlet.surfrm.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.surfrm);
				break;
			
			case MenuItem.MENU_SUSPECT_CONFIRM:
				tbrMidlet.qf.setFormType("susconf");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_SUSPECT_VERIFY:
				tbrMidlet.svf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.svf);
				break;
			
			case MenuItem.MENU_PATIENT_INFO:
				tbrMidlet.pif.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.pif);
				break;
				
			case MenuItem.MENU_PATIENT_TB_INFO:
				tbrMidlet.ptbf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.ptbf);
				break;
						
			case MenuItem.MENU_PATIENT_GPS:
				if(tbrMidlet.pgf==null) {
					tbrMidlet.showAlert( ErrMsg.NO_GPS , null );
				}
				else
				 {
					tbrMidlet.pgf.setPrevDisplayable(this);
					tbrMidlet.startTBReachForm(tbrMidlet.pgf);
				 }
				break;
				
			case MenuItem.MENU_PATIENT_FUP_EFFORT:
				tbrMidlet.qf.setFormType("pfupeff");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_PEADS_CLINICAL_VISIT:
				tbrMidlet.qf.setFormType("pedclvisf");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_CLINICAL_VISIT:
				tbrMidlet.qf.setFormType("clvisf");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
/*			case MenuItem.MENU_PATIENT_SPUTUM:
				tbrMidlet.qf.setFormType("spcoll");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;*/
			case MenuItem.MENU_PATIENT_SPUTUM:
				tbrMidlet.spcf.setPrevDisplayable(this);
				
				tbrMidlet.startTBReachForm(tbrMidlet.spcf);
				break;
				
			case MenuItem.MENU_SPUTUM_RESULT:
				tbrMidlet.srf.setPrevDisplayable(this);
				
				tbrMidlet.startTBReachForm(tbrMidlet.srf);
				break;
				
				
			
			case MenuItem.MENU_REFUSAL:
				tbrMidlet.rf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.rf);
				break;
				
			case MenuItem.MENU_BASELINE_TREATMENT:
				tbrMidlet.qf.setFormType("bastrt");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_FOLLOWUP_TREATMENT:
				tbrMidlet.qf.setFormType("fotrt");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_DRUG_ADMIN:
				tbrMidlet.qf.setFormType("dradm");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_DRUG_DISPENSAL:
				tbrMidlet.qf.setFormType("drugdisp");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			case MenuItem.MENU_END_FOLLOWUP:
				/*tbrMidlet.eff.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.eff);*/
				tbrMidlet.qf.setFormType("endfol");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
				
			
			case MenuItem.MENU_MR_NUM:
				tbrMidlet.maf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.maf);
				break;
				
			case MenuItem.MENU_SEARCH:
				tbrMidlet.lsf.setFormType("search");
				tbrMidlet.lsf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.lsf);
				break;
				
			case MenuItem.MENU_SPUTUM_RESULT_SEARCH:
				tbrMidlet.sqf.setFormType("sp_search");
				tbrMidlet.sqf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.sqf);
				break;
				
			case MenuItem.MENU_LAB_SEARCH:
				tbrMidlet.lsf.setFormType("lsearch");
				tbrMidlet.lsf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.lsf);
				break;
				
			case MenuItem.MENU_FORM_COUNT:
				tbrMidlet.sqf.setFormType("fCount");
				tbrMidlet.sqf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.sqf);
				break;
				
			case MenuItem.MENU_DFR:
				tbrMidlet.dfr.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.dfr);
				break;
				
			case MenuItem.MENU_CLINICAL_DIAGNOSIS:
				tbrMidlet.qf.setFormType("cdfinfo");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_PCF:
				tbrMidlet.qf.setFormType("pcfinfo");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_PCDF:
				tbrMidlet.qf.setFormType("pcdfinfo");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_NEW_CT_SUSPECT:
				tbrMidlet.ctns.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.ctns);
				break;
			
			case MenuItem.MENU_CONTACT_SPUTUM:
				tbrMidlet.qf.setFormType("cspcoll");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			
			case MenuItem.MENU_DOTS_ASSIGN:
				tbrMidlet.dnaf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.dnaf);
				break;
			
			case MenuItem.MENU_ADDR_UPDATE:
				if(tbrMidlet.pgf==null) {
					tbrMidlet.showAlert( ErrMsg.NO_GPS , null );
				}
				else {
			
				tbrMidlet.auf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.auf);
				}
				break;
			
			case MenuItem.MENU_PAT_VERIFY:
				tbrMidlet.qf.setFormType("patverify");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			
			case MenuItem.MENU_NO_ACTIVE_FOLLOWUP:
				tbrMidlet.qf.setFormType("naafquery");
				tbrMidlet.qf.setPrevDisplayable(this);
				tbrMidlet.startTBReachForm(tbrMidlet.qf);
				break;
			}
		}		
		else if(c==cmdExit) {
			System.out.println("entering handler");
			deleteAll();
			
			try {
				tbrMidlet.destroyApp(false);
				tbrMidlet.notifyDestroyed();
				
			}
			catch(MIDletStateChangeException e) {
				System.out.println("BAAAA->" + e.toString());
			}
			System.out.println("leaving handler");
			
		}
	}
		
		
		
	
	
	public void init() {
		tbrMidlet.getSettings().reloadItems();
		deleteAll();
		itemList = tbrMidlet.getSettings().getListItems();
		for (int i = 0; i < itemList.length; i++) {
				if (itemList[i].isShow()) {
					append(itemList[i].getDisplayName(), null);
				}
			}
		addCommand(cmdOK);
		addCommand(cmdExit);
		
		
		
		setCommandListener(this);
	}
}
