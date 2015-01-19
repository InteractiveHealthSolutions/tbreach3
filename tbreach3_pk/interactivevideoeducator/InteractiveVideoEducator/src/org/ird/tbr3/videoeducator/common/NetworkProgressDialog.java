package org.ird.tbr3.videoeducator.common;

import android.app.ProgressDialog;
import android.content.Context;

public class NetworkProgressDialog extends ProgressDialog {

	public NetworkProgressDialog(Context context, String message) {
		super(context);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		setMessage(message);
		setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}

}
