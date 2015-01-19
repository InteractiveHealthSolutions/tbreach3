package org.ird.tbr3.videoeducator.screens;

import org.ird.tbr3.videoeducator.R;
import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.database.model.Language;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageViewer extends Activity {

	ImageView imageView;
	Button btnClose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);
		
		imageView = (ImageView) findViewById(R.image_viewer.ivMain);
		btnClose = (Button) findViewById(R.id.btnClose);
		if(Language.ENGLISH_US.equals(App.LANGUAGE.getLanguageId())){
			btnClose.setText("Close");
		} else if(Language.URDU_PAK.equals(App.LANGUAGE.getLanguageId())){
			btnClose.setText("بند کریں");
			btnClose.setTypeface(FontManager.global.getTypeFace(Language.URDU_PAK));
		} else if(Language.BENGLA_BAN.equals(App.LANGUAGE.getLanguageId())){
			btnClose.setText("বন্ধ করা");
			btnClose.setTypeface(FontManager.global.getTypeFace(Language.BENGLA_BAN));
		}
		
		btnClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		Intent intent = getIntent();
		String path = intent.getStringExtra("image_to_display");
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+path);
		
		Drawable drawable = new BitmapDrawable(getResources(), bitmap);
		imageView.setImageDrawable(drawable);
		String title = getIntent().getStringExtra("image_title");
		if(title != null) {
			setTitle(title);
		}
	}
}
