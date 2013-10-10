/**
 * 
 */
package com.pg.myMoneyTracker;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pg.R;
import com.pg.PocketGizmo.PocketGizmoApplication;
import com.pg.adapters.MMYspinnerAdapter;
import com.pg.data.All_Transaction_Master;
import com.pg.data.Category_Master;
import com.pg.data.Payment_Modes;

/**
 * @author shashank
 * 
 */
public class MMY_ExpenseIncome extends Activity implements OnTouchListener,
		OnClickListener {
	final String TAG = getClass().getName();
	PocketGizmoApplication pgAppObj;

	TextView txvMMYheader;
	TextView txvMMYdate, txvMMYtime, txvMMYpaymentmode, txvMMYdescription,
			txvMMYamount;
	EditText edtMMYdate, edtMMYtime, edtMMYpaymentmode, edtMMYdescription,
			edtMMYamount;
	Button btnSave, btnCancel;
	Spinner spnMMYpaymentmode, spnMMYcategory;

	int date, month, year;
	int hour, minutes, seconds;
	final int date_dialog = 1;
	final int time_dialog = 2;

	Typeface fntAppTitle, fntFormFields;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getApplicationObject();
		pgAppObj.logMe(TAG, "onCreate()");
		fntAppTitle = pgAppObj.get_fntAppTitle();
		fntFormFields = pgAppObj.get_fntFormFields();

		setTitle("myMoney Tracker - Expense / Income");
		setTitleColor(getResources().getColor(R.color.title));
		setContentView(R.layout.record_expense_income);

		txvMMYheader = (TextView) findViewById(R.id.txvMMYheader);

		txvMMYdate = (TextView) findViewById(R.id.txvMMYdate);
		txvMMYdate.setTypeface(fntFormFields);

		txvMMYtime = (TextView) findViewById(R.id.txvMMYtime);
		txvMMYtime.setTypeface(fntFormFields);

		edtMMYdate = (EditText) findViewById(R.id.edtMMYdate);
		edtMMYdate.setInputType(InputType.TYPE_NULL);
		edtMMYdate.setOnTouchListener(this);

		edtMMYtime = (EditText) findViewById(R.id.edtMMYtime);
		edtMMYtime.setInputType(InputType.TYPE_NULL);
		edtMMYtime.setOnTouchListener(this);

		txvMMYpaymentmode = (TextView) findViewById(R.id.txvMMYpaymentmode);
		txvMMYpaymentmode.setTypeface(fntFormFields);

		txvMMYdescription = (TextView) findViewById(R.id.txvMMYdescription);
		txvMMYdescription.setTypeface(fntFormFields);

		edtMMYdescription = (EditText) findViewById(R.id.edtMMYdescription);
		edtMMYdescription.setTypeface(fntFormFields, Typeface.BOLD);
		edtMMYdescription.setOnTouchListener(this);

		txvMMYamount = (TextView) findViewById(R.id.txvMMYamount);
		txvMMYamount.setTypeface(fntFormFields);

		edtMMYamount = (EditText) findViewById(R.id.edtMMYamount);
		edtMMYamount.setOnTouchListener(this);

		btnSave = (Button) findViewById(R.id.btnOK);
		btnSave.setText("Save");
		btnSave.setOnClickListener(this);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);

		spnMMYpaymentmode = (Spinner) findViewById(R.id.spnMMYpaymentmode);
		spnMMYcategory = (Spinner) findViewById(R.id.spnMMYcategory);

		updateUI();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void updateUI() {
		Intent prevIntent = getIntent();

		if (prevIntent.hasExtra("mmy_trans_type")) {
			if (prevIntent.getStringExtra("mmy_trans_type").contains("expense")) {
				txvMMYheader.setText("Record Expense");
				txvMMYheader.setTextColor(getResources().getColor(
						android.R.color.white));
				txvMMYheader.setBackgroundColor(getResources().getColor(
						R.color.textHeaderRed));
			} else {
				txvMMYheader.setText("Record Income");
				txvMMYheader.setTextColor(getResources().getColor(
						android.R.color.black));
				txvMMYheader.setBackgroundColor(getResources().getColor(
						R.color.textHeaderGreen));
			}
		}
		getCurrentDateTime();
		updateCurrentDateTime();

		tempDisplayAllTransactions();
		// getPaymentModes();
		// getCategories();
	}

	private void tempDisplayAllTransactions() {
		All_Transaction_Master objData = new All_Transaction_Master(this);
		objData.getAllRecordsOfField(All_Transaction_Master._id);
	}

	private void getPaymentModes() {
		// spnMMYpaymentmode.setAdapter(new MMYspinnerAdapter(this,
		// R.id.lnrSpinnerRow, getResources().getStringArray(
		// R.array.mmy_spn_paymentmodes), getResources()
		// .getStringArray(R.array.mmy_spn_paymentmode_icon)));

		Payment_Modes objData = new Payment_Modes(this);

		spnMMYpaymentmode
				.setAdapter(new MMYspinnerAdapter(this, R.id.lnrSpinnerRow,
						objData.getAllRecordsOfField(Payment_Modes.card_name),
						getResources().getStringArray(
								R.array.mmy_spn_paymentmode_icon)));
	}

	private void getCategories() {
		Category_Master objData = new Category_Master(this);

		spnMMYcategory.setAdapter(new MMYspinnerAdapter(this,
				R.id.lnrSpinnerRow, objData
						.getAllRecordsOfField(Category_Master.category_desc),
				getResources().getStringArray(R.array.mmy_spn_category_icon)));
	}

	private void getCurrentDateTime() {
		pgAppObj.logMe(TAG, "----> getCurrentDateTime()");

		GregorianCalendar currCal = new GregorianCalendar();
		date = currCal.get(GregorianCalendar.DATE);
		month = currCal.get(GregorianCalendar.MONTH);
		year = currCal.get(GregorianCalendar.YEAR);
		hour = currCal.get(GregorianCalendar.HOUR_OF_DAY);
		minutes = currCal.get(GregorianCalendar.MINUTE);
		seconds = currCal.get(GregorianCalendar.SECOND);
	}

	private void updateCurrentDateTime() {
		pgAppObj.logMe(TAG, "----> updateCurrentDateTime()");

		// set current date
		edtMMYdate.setText(date + "/" + (month + 1) + "/" + year);

		// set current time
		edtMMYtime.setText(hour + ":" + minutes);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		pgAppObj.logMe(TAG, "----> onCreateDialog()");

		switch (id) {
		case date_dialog:
			return new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int dtyear,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub

					date = dayOfMonth;
					month = monthOfYear;
					year = dtyear;
					updateCurrentDateTime();
				}
			}, year, month, date);

		case time_dialog:
			return new TimePickerDialog(this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub

					hour = hourOfDay;
					minutes = minute;
					updateCurrentDateTime();
				}
			}, hour, minutes, true);
		}

		return super.onCreateDialog(id);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		if (v == edtMMYdate) {
			showDialog(date_dialog);

		} else if (v == edtMMYtime) {
			showDialog(time_dialog);

		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSave) {
			addRecord();
		} else if (v == btnCancel) {
			finish();
		}
	}

	private void addRecord() {

	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
