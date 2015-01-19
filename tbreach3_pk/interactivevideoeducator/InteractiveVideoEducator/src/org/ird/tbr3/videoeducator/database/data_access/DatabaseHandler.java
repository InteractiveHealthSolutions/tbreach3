package org.ird.tbr3.videoeducator.database.data_access;

import org.ird.tbr3.videoeducator.database.DefaultData;
import org.ird.tbr3.videoeducator.database.model.Answer;
import org.ird.tbr3.videoeducator.database.model.Encounter;
import org.ird.tbr3.videoeducator.database.model.EncounterType;
import org.ird.tbr3.videoeducator.database.model.Question;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHandler extends SQLiteOpenHelper {

	private static DatabaseHandler databaseHandler;
	Context context;
	
	// Database Version
    private static final int DATABASE_VERSION =100;
 
    // Database Name
    private static final String DATABASE_NAME = "ponseti";
 
    // Table names
    public static final String TABLE_ENCOUNTER_TYPES = "encounter_types";
    public static final String TABLE_ENCOUNTERS = "encounters";
    public static final String TABLE_ANSWERS = "answers";
    
	// Constructor
    private DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
    
    public static DatabaseHandler getInstance(Context context) {
    	if(databaseHandler == null) {
    		databaseHandler = new DatabaseHandler(context);
    	}
    	
    	return databaseHandler;
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String t1 = "CREATE TABLE IF NOT EXISTS "+DatabaseHandler.TABLE_ENCOUNTER_TYPES+" ("
				
				+EncounterType.COLUMN_TYPE_ID + " INTEGER PRIMARY KEY NOT NULL," 
	            +EncounterType.COLUMN_TYPE_NAME + " TEXT UNIQUE NOT NULL);";


		String t2 = "CREATE TABLE IF NOT EXISTS "+DatabaseHandler.TABLE_ENCOUNTERS+" ("

				+Encounter.COLUMN_ENCOUNTER_ID + " INTEGER PRIMARY KEY NOT NULL,"
			    +Encounter.COLUMN_PATIENT_MR_NUMBER + " TEXT NOT NULL,"
				+Encounter.COLUMN_PATIENT_NAME + " TEXT NOT NULL,"
			    +Encounter.COLUMN_PATIENT_AGE + " INTEGER NOT NULL,"
				+Encounter.COLUMN_ENCOUNTER_DATE + " TEXT NOT NULL,"
	            +Encounter.COLUMN_ENCOUNTER_TYPE_ID + " INTEGER NOT NULL,"
	            + " FOREIGN KEY ("+EncounterType.COLUMN_TYPE_ID+") REFERENCES "+DatabaseHandler.TABLE_ENCOUNTER_TYPES+" ("+EncounterType.COLUMN_TYPE_ID+"));";


		String t3 = "CREATE TABLE IF NOT EXISTS "+DatabaseHandler.TABLE_ANSWERS+" ("
				
				+Answer.COLUMN_ANSWER_ID + " INTEGER PRIMARY KEY NOT NULL," 
	            +Answer.COLUMN_TEXT + " TEXT NOT NULL,"
	            +Question.COLUMN_QUESTION_ID + " INTEGER NOT NULL,"
	            +Encounter.COLUMN_ENCOUNTER_ID + " INTEGER NOT NULL,"
	            // TODO when questions are stored in database, un-comment the next line
	            // + " FOREIGN KEY ("+Answer.COLUMN_QUESTION_ID+") REFERENCES "+DatabaseHandler.TABLE_QUESTIONS+" ("+Question.COLUMN_QUESTION_ID+"), "
	            + " FOREIGN KEY ("+Encounter.COLUMN_ENCOUNTER_ID+") REFERENCES "+DatabaseHandler.TABLE_ENCOUNTERS+" ("+Encounter.COLUMN_ENCOUNTER_ID+"));";
		
		db.execSQL(t1);
		db.execSQL(t2);
		db.execSQL(t3);
		
		new DefaultData(context).insertDefaultData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String t0 = "drop table if exists "+DatabaseHandler.TABLE_ENCOUNTER_TYPES;
		String t1 = "drop table if exists "+DatabaseHandler.TABLE_ENCOUNTERS;
		String t2 = "drop table if exists "+DatabaseHandler.TABLE_ANSWERS;
		
		db.execSQL(t0);
		db.execSQL(t1);
		db.execSQL(t2);
		
		onCreate(db);
	}
	
	public void closeDb() {
		getWritableDatabase().close();
	}
}
