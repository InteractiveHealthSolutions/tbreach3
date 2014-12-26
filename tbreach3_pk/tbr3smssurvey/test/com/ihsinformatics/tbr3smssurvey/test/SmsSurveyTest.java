/**
 * 
 */

package com.ihsinformatics.tbr3smssurvey.test;

import java.util.Date;
import java.util.UUID;

import junit.framework.TestCase;

import com.ihsinformatics.tbr3smssurvey.SmsSurvey;
import com.ihsinformatics.tbr3smssurvey.model.Participant;
import com.ihsinformatics.tbr3smssurvey.model.Survey;
import com.ihsinformatics.tbr3smssurvey.util.HibernateUtil;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class SmsSurveyTest extends TestCase
{
	HibernateUtil	util	= new HibernateUtil ();
	Survey			survey;
	Participant		participant;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp () throws Exception
	{
		super.setUp ();
		survey = new Survey (0, "Test Survey", "Owais", new Date (), new Date (), false, "TS", UUID.randomUUID ().toString ());
		participant = new Participant (0, "03453174270", "Owais", new Date (), false, UUID.randomUUID ().toString ());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown () throws Exception
	{
		super.tearDown ();
	}

	/**
	 * Test method to check if the initiating text of survey matches
	 * {@link com.ihsinformatics.tbr3smssurvey.SmsSurvey#isInitText(java.lang.String)}
	 * .
	 */
	public final void testIsInitText ()
	{
		assertEquals (survey.getInitText (), "TS");
	}

	/**
	 * Test method to check if a new transaction is created
	 * {@link com.ihsinformatics.tbr3smssurvey.SmsSurvey#initiateSurvey(com.ihsinformatics.tbr3smssurvey.model.Participant)}
	 * .
	 */
	public final void testInitiateSurvey_shouldCreateTransaction ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.SmsSurvey#getWelcomeMessage()}.
	 */
	public final void testGetWelcomeMessage ()
	{
		fail ("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.SmsSurvey#getByeMessage()}.
	 */
	public final void testGetByeMessage ()
	{
		fail ("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.SmsSurvey#parseAnswer(com.ihsinformatics.tbr3smssurvey.model.Participant, java.lang.String)}
	 * .
	 */
	public final void testParseAnswer ()
	{
		participant = new Participant (7, "03453174270", "Owais", new Date (), false, UUID.randomUUID ().toString ());
		SmsSurvey  smsSurvey = new SmsSurvey(1);
		// assertNotEquals("", smsSurvey.parseAnswer(participant, ""));
		
		// Test to check how code works if wrong 6 digit id is passed
		/*assertThat(smsSurvey.parseAnswer(participant, "wrongid"), is("Welcome to the Sehatmand Zindagi Health Challenge. Please reply with 6-digit location ID."));
		assertThat(smsSurvey.parseAnswer(participant, "wrongid"), is(not("True/False: Only at Sehatmand Zindagi can you find affordable, high quality GeneXpert, Spirometry, digital x rays, and RBS/FBS tests. Reply with T/F.")));*/
		
		// Test to check how code works if correct 6 digit is passed
		/*assertThat(smsSurvey.parseAnswer(participant, "101032"), is("True/False: Only at Sehatmand Zindagi can you find affordable, high quality GeneXpert, Spirometry, digital x rays, and RBS/FBS tests. Reply with T/F."));*/
		
		//  Test to check how code works if wrong answer to a question is passed
		/*assertThat(smsSurvey.parseAnswer(participant, "some wrong digit"), is("True/False: Guideline-recommended Spirometry testing helps patients manage & control their asthma & COPD symptoms. Reply with T/F."));
		assertThat(smsSurvey.parseAnswer(participant, "tata"), is("True/False: Guideline-recommended Spirometry testing helps patients manage & control their asthma & COPD symptoms. Reply with T/F."));
		assertThat(smsSurvey.parseAnswer(participant, "true"), is("True/False: Guideline-recommended Spirometry testing helps patients manage & control their asthma & COPD symptoms. Reply with T/F."));
		assertThat(smsSurvey.parseAnswer(participant, "false"), is("True/False: Guideline-recommended Spirometry testing helps patients manage & control their asthma & COPD symptoms. Reply with T/F."));*/
		
	//  Test to check how code works if correct answer to above question is passed
		 /*assertThat(smsSurvey.parseAnswer(participant, "T"), is("True/False: Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F."));	
		 assertThat(smsSurvey.parseAnswer(participant, "t"), is("True/False: Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F."));
		 assertThat(smsSurvey.parseAnswer(participant, "f"), is("True/False: Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F."));
		 assertThat(smsSurvey.parseAnswer(participant, "true"), is("True/False: Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F."));
		 assertThat(smsSurvey.parseAnswer(participant, "false"), is("True/False: Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F."));*/
		
	//  Test to check reset suvey works
		/*assertThat(smsSurvey.parseAnswer(participant, "R"), is("Welcome to the Sehatmand Zindagi Health Challenge. Please reply with 6-digit location ID."));
		assertThat(smsSurvey.parseAnswer(participant, "R"), is(not("Welcome to the Sehatmand Zindagi Health Challenge. Please reply with 6-digit location ID.")));*/
		
	//  Test to check reset suvey works
			// assertThat(smsSurvey.parseAnswer(participant, "X"), is("Thank you for participating in our SMS Competition. We look forward to your attendance at our Grand Game Night on Saturday, the 23rd of August."));
			// assertThat(smsSurvey.parseAnswer(participant, "X"), is(not("Thank you for participating in our SMS Competition. We look forward to your attendance at our Grand Game Night on Saturday, the 23rd of August.")));
	}

}


	
	


