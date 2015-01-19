package org.ird.tbr3.videoeducator.view;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.common.OnAudioCompletionListener;
import org.ird.tbr3.videoeducator.common.OnImageLongTouchedListener;
import org.ird.tbr3.videoeducator.common.OnQuestionAnsweredListener;
import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.database.model.LocalizedQuestion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("ViewConstructor")
public class QuestionLayout extends ScrollView implements OnAudioCompletionListener {
	
	GridRadioGroupLayout gridRadioGroupLayout;
	//AnimationDrawable frameAnimation;
	LocalizedQuestion lq;
	//TextView tvQuestion;
	Button btnReplay;
	Boolean isLTR;
	
	public QuestionLayout(Context context, OnImageLongTouchedListener imageLongTouchedListener, OnQuestionAnsweredListener questionAnsweredListener, final LocalizedQuestion lq, int viewIndex) {
		super(context);
		setPadding(10, 5, 10, 10);
		this.lq = lq;
		
		setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));		
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
		addView(layoutInflater.inflate(R.layout.question_displayer, this, false) ); 
		
		gridRadioGroupLayout = new GridRadioGroupLayout(context, imageLongTouchedListener, questionAnsweredListener, lq, viewIndex);		
		
		gridRadioGroupLayout.setOnTouchListener(new OnTouchListener(){

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_MOVE){
		            return true;
		        }
		        return false;
		    }

		});
		
		((LinearLayout) findViewById(R.id.llOptions)).addView(gridRadioGroupLayout);
		
		
		btnReplay = (Button) findViewById(R.id.btnReplay);
		btnReplay.setText(lq.getText());
		btnReplay.setTextSize(28f);
		
		btnReplay.setGravity(Gravity.CENTER_VERTICAL);
		btnReplay.setTextColor(Color.WHITE);
		if(App.LANGUAGE.getLanguageId().equals(Language.URDU_PAK)) {
			btnReplay.setTypeface(FontManager.global.getTypeFace(Language.URDU_PAK));
			btnReplay.setTextSize(40f);
		}
		btnReplay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playAudio();
				
			}
		});
		
		if(App.LANGUAGE.getScriptType().equals(Language.LTR_SCRIPT)) {
			isLTR = true;
			btnReplay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.zooming_star),null, null, null);
			//frameAnimation = (AnimationDrawable) btnReplay.getCompoundDrawables()[0];
		} else {
			isLTR = false;
			btnReplay.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.zooming_star), null); //rtl
			//frameAnimation = (AnimationDrawable) btnReplay.getCompoundDrawables()[2];
		}
		
	}
	
	public void playAudio() {
//		if(isLTR) {
//			btnReplay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.star_animated), null, null, null);
//		} else {
//			btnReplay.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.star_animated), null); //rtl
//		}
		//frameAnimation.start();
		AudioPlayer.getInstance().playAudio(Environment.getExternalStorageDirectory().getPath()+lq.getAudioPath(), this, 0);
		
	}

	@Override
	public void onCompletion(int requestId) {
//		if(isLTR) {
//			btnReplay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.star_normal), null, null, null);
//		} else {
//			btnReplay.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.star_normal), null); //rtl
//		}
		
		//frameAnimation.stop();
		
	}

}
