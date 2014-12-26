/**
 * 
 */
package com.ihsinformatics.tbr3smssurvey.exception;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class DataAccessException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5591440331049676770L;

	/**
	 * 
	 */
	public DataAccessException ()
	{
	}

	/**
	 * @param message
	 */
	public DataAccessException (String message)
	{
		super (message);
	}

	/**
	 * @param cause
	 */
	public DataAccessException (Throwable cause)
	{
		super (cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException (String message, Throwable cause)
	{
		super (message, cause);
	}

}
