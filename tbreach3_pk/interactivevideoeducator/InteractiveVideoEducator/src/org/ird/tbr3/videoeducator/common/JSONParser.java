/**
 * This class reads and writes JSON objects/arrays
 */

package org.ird.tbr3.videoeducator.common;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Answer;
import org.ird.tbr3.videoeducator.database.model.Option;
import org.ird.tbr3.videoeducator.database.model.Question;
import org.ird.tbr3.videoeducator.database.model.QuestionType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class JSONParser
{
	private static final String	TAG	= "JSONParser";

	public static JSONObject getJSONObject (String jsonText)
	{
		// try parse the string to a JSON object
		try
		{
			JSONObject jsonObj = new JSONObject (jsonText);
			return jsonObj;
		}
		catch (JSONException e)
		{
			Log.e (TAG, "Error parsing data. " + e.getMessage ());
			return null;
		}
	}

	public static JSONObject[] getJSONArrayFromObject (JSONObject jsonObj, String arrayElement)
	{
		try
		{
			JSONArray jsonArray = jsonObj.getJSONArray (arrayElement);
			JSONObject[] jsonObjects = new JSONObject[jsonArray.length ()];
			for (int i = 0; i < jsonArray.length (); i++)
			{
				jsonObjects[i] = JSONParser.getJSONObject (jsonArray.getString (i));
			}
			return jsonObjects;
		}
		catch (JSONException e)
		{
			Log.e (TAG,
					"Error parsing array from JSON Object using element \'" + arrayElement + "\'. " + e.getMessage ());
			return null;
		}
	}
	
	public List<Answer> parseAnswers(JSONObject jsonObject, long encounterId) {
		try {
		List<Answer> answers = new ArrayList<Answer>();
		DataProvider dataProvider = DataProvider.getInstance();
		
		JSONArray observations = new JSONArray(jsonObject.getString("obs"));
		JSONObject observation;
		Question question;
		Option option;
		String s;
		for(int i=0; i<observations.length(); i++) {
			observation = observations.getJSONObject(i);
			question = dataProvider.getQuestion(observation.getString("concept"));
			if(question.getQuestionType() != QuestionType.OTHER) {
				option = dataProvider.getOption(observation.getString("value"));
				answers.add(new Answer(question.getQuestionId(), encounterId, option.getEnglishText()));
			} else {
				s = observation.getString("value");
				answers.add(new Answer(question.getQuestionId(), encounterId, s));
			}
			
		}
		
		return answers;
		} catch(JSONException jsonException) {
			jsonException.printStackTrace();
		}
		
		return null;
	}
}
