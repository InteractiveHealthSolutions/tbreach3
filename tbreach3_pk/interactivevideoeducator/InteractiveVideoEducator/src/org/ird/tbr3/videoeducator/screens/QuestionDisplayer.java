package org.ird.tbr3.videoeducator.screens;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.common.OnImageLongTouchedListener;
import org.ird.tbr3.videoeducator.common.OnQuestionAnsweredListener;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.LocalizedQuestion;
import org.ird.tbr3.videoeducator.database.model.Question;
import org.ird.tbr3.videoeducator.database.model.Video;
import org.ird.tbr3.videoeducator.view.QuestionLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

public class QuestionDisplayer extends Activity implements OnImageLongTouchedListener, OnQuestionAnsweredListener {

	ViewPager vpQuestions;
	List<QuestionLayout> views;
	Video video;
	DataProvider dataProvider;
	List<LocalizedQuestion> localizedQuestionsList;
	public static Boolean pre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_question_displayer);
		dataProvider = DataProvider.getInstance();
		Intent intent = getIntent();
		pre = Boolean.valueOf(intent.getStringExtra("pre"));
		video = FontManager.video;
		localizedQuestionsList = dataProvider.getLocalizedQuestions(video.getVideoId(), App.LANGUAGE.getLanguageId());
		
		views = new ArrayList<QuestionLayout>();
		int count = 0;
		for(LocalizedQuestion lq:localizedQuestionsList) {  
			int occurance = dataProvider.getQuestion(lq.getQuestionId()).getQuestionOccurance();
			if(pre && (occurance == Question.BEFORE || occurance == Question.BOTH)) {
				views.add(new QuestionLayout(this, this, this, lq, count));
				count++;
			} else if (!pre && (occurance == Question.AFTER || occurance == Question.BOTH)) {
				views.add(new QuestionLayout(this, this, this, lq, count));
				count++;
			}
		}
		
		vpQuestions = (ViewPager) findViewById(R.id.vpQuestions);
		
		ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				views.get(arg0).playAudio();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		}; 
		vpQuestions.setOnPageChangeListener(pageChangeListener);
		
		vpQuestions.setAdapter(new PagerAdapter() {
			
			@Override
			public void destroyItem(View view, int arg1, Object object) {
				((ViewPager) view).removeView((ScrollView) object);
			}

			@Override
			public void finishUpdate(View arg0) {

			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public Object instantiateItem(View view, int position) {
				View myView = views.get(position);
				((ViewPager) view).addView(myView);
				return myView;
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {

			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {

			}
			
			@Override
			public CharSequence getPageTitle(int position) {
				switch (position) {
				case 0:
					return " ";
				case 1:
					return " ";
				case 2:
					return " ";
				}
				return null;
			}
		});
		
		pageChangeListener.onPageSelected(0);
		
	}

	@Override
	public void viewImage(String imageURL) {
		Intent displayImage = new Intent(this, ImageViewer.class);
		displayImage.putExtra("image_to_display", imageURL);
		startActivity(displayImage);
	}

	@Override
	public void moveToQuestion(int position, Boolean smoothScroll) {
		
		if(position < views.size()) {
			vpQuestions.setCurrentItem(position, smoothScroll);
		} else {
			if(pre) {
				startActivity(new Intent(QuestionDisplayer.this, VideoViewer.class));
				AudioPlayer.getInstance().stop();
				finish();
			} else {
				//Toast.makeText(this, "Data sent to server", Toast.LENGTH_LONG).show();
				startActivity(new Intent(QuestionDisplayer.this, ThanksMessageActivity.class));
				AudioPlayer.getInstance().stop();
				finish();
			}
		}
		
	}
	
	@Override
	public void onBackPressed() {
		
		//super.onBackPressed();
	}
		
}
