package org.ird.tbr3.videoeducator.common;

import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.database.model.Video;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {
	Context aContext;
	private static Boolean init = true;
	private Typeface urduFont, benglaFont;
	public static Video video;
	 
	public static FontManager global;
	
	public FontManager(Context context) {
		aContext = context;
		urduFont = Typeface.createFromAsset(aContext.getAssets(), "jameel_noori_nastaleeq_regular.ttf");
		benglaFont = Typeface.createFromAsset(aContext.getAssets(), "kalpurush.ttf");
		
		if(init){ // otherwise stack/memory overflow exception will be thrown
			init = false;
			global = new FontManager(context);
		}
	}
	
	 public Typeface getTypeFace(String language) {
		 
		 if(language.equals(Language.URDU_PAK)){
			 return urduFont;
		 } else if(language.equals(Language.BENGLA_BAN)) {
			 return benglaFont;
		 }
		 return null;
	 }
	
}
