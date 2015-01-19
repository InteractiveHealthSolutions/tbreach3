package org.ird.tbr3.videoeducator.screens;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.BaseActivity;
import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Video;
import org.ird.tbr3.videoeducator.report.ReportsActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends BaseActivity {

	Context context = this;
	ListView listView;
	List<Video> videosList;
	Button btnExport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new FontManager(getApplicationContext());
		listView  = (ListView) findViewById(R.id.listView);
		btnExport = (Button) findViewById(R.id.btnExport);
		btnExport.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ReportsActivity.class));
				
			}
		});
		
		videosList = DataProvider.getInstance().getVideos();
		
		List<String> videoTitlesList = new ArrayList<String>();
		for(Video v:videosList){
			videoTitlesList.add("  "+v.getVideoTitle());	
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_video_list_element, R.id.tvVideoList, videoTitlesList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				int viewId = arg0.getId();
				switch (viewId) {
				case R.id.listView:
					Intent intent = new Intent(MainActivity.this, UserStart.class);
//					intent.putExtra("video", videosList.get(arg2));
//					intent.putExtra("pre", "true");
					FontManager.video = videosList.get(arg2);
					startActivity(intent);
					break;

				default:
					break;
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
