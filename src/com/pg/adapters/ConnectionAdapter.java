package com.pg.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pg.R;
import com.pg.PocketGizmo.CheckNetwork;
import com.pg.PocketGizmo.PocketGizmoApplication;

public class ConnectionAdapter extends ArrayAdapter<String> implements
		OnClickListener {

	private PocketGizmoApplication pgAppObj;
	private Context context;
	// private int layoutResourceId;
	// private int textViewResourceId;
	private final String TAG = "ConnectionAdapter";

	private static int[] iconArray = { R.drawable.gprs48x48,
			R.drawable.bluetooth48x48, R.drawable.wifi48x48,
			R.drawable.usb48x48, R.drawable.offline48x48 };

	private static String[] textArray = { "GPRS", "Bluetooth", "Wi-Fi", "USB",
			"Go Offline" };

	public ConnectionAdapter(Context context) {
		super(context, R.id.txvConnectionText, textArray);
		// TODO Auto-generated constructor stub

		this.context = context;
		getApplicationObject();
		// this.layoutResourceId = layoutResourceId;
		// this.textViewResourceId = textViewResourceId;
		pgAppObj.logMe(TAG, "----> ConntectionAdapter()");

	}

	private RadioButton radSelect;
	private int position = -1;
	ViewGroup parent2;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {

			pgAppObj.logMe(TAG + " getView()", "position = " + position);

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.connection_item, null);
			convertView.setOnClickListener(this);

			ImageView imgIcon;
			imgIcon = (ImageView) convertView
					.findViewById(R.id.imgConnectionIcon);
			imgIcon.setImageResource(iconArray[position]);

			TextView txvText;
			txvText = (TextView) convertView
					.findViewById(R.id.txvConnectionText);
			txvText.setTypeface(Typeface.DEFAULT);
			txvText.setTextColor(Color.GREEN);
			txvText.setText(textArray[position]);

			if (position != 4) {
				final ProgressBar pgbConnection;
				pgbConnection = (ProgressBar) convertView
						.findViewById(R.id.pgbarConnection);

				Button btnTest;
				btnTest = (Button) convertView
						.findViewById(R.id.btnConnectionTest);
				btnTest.setVisibility(View.VISIBLE);
				btnTest.setId(position);
				btnTest.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((Button) v).setVisibility(View.GONE);
						pgbConnection.setVisibility(View.VISIBLE);

						CheckNetwork obj = new CheckNetwork(getContext());

						if (obj.checkInternetConnection(0) == true) {
							// if (PocketGizmoApplication.getInstance()
							// .checkInternetConnection() == true) {
							Log.i(TAG, "ONLINE");
							pgbConnection.setVisibility(View.GONE);

						} else {
							Log.i(TAG, "OFFLINE");
							// pgbConnection.setVisibility(View.GONE);
							// ((Button) v).setVisibility(View.VISIBLE);

						}
					}
				});
			}

			RadioButton radSelect;
			radSelect = (RadioButton) convertView.findViewById(R.id.radSelect);
			radSelect.setId(position);
			radSelect.setChecked(false);
			radSelect.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Log.i(TAG, "" + ((RadioButton) v).getId());

					if (position != ConnectionAdapter.this.position
							&& ConnectionAdapter.this.radSelect != null) {
						ConnectionAdapter.this.radSelect.setChecked(false);
					}

					ConnectionAdapter.this.position = position;
					ConnectionAdapter.this.radSelect = (RadioButton) v;
				}
			});

			if (this.position != position) {
				radSelect.setChecked(false);

			} else {
				radSelect.setChecked(true);

				if (this.radSelect != null && radSelect != this.radSelect) {
					this.radSelect = radSelect;
				}
			}
		}

		return convertView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Log.i(TAG, "some click event on row");

		if (v instanceof Button) {
			Log.i(TAG, "Button clicked");
			v.setVisibility(View.GONE);

		}
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			Log.i(TAG, "pgAppObj --> null = true");
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
