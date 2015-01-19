package org.ird.tbr3.videoeducator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class QuestionsViewpager extends ViewPager {

	public QuestionsViewpager(Context context) {
		super(context);
		
	}
	
	public QuestionsViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		
		return false;
	}

}
