/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ihsinformatics.tbreach3tanzania.shared.Parameter;
import com.ihsinformatics.tbreach3tanzania.shared.model.*;

/**
 * The client side stub for the RPC service.
 * 
 * @author owais.hussain@irdresearch.org
 */
@RemoteServiceRelativePath("greet")
public interface ServerService extends RemoteService
{
	/* Authentication methods */
	User authenticate (String userName, String password, String role) throws Exception;

	Boolean authenticateUser (String text) throws Exception;

	/* Delete methods */
	Boolean deleteDefinition (Definition definition) throws Exception;
	
	Boolean deleteDictionary (Dictionary dictionary) throws Exception;

	Boolean deleteEncounter (Encounter encounter) throws Exception;

	Boolean deleteEncounterElement (EncounterElement encounterElement) throws Exception;
	
	Boolean deleteEncounterPrerequisite (EncounterPrerequisite encounterPrerequisite) throws Exception;

	Boolean deleteEncounterResults (EncounterResults encounterResults) throws Exception;

	Boolean deleteEncounterType (EncounterType encounterType) throws Exception;
	
	Boolean deleteEncounterValue (EncounterValue encounterValue) throws Exception;
	
	Boolean deleteFeedback (Feedback feedback) throws Exception;

	Boolean deleteLabTest (LabTest labTest) throws Exception;
	
	Boolean deleteLocation (Location location) throws Exception;

	Boolean deletePatient (Patient patient) throws Exception;

	Boolean deletePerson (Person person) throws Exception;

	Boolean deletePersonRole (PersonRole personRole) throws Exception;

	Boolean deleteUser (User user) throws Exception;

	Boolean deleteUserMapping (UserMapping userMapping) throws Exception;

	Boolean deleteUserRights (UserRights userRights) throws Exception;

	/* Find methods */
	Definition findDefinition (String definitionType, String definitionKey) throws Exception;

	Dictionary findDictionary (String term) throws Exception;

	Encounter findEncounter (EncounterId encounterID) throws Exception;

	EncounterElement findEncounterElement (String encounterType, String element) throws Exception;

	EncounterElement[] findEncounterElements (String encounterType) throws Exception;

	EncounterResults[] findEncounterResults (EncounterId encounterId) throws Exception;

	EncounterResults findEncounterResultsByElement (EncounterResultsId encounterResultsID) throws Exception;

	EncounterPrerequisite findEncounterPrerequisite (EncounterPrerequisiteId encounterPrerequisiteId) throws Exception;

	EncounterPrerequisite[] findEncounterPrerequisites (EncounterType encounterType) throws Exception;
	
	EncounterType findEncounterType (String encounterType) throws Exception;

	EncounterValue findEncounterValue (String encounterType, String element, String value) throws Exception;

	LabTest[] findLabTests (String patientId) throws Exception;
	
	Location findLocation (String locationID) throws Exception;

	Location[] findLocationsByType (String locationType) throws Exception;

	Patient findPatient (String patientID) throws Exception;

	Patient findPatientByMR (String Mrno) throws Exception;

	Person findPerson (String pid) throws Exception;

	PersonRole[] findPersonRoles (String pid) throws Exception;
	
	Person[] findPersonsByName (String firstName, String lastName) throws Exception;

	Person findPersonsByNIC (String NIC) throws Exception;

	User findUser (String currentUserName) throws Exception;

	UserMapping findUserMapping (UserMappingId userMappingId) throws Exception;

	UserMapping[] findUserMappingsByUser (String userId) throws Exception;

	UserRights findUserRights (String roleName, String menuName) throws Exception;
	
	SmsRules  findSmsRule (String smsRuleId) throws Exception;

	SmsRecipient  findSmsRecipient (String smsRecipientId) throws Exception;
	
	SmsRecipient[]  findSmsRecipients () throws Exception;
	
	/* Save methods */
	Boolean saveDefinition (Definition definition) throws Exception;
	
	Boolean saveDictionary (Dictionary dictionary) throws Exception;

	Boolean saveEncounter (Encounter encounter) throws Exception;

	Boolean saveEncounterElement (EncounterElement encounterElement) throws Exception;
	
	Boolean saveEncounterPrerequisite (EncounterPrerequisite encounterPrerequisite) throws Exception;

	Boolean saveEncounterResults (EncounterResults encounterResults) throws Exception;

	Boolean saveEncounterType (EncounterType encounterType) throws Exception;
	
	Boolean saveEncounterValue (EncounterValue encounterValue) throws Exception;

	String saveEncounterWithResults (Encounter encounter, EncounterResults[] encounterResults);
	
	Boolean saveFeedback (Feedback feedback) throws Exception;

	Boolean saveLabTest (LabTest labTest) throws Exception;
	
	Boolean saveLocation (Location location) throws Exception;

	Boolean savePatient (Patient patient) throws Exception;

	Boolean savePerson (Person person) throws Exception;

	Boolean savePersonRole (PersonRole personRole) throws Exception;

	Boolean saveUser (User user, String[] currentRoles);
	
	Boolean saveUserMapping (UserMapping userMapping) throws Exception;

	Boolean saveUserRights (UserRights userRights) throws Exception;
	
	//boolean saveSmsRule (SmsRules smsRule);

	/* Update methods */
	Boolean updateDefaults (Defaults[] defaults) throws Exception;

	Boolean updateDefinition (Definition definition) throws Exception;

	Boolean updateDictionary (Dictionary dictionary) throws Exception;

	Boolean updateEncounter (Encounter encounter) throws Exception;

	Boolean updateEncounterElement (EncounterElement element) throws Exception;

	Boolean updateEncounterPrerequisite (EncounterPrerequisite prerequisite) throws Exception;

	Boolean updateEncounterResults (EncounterResults encounterResults) throws Exception;
	
	Boolean updateEncounterType (EncounterType encounterType) throws Exception;
	
	Boolean updateEncounterValue (EncounterValue encounterValue) throws Exception;

	Boolean updateEncounterWithResults (Encounter encounter, EncounterResults[] encounterResults) throws Exception;

	Boolean updateFeedback (Feedback feedback) throws Exception;

	Boolean updateLabTest (LabTest labTest) throws Exception;
	
	Boolean updateLocation (Location location) throws Exception;

	Boolean updatePassword (String userName, String newPassword) throws Exception;

	Boolean updatePatient (Patient patient) throws Exception;

	Boolean updatePerson (Person person) throws Exception;
	
	Boolean updatePersonRole (PersonRole personRole) throws Exception;

	Boolean updateUser (User user, String[] currentRoles);

	Boolean updateUserMapping (UserMapping userMapping) throws Exception;

	Boolean updateUserRights (UserRights userRights) throws Exception;
	
	Boolean updateSmsRule (SmsRules[] smsRule) throws Exception;
	
	Boolean updateSmsRule (SmsRules smsRule) throws Exception;

	/* Other methods */
	Long count (String tableName, String filter) throws Exception;

	Boolean verifySecretAnswer (String userName, String secretAnswer) throws Exception;

	String generatePDFfromQuery (String database, String query) throws Exception;
	
	String generateCSVfromQuery (String database, String query) throws Exception;

	String generateReport (String fileName, Parameter[] params, boolean export) throws Exception;

	String generateReportFromQuery (String database, String reportName, String query, Boolean export) throws Exception;

	String[] getColumnData (String tableName, String columnName, String filter) throws Exception;
	
	String[] getLatestColumnData (String tableName, String columnName, String filter) throws Exception;

	String getCurrentUserName () throws Exception;
	
	String[][] getSchema() throws Exception;

	Definition[] getDefinitions () throws Exception;
	
	Defaults[] getDefaults() throws Exception;

	String[] getDumpFiles () throws Exception;

	String[][] getReportsList () throws Exception;

	String[] getRowRecord (String tableName, String[] columnNames, String filter) throws Exception;

	String getObject (String tableName, String columnName, String filter) throws Exception;

	String[] getQueriesResults (String[] queries) throws Exception;

	String getSecretQuestion (String userName) throws Exception;

	String getSnapshotTime () throws Exception;

	String[][] getTableData (String tableName, String[] columnNames, String filter) throws Exception;

	String[][] getTableData (String sqlQuery) throws Exception;

	Boolean[] getUserRgihts (String userName, String role, String menuName) throws Exception;

	Boolean exists (String tableName, String filter) throws Exception;

	int execute (String query) throws Exception;

	Boolean execute (String[] queries) throws Exception;

	Boolean executeProcedure (String procedure) throws Exception;

	void recordLogin (String userName) throws Exception;

	void recordLogout (String userName) throws Exception;

	void sendGenericSMSAlert (Sms sms) throws Exception;

	void sendGenericSMSAlert (Sms[] sms) throws Exception;

	void setCurrentUser (String userName, String role) throws Exception;

	boolean updateSmsRecipient (SmsRecipient[] smsRule);

	boolean updateSmsRecipient (SmsRecipient smsRule);

	AlertSentGeneXpertResults[] getGeneXpertResultAsList ();
	
}
