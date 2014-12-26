/**
 * This test class uses a utility, Ninja Squad to clean and fill data in DB under observation
 */
package com.ihsinformatics.tbr3smssurvey.test;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ihsinformatics.tbr3smssurvey.CommonDao;
import com.ihsinformatics.tbr3smssurvey.model.Participant;
import com.ihsinformatics.tbr3smssurvey.model.Survey;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class CommonDaoTest
{
	private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	DbSetup	dbSetup;
	CommonDao				commonDao;
	Survey					survey;
	Survey					testSurvey;
	ArrayList<Participant>	participants;
	
	public static final Operation	DELETE_ALL			= deleteAllFrom ("survey", "participant", "transaction", "question", "answer");
	public static final Operation	INSERT_SURVEY_DATA	= sequenceOf (insertInto ("survey")
			.columns ("survey_id", "survey_name", "surveyer", "date_start", "date_expire", "is_expired", "init_text", "uuid")
			.values (1, "Sehatmand Zindagi Challenge", "Owais", "2014-08-04", "2014-08-04", false, "SZ", UUID.randomUUID ().toString ())
			.values (2, "Global Tuberculosis Survey", "Owais", "2014-08-04", "2014-08-04", false, "TB", UUID.randomUUID ().toString ()).build ());
	public static final Operation	INSERT_QUESTION_DATA	= sequenceOf (insertInto ("question")
			.columns ("survey_id", "question_id", "question_text", "answer_pattern", "uuid")
			.values (1, 1, "Welcome to the Sehatmand Zindagi Health Challenge. Please reply with 6-digit location ID.", "[0-9]{6,6}", UUID.randomUUID ().toString ())
			.values (1, 2, "Only at Sehatmand Zindagi can you find affordable, high quality GeneXpert, Spirometry, digital x rays, and RBS/FBS tests. Reply with T/F.", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (1, 3, "Guideline-recommended Spirometry testing helps patients manage & control their asthma & COPD symptoms. Reply with T/F.", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (1, 4, "Sehatmand Zindagi digital X-rays uses a very high dose of radiation and needs a long time for producing images. Reply with T/F.", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (1, 5, "Tests and treatment provided to patients are not approved by any globally-recognized organization. Reply with T/F.", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (1, 6, "If a patient tests positive for diabetes through SZ RBS/FPS testing, Daonil and Glucophage can be prescribed to your patients for free. Reply with T/F", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (1, 7, "Thank you for participating in our SMS Competition. We look forward to your attendance at our Grand Game Night on Saturday, the 23rd of August.", "[T|F|R|X]", UUID.randomUUID ().toString ())
			.values (2, 1, "Welcome to TB Screening. We will ask you 4 questions to determine whether you are a TB suspect or not. Please reply with Y to start screening", "[Y]", UUID.randomUUID ().toString ())
			.values (2, 2, "Y/N. Do you have productive cough for more than 2 weeks?", "[Y|N]", UUID.randomUUID ().toString ())
			.values (2, 3, "Y/N. Have you observed unexplained weight loss, fever or night sweats?", "[Y|N]", UUID.randomUUID ().toString ())
			.values (2, 4, "Y/N. Does anyone in your household have TB, or under treatment of TB?", "[Y|N]", UUID.randomUUID ().toString ())
			.values (2, 5, "Thank you for participating.", null, UUID.randomUUID ().toString ()).build ());
	public static final Operation	INSERT_ANSWER_DATA	= sequenceOf (insertInto ("answer")
			.columns ("survey_id", "question_id", "answer_id", "answer", "reply", "next_question_id", "resets_survey", "ends_survey", "uuid")
			.values (1, 1, 1, "*", "NULL", 2, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 1, 2, "R", "NULL", 1, 1, 1, UUID.randomUUID ().toString ())
			.values (1, 1, 3, "X", "NULL", 7, 0, 1, UUID.randomUUID ().toString ())
			.values (1, 2, 1, "T", "The correct answer is \"True\"", 3, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 2, 2, "F", "The correct answer is \"True\"", 3, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 2, 3, "R", "NULL", 1, 1, 0, UUID.randomUUID ().toString ())
			.values (1, 2, 4, "X", "NULL", 7, 0, 1, UUID.randomUUID ().toString ())
			.values (1, 3, 1, "T", "The correct answer is \"True\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 3, 2, "F", "The correct answer is \"True\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 3, 3, "R", "NULL", 1, 1, 0, UUID.randomUUID ().toString ())
			.values (1, 3, 4, "X", "NULL", 7, 0, 1, UUID.randomUUID ().toString ())
			.values (1, 4, 1, "T", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 4, 2, "F", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 4, 3, "R", "NULL", 1, 1, 0, UUID.randomUUID ().toString ())
			.values (1, 4, 4, "X", "NULL", 7, 0, 1, UUID.randomUUID ().toString ())
			.values (1, 5, 1, "T", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 5, 2, "F", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 5, 3, "R", "NULL", 1, 1, 0, UUID.randomUUID ().toString ())
			.values (1, 5, 4, "X", "NULL", 7, 0, 1, UUID.randomUUID ().toString ())
			.values (1, 6, 1, "T", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 6, 2, "F", "The correct answer is \"False\"", 4, 0, 0, UUID.randomUUID ().toString ())
			.values (1, 6, 3, "R", "NULL", 1, 1, 0, UUID.randomUUID ().toString ())
			.values (1, 6, 4, "X", "NULL", 7, 1, 0, UUID.randomUUID ().toString ())
			.build ());
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception
	{
		commonDao = new CommonDao ();
		survey = new Survey ();
		testSurvey = new Survey ();
		
		Operation operation = sequenceOf (DELETE_ALL, INSERT_SURVEY_DATA, INSERT_QUESTION_DATA, INSERT_ANSWER_DATA);
		dbSetup = new DbSetup (new DriverManagerDestination ("jdbc:mysql://localhost:3306/smssurvey", "root", "jingle94"), operation);
		dbSetup.launch ();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception
	{
	}

	/**
	 * Test method to save Survey
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldSaveSurvey ()
	{
		survey = new Survey (0, "Sehatmand Zindagi Challenge", "TBR3", new Date (), new Date (), false, "SZ", UUID.randomUUID ().toString ());
		assertTrue (commonDao.saveSurvey (survey));
	}

	/**
	 * Test method for invalid expiry date (before start date)
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldNotSaveSurvey ()
	{
		Calendar expiry = Calendar.getInstance ();
		expiry.set (Calendar.YEAR, expiry.get (Calendar.YEAR) - 1);
		testSurvey = new Survey (0, "Bubblegum Survey", "Candyland", new Date (), expiry.getTime (), false, "BB", UUID.randomUUID ().toString ());
		assertFalse ("Date of expiry should not be before date of creation.", commonDao.saveSurvey (testSurvey));
	}

	/**
	 * Test method to save Participant objects
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveParticipant(com.ihsinformatics.tbr3smssurvey.model.Participant)}
	 * .
	 */
	@Test
	public final void shouldSaveParticipant ()
	{
		participants = new ArrayList<Participant> ();
		participants.add (new Participant (0, "03453174270", "Owais", new Date (), false, UUID.randomUUID ().toString ()));
		participants.add (new Participant (0, "+923233258483", "Omar Ahmed", new Date (), true, UUID.randomUUID ().toString ()));
		participants.add (new Participant (0, "00923452345345", "Maimoona", new Date (), false, UUID.randomUUID ().toString ()));
		for (Participant participant : participants)
			assertTrue (commonDao.saveParticipant (participant));
	}

	/**
	 * Test method to save Question
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void shouldSaveQuestion ()
	{
	}

	/**
	 * Test method for invalid question, without providing valid answer pattern (if provided)
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void shouldNotSaveQuestion ()
	{
	}

	/**
	 * Test method to save Answers against a question
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveAnswer(com.ihsinformatics.tbr3smssurvey.model.Answer)}
	 * .
	 */
	@Test
	public final void shouldSaveAnswer ()
	{
	}

	/**
	 * Test method to save answer of a non-existing question
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveAnswer(com.ihsinformatics.tbr3smssurvey.model.Answer)}
	 * .
	 */
	@Test
	public final void shouldNotSaveAnswer ()
	{
	}

	/**
	 * Test method to save a Transaction
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveTransaction(com.ihsinformatics.tbr3smssurvey.model.Transaction)}
	 * .
	 */
	@Test
	public final void shouldSaveTransaction ()
	{
	}

	/**
	 * Test method to save invalid Transaction
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#saveTransaction(com.ihsinformatics.tbr3smssurvey.model.Transaction)}
	 * .
	 */
	@Test
	public final void shouldNotSaveTransaction ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldUpdateSurvey ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldExpireSurvey ()
	{
		testSurvey = new Survey (0, "Bubblegum Survey", "Candyland", new Date (), new Date (), false, "BB", UUID.randomUUID ().toString ());
		commonDao.saveSurvey (testSurvey);
		Calendar dateExpired = Calendar.getInstance ();
		dateExpired.set (Calendar.YEAR, dateExpired.get (Calendar.YEAR) + 1);
		testSurvey.setDateExpire (dateExpired.getTime ());
		commonDao.updateSurvey (testSurvey);
		assertTrue ("Survey should be set to expired.", commonDao.findSurveyByName (testSurvey.getSurveyName ()).getIsExpired ());
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateParticipant(com.ihsinformatics.tbr3smssurvey.model.Participant)}
	 * .
	 */
	@Test
	public final void shouldUpdateParticipant ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void shouldUpdateQuestion ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void shouldNotUpdateQuestion ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateAnswer(com.ihsinformatics.tbr3smssurvey.model.Answer)}
	 * .
	 */
	@Test
	public final void shouldUpdateAnswer ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateTransaction(com.ihsinformatics.tbr3smssurvey.model.Transaction)}
	 * .
	 */
	@Test
	public final void shouldUpdateTransaction ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#updateTransaction(com.ihsinformatics.tbr3smssurvey.model.Transaction)}
	 * .
	 */
	@Test
	public final void shouldUpdateTransactionAnswer ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldDeleteSurvey ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteSurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void shouldNotDeleteSurvey ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteParticipant(com.ihsinformatics.tbr3smssurvey.model.Participant)}
	 * .
	 */
	@Test
	public final void shouldDeleteParticipant ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteParticipant(com.ihsinformatics.tbr3smssurvey.model.Participant)}
	 * .
	 */
	@Test
	public final void shouldNotDeleteParticipant ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void shouldDeleteQuestion ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteAnswer(com.ihsinformatics.tbr3smssurvey.model.Answer)}
	 * .
	 */
	@Test
	public final void shouldDeleteAnswer ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#deleteTransaction(com.ihsinformatics.tbr3smssurvey.model.Transaction)}
	 * .
	 */
	@Test
	public final void shouldDeleteTransaction ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findSurveyById(int)}.
	 */
	@Test
	public final void testFindSurveyById ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findSurveyByName(java.lang.String)}
	 * .
	 */
	@Test
	public final void testFindSurveyByName ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findParticipantById(int)}
	 * .
	 */
	@Test
	public final void testFindParticipantById ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findParticipantByContactNo(int)}
	 * .
	 */
	@Test
	public final void testFindParticipantByContactNo ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findQuestionById(int)}.
	 */
	@Test
	public final void testFindQuestionById ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findQuestionsBySurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void testFindQuestionsBySurvey ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findAnswersBySurvey(com.ihsinformatics.tbr3smssurvey.model.Survey)}
	 * .
	 */
	@Test
	public final void testFindAnswersBySurvey ()
	{
	}

	/**
	 * Test method for
	 * {@link com.ihsinformatics.tbr3smssurvey.CommonDao#findAnswersByQuestion(com.ihsinformatics.tbr3smssurvey.model.Question)}
	 * .
	 */
	@Test
	public final void testFindAnswersByQuestion ()
	{
	}

}
