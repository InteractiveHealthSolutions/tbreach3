/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:58:51 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * Sputumresults generated by hbm2java
 */
public class BaselineDetails implements java.io.Serializable
{

	/**
	 * 
	 */
	private Integer				sputumOrderNo;
	private String				patientId;
	private Date				treatmentInitiation;
	private String				chwId;
	private String				locationId;
	private String				baselineSputum;
	private String				baselineChest;
	private String				otherXraySite;
	private String				xrayResult;
	private String				baselineGeneXpert;
	private String 				geneXpertDrugSensitivity;
	private String 				typePatient;
	private String 				patientCategory;
	private String 				weight;
	private String 				regimen;
	private String 				fixedDose;
	private String 				strepto;
	private Date				startTimeForm;
	private Date				endTimeForm;

	public BaselineDetails ()
	{
	}



	public Date getStartTimeForm() {
		return startTimeForm;
	}

	public void setStartTimeForm(Date startTimeForm) {
		this.startTimeForm = startTimeForm;
	}

	public Date getEndTimeForm() {
		return endTimeForm;
	}

	public void setEndTimeForm(Date endTimeForm) {
		this.endTimeForm = endTimeForm;
	}

	public void setSputumOrderNo(Integer sputumOrderNo) {
		this.sputumOrderNo = sputumOrderNo;
	}

	public Date getTreatmentInitiation() {
		return treatmentInitiation;
	}

	public void setTreatmentInitiation(Date treatmentInitiation) {
		this.treatmentInitiation = treatmentInitiation;
	}

	public String getChwId() {
		return chwId;
	}

	public void setChwId(String chwId) {
		this.chwId = chwId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getBaselineSputum() {
		return baselineSputum;
	}

	public void setBaselineSputum(String baselineSputum) {
		this.baselineSputum = baselineSputum;
	}

	public String getBaselineChest() {
		return baselineChest;
	}

	public void setBaselineChest(String baselineChest) {
		this.baselineChest = baselineChest;
	}
	
	public String getOtherXraySite() {
		return otherXraySite;
	}

	public void setOtherXraySite(String xraySite) {
		this.otherXraySite = xraySite;
	}
	
	public String getXrayResult() {
		return xrayResult;
	}

	public void setXrayResult(String xrayResult) {
		this.xrayResult = xrayResult;
	}

	public String getBaselineGeneXpert() {
		return baselineGeneXpert;
	}

	public void setBaselineGeneXpert(String baselineGeneXpert) {
		this.baselineGeneXpert = baselineGeneXpert;
	}

	public String getGeneXpertDrugSensitivity() {
		return geneXpertDrugSensitivity;
	}

	public void setGeneXpertDrugSensitivity(String geneXpertDrugSensitivity) {
		this.geneXpertDrugSensitivity = geneXpertDrugSensitivity;
	}

	public String getTypePatient() {
		return typePatient;
	}

	public void setTypePatient(String typePatient) {
		this.typePatient = typePatient;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public String getFixedDose() {
		return fixedDose;
	}

	public void setFixedDose(String fixedDose) {
		this.fixedDose = fixedDose;
	}

	public String getStrepto() {
		return strepto;
	}

	public void setStrepto(String strepto) {
		this.strepto = strepto;
	}

	public Integer getSputumOrderNo ()
	{
		return this.sputumOrderNo;
	}

	public String getPatientId ()
	{
		return this.patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}



}
