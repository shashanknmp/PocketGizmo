package com.pg.PocketGizmo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.newrelic.agent.android.NewRelic;
import com.pg.R;

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

		getApplicationObject();
		setTitleColor(getResources().getColor(R.color.title));
		setContentView(R.layout.pocketgizmomain);

		newrelicAgent();

		txvAppName = (TextView) findViewById(R.id.txvAppName);
		// set font now

		txvAppName.setText(txvAppName.getText() + " "
				+ pgAppObj.getVersionCode());

		checkAvailableConnections();
		checkDatabases();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		pgAppObj.logMe(TAG, "onDestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		pgAppObj.logMe(TAG, "onPause()");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		pgAppObj.logMe(TAG, "onRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		pgAppObj.logMe(TAG, "onResume()");
		// Login_Master login = new Login_Master();
		// login.addRecord();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		pgAppObj.logMe(TAG, "onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		pgAppObj.logMe(TAG, "onStop()");
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
		// pgAppObj.get_pgOpenHelper().copyDBfromAssets();
	}

	private void newrelicAgent() {
		NewRelic.withApplicationToken(
				"AA7e3a3bdcbb1f7eb9a978e736f653d89eccf257bf").start(
				this.getApplication());
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}