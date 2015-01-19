package org.ird.tbr3.videoeducator.common;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ird.tbr3.videoeducator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


public class DateSelector extends Activity implements OnClickListener {

	public static final byte DATE_TYPE_DATE = 0;
	public static final byte DATE_TYPE_DATE_TIME = 1;
	public static final byte DATE_TYPE_TIME = 2;
	public static final byte DATE_TYPE_MONTH = 3;
	
	private Button btnSubmit;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	private final String[] dayInMonth = { "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31" };

	private final String[] month = { "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12" };
	
	private String  selectedTime;
	private byte dialogType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_date_selector);
		setFinishOnTouchOutside(false);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		datePicker.setCalendarViewShown(false);
		
		Date date = new Date();
		datePicker.setMaxDate(date.getTime());
		btnSubmit.setOnClickListener(this);
		
		Intent i = getIntent();
		dialogType = i.getByteExtra("dialog_type", (byte) 0);
		if (DATE_TYPE_DATE_TIME == dialogType || DATE_TYPE_TIME == dialogType){
			((LinearLayout)findViewById(R.id.llDateTime)).setVisibility(View.VISIBLE);
			timePicker = (TimePicker) findViewById(R.id.timePicker);
			timePicker.setIs24HourView(true);
			timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
				
				public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
					if(minute > 9) {
						selectedTime = hourOfDay+":"+minute;
					} else {
						selectedTime = hourOfDay+":0"+minute;
					}
					
				}
			});
		}
		
		if(dialogType == DATE_TYPE_MONTH) {
			try {
		        Field f[] = datePicker.getClass().getDeclaredFields();
		        for (Field field : f) {
		        	if (field.getName().equals("mDayPicker") || field.getName().equals("mDaySpinner") /*|| field.getName().equals("mCalendarView")*/) {
		                field.setAccessible(true);
		                Object dayPicker = new Object();
		                dayPicker = field.get(datePicker);
		                ((View) dayPicker).setVisibility(View.GONE);
		            }
		        }
		    } 
		    catch (SecurityException e) {
		    	e.printStackTrace();
		    } 
		    catch (IllegalArgumentException e) {
		    	e.printStackTrace();
		    } 
		    catch (IllegalAccessException e) {
		    	e.printStackTrace();
		    }
		}
	}
    
	public void onClick(View v) {
		int id = v.getId();
		
		switch (id) {
		case R.id.btnSubmit: {
			datePicker.clearFocus();
			Intent sendBack = new Intent();
			String selectedDate;
			if(dialogType != DATE_TYPE_MONTH) {
				selectedDate = dayInMonth[datePicker.getDayOfMonth() - 1] + "-"
						+ month[(datePicker.getMonth())] + "-" + datePicker.getYear()/*parseYear(dp.getYear())*/;
			} else {
				selectedDate = month[(datePicker.getMonth())] + "-" + datePicker.getYear();
			}
			if (selectedTime == null) {
				DateFormat dfTime = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
				selectedTime = dfTime.format(new Date());
			}
			if(DATE_TYPE_DATE == dialogType || DATE_TYPE_MONTH ==dialogType) {				
				sendBack.putExtra("date", selectedDate);
			} else if(DATE_TYPE_DATE_TIME == dialogType) {
				sendBack.putExtra("date", selectedDate+" "+ selectedTime);
			} else if(DATE_TYPE_TIME == dialogType) {
				sendBack.putExtra("date", selectedTime);
			}
			setResult(RESULT_OK, sendBack);
			finish();
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Toast.makeText(this, "Press submit button to select date", Toast.LENGTH_SHORT).show();
	}
	@SuppressWarnings("unused")
	private String parseYear(int yr) {
		String y = Integer.toString(datePicker.getYear());
		y = y.substring(y.length() - 2, y.length());
		return y;
	}
	
}
