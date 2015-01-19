package org.ird.tbr3.videoeducator.database;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.database.data_access.EncounterTypeDAO;
import org.ird.tbr3.videoeducator.database.model.EncounterType;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DefaultData {
	
	Context context;
	public DefaultData(Context context) {
		this.context = context;
	}
	
	public void insertDefaultData(SQLiteDatabase db) {
		
			List<EncounterType> encounterTypes = new ArrayList<EncounterType>();
			encounterTypes.add(new EncounterType("Sputum Instructions"));
			new EncounterTypeDAO(context).insert(db, encounterTypes);
		
	}
}
