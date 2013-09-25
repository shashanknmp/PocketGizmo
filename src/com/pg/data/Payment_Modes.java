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
public class Payment_Modes {

	/**
	 * 
	 */
	private final String TAG = "Payment_Modes";
	private final String TableName1 = "myBank_Card_Master";
	private final String TableName2 = "myPrepaid_Card_Master";

	public final static int _id = 0;
	public final static int card_type = 1;
	public final static int card_expiry_date = 2;
	public final static int card_name = 3;
	public final static int card_status = 4;
	public final static int card_no = 5;
	public final static int bank_master_id = 6;
	public final static int prepaid_card_balance = 7;

	private Context context;
	private PocketGizmoApplication pgAppObj;

	public Payment_Modes(Context context) {
		// TODO Auto-generated constructor stub
		getApplicationObject();
		this.context = context;
	}

	/**
	 * @return the strTableName
	 */
	public String getTableName() {
		pgAppObj.logMe(TAG, "TableName = " + TableName1);
		return TableName1;
	}

	public Cursor getAllRecords() {
		Cursor cursorData = pgAppObj.get_pgSQLDB().query(TableName1, null, null,
				null, null, null, null);
		cursorData.moveToFirst();

		return cursorData;
	}

	public String[] getAllRecordsOfField(int fieldName) {
		ArrayList<String> mArrayList = new ArrayList<String>();
		String[] strContents;
		Cursor cursorData;

		cursorData = pgAppObj.get_pgSQLDB().query(TableName1, null, null, null,
				null, null, null);
		cursorData.moveToFirst();

		mArrayList.add("Cash");

		while (!cursorData.isAfterLast()) {
			mArrayList.add(cursorData.getString(fieldName));
			cursorData.moveToNext();
		}

		pgAppObj.logMe(TAG, "size = " + mArrayList.size());
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
