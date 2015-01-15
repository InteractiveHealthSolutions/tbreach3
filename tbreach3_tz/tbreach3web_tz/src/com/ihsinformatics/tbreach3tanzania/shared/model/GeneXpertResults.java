/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
// default package
// Generated Jan 6, 2011 10:24:45 AM by Hibernate Tools 3.4.0.Beta1
package com.ihsinformatics.tbreach3tanzania.shared.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * GeneXpertResults generated by hbm2java
 */
@Entity
public class GeneXpertResults implements java.io.Serializable
{


	private static final long	serialVersionUID	= 5200839127427690164L;

	private Integer					testId;
	
	private String				sputumTestId;
	private String				patientId;
	//private Integer				irs;
	private String				laboratoryId;
	private String				collectedBy;
	private Date				dateSubmitted;
	private Date				dateTested;
	private String				geneXpertResult;
	private Boolean				isPositive;
	private String				mtbBurden;
	private String				drugResistance;
	private Integer				errorCode;
	private String				remarks;
	
	@Id
	@GeneratedValue
	private String 				pcId;
	private String 				instrumentId;
	private String 				moduleId;
	private String 				cartridgeId;
	private String 				reagentLotId;
	private String 				cartirdigeExpiryDate;
	private String				operatorId;
	
	private String 				probeResultA;
	private String 				probeResultB;
	private String 				probeResultC;
	private String 				probeResultD;
	private String 				probeResultE;
	private String 				probeResultSPC;
	
	private Double 				probeCtA;
	private Double 				probeCtB;
	private Double 				probeCtC;
	private Double 				probeCtD;
	private Double 				probeCtE;
	private Double 				probeCtSPC;
	
	private Double 				probeEndptA;
	private Double 				probeEndptB;
	private Double 				probeEndptC;
	private Double 				probeEndptD;
	private Double 				probeEndptE;
	private Double 				probeEndptSPC;
	
	public GeneXpertResults(String sputumTestId, String patientId, String laboratoryId, String collectedBy, Date dateSubmitted, Date dateTested,
			String geneXpertResult, Boolean isPositive, String mtbBurden, String drugResistance, Integer errorCode, String remarks, String pcId,
			String instrumentSerial, String moduleId, String cartridgeId, String reagentLotId, String expDate, String probeResultA,
			String probeResultB, String probeResultC, String probeResultD, String probeResultE, String probeResultSPC, Double probeCtA,
			Double probeCtB, Double probeCtC, Double probeCtD, Double probeCtE, Double probeCtSPC, Double probeEndptA, Double probeEndptB,
			Double probeEndptC, Double probeEndptD, Double probeEndptE, Double probeEndptSPC)
	{
		super();
		this.sputumTestId = sputumTestId;
		this.patientId = patientId;
		this.laboratoryId = laboratoryId;
		this.collectedBy = collectedBy;
		this.dateSubmitted = dateSubmitted;
		this.dateTested = dateTested;
		this.geneXpertResult = geneXpertResult;
		this.isPositive = isPositive;
		this.mtbBurden = mtbBurden;
		this.drugResistance = drugResistance;
		this.errorCode = errorCode;
		this.remarks = remarks;
		this.pcId = pcId;
		this.instrumentId = instrumentSerial;
		this.moduleId = moduleId;
		this.cartridgeId = cartridgeId;
		this.reagentLotId = reagentLotId;
		this.cartirdigeExpiryDate = expDate;
		this.probeResultA = probeResultA;
		this.probeResultB = probeResultB;
		this.probeResultC = probeResultC;
		this.probeResultD = probeResultD;
		this.probeResultE = probeResultE;
		this.probeResultSPC = probeResultSPC;
		this.probeCtA = probeCtA;
		this.probeCtB = probeCtB;
		this.probeCtC = probeCtC;
		this.probeCtD = probeCtD;
		this.probeCtE = probeCtE;
		this.probeCtSPC = probeCtSPC;
		this.probeEndptA = probeEndptA;
		this.probeEndptB = probeEndptB;
		this.probeEndptC = probeEndptC;
		this.probeEndptD = probeEndptD;
		this.probeEndptE = probeEndptE;
		this.probeEndptSPC = probeEndptSPC;
	}

	

	public GeneXpertResults()
	{
		// Not implemented
	}

	public GeneXpertResults(String sputumTestId, String patientId)
	{
		this.sputumTestId = sputumTestId;
		this.patientId = patientId;
	}

	/*public GeneXpertResults(String sputumTestId, String patientId, String laboratoryId, String collectedBy, Date dateSubmitted,
			Date dateTested, String geneXpertResult, Boolean isPositive, String mtbBurden, String drugResistance, Integer errorCode, String remarks)
	{
		this.sputumTestId = sputumTestId;
		this.patientId = patientId;
		//this.irs = irs;
		this.laboratoryId = laboratoryId;
		this.collectedBy = collectedBy;
		this.dateSubmitted = dateSubmitted;
		this.dateTested = dateTested;
		this.geneXpertResult = geneXpertResult;
		this.isPositive = isPositive;
		this.mtbBurden = mtbBurden;
		this.drugResistance = drugResistance;
		this.errorCode = errorCode;
		this.remarks = remarks;
	}*/

	public String getSputumTestId()
	{
		return this.sputumTestId;
	}

	public void setSputumTestId(String sputumTestId)
	{
		this.sputumTestId = sputumTestId;
	}

	public String getPatientId()
	{
		return this.patientId;
	}

	public void setPatientId(String patientId)
	{
		this.patientId = patientId;
	}

	/*public Integer getIrs()
	{
		return this.irs;
	}

	public void setIrs(Integer irs)
	{
		this.irs = irs;
	}*/

	public String getLaboratoryId()
	{
		return this.laboratoryId;
	}

	public void setLaboratoryId(String laboratoryId)
	{
		this.laboratoryId = laboratoryId;
	}

	public String getCollectedBy()
	{
		return this.collectedBy;
	}

	public void setCollectedBy(String collectedBy)
	{
		this.collectedBy = collectedBy;
	}

	public Date getDateSubmitted()
	{
		return this.dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted)
	{
		this.dateSubmitted = dateSubmitted;
	}

	public Date getDateTested()
	{
		return this.dateTested;
	}

	public void setDateTested(Date dateTested)
	{
		this.dateTested = dateTested;
	}

	public String getGeneXpertResult()
	{
		return geneXpertResult;
	}

	public void setGeneXpertResult(String geneXpertResult)
	{
		this.geneXpertResult = geneXpertResult;
	}

	public Boolean getIsPositive()
	{
		return this.isPositive;
	}

	public void setIsPositive(Boolean isPositive)
	{
		this.isPositive = isPositive;
	}

	public String getMtbBurden()
	{
		return mtbBurden;
	}

	public void setMtbBurden(String mtbBurden)
	{
		this.mtbBurden = mtbBurden;
	}

	public String getDrugResistance()
	{
		return this.drugResistance;
	}

	public void setDrugResistance(String drugResistance)
	{
		this.drugResistance = drugResistance;
	}

	public Integer getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(Integer errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getRemarks()
	{
		return this.remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * @return the pcId
	 */
	public String getPcId()
	{
		return pcId;
	}

	/**
	 * @param pcId the pcId to set
	 */
	public void setPcId(String pcId)
	{
		this.pcId = pcId;
	}

	/**
	 * @return the instrumentSerial
	 */
	public String getInstrumentSerial()
	{
		return instrumentId;
	}

	/**
	 * @param instrumentSerial the instrumentSerial to set
	 */
	public void setInstrumentSerial(String instrumentSerial)
	{
		this.instrumentId = instrumentSerial;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId()
	{
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId)
	{
		this.moduleId = moduleId;
	}

	/**
	 * @return the cartrigeId
	 */
	public String getCartridgeId()
	{
		return cartridgeId;
	}

	/**
	 * @param cartrigeId the cartrigeId to set
	 */
	public void setCartridgeId(String cartrigeId)
	{
		this.cartridgeId = cartrigeId;
	}

	/**
	 * @return the reagentLotId
	 */
	public String getReagentLotId()
	{
		return reagentLotId;
	}

	/**
	 * @param reagentLotId the reagentLotId to set
	 */
	public void setReagentLotId(String reagentLotId)
	{
		this.reagentLotId = reagentLotId;
	}

	/**
	 * @return the expDate
	 */
	public String getExpDate()
	{
		return cartirdigeExpiryDate;
	}

	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(String expDate)
	{
		this.cartirdigeExpiryDate = expDate;
	}

	/**
	 * @return the probeResultA
	 */
	public String getProbeResultA()
	{
		return probeResultA;
	}

	/**
	 * @param probeResultA the probeResultA to set
	 */
	public void setProbeResultA(String probeResultA)
	{
		this.probeResultA = probeResultA;
	}

	/**
	 * @return the probeResultB
	 */
	public String getProbeResultB()
	{
		return probeResultB;
	}

	/**
	 * @param probeResultB the probeResultB to set
	 */
	public void setProbeResultB(String probeResultB)
	{
		this.probeResultB = probeResultB;
	}

	/**
	 * @return the probeResultC
	 */
	public String getProbeResultC()
	{
		return probeResultC;
	}

	/**
	 * @param probeResultC the probeResultC to set
	 */
	public void setProbeResultC(String probeResultC)
	{
		this.probeResultC = probeResultC;
	}

	/**
	 * @return the probeResultD
	 */
	public String getProbeResultD()
	{
		return probeResultD;
	}

	/**
	 * @param probeResultD the probeResultD to set
	 */
	public void setProbeResultD(String probeResultD)
	{
		this.probeResultD = probeResultD;
	}

	/**
	 * @return the probeResultE
	 */
	public String getProbeResultE()
	{
		return probeResultE;
	}

	/**
	 * @param probeResultE the probeResultE to set
	 */
	public void setProbeResultE(String probeResultE)
	{
		this.probeResultE = probeResultE;
	}

	/**
	 * @return the probeResultSPC
	 */
	public String getProbeResultSPC()
	{
		return probeResultSPC;
	}

	/**
	 * @param probeResultSPC the probeResultSPC to set
	 */
	public void setProbeResultSPC(String probeResultSPC)
	{
		this.probeResultSPC = probeResultSPC;
	}

	/**
	 * @return the probeCtA
	 */
	public Double getProbeCtA()
	{
		return probeCtA;
	}

	/**
	 * @param probeCtA the probeCtA to set
	 */
	public void setProbeCtA(Double probeCtA)
	{
		this.probeCtA = probeCtA;
	}

	/**
	 * @return the probeCtB
	 */
	public Double getProbeCtB()
	{
		return probeCtB;
	}

	/**
	 * @param probeCtB the probeCtB to set
	 */
	public void setProbeCtB(Double probeCtB)
	{
		this.probeCtB = probeCtB;
	}

	/**
	 * @return the probeCtC
	 */
	public Double getProbeCtC()
	{
		return probeCtC;
	}

	/**
	 * @param probeCtC the probeCtC to set
	 */
	public void setProbeCtC(Double probeCtC)
	{
		this.probeCtC = probeCtC;
	}

	/**
	 * @return the probeCtD
	 */
	public Double getProbeCtD()
	{
		return probeCtD;
	}

	/**
	 * @param probeCtD the probeCtD to set
	 */
	public void setProbeCtD(Double probeCtD)
	{
		this.probeCtD = probeCtD;
	}

	/**
	 * @return the probeCtE
	 */
	public Double getProbeCtE()
	{
		return probeCtE;
	}

	/**
	 * @param probeCtE the probeCtE to set
	 */
	public void setProbeCtE(Double probeCtE)
	{
		this.probeCtE = probeCtE;
	}

	/**
	 * @return the probeCtSPC
	 */
	public Double getProbeCtSPC()
	{
		return probeCtSPC;
	}

	/**
	 * @param probeCtSPC the probeCtSPC to set
	 */
	public void setProbeCtSPC(Double probeCtSPC)
	{
		this.probeCtSPC = probeCtSPC;
	}

	/**
	 * @return the probeEndptA
	 */
	public Double getProbeEndptA()
	{
		return probeEndptA;
	}

	/**
	 * @param probeEndptA the probeEndptA to set
	 */
	public void setProbeEndptA(Double probeEndptA)
	{
		this.probeEndptA = probeEndptA;
	}

	/**
	 * @return the probeEndptB
	 */
	public Double getProbeEndptB()
	{
		return probeEndptB;
	}

	/**
	 * @param probeEndptB the probeEndptB to set
	 */
	public void setProbeEndptB(Double probeEndptB)
	{
		this.probeEndptB = probeEndptB;
	}

	/**
	 * @return the probeEndptC
	 */
	public Double getProbeEndptC()
	{
		return probeEndptC;
	}

	/**
	 * @param probeEndptC the probeEndptC to set
	 */
	public void setProbeEndptC(Double probeEndptC)
	{
		this.probeEndptC = probeEndptC;
	}

	/**
	 * @return the probeEndptD
	 */
	public Double getProbeEndptD()
	{
		return probeEndptD;
	}

	/**
	 * @param probeEndptD the probeEndptD to set
	 */
	public void setProbeEndptD(Double probeEndptD)
	{
		this.probeEndptD = probeEndptD;
	}

	/**
	 * @return the probeEndptE
	 */
	public Double getProbeEndptE()
	{
		return probeEndptE;
	}

	/**
	 * @param probeEndptE the probeEndptE to set
	 */
	public void setProbeEndptE(Double probeEndptE)
	{
		this.probeEndptE = probeEndptE;
	}

	/**
	 * @return the probeEndptSPC
	 */
	public Double getProbeEndptSPC()
	{
		return probeEndptSPC;
	}

	/**
	 * @param probeEndptSPC the probeEndptSPC to set
	 */
	public void setProbeEndptSPC(Double probeEndptSPC)
	{
		this.probeEndptSPC = probeEndptSPC;
	}

	/**
	 * @return the testId
	 */
	public Integer getTestId() {
		return testId;
	}



	/**
	 * @param testId the testId to set
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}



	/**
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}



	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "cartridgeId=" + cartridgeId + ", collectedBy=" + collectedBy + ", dateSubmitted=" + dateSubmitted + ", dateTested="
				+ dateTested + ", drugResistance=" + drugResistance + ", errorCode=" + errorCode + ", expDate=" + cartirdigeExpiryDate + ", geneXpertResult="
				+ geneXpertResult + ", instrumentSerial=" + instrumentId + ", isPositive=" + isPositive + ", laboratoryId=" + laboratoryId
				+ ", moduleId=" + moduleId + ", mtbBurden=" + mtbBurden + ", patientId=" + patientId + ", pcId=" + pcId + ", probeCtA=" + probeCtA
				+ ", probeCtB=" + probeCtB + ", probeCtC=" + probeCtC + ", probeCtD=" + probeCtD + ", probeCtE=" + probeCtE + ", probeCtSPC="
				+ probeCtSPC + ", probeEndptA=" + probeEndptA + ", probeEndptB=" + probeEndptB + ", probeEndptC=" + probeEndptC + ", probeEndptD="
				+ probeEndptD + ", probeEndptE=" + probeEndptE + ", probeEndptSPC=" + probeEndptSPC + ", probeResultA=" + probeResultA
				+ ", probeResultB=" + probeResultB + ", probeResultC=" + probeResultC + ", probeResultD=" + probeResultD + ", probeResultE="
				+ probeResultE + ", probeResultSPC=" + probeResultSPC + ", reagentLotId=" + reagentLotId + ", remarks=" + remarks + ", sputumTestId="
				+ sputumTestId;
	}

}
