package org.ird.tbr3.videoeducator.report;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.data_access.DatabaseHandler;
import org.ird.tbr3.videoeducator.database.model.Answer;
import org.ird.tbr3.videoeducator.database.model.Encounter;
import org.ird.tbr3.videoeducator.database.model.EncounterType;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReportsGenerator {

	Context context;

	public ReportsGenerator(Context context) {

		this.context = context;
	}

	public List<String[]> getReport(String month) {
		List<String[]> data = new ArrayList<String[]>();
		String query = "select e."+Encounter.COLUMN_PATIENT_MR_NUMBER+ ", e."+Encounter.COLUMN_PATIENT_NAME+ ", e."+Encounter.COLUMN_PATIENT_AGE+ ", a."+Answer.COLUMN_QUESTION_ID
						+ ", a."+Answer.COLUMN_TEXT+ ", a."+Answer.COLUMN_ENCOUNTER_ID+ ", e."+Encounter.COLUMN_ENCOUNTER_TYPE_ID            
					
					+" from "+DatabaseHandler.TABLE_ANSWERS+" a inner join "
					+DatabaseHandler.TABLE_ENCOUNTERS +" e on e."
					+Encounter.COLUMN_ENCOUNTER_ID+" = a."+Answer.COLUMN_ENCOUNTER_ID
					+" where "+Encounter.COLUMN_ENCOUNTER_DATE +" like '%"+month+"'"; 
		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.getCount()>0) {
			while (c.moveToNext()) {
				data.add(putQuestionText(new String[]{c.getString(0), c.getString(1), c.getInt(2)+"", c.getInt(3)+"", c.getString(4), c.getInt(5)+"", c.getInt(6)+""}));	
			}
			
			return processData(data);
		} 

		return null;
	}
	
	private String[] putQuestionText(String[] data) {
		data[3] = DataProvider.getInstance().getQuestion(Integer.parseInt(data[3])).getEnglishText();
		// data[4] = getEncounterName(Integer.parseInt(data[4]));
		return data;
	}
	
	private List<String[]> processData(List<String[]> data) {
		List<String[]> toReturn = new ArrayList<String[]>();
		
		boolean headersConsumed = false;
		int length = getNumberOfAnswers(Integer.parseInt(data.get(0)[5]));;
		
		String[] row = new String[length+3];
		String[] header = new String[length+3];
		header[0] = "Mr Number";
		header[1] = "Name";
		header[2] = "Age";
		int i=3;
		boolean isFirst = true;
		for(String[] s:data) {
			
			if(i<length+3) {
				if(isFirst) {
					row[0] = s[0];
					row[1] = s[1];
					row[2] = s[2];
					
				}
				if(!headersConsumed) {
					header[i] = s[3];
					row[i] = s[4];
				} else {
					row[i] = s[4];
				}
				isFirst = false;
			} else {
				
				i = 3;
				isFirst = true;
				if(!headersConsumed) {
					toReturn.add(header);
				}
				
				toReturn.add(row);
				row = new String[length+3];
				row[0] = s[0];
				row[1] = s[1];
				row[2] = s[2];
				row[i] = s[4];
				
				headersConsumed = true;
			}
			
			i++;
		}
		
		toReturn.add(row);
		return toReturn;
	}
	
	private String getEncounterName(int encounterId) {
		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
		Cursor c = db.query(DatabaseHandler.TABLE_ENCOUNTER_TYPES, null, EncounterType.COLUMN_TYPE_ID+"="+1/*encounterId*/, null, null, null, null);
		c.moveToFirst();
		return c.getString(1);
	}
	
	private int getNumberOfAnswers(int encounterId) {
		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
		String query = "select count(*) from "+DatabaseHandler.TABLE_ANSWERS+" where "+Answer.COLUMN_ENCOUNTER_ID + " = " + encounterId;
		Cursor c = db.rawQuery(query, null);
		c.moveToFirst();
		return c.getInt(0);
	}
}
