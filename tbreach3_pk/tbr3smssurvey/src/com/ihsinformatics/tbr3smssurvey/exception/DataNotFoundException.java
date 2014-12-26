/**
 * 
 */
package com.ihsinformatics.tbr3smssurvey.exception;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class DataNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8451419981007260205L;

	/**
	 * 
	 */
	public DataNotFoundException ()
	{
	}
	
	/**
	 * @param message
	 */
	public DataNotFoundException (String message)
	{
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public DataNotFoundException (Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public DataNotFoundException (String message, Throwable cause)
	{
		super(message, cause);
	}
}
