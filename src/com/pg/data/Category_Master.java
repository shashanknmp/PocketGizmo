package com.pg.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.pg.PocketGizmo.PocketGizmoApplication;

public class Category_Master {

	private final String TAG = "Category_Master";
	private final String TableName = "category_master";
	public final static int _id = 0;
	public final static int category_desc = 1;

	private Context context;
	private PocketGizmoApplication pgAppObj;

	// public static String Fields[] = { "login_id", "login_fname",
	// "login_lname",
	// "login_username", "login_passwsd", "login_email",
	// "login_account_expiry", "login_account_active", "login_image" };
	//
	// public static String Table_Creation_Fields[] = {
	// "(login_id primary key autoincrement,", "login_fname text,",
	// "login_lname text,", "login_username text,", "login_passwsd text,",
	// "login_email text,", "login_account_expiry datetime,",
	// "login_account_active boolean,", "login_image text);" };
	//
	// public static String Table_CREATE = "create table " + TableName
	// + (Table_Creation_Fields);

	public Category_Master(Context context) {
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

	/*	*//**
	 * @return the fields
	 */
	/*
	 * public String[] getFields() { return Fields; }
	 */
	/*	*//**
	 * @param fields
	 *            the fields to set
	 */
	/*
	 * public void setFields(String[] Fields) { this.Fields = Fields; }
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
