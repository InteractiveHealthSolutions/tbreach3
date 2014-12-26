/**
 * 
 */

package com.ihsinformatics.tbr3smssurvey.exception;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class DmlException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5733468923306909816L;

	/**
	 * 
	 */
	public DmlException ()
	{
	}

	/**
	 * @param message
	 */
	public DmlException (String message)
	{
		super (message);
	}

	/**
	 * @param cause
	 */
	public DmlException (Throwable cause)
	{
		super (cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DmlException (String message, Throwable cause)
	{
		super (message, cause);
	}

}
