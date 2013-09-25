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
public class User_Master {

	/**
	 * 
	 */
	private final String TAG = "User_Master";
	private final String TableName = "User_Master";

	public final static int user_photo = 0;
	public final static int user_account_status = 1;
	public final static int user_passwd_expiry = 2;
	public final static int user_email = 3;
	public final static int user_passwd = 4;
	public final static int user_username = 5;
	public final static int user_aadhar = 6;
	public final static int user_pan = 7;
	public final static int user_dob = 8;
	public final static int _id = 9;
	public final static int user_imei = 10;
	public final static int user_fname = 11;
	public final static int user_lname = 12;
	public final static int user_mname = 13;

	private Context context;
	private PocketGizmoApplication pgAppObj;

	public User_Master(Context context) {
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
