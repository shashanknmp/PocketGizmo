package com.pg.PocketGizmo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pg.R;
import com.pg.data.DBAdapter;
import com.pg.data.Login_Master;

public class PocketGizmoActivity extends Activity {

	private final String TAG = getClass().getName();
	private ProgressBar prgProgressBar1;
	private int progressStatus1 = 0;
	private TextView txvAppName;
	private Handler handler1 = new Handler();
	private PocketGizmoApplication pgAppObj;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i(TAG, "onCreate()");
		setContentView(R.layout.pocketgizmomain);

		txvAppName = (TextView) findViewById(R.id.txvAppName);
		// set font now

		getApplicationObject();
		txvAppName.setText(txvAppName.getText() + " "
				+ pgAppObj.getVersionCode());

		checkAvailableConnections();
		// checkDatabases();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		Log.i(TAG, "onDestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Log.i(TAG, "onPause()");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		Log.i(TAG, "onRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.i(TAG, "onResume()");
		Login_Master login = new Login_Master();
		login.addRecord();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Log.i(TAG, "onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		Log.i(TAG, "onStop()");
	}

	private void checkAvailableConnections() {
		prgProgressBar1 = (ProgressBar) findViewById(R.id.progressBar2);

		// Start lengthy operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (progressStatus1 < 100) {
					// mProgressStatus = doWork();
					progressStatus1 = progressStatus1 + 3;
					try {
						Thread.sleep(25);
					} catch (Exception e) {
					}

					// Update the progress bar
					handler1.post(new Runnable() {
						public void run() {
							prgProgressBar1.setProgress(progressStatus1);
						}
					});
				}
				if (progressStatus1 >= 100) {
					Intent loginIntent = new Intent(PocketGizmoActivity.this,
							LoginActivity.class);
					startActivity(loginIntent);
					finish();
				}
			}
		}).start();
	}

	private void checkDatabases() {
		// DBAdapter db = new DBAdapter(this);
		// Log.i(TAG, "after new DBAdapter()");
		//
		// db.open();
		// Log.i(TAG, "after db.open()");
		pgAppObj.get_dbAdapter().close();
		Log.i(TAG, ">>> db.close()");
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			Log.i(TAG, "pgAppObj --> null = true");
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}