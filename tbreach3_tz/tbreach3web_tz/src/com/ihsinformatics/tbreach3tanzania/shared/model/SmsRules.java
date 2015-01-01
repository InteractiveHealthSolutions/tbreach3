package com.ihsinformatics.tbreach3tanzania.shared.model;

public class SmsRules implements java.io.Serializable
{

	private static final long	serialVersionUID	= -1781517346740667374L;
	private Integer idSmsRules;
	private boolean enable;
	private String description;
	private String targetNumber;
	
	public SmsRules(){
		
	}
	
	public SmsRules(Integer smsRuleId, boolean enable, String description, String target){
		
		this.idSmsRules = smsRuleId;
		this.enable = enable;
		this.description = description;
		this.targetNumber = target;
	
	}
	
	public Integer getSmsRuleId (){
		
		return this.idSmsRules;
	}
	
	public boolean getEnable (){
		
		return this.enable;
	}
	
	public String getDescription (){
		
		return this.description;
	}
	
	public String getTarget (){
		
		return this.targetNumber;
	}

	public void setSmsRuleId (Integer smsRuleId)
	{
		this.idSmsRules = smsRuleId;
	}
	
	public void setEnable (boolean enable)
	{
		this.enable = enable;
	}
	
	public void setDescription (String description)
	{
		this.description = description;
	}
	
	public void setTarget (String target)
	{
		this.targetNumber = target;
	}
	
	public String toString ()
	{
		return idSmsRules+", "+enable+", "+description+", "+targetNumber;
	}
	
}
