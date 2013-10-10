package com.pg.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.pg.PocketGizmo.PocketGizmoApplication;

public class All_Transaction_Master {

	private final String TAG = "All_Transaction_Master";
	private final String TableName = "all_transaction_master";
	public final static int _id = 0;
	public final static int transaction_master_date = 1;
	public final static int transaction_master_time = 2;
	public final static int transaction_master_desc = 3;
	public final static int transaction_master_amount = 4;
	public final static int all_category_master_id = 5;
	public final static int synced = 6;

	private Context context;
	private PocketGizmoApplication pgAppObj;

	public All_Transaction_Master(Context context) {
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

	/**
	 * @param fields
	 *            the fields to set
	 */
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

		// cursorData = pgAppObj.get_pgSQLDB().query(TableName, null, null,
		// null,
		// null, null, null);
		//
		// cursorData.moveToFirst();

		cursorData = getAllRecords();
		while (!cursorData.isAfterLast()) {

			mArrayList.add(cursorData.getString(fieldName));
			pgAppObj.logMe(
					TAG,
					cursorData.getString(_id) + "-"
							+ cursorData.getString(transaction_master_date)
							+ "-"
							+ cursorData.getString(transaction_master_time)
							+ "-"
							+ cursorData.getString(transaction_master_desc)
							+ "-"
							+ cursorData.getString(transaction_master_amount)
							+ "-"
							+ cursorData.getString(all_category_master_id)
							+ "-" + cursorData.getString(synced));
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
