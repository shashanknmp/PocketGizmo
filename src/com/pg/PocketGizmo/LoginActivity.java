package com.pg.PocketGizmo;

import java.util.logging.LogManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.pg.R;
import com.pg.alertdialogs.ConnectionDialog;
import com.pg.data.DBAdapter_OLD_DO_NOT_USE;
import com.pg.data.Login_Master;
import com.pg.data.PocketGizmoDBOpenHelper;

public class LoginActivity extends Activity implements OnClickListener {

	private PocketGizmoApplication pgAppObj;
	private final String TAG = getClass().getName();

	private TextView txvAppName;
	private ViewFlipper vFlipper1;
	private TextView txvFieldUN, txvFieldPWD, txvFieldCTYPE;
	private EditText edtUsername, edtPassword;
	private ImageButton imgButton1;
	private Button btnLoginOK, btnLoginCancel;
	private static final int SHOW_CONNECTION_DIALOG = 1;

	private PocketGizmoDBOpenHelper pgOpenHelper;
	private SQLiteDatabase sqlDB = null;

	private Typeface fntAppTitle, fntFormFields, fntViewFlipper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getApplicationObject();
		pgOpenHelper = pgAppObj.get_pgOpenHelper();
		// sqlDB = pgAppObj.get_sqlDB();
		fntAppTitle = pgAppObj.get_fntAppTitle();
		fntFormFields = pgAppObj.get_fntFormFields();
		fntViewFlipper = pgAppObj.get_fntViewFlipper();

		setTitle("PocketGizmo - Login");
		setTitleColor(getResources().getColor(R.color.title));
		setContentView(R.layout.loginmain);

		txvAppName = (TextView) findViewById(R.id.txvAppName);
		txvAppName.setText(R.string.app_name);
		txvAppName.setTypeface(fntAppTitle, Typeface.NORMAL);

		vFlipper1 = ((ViewFlipper) this.findViewById(R.id.viewFlipper1));
		vFlipper1.setOnClickListener(this);

		for (int i = 0; i < vFlipper1.getChildCount(); i++) {
			TextView tview = (TextView) vFlipper1.getChildAt(i);
			tview.setTypeface(fntViewFlipper);
		}

		vFlipper1.startFlipping();
		vFlipper1.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in));
		vFlipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_out));

		txvFieldUN = (TextView) findViewById(R.id.txvFieldUN);
		txvFieldUN.setTypeface(fntFormFields);

		edtUsername = (EditText) findViewById(R.id.edtUsername);
		edtUsername.setTypeface(fntFormFields, Typeface.BOLD);

		txvFieldPWD = (TextView) findViewById(R.id.txvFieldPWD);
		txvFieldPWD.setTypeface(fntFormFields);

		edtPassword = (EditText) findViewById(R.id.edtPassword);
		edtPassword.setTypeface(fntFormFields, Typeface.BOLD);

		txvFieldCTYPE = (TextView) findViewById(R.id.txvFieldCTYPE);
		txvFieldCTYPE.setTypeface(fntFormFields);

		imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
		imgButton1.setOnClickListener(this);

		btnLoginOK = (Button) findViewById(R.id.btnOK);
		btnLoginOK.setText(R.string.btnlogin);
		btnLoginOK.setOnClickListener(this);

		btnLoginCancel = (Button) findViewById(R.id.btnCancel);
		btnLoginCancel.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		pgAppObj.logMe(TAG, "onResume()");
		// device resolution

		pgAppObj.logMe(TAG, "\n");
		pgAppObj.logMe(TAG, "\n");
		pgAppObj.logMe(TAG, "Width = " + pgAppObj.get_deviceWidth());
		pgAppObj.logMe(TAG, "Height = " + pgAppObj.get_deviceHeight());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		pgAppObj.logMe(TAG, "onPause()");
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub

		switch (id) {

		case SHOW_CONNECTION_DIALOG:
			new ConnectionDialog(this, "Server Connection");

		}
		return super.onCreateDialog(id);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnLoginOK) {
			loadMainMenu();
			finish();

		} else if (v == btnLoginCancel) {
			pgAppObj.get_pgOpenHelper().deleteDB();
			// // closeAllConnections(); // server disconnect, DB disconnect, BT
			// // disconnect
			// // finish();
			// Login_Master login = new Login_Master();
			// Cursor c = login.getAllRecords();
			// pgAppObj.logMe(TAG, "Total Record(s):  " + c.getCount());
			// pgAppObj.logMe(TAG, "Total Columns:  " + c.getColumnCount());
			//
			// int index;
			//
			// if (c.moveToFirst()) {
			// do {
			// pgAppObj.logMe(TAG,
			// "====================================================");
			// for (index = 0; index < c.getColumnCount(); index++) {
			// pgAppObj.logMe(TAG,
			// "cursor---> " + c.getColumnName(index) + ">>"
			// + c.getString(index));
			// }
			// pgAppObj.logMe(TAG,
			// "====================================================");
			//
			// // Log.i(TAG, "cursor---> " + c.getString(1));
			// // Log.i(TAG, "cursor---> " + c.getString(2));
			// // Log.i(TAG, "cursor---> " + c.getString(3));
			// // Log.i(TAG, "cursor---> " + c.getString(4));
			// // Log.i(TAG, "cursor---> " + c.getString(5));
			// // Log.i(TAG, "cursor---> " + c.getString(6));
			// // Log.i(TAG, "cursor---> " + c.getString(7));
			// // Log.i(TAG, "cursor---> " + c.getString(8));
			// } while (c.moveToNext());
			// }
			// c.close();
			//
			// // now delete all records
			// pgAppObj.logMe(TAG, "deleted =====" + login.deleteAllRecords());

		} else if (v == imgButton1) {
			showDialog(SHOW_CONNECTION_DIALOG);

		} else if (v == vFlipper1) {
			// vFlipper1.startFlipping();
			// vFlipper1.stopFlipping();

		}
	}

	private void loadMainMenu() {
		Intent mainMenuIntent = new Intent(LoginActivity.this,
				MainMenuActivity.class);
		startActivity(mainMenuIntent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		// getMenuInflater().inflate(R.menu.optionsmenu, menu);

		MenuItem mnuItem1 = menu.add(0, 99, 0, "About...");
		mnuItem1.setIcon(R.drawable.developer128x128);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 99:
			Toast.makeText(this,
					"Developed by Shashank N Paralkar\nshashanknmp@gmail.com",
					Toast.LENGTH_SHORT).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
