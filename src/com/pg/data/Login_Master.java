package com.pg.data;

import java.util.Currency;

import com.pg.pocketgizmo.PocketGizmoApplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Login_Master {
	private PocketGizmoApplication pgAppObj;
	private DBAdapter dbAdapter;
	private SQLiteDatabase sqlDB;

	private static final String TAG = "Login_Master";
	public static String TABLE_NAME = "Login_Master";

	public static String FIELDS[] = { "login_id", "login_fname", "login_lname",
			"login_username", "login_passwsd", "login_email",
			"login_account_expiry", "login_account_active", "login_image" };

	// public static String Table_Creation_Fields = "(" + Fields[0]
	// + " integer primary key autoincrement, " + Fields[1] + " text, "
	// + Fields[2] + " text, " + Fields[3] + " text, " + Fields[4]
	// + " text, " + Fields[5] + " text, " + Fields[6] + " datetime, "
	// + Fields[7] + " boolean, " + Fields[8] + " text);";

	private static String Table_Creation_Fields = "(" + FIELDS[0]
			+ " text primary key, " + FIELDS[1] + " text, " + FIELDS[2]
			+ " text, " + FIELDS[3] + " text, " + FIELDS[4] + " text, "
			+ FIELDS[5] + " text, " + FIELDS[6] + " datetime, " + FIELDS[7]
			+ " boolean, " + FIELDS[8] + " text);";

	public static String Table_CREATE = "CREATE TABLE " + TABLE_NAME
			+ (Table_Creation_Fields);

	public static void print() {
		Log.i(TAG, "==============");
		Log.i(TAG, Table_CREATE);

	}

	// public static final String Table_CREATE =
	// "create table contacts (_id integer primary key autoincrement, "
	// + "name text not null, email text not null);";

	public Login_Master() {
		getApplicationObject();
		dbAdapter = pgAppObj.get_dbAdapter();
		sqlDB = pgAppObj.get_sqlDB();
	}

	/**
	 * @return the strTableName
	 */
	public String getTableName() {
		return TABLE_NAME;
	}

	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return FIELDS;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	private void setFields(String[] Fields) {
		this.FIELDS = FIELDS;
	}

	public void addRecord() {
		ContentValues newRecord = new ContentValues();
		long rowID;

		newRecord.put(FIELDS[0],
				pgAppObj.getIMEI() + java.lang.System.currentTimeMillis());

		for (int i = 1; i < FIELDS.length; i++) {
			Log.i(TAG, "===> " + FIELDS[i]);
			newRecord.put(FIELDS[i], "java-" + i);
		}

		dbAdapter = pgAppObj.get_dbAdapter();
		if (dbAdapter == null) {
			Log.i(TAG, "pgAppObj.getDB() = " + pgAppObj.get_dbAdapter());
		}

		rowID = sqlDB.insert(TABLE_NAME, null, newRecord);

		Log.i(TAG, "insert() rowID = " + rowID);
		// initialValues.put(KEY_EMAIL, email);
		// return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public int deleteRecord() {
		return 0;
	}

	public int deleteAllRecords() {
		Log.i(TAG, "deleteRecord()");

		return sqlDB.delete(TABLE_NAME, null, null);
		// return sqlDB.delete(TABLE_NAME, "" + FIELDS[0].toString() + “= *",
		// null) > 0;
	}

	public void getRecord() {

	}

	public Cursor getAllRecords() {
		return sqlDB.query(TABLE_NAME, FIELDS, null, null, null, null, null);

	}

	public void updateRecord() {

	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			Log.i(TAG, "pgAppObj --> null = true");
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
