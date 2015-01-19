package org.ird.tbr3.videoeducator.common;

import org.ird.tbr3.videoeducator.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MonthSelector extends Activity {

	TextView tvJan, tvFeb, tvMAr, tvApr, tvMay, tvJun, tvJul, tvAug, tvSep,
			tvOct, tvNov, tvDec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_selector);
	}

}
