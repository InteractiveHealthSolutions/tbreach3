/**
 * This class provides functionality to create and manage SMS-based survey
 */

package com.ihsinformatics.tbr3smssurvey;

import java.util.Date;
import com.ihsinformatics.tbr3smssurvey.exception.DataNotFoundException;
import com.ihsinformatics.tbr3smssurvey.model.Answer;
import com.ihsinformatics.tbr3smssurvey.model.Participant;
import com.ihsinformatics.tbr3smssurvey.model.Question;
import com.ihsinformatics.tbr3smssurvey.model.QuestionId;
import com.ihsinformatics.tbr3smssurvey.model.Survey;
import com.ihsinformatics.tbr3smssurvey.model.Transaction;
import com.ihsinformatics.tbr3smssurvey.util.HibernateUtil;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class SmsSurvey
{
	private static CommonDao	commonDao	= new CommonDao ();
	private static Survey		survey;

	public SmsSurvey (int surveyId)
	{
		survey = (Survey) HibernateUtil.util.findObject ("from Survey where surveyId=" + surveyId);
	}

	/**
	 * Checks whether given text initiates current survey or not
	 * 
	 * @param text
	 * @return
	 */
	public boolean isInitText (String text)
	{
		return survey.getInitText ().equalsIgnoreCase (text);
	}

	/**
	 * Initiates new survey transaction
	 * 
	 * @param participant
	 * @return
	 * @throws DataNotFoundException
	 *             when no questions are defined for the survey
	 */
	public String initiateSurvey (Participant participant) throws DataNotFoundException
	{
		// Start a new transaction
		Transaction transaction = new Transaction (participant.getParticipantId (), survey.getSurveyId (), 1, survey.getInitText (), new Date (), new Date ());
		commonDao.saveTransaction (transaction);
		// Send first question of the survey, if it exists
		QuestionId questionId = new QuestionId (survey.getSurveyId (), 1);
		Question question = commonDao.findQuestionById (questionId);
		if (question != null)
		{
			return question.getQuestionText ();
		}
		throw new DataNotFoundException ("No questions were defined for this survey.");
	}

	/**
	 * Returns welcome message with list of active Surveys and their initiating
	 * method
	 * 
	 * @return
	 */
	public String getWelcomeMessage ()
	{
		StringBuilder welcomeText = new StringBuilder ();
		welcomeText.append ("Welcome to Interactive SMS Survey Service.\n");
		Object[] surveyObjs = HibernateUtil.util.findObjects ("from Survey where isExpired = 0");
		for (Object obj : surveyObjs)
		{
			Survey s = (Survey) obj;
			welcomeText.append ("To start ");
			welcomeText.append (s.getSurveyName () + ",");
			welcomeText.append (" please reply with ");
			welcomeText.append (s.getInitText () + "\n");
		}
		return welcomeText.toString ();
	}

	public String getByeMessage ()
	{
		StringBuilder byeText = new StringBuilder ();
		byeText.append ("Thank you for participating. \n");
		Object[] surveyObjs = HibernateUtil.util.findObjects ("from Survey where isExpired = 0");
		for (Object obj : surveyObjs)
		{
			Survey s = (Survey) obj;
			byeText.append ("To start ");
			byeText.append (s.getSurveyName () + ",");
			byeText.append (" please reply with ");
			byeText.append (s.getInitText () + "\n");
		}
		return byeText.toString ();
	}

	/**
	 * Processes given text and handles active transaction for participant
	 * according to the business logic defined. If the answer resets the survey,
	 * then transaction is closed and survey is initiated again. If the answer
	 * ends the survey, then transaction is closed and endSurvey method is
	 * called. If the answer is not invalid, then the question is repeated. If
	 * the answer is valid and a reply is defined for given answer, the
	 * reply appended with next question is returned and the transaction is updated.
	 * 
	 * @param participant
	 * @param text
	 */
	public String parseAnswer (Participant participant, String text)
	{	
		String response = null;
		// Find last transaction from the participant
		Transaction transaction = commonDao.findLastTransactionByParticipant (participant);
		if (transaction == null)
		{
			return getWelcomeMessage();
			// return initiateSurvey (participant);
		}
		else if(transaction.getQuestionId() == commonDao.findLastQuestionBySurvey(survey).getId().getQuestionId()) 
		{
			return getWelcomeMessage();
		}
		QuestionId questionId = new QuestionId (transaction.getSurveyId (), transaction.getQuestionId ());
		Question question = commonDao.findQuestionById (questionId);
		if (question.getAnswerPattern () != null)
		{
			if (!text.matches (question.getAnswerPattern ()))
			{
				return question.getQuestionText ();
			}
		}
		Answer answer = commonDao.findAnswerByQuestionIdAndAnswer (questionId, text);
		// If the answer is not found, try searching for '*'
		if (answer == null)
		{
			answer = commonDao.findAnswerByQuestionIdAndAnswer (questionId, "*");
			if (answer == null)
			{
				return "Sorry, no default answer choice was defined for this question.";
			}
		}
		if (answer.getResetsSurvey ())
		{
			transaction.setAnswer (text);
			transaction.setDateAnswered (new Date ());
			commonDao.updateTransaction (transaction);
			response = initiateSurvey (participant);
		}
		else if (answer.getEndsSurvey ())
		{
			// TODO: find last question of the survey, send it and end the transaction
			// Update the answer in transaction
			transaction.setAnswer (text);
			transaction.setDateAnswered (new Date ());
			commonDao.updateTransaction (transaction);
			
			// Create new transaction
			Question lastQuestion = commonDao.findLastQuestionBySurvey (survey);
			Transaction newTransaction = new Transaction (participant.getParticipantId (), survey.getSurveyId (), lastQuestion.getId().getQuestionId(), null, new Date(), null);
			commonDao.saveTransaction (newTransaction);
			
			response =  lastQuestion.getQuestionText();
		}
		// Match the text with answer pattern in question
		else
		{
			// Update the answer in transaction
			transaction.setAnswer (text);
			transaction.setDateAnswered (new Date ());
			commonDao.updateTransaction (transaction);
			// Check for response
			StringBuffer reply = new StringBuffer ();
			if (answer.getReply () != null)
			{
				reply.append (answer.getReply ());
				reply.append ("\n");
			}
			// Create new transaction
			QuestionId nextId = new QuestionId (questionId.getSurveyId (), answer.getNextQuestionId ());
			Question nextQuestion = commonDao.findQuestionById (nextId);
			Transaction newTransaction = new Transaction (participant.getParticipantId (), survey.getSurveyId (), nextId.getQuestionId (), null, new Date(), null);
			commonDao.saveTransaction (newTransaction);
			reply.append (nextQuestion.getQuestionText ());
			response = reply.toString ();
		}
		return response;
	}
}
