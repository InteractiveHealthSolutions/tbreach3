package org.ird.tbr3.videoeducator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.AudioPlayer;
import org.ird.tbr3.videoeducator.common.OnAudioCompletionListener;
import org.ird.tbr3.videoeducator.common.OnImageLongTouchedListener;
import org.ird.tbr3.videoeducator.common.OnQuestionAnsweredListener;
import org.ird.tbr3.videoeducator.common.Tools;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.database.model.LocalizedOption;
import org.ird.tbr3.videoeducator.database.model.LocalizedQuestion;
import org.ird.tbr3.videoeducator.database.model.Option;
import org.ird.tbr3.videoeducator.database.model.Question;
import org.ird.tbr3.videoeducator.database.model.QuestionType;
import org.ird.tbr3.videoeducator.network_tasks.DataCollector;
import org.ird.tbr3.videoeducator.screens.QuestionDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.Settings.System;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class GridRadioGroupLayout extends GridView implements OnClickListener, OnAudioCompletionListener {
	
	private List<OptionRadioButton> radioButtonsList;
	private List<LocalizedOption> localizedOptionsList;
	private DataProvider dataProvider;
	private List<LocalizedOption> localizedOptions;
	private Tools tools;
	int viewIndex;
	int start, limit;
	private OnImageLongTouchedListener imageLongTouchedListener;
	private OnQuestionAnsweredListener questionAnsweredListener;
	private LocalizedQuestion lq;
	private Context context;
	private List<Option> options, selectedOptions;
	private List<Integer> touchOrderList;
	private boolean answerd;
	public GridRadioGroupLayout(Context context, OnImageLongTouchedListener imageLongTouchedListener, OnQuestionAnsweredListener questionAnsweredListener, LocalizedQuestion lq, int viewIndex) {
		super(context);
		android.widget.GridLayout.LayoutParams layoutParamsGrid;
		layoutParamsGrid = new android.widget.GridLayout.LayoutParams();
		layoutParamsGrid.width = android.widget.GridLayout.LayoutParams.MATCH_PARENT;
		layoutParamsGrid.height = android.widget.GridLayout.LayoutParams.MATCH_PARENT;
		setLayoutParams(layoutParamsGrid);
		setPadding(0, 20, 0, 0);
		
		this.context = context;
		this.viewIndex = viewIndex;
		
		this.imageLongTouchedListener = imageLongTouchedListener;
		this.questionAnsweredListener = questionAnsweredListener;
		init(context, lq);
	}
	
	public GridRadioGroupLayout(Context context) {
		super(context);
		
	}
	
	private void init(Context context, LocalizedQuestion lq) {
		this.lq = lq;
		tools = Tools.getInstance();
		localizedOptions = new ArrayList<LocalizedOption>();
		//setColumnCount(2);
		dataProvider = DataProvider.getInstance();
		Question question = dataProvider.getQuestion(lq.getQuestionId());
		int questionType = question.getQuestionType();
		localizedOptionsList = dataProvider.getLocalizedOptions(lq.getQuestionId(), App.LANGUAGE.getLanguageId());		
		radioButtonsList = new ArrayList<OptionRadioButton>();
		start = 0;
		limit = 0;
		
		if(App.LANGUAGE.getScriptType().equals(Language.LTR_SCRIPT)){
			start = 0;
			limit = localizedOptionsList.size();
		} else if(App.LANGUAGE.getScriptType().equals(Language.RTL_SCRIPT)){
			start = localizedOptionsList.size()-1;
			limit = 0;
		}
		
		if(questionType == QuestionType.SINGLE_SELECT_TEXT || questionType == QuestionType.MULTI_SELECT_TEXT) {
			for(int i=start; condition(i); i = next(i)) {
				LocalizedOption lo = localizedOptionsList.get(i);
				OptionRadioButton rb = new OptionRadioButton(context, questionType, lo);
				
				localizedOptions.add(lo);
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+lo.getImageFilePath());
				//Bitmap bitmapOrig = Bitmap.createScaledBitmap(bitmap, 128, 128, false);
				Drawable drawable = new BitmapDrawable(getResources(), tools.resizeBitmap(bitmap, 200));
				//Drawable drawable = Drawable.createFromPath(Environment.getExternalStorageDirectory().getPath()+lo.getImageFilePath());
				
				rb.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
				rb.setText(lo.getText());
				
				rb.setOnClickListener(this);
				radioButtonsList.add(rb);
			}
		
		} else if(questionType == QuestionType.SINGLE_SELECT_IMAGE || questionType == QuestionType.MULTI_SELECT_IMAGE) {
			for(int i=0; i<localizedOptionsList.size();) {
				LocalizedOption localizedOption = getRandom(localizedOptionsList);
				OptionRadioButton rb = new OptionRadioButton(context, questionType, localizedOption);
				final LocalizedOption lo = localizedOption;
				localizedOptions.add(localizedOption);
				
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+lo.getImageFilePath());
				Drawable drawable = new BitmapDrawable(getResources(), tools.resizeBitmap(bitmap, 360));
				
				rb.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
				rb.setText(lo.getText());
				rb.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						imageLongTouchedListener.viewImage(lo.getImageFilePath());
						
						return false;
					}
				});
				
				rb.setOnClickListener(this);
				radioButtonsList.add(rb);
			}
		}	
		GridViewRadioButtonAdapter adapter = new GridViewRadioButtonAdapter(context, radioButtonsList);
		setAdapter(adapter);
		setNumColumns(localizedOptions.size());
		
		
		
		options = dataProvider.getOptions(lq.getQuestionId());
		
		selectedOptions = new ArrayList<Option>();
		touchOrderList = new ArrayList<Integer>();
		answerd = false;
	}

	
	@Override
	public void onClick(View v) {
		
		try {
			/*for(OptionRadioButton rb:radioButtonsList) {
				if(rb.getOptionType() != QuestionType.SINGLE_SELECT_IMAGE && rb.getOptionType() != QuestionType.MULTI_SELECT_IMAGE) {
					if(v == rb) {
						rb.setChecked(true);					
						rb.setCompoundDrawablesWithIntrinsicBounds(null, tools.getDrawableFromLocalStorage(context, localizedOptions.get(radioButtonsList.indexOf(rb)).getImageFilePath().replace(".png", "_selected.png"), 200), null, null);
					} else {			
						rb.setChecked(false);
						rb.setCompoundDrawablesWithIntrinsicBounds(null, tools.getDrawableFromLocalStorage(context, localizedOptions.get(radioButtonsList.indexOf(rb)).getImageFilePath().replace(".png", "_selected.png"), 200), null, null);
					}
				}
			}*/
			OptionRadioButton orb = (OptionRadioButton) v;
			
			if(QuestionDisplayer.pre) {
				if(!answerd) {
					answerd = true;
					DataCollector.obsList.add(new String[]{dataProvider.getQuestion(lq.getQuestionId()).getConseptName(), orb.getConceptName()});
					questionAnsweredListener.moveToQuestion(viewIndex+1, true);
				}
			} else {
				if(orb.getOptionType() == QuestionType.SINGLE_SELECT_IMAGE || orb.getOptionType() == QuestionType.MULTI_SELECT_IMAGE){
					
					selectedOptions.add(orb.getOption());
					touchOrderList.add(selectedOptions.size());
					LocalizedOption lo = orb.getLocalizedOption();
					
					if(!dataProvider.getOption(lo.getOptionId()).isCorrect() && !answerd) {
						AudioPlayer.getInstance().playAudio(tools.getExternalStorageDirectoryPath(lo.getAudioPath()));
						//when a wrong option is pressed once it must not be click-able again, also change the image og that option to disabled one
						orb.setClickable(false); 
						String imageFilePath = orb.getLocalizedOption().getImageFilePath();
						imageFilePath = processImageFilePath(imageFilePath);
						orb.setCompoundDrawablesWithIntrinsicBounds(null, tools.getDrawableFromLocalStorage(this.context, imageFilePath, 360), null, null);
						
						
					} else if(dataProvider.getOption(lo.getOptionId()).isCorrect() && !answerd) {
						answerd = true;
						AudioPlayer.getInstance().playAudio(tools.getExternalStorageDirectoryPath(lo.getAudioPath()), this, 0);
						for(int i = 0; i<options.size(); i++) {
							Option temp = options.get(i);
							if(selectedOptions.contains(temp)) {
								DataCollector.obsList.add(new String[]{temp.getConceptName(), touchOrderList.get(selectedOptions.indexOf(temp))+""});
							} else {
								DataCollector.obsList.add(new String[]{temp.getConceptName(), "0"});
							}
						}
					}
				} else {
					if(!answerd) {
						answerd = true;
						DataCollector.obsList.add(new String[] {dataProvider.getQuestion(lq.getQuestionId()).getConseptName(), orb.getConceptName()});
						questionAnsweredListener.moveToQuestion(viewIndex+1, true);
					}
				}
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	private String processImageFilePath(String imageFilePath) {
		if(imageFilePath.endsWith(".jpg")) {
			imageFilePath = imageFilePath.replace(".jpg", "_disabled.jpg");
		} else if(imageFilePath.endsWith(".png")) {
			imageFilePath = imageFilePath.replace(".png", "_disabled.png");
		}
		
		return imageFilePath;
	}
	public OptionRadioButton getCheckedRadioButton() {
		for(OptionRadioButton rb:radioButtonsList) {
			if(rb.isChecked()) {
				return rb;
			}
		}
		
		return null;
	}
	
	public int getCheckedRadioButtonId() {
		for(OptionRadioButton rb:radioButtonsList) {
			if(rb.isChecked()) {
				return rb.getId();
			}
		}
		
		return -1;
	}
	
	public String getCheckedRadioButtonText() {
		for(OptionRadioButton rb:radioButtonsList) {
			if(rb.isChecked()) {
				return rb.getText().toString();
			}
		}
		
		return null;
	}
	
	@Override
	public void onCompletion(int resourseId) {
		questionAnsweredListener.moveToQuestion(viewIndex+1, true);
	}
	
	private  int next(int i) {
		if(limit!=0) {
			 return i+1;
		} else if (limit == 0) {
			 return i-1;
		}
		return 0;
	}
	
	private Boolean condition(int i) {
		if(limit!=0) {
			if (i<limit) {
				return true;
			} else {
				return false;
			}
		} else if (limit == 0) {
			if (i>=limit) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	private LocalizedOption getRandom(List<LocalizedOption> localizedOptions) {
				int random;
				if(localizedOptions.size()>1) {
					random = new Random().nextInt(localizedOptions.size()-1);
				} else {
					random = 0;
				}
				
				LocalizedOption localizedOption = localizedOptions.get(random);
				localizedOptions.remove(random);
				return localizedOption;
	}
	
}
