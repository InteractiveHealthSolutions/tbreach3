package org.ird.tbr3.videoeducator.report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.DateSelector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

public class ReportsActivity extends Activity {

	Button btnExport;
	EditText etMonth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);
		
		btnExport = (Button) findViewById(R.id.btnExport);
		etMonth = (EditText) findViewById(R.id.etMonth);
		btnExport.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					// new Que
					List<String[]> data = new ReportsGenerator(ReportsActivity.this).getReport(etMonth.getText().toString());
					if(data != null) {
						export(data);
					} else {
						Toast.makeText(ReportsActivity.this, "No data found", Toast.LENGTH_LONG).show();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnExport.setEnabled(false);
		
		etMonth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ReportsActivity.this, DateSelector.class);
				i.putExtra("dialog_type", DateSelector.DATE_TYPE_MONTH);
				startActivityForResult(i, 0);
				
			}
		});

		
	}

	private void export(List<String[]> data) throws IOException {
		File exportDir = new File(Environment.getExternalStorageDirectory(), "DCIM/Video App Data");
		if (!exportDir.exists()) {
			exportDir.mkdirs();
		}

		File file = new File(exportDir, etMonth.getText().toString()+".csv");

		file.createNewFile();
		CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

		csvWrite.writeAll(data);

		csvWrite.close();
		Toast.makeText(this, "data exported successfully to "+exportDir+"/"+etMonth.getText().toString()+".csv", Toast.LENGTH_LONG).show();
		finish();

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle res = data.getExtras();
		String d = res.getString("date");
		if(requestCode == 0) {
			etMonth.setText(d);
		} 
		
		if(etMonth.getText() != null) {
			btnExport.setEnabled(true);
		} else {
			btnExport.setEnabled(false);
		}
		
	}
	
	
	
	
}
