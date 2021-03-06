/**
 * 
 */

package org.irdresearch.tbr3mobile;

import org.irdresearch.tbr3mobile.shared.AlertType;
import org.irdresearch.tbr3mobile.util.ServerService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class LoginActivity extends Activity implements IActivity, OnClickListener
{
	private ServerService			serverService;
	protected static ProgressDialog	loading;
	EditText						username;
	EditText						password;
	Button							login;
	CheckBox						offline;
	FrameLayout						ihsLogo;
	FrameLayout						irdLogo;
	FrameLayout						openmrsLogo;
	View[]							views;
	Animation						alphaAnimation;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		setTheme (App.getTheme ());
		setContentView (R.layout.login);
		serverService = new ServerService (getApplicationContext ());
		loading = new ProgressDialog (this);
		username = (EditText) findViewById (R.login_id.usernameEditText);
		password = (EditText) findViewById (R.login_id.passwordEditText);
		login = (Button) findViewById (R.login_id.loginButton);
		offline = (CheckBox) findViewById (R.login_id.offlineCheckBox);
		ihsLogo = (FrameLayout) findViewById (R.login_id.ihsLogo);
		irdLogo = (FrameLayout) findViewById (R.login_id.irdLogo);
		openmrsLogo = (FrameLayout) findViewById (R.login_id.openmrsLogo);
		alphaAnimation = AnimationUtils.loadAnimation (this, R.anim.alpha_animation);
		login.setOnClickListener (this);
		ihsLogo.setOnClickListener (this);
		irdLogo.setOnClickListener (this);
		openmrsLogo.setOnClickListener (this);
		views = new View[] {username, password, login};
		super.onCreate (savedInstanceState);
		initView (views);
	}

	@Override
	public void initView (View[] views)
	{
		if (App.isAutoLogin ())
		{
			serverService.setCurrentUser (App.get (username));
			Intent intent = new Intent (this, MainMenuActivity.class);
			startActivity (intent);
			finish ();
		}
		username.setText (App.getUsername ());
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ().inflate (R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId ())
		{
			case R.menu_id.itemPreferences :
				startActivity (new Intent (this, Preferences.class));
				break;
		}
		return true;
	}

	@Override
	protected void onStop ()
	{
		super.onStop ();
		finish ();
	}

	@Override
	public void updateDisplay ()
	{
	}

	@Override
	public boolean validate ()
	{
		boolean valid = true;
		StringBuffer message = new StringBuffer ();
		// Validate mandatory controls
		if (App.get (username).equals (""))
		{
			valid = false;
			message.append (username.getTag () + ". ");
			((EditText) username).setHintTextColor (getResources ().getColor (R.color.Red));
		}
		if (App.get (password).equals (""))
		{
			valid = false;
			message.append (password.getTag () + ". ");
			((EditText) password).setHintTextColor (getResources ().getColor (R.color.Red));
		}
		if (!valid)
		{
			message.append (getResources ().getString (R.string.empty_data) + "\n");
		}
		// For offline mode, match current username and password with saved
		if (offline.isChecked ())
		{
			if (!App.get (username).equals (App.getUsername ()))
			{
				if (!App.get (password).equals (App.getPassword ()))
				{
					valid = false;
					message.append (getResources ().getString (R.string.authentication_error));
				}
			}
		}
		if (!valid)
		{
			App.getAlertDialog (this, AlertType.ERROR, message.toString ()).show ();
		}
		return valid;
	}

	@Override
	public boolean submit ()
	{
		// Check connection with server or offline mode
		if (!serverService.checkInternetConnection () && !offline.isChecked ())
		{
			AlertDialog alertDialog = App.getAlertDialog (this, AlertType.ERROR, getResources ().getString (R.string.data_connection_error));
			alertDialog.setTitle (getResources ().getString (R.string.error_title));
			alertDialog.setButton (AlertDialog.BUTTON_POSITIVE, "OK", new AlertDialog.OnClickListener ()
			{
				@Override
				public void onClick (DialogInterface dialog, int which)
				{
					finish ();
				}
			});
			alertDialog.show ();
		}
		else if (validate ())
		{
			// Authenticate from server
			AsyncTask<String, String, Boolean> authenticationTask = new AsyncTask<String, String, Boolean> ()
			{
				@Override
				protected Boolean doInBackground (String... params)
				{
					runOnUiThread (new Runnable ()
					{
						@Override
						public void run ()
						{
							loading.setIndeterminate (true);
							loading.setCancelable (false);
							loading.setMessage (getResources ().getString (R.string.loading_message));
							loading.show ();
						}
					});
					if (offline.isChecked ())
					{
						if (App.getUsername ().equalsIgnoreCase (App.get (username)) && App.getPassword ().equals (App.get (password)))
						{
							return true;
						}
						return false;
					}
					App.setUsername (App.get (username));
					App.setPassword (App.get (password));
					boolean exists = serverService.authenticate ();
					return exists;
				}

				@Override
				protected void onProgressUpdate (String... values)
				{
				};

				@Override
				protected void onPostExecute (Boolean result)
				{
					super.onPostExecute (result);
					loading.dismiss ();
					if (result)
					{
						serverService.setCurrentUser (App.get (username));
						// Save username and password in preferences
						SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences (LoginActivity.this);
						SharedPreferences.Editor editor = preferences.edit ();
						editor.putString (Preferences.USERNAME, App.getUsername ());
						editor.putString (Preferences.PASSWORD, App.getPassword ());
						editor.apply ();
						Intent intent = new Intent (LoginActivity.this, MainMenuActivity.class);
						startActivity (intent);
						finish ();
					}
					else
					{
						App.setUsername ("");
						App.setPassword ("");
						Toast toast = Toast.makeText (LoginActivity.this, getResources ().getString (R.string.authentication_error), App.getDelay ());
						toast.setGravity (Gravity.CENTER, 0, 0);
						toast.show ();
					}
				}
			};
			authenticationTask.execute ("");
		}
		return false;
	}

	@Override
	public void onClick (View view)
	{
		Intent browserIntent;
		view.startAnimation (alphaAnimation);
		switch (view.getId ())
		{
			case R.login_id.loginButton :
				App.setOfflineMode (offline.isChecked ());
				submit ();
				break;
			case R.login_id.ihsLogo :
				browserIntent = new Intent ("android.intent.action.VIEW", Uri.parse ("http://www.ihsinformatics.com"));
				startActivity (browserIntent);
				break;
			case R.login_id.irdLogo :
				browserIntent = new Intent ("android.intent.action.VIEW", Uri.parse ("http://www.irdresearch.org"));
				startActivity (browserIntent);
				break;
			case R.login_id.openmrsLogo :
				browserIntent = new Intent ("android.intent.action.VIEW", Uri.parse ("http://www.openmrs.org"));
				startActivity (browserIntent);
				break;
		}
	}
}
