package org.ird.tbr3.videoeducator.database.data_access;

import java.util.List;

import org.ird.tbr3.videoeducator.database.model.Answer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AnswerDAO {
	
	Context context;
	public AnswerDAO(Context context) {
		this.context = context;
	}
	
public void insert(List<Answer> answers) {
		
		String query = "insert into " + DatabaseHandler.TABLE_ANSWERS + " ("+Answer.COLUMN_TEXT+", "+Answer.COLUMN_QUESTION_ID+", "+Answer.COLUMN_ENCOUNTER_ID+") values";
		for (int i = 0; i < answers.size(); i++) {
			if (i < answers.size() - 1) {
				query += "('" + answers.get(i).getText() + "', " + answers.get(i).getQuestionId() + ", " + answers.get(i).getEncounterId() + "),";
			} else {
				query += "('" + answers.get(i).getText() + "', " + answers.get(i).getQuestionId() + ", " + answers.get(i).getEncounterId() + ")";
			}
			
		}
		
		query += ";";
		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
		
		db.execSQL(query);
	}

	
	
}
