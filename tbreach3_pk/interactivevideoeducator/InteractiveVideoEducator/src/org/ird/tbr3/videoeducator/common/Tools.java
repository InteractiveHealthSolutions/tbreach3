package org.ird.tbr3.videoeducator.common;

import org.ird.tbr3.videoeducator.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Tools {
	private static Tools tools;

	MediaPlayer mp;

	private Tools() {
		mp = new MediaPlayer();
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {

			}
		});
	}

	public static Tools getInstance() {
		if (tools == null) {
			tools = new Tools();
		}
		return tools;
	}

	

	public String getExternalStorageDirectoryPath(String path) {
		return Environment.getExternalStorageDirectory().getPath() + path;
	}

	public void runButtonTouchEffect(Context context, View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			v.setBackground(context.getResources().getDrawable(
					R.drawable.border_filled));

		} else if (event.getAction() == MotionEvent.ACTION_UP) {

			v.setBackground(context.getResources().getDrawable(
					R.drawable.border));

		}
	}
	
	/**
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean isValidId (String id) {
		boolean isValid = true;
		isValid = id.length () == 14;
		id = id.replaceAll ("\\W", "");
		// Validate Luhn check digit
		if (isValid)
		{
			String idWithoutCheckdigit = id.substring (0, id.length () - 1);
			char idCheckdigit = id.charAt (id.length () - 1);
			String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVYWXZ_";
			idWithoutCheckdigit = idWithoutCheckdigit.trim();
			int sum = 0;
			for (int i = 0; i < idWithoutCheckdigit.length (); i++)
			{
				char ch = idWithoutCheckdigit.charAt (idWithoutCheckdigit.length () - i - 1);
				if (validChars.indexOf (ch) == -1)
					isValid = false;
				int digit = (int) ch - 48;
				int weight;
				if (i % 2 == 0)
				{
					weight = (2 * digit) - (int) (digit / 5) * 9;
				}
				else
				{
					weight = digit;
				}
				sum += weight;
			}
			sum = Math.abs (sum) + 10;
			int checkDigit = (10 - (sum % 10)) % 10;
			isValid = checkDigit == Integer.parseInt (String.valueOf (idCheckdigit));
		}
		return isValid;
	}
	
	public boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();

			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					Log.i("Class", info[i].getState().toString());
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Drawable getDrawableFromLocalStorage(Context c, String path, int resizeTo) {
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+path);
		Drawable drawable = new BitmapDrawable(c.getResources(), resizeBitmap(bitmap, resizeTo));
		return drawable;
	}
	
	public Bitmap resizeBitmap(Bitmap bitmap, int scale) {
		if (bitmap != null) {
			double width = bitmap.getWidth();
			double height = bitmap.getHeight();

			double n = height / scale;
			double newWidth = width / n;

			Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, (int) newWidth, scale, true);
			return newBitmap;
		} else {
			return null;
		}
	}
}
