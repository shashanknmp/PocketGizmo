package com.pg.alertdialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.pg.R;
import com.pg.adapters.ConnectionAdapter;

public class ConnectionDialog extends AlertDialog.Builder implements
		OnClickListener {

	final String TAG = "ConnectionDialog";
	Context context;
	String strTitle;

	public ConnectionDialog(Context context, String strTitle) {
		super(context);
		// TODO Auto-generated constructor stub

		Log.i(TAG, "before constructor");
		this.context = context;

		if (strTitle == null || strTitle.equalsIgnoreCase("")) {
			Log.i(TAG, "null");
			this.setTitle("Select SERVER connection");

		} else {
			Log.i(TAG, "not null");
			this.setTitle(strTitle);

		}

		//
		// if (strTitle.equalsIgnoreCase("") || strTitle.equals(null)) {
		// this.setTitle("Select SERVER connection");
		//
		// } else {
		//
		// }
		show();
		Log.i(TAG, "after constructor");
	}

	@Override
	public AlertDialog show() {
		// TODO Auto-generated method stub

		// setInverseBackgroundForced(true);
		setIcon(R.drawable.server_connection48x48);
		setAdapter(new ConnectionAdapter(context), this);
		// setSingleChoiceItems(new ConnectionAdapter(context), 0, this);
		setPositiveButton("Connect", this);
		setNegativeButton("Cancel", this);
		create();

		return super.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

		Log.i(TAG, "clicked " + which);
	}
}
