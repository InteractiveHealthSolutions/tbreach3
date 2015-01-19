package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MotherTongue extends Activity {

	LayoutParams layoutParams;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mother_tnngue);
		setFinishOnTouchOutside(false);
		
		layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.bottomMargin = 10;
		
		LinearLayout llMain = (LinearLayout) findViewById(R.id.llMain);
		
		String[] motherTongues = App.LANGUAGE.getmotherLanguages();
		
		for(String motherToungue:motherTongues){
			llMain.addView(getButton(motherToungue));
		}
	}

	private Button getButton(final String text) {
		final Button btn = new Button(this);
		btn.setLayoutParams(layoutParams);
		btn.setText(text);
		btn.setTextSize(30);
		btn.setBackground(getResources().getDrawable(R.drawable.border));
		btn.setPadding(0, 10, 0, 10);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DataCollector.obsList.add(new String[]{App.MOTHER_TOUNGE_UUID, text});
				Intent intent = new Intent(MotherTongue.this, VideoIntroduction.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		btn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					btn.setBackground(getResources().getDrawable(R.drawable.border_filled));
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn.setBackground(getResources().getDrawable(R.drawable.border));
				}
				return false;
			}
		});
		
		return btn;
	}

}
