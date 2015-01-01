package org.irdresearch.tbreach2.shared.model;

import java.io.Serializable;
import java.util.Date;

public class ReminderText implements Serializable {
private static final long serialVersionUID = -8100977248957702858L;

private int recordNum;
private String text;
private String description;
private Date lastUpdated;
private Reminder reminder;
private String 	createdByUserId;
private String 	createdByUserName;
private Date 	createdDate;
private String 	lastEditedByUserId;
private String 	lastEditedByUserName;
public int getRecordNum() {
	return recordNum;
}
public String getText() {
	return text;
}
public String getDescription() {
	return description;
}
public Date getLastUpdated() {
	return lastUpdated;
}
public Reminder getReminder() {
	return reminder;
}

public void setRecordNum(int value) {
	recordNum=value;
}
public void setText(String value) {
	try{
	text=value.trim();
	}catch (Exception e) {
	}
}
public void setDescription(String value) {
	description=value;
}
public void setLastUpdated(Date date) {
	lastUpdated=date;
}
public void setReminder(Reminder rem) {
	reminder=rem;
}
/**
 * @param createdByUserId the createdByUserId to set
 */
public void setCreatedByUserId(String createdByUserId) {
	this.createdByUserId = createdByUserId;
}
/**
 * @return the createdByUserId
 */
public String getCreatedByUserId() {
	return createdByUserId;
}
/**
 * @param createdByUserName the createdByUserName to set
 */
public void setCreatedByUserName(String createdByUserName) {
	this.createdByUserName = createdByUserName;
}
/**
 * @return the createdByUserName
 */
public String getCreatedByUserName() {
	return createdByUserName;
}
/**
 * @param createdDate the createdDate to set
 */
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
/**
 * @return the createdDate
 */
public Date getCreatedDate() {
	return createdDate;
}

/*public void setCreator(User creator){
	createdByUserId=creator.getName();
	createdByUserName=creator.getFullName();
	createdDate=new Date();
}*/
/**
 * @param lastEditedByUserId the lastEditedByUserId to set
 */
public void setLastEditedByUserId(String lastEditedByUserId) {
	this.lastEditedByUserId = lastEditedByUserId;
}
/**
 * @return the lastEditedByUserId
 */
public String getLastEditedByUserId() {
	return lastEditedByUserId;
}
/**
 * @param lastEditedByUserName the lastEditedByUserName to set
 */
public void setLastEditedByUserName(String lastEditedByUserName) {
	this.lastEditedByUserName = lastEditedByUserName;
}
/**
 * @return the lastEditedByUserName
 */
public String getLastEditedByUserName() {
	return lastEditedByUserName;
}
/*public void setLastEditor(User editor){
	lastEditedByUserId=editor.getName();
	lastEditedByUserName=editor.getFullName();
}*/

}
