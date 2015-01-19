package org.ird.tbr3.videoeducator.database.data_access;

import org.ird.tbr3.videoeducator.database.model.Encounter;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EncounterDAO {

	Context context;

	public EncounterDAO(Context context) {
		this.context = context;
	}

	public long insert(Encounter encounter) {

		SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Encounter.COLUMN_ENCOUNTER_TYPE_ID, encounter.getTypeId());
		values.put(Encounter.COLUMN_PATIENT_MR_NUMBER, encounter.getPatientMrNumber());
		values.put(Encounter.COLUMN_PATIENT_NAME, encounter.getPatientName());
		values.put(Encounter.COLUMN_PATIENT_AGE, encounter.getPatienAge());
		values.put(Encounter.COLUMN_ENCOUNTER_DATE, encounter.getDate());

		return db.insert(DatabaseHandler.TABLE_ENCOUNTERS, null, values);

	}

}
