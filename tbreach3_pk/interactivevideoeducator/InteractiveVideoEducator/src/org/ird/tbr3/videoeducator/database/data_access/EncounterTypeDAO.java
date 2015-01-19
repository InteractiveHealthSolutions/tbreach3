package org.ird.tbr3.videoeducator.database.data_access;

import java.util.List;

import org.ird.tbr3.videoeducator.database.model.EncounterType;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EncounterTypeDAO {

	Context context;

	public EncounterTypeDAO(Context context) {
		this.context = context;
	}
	
	public void insert(List<EncounterType> encounterType) {
		String query = "insert into " + DatabaseHandler.TABLE_ENCOUNTER_TYPES + " ("+EncounterType.COLUMN_TYPE_NAME+") values";
		for (int i = 0; i < encounterType.size(); i++) {
			if (i < encounterType.size() - 1) {
				query += "('" + encounterType.get(i).getTypeName() + "'),";
			} else {
				query += "('" + encounterType.get(i).getTypeName() + "')";
			}
		}
		
		query += ";";
		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
		
		db.execSQL(query);
	}
	
	public void insert(SQLiteDatabase db, List<EncounterType> encounterType) {
		String query = "insert into " + DatabaseHandler.TABLE_ENCOUNTER_TYPES + " ("+EncounterType.COLUMN_TYPE_NAME+") values";
		for (int i = 0; i < encounterType.size(); i++) {
			if (i < encounterType.size() - 1) {
				query += "('" + encounterType.get(i).getTypeName() + "'),";
			} else {
				query += "('" + encounterType.get(i).getTypeName() + "')";
			}
		}
		
		query += ";";
		
		db.execSQL(query);
	}
}
