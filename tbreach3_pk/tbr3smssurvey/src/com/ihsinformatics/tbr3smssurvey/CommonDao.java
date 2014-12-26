/**
 * This class is responsible 
 */

package com.ihsinformatics.tbr3smssurvey;

import java.util.Date;
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
public class CommonDao
{
	public CommonDao ()
	{
	}

	/* Delete methods */
	public boolean saveSurvey (Survey survey)
	{
		if (survey.getDateExpire ().before (survey.getDateStart ()))
			return false;
		return HibernateUtil.util.save (survey);
	}

	public boolean saveParticipant (Participant participant)
	{
		return HibernateUtil.util.save (participant);
	}

	public boolean saveQuestion (Question question)
	{
		Object id = HibernateUtil.util.selectObject ("select ifnull(max(question_id), 1) from question where survey_id = " + question.getId ().getSurveyId ());
		QuestionId questionId = new QuestionId (question.getId ().getSurveyId (), Integer.parseInt (id.toString ()));
		question.setId (questionId);
		return HibernateUtil.util.save (question);
	}

	public boolean saveAnswer (Answer answer)
	{
		return HibernateUtil.util.save (answer);
	}

	public boolean saveTransaction (Transaction transaction)
	{
		return HibernateUtil.util.save (transaction);
	}

	/* Update methods */
	public boolean updateSurvey (Survey survey)
	{
		survey.setIsExpired (survey.getDateExpire ().after (new Date ()));
		return HibernateUtil.util.update (survey);
	}

	public boolean updateParticipant (Participant participant)
	{
		return HibernateUtil.util.update (participant);
	}

	public boolean updateQuestion (Question question)
	{
		return HibernateUtil.util.update (question);
	}

	public boolean updateAnswer (Answer answer)
	{
		return HibernateUtil.util.update (answer);
	}

	public boolean updateTransaction (Transaction transaction)
	{
		if (transaction.getDateAnswered () == null)
			transaction.setDateAnswered (new Date ());
		if (transaction.getAnswer () == null)
			transaction.setAnswer ("");
		return HibernateUtil.util.update (transaction);
	}

	/* Delete methods */
	public boolean deleteSurvey (Survey survey)
	{
		return HibernateUtil.util.delete (survey);
	}

	public boolean deleteParticipant (Participant participant)
	{
		return HibernateUtil.util.delete (participant);
	}

	public boolean deleteQuestion (Question question)
	{
		return HibernateUtil.util.delete (question);
	}

	public boolean deleteAnswer (Answer answer)
	{
		return HibernateUtil.util.delete (answer);
	}

	public boolean deleteTransaction (Transaction transaction)
	{
		return HibernateUtil.util.delete (transaction);
	}

	/* Find methods */
	public Survey findSurveyById (int surveyId)
	{
		Survey survey = (Survey) HibernateUtil.util.findObject ("from Survey where surveyId=" + surveyId);
		return survey;
	}

	public Survey findSurveyByName (String surveyName)
	{
		Survey survey = (Survey) HibernateUtil.util.findObject ("from Survey where surveyName='" + surveyName + "'");
		return survey;
	}

	public Participant findParticipantById (int participantId)
	{
		Participant participant = (Participant) HibernateUtil.util.findObject ("from Participant where participantId=" + participantId);
		return participant;
	}

	public Participant findParticipantByContactNo (int contactNo)
	{
		Participant participant = (Participant) HibernateUtil.util.findObject ("from Participant where contactNo='" + contactNo + "'");
		return participant;
	}

	public Question findQuestionById (QuestionId questionId)
	{
		Question question = (Question) HibernateUtil.util.findObject ("from Question where id.surveyId=" + questionId.getSurveyId () + " and id.questionId=" + questionId.getQuestionId ());
		return question;
	}

	public Question[] findQuestionsBySurvey (Survey survey)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Question where questionId.id=" + survey.getSurveyId ());
		Question[] questions = new Question[objs.length];
		for (int i = 0; i < objs.length; i++)
		{
			questions[i] = (Question) objs[i];
		}
		return questions;
	}
	
	public Question findLastQuestionBySurvey (Survey survey)
	{
		return (Question) HibernateUtil.util.findObject ("from Question where id.surveyId=" + survey.getSurveyId ()+" and id.questionId = 7");//(select max(id.questionId) from Question)
		
	}

	public Answer[] findAnswersBySurvey (Survey survey)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Answer where answerId.id.surveyId=" + survey.getSurveyId ());
		Answer[] answers = new Answer[objs.length];
		for (int i = 0; i < objs.length; i++)
		{
			answers[i] = (Answer) objs[i];
		}
		return answers;
	}

	public Answer[] findAnswersByQuestion (Question question)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Answer where answerId.id.surveyId=" + question.getId ().getSurveyId () + " and answerId.id.questionId="
				+ question.getId ().getQuestionId ());
		Answer[] answers = new Answer[objs.length];
		for (int i = 0; i < objs.length; i++)
		{
			answers[i] = (Answer) objs[i];
		}
		return answers;
	}

	public Answer findAnswerByQuestionIdAndAnswer (QuestionId questionId, String answerText)
	{
		Answer answer = (Answer) HibernateUtil.util.findObject ("from Answer where id.surveyId=" + questionId.getSurveyId () + " and id.questionId=" + questionId.getQuestionId () + " and answer='"
				+ answerText + "'");
		return answer;
	}

	public Transaction findTransactionById (int transactionId)
	{
		Transaction transaction = (Transaction) HibernateUtil.util.findObject ("from Transaction where transactionId=" + transactionId);
		return transaction;
	}

	public Transaction[] findTransactionsByParticipant (Participant participant)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Transaction where participantId=" + participant.getParticipantId ());
		Transaction[] transactions = new Transaction[objs.length];
		for (int i = 0; i < objs.length; i++)
			transactions[i] = (Transaction) objs[i];
		return transactions;
	}

	public Transaction findLastTransactionByParticipant (Participant participant)
	{
		Transaction transaction = (Transaction) HibernateUtil.util.findObject ("from Transaction where participantId=" + participant.getParticipantId () + " and dateCreated = (select max(dateCreated) from Transaction where participantId=" + participant.getParticipantId () + ")");
		return transaction;
	}

	public Transaction[] findTransactionsByQuestion (QuestionId questionId)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Transaction where surveyId=" + questionId.getSurveyId () + " and questionId=" + questionId.getQuestionId ());
		Transaction[] transactions = new Transaction[objs.length];
		for (int i = 0; i < objs.length; i++)
			transactions[i] = (Transaction) objs[i];
		return transactions;
	}

	public Transaction[] findTransactionsByParticipantAndQuestion (Participant participant, QuestionId questionId)
	{
		Object[] objs = HibernateUtil.util.findObjects ("from Transaction where participantId = " + participant.getParticipantId () + " and surveyId=" + questionId.getSurveyId () + " and questionId="
				+ questionId.getQuestionId ());
		Transaction[] transactions = new Transaction[objs.length];
		for (int i = 0; i < objs.length; i++)
			transactions[i] = (Transaction) objs[i];
		return transactions;
	}

}
