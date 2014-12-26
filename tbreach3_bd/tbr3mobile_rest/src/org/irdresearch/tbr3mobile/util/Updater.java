/**
 * 
 */

package org.irdresearch.tbr3mobile.util;

import org.irdresearch.tbr3mobile.App;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class Updater extends Thread
{
	public boolean	isRunning	= false;
	private Context	context;

	public Updater (Context context)
	{
		this.context = context;
	}

	@Override
	public void run ()
	{
		super.run ();
		while (isRunning)
		{
			try
			{
				Log.d (Updater.class.getSimpleName (), "Forrest running...");
				Thread.sleep (App.getDelay ());

				try
				{
					DatabaseUtil util = new DatabaseUtil (this.context);
					SQLiteDatabase db = util.getWritableDatabase ();

					ContentValues values = new ContentValues ();
					values.put ("Preference", "Color");
					values.put ("Value", "Green");
					db.insert ("preference", null, values);
					// THIS IS WHERE I LEFT
					db.insertWithOnConflict ("", null, values, SQLiteDatabase.CONFLICT_REPLACE);
					db.close ();
					util.close ();
				}
				catch (Exception e)
				{
					e.printStackTrace ();
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace ();
				isRunning = false;
			}
		}
	}
}
