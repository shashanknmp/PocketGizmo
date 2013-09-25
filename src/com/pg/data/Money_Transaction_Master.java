/**
 * 
 */
package com.pg.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.pg.PocketGizmo.PocketGizmoApplication;

/**
 * @author shashank
 * 
 */
public class Money_Transaction_Master {

	/**
	 * 
	 */
	private final String TAG = "Money_Transaction_Master";
	private final String TableName = "myMoney_Transaction_Master";
	public final static int mmy_transaction_balance = 0;
	public final static int mmy_transaction_amount = 1;
	public final static int mmy_transaction_desc = 2;
	public final static int mmy_transaction_time = 3;
	public final static int mmy_transaction_date = 4;
	public final static int mmy_transaction_payment_mode = 5;
	public final static int mmy_transaction_type = 6;
	public final static int _id = 7;
	public final static int user_master_id = 8;
	public final static int category_id = 9;

	private Context context;
	private PocketGizmoApplication pgAppObj;

	public Money_Transaction_Master(Context context) {
		// TODO Auto-generated constructor stub
		getApplicationObject();
		this.context = context;
	}

	/**
	 * @return the strTableName
	 */
	public String getTableName() {
		pgAppObj.logMe(TAG, "TableName = " + TableName);
		return TableName;
	}

	public Cursor getAllRecords() {
		Cursor cursorData = pgAppObj.get_pgSQLDB().query(TableName, null, null,
				null, null, null, null);
		cursorData.moveToFirst();

		return cursorData;
	}

	public String[] getAllRecordsOfField(int fieldName) {
		ArrayList<String> mArrayList = new ArrayList<String>();
		String[] strContents;
		Cursor cursorData;

		cursorData = pgAppObj.get_pgSQLDB().query(TableName, null, null, null,
				null, null, null);

		cursorData.moveToFirst();
		while (!cursorData.isAfterLast()) {

			mArrayList.add(cursorData.getString(fieldName));
			cursorData.moveToNext();
		}

		strContents = new String[mArrayList.size()];
		mArrayList.toArray(strContents);

		return strContents;
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
