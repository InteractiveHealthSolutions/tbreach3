package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.common.Tools;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LanguageSelecter extends Activity implements OnClickListener, OnTouchListener/*, OnAudioCompletionListener*/ {

	Button btnEnglish, btnUrdu, btnBengali, btnBahasa;
	Button btnEnglishSpk, btnUrduSpk, btnBengaliSpk;
	TextView tvSelectLanguage;
	AudioPlayer audioPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_language_selecter);
		audioPlayer = AudioPlayer.getInstance();
		
		tvSelectLanguage = (TextView) findViewById(R.id.tvSelectLang);
		
		btnEnglish = (Button) findViewById(R.id.btnEnglish);
		btnUrdu = (Button) findViewById(R.id.btnUrdu);
		btnBengali = (Button) findViewById(R.id.btnBengli);
		btnBahasa = (Button) findViewById(R.id.btnBahasa);

		btnUrdu.setTypeface(FontManager.global.getTypeFace(Language.URDU_PAK));
		btnUrdu.setTextSize(40);

		btnBengali.setTypeface(FontManager.global.getTypeFace(Language.BENGLA_BAN));
		btnBengali.setTextSize(32);

		btnEnglish.setOnClickListener(this);
		btnUrdu.setOnClickListener(this);
		btnBengali.setOnClickListener(this);
		btnBahasa.setOnClickListener(this);
		
		btnEnglish.setOnTouchListener(this);
		btnBahasa.setOnTouchListener(this);
		btnUrdu.setOnTouchListener(this);
		btnBengali.setOnTouchListener(this);

	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
			switch (id) {
			
			case R.id.btnEnglish:
					
					DataCollector.obsList.add(new String[]{App.LANGUAGE_UUID, "English"});
					startForLanguage(DataProvider.getInstance().getLanguage(Language.ENGLISH_US));	
				break;
				
			case R.id.btnUrdu:
					
					DataCollector.obsList.add(new String[]{App.LANGUAGE_UUID, "Urdu"});
					startForLanguage(DataProvider.getInstance().getLanguage(Language.URDU_PAK));	
				break;
				
			case R.id.btnBengli:
				
				break;

			default:
				break;
			}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Tools.getInstance().runButtonTouchEffect(LanguageSelecter.this, v, event);

		return false;
	}



	private void startForLanguage(Language language) {
		App.LANGUAGE = language;
		Intent intent = new Intent(LanguageSelecter.this, MotherTongue.class); 
		startActivity(intent);
		finish();
	}

}
