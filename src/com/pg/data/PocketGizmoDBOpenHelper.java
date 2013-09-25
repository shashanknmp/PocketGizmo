/**
 * 
 */
package com.pg.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pg.PocketGizmo.PocketGizmoApplication;

/**
 * @author shashank
 * 
 */
public class PocketGizmoDBOpenHelper extends SQLiteOpenHelper {

	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;
	private Context context;
	private String DB_PATH;
	public static final String DATABASE_NAME = "PocketGizmo.db";
	public static int DATABASE_VERSION = 1;

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public PocketGizmoDBOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub

		getApplicationObject();
		pgAppObj.logMe(TAG, "PocketGizmoDBOpenHelper()");
		this.context = context;
		DB_PATH = pgAppObj.getDataDir() + "/databases/" + DATABASE_NAME;
		pgAppObj.logMe(TAG, "DB_PATH = " + DB_PATH);

		copyDBfromAssets();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		pgAppObj.logMe(TAG, "onCreate()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		pgAppObj.logMe(TAG, "onUpgrade()");

	}

	public boolean isDBavailable() {
		pgAppObj.logMe(TAG, "isDBavailable()");

		File fHandle = new File(DB_PATH);
		if (fHandle.exists()) {
			return true;

		} else {
			return false;
		}
	}

	public boolean copyDBfromAssets() {
		pgAppObj.logMe(TAG, "copyDBfromAssets()");

		boolean DBcopied = false;

		try {
			if (isDBavailable() == false) {
				this.getWritableDatabase();

				startCopyingDB(context.getAssets().open(DATABASE_NAME),
						new FileOutputStream(DB_PATH));
				DBcopied = true;
			}

		} catch (FileNotFoundException e) {
			DBcopied = false;
			pgAppObj.logMe(TAG, "FileNotFoundException: " + e.toString());

		} catch (IOException e) {
			DBcopied = false;
			pgAppObj.logMe(TAG, "IOException: " + e.toString());
		}
		Log.i(TAG, "DB_PATH = " + DB_PATH);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		Log.i(TAG, "==============" + DBcopied);
		return DBcopied;
	}

	private void startCopyingDB(InputStream inputStream,
			FileOutputStream outputStream) {
		pgAppObj.logMe(TAG, "startCopyingDB()");

		// ---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		try {
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			pgAppObj.logMe(TAG, "startCopyingDB - Exception" + e.toString());
		}
	}

	public boolean deleteDB() {
		pgAppObj.logMe(TAG, "deleteDB()");

		boolean status;

		// close();
		status = context.deleteDatabase(DATABASE_NAME);
		if (status == true) {
			pgAppObj.logMe(TAG, "DB deleted");

		} else {
			pgAppObj.logMe(TAG, "DB deletion failed");

		}
		return status;
	}

	// opens the database for reading / writing
	public SQLiteDatabase openDBforWrite() {
		pgAppObj.logMe(TAG, "openDBforWrite()");

		try {
			pgAppObj.logMe(TAG, "returning DB");

			return getWritableDatabase();

		} catch (SQLException e) {
			pgAppObj.logMe(TAG, "err: " + e.toString());
		}
		return null;
	}

	// opens the database for reading
	public SQLiteDatabase openDBforRead() {
		pgAppObj.logMe(TAG, "openDBforRead()");

		try {
			return getReadableDatabase();

		} catch (SQLException e) {
			pgAppObj.logMe(TAG, "err: " + e.toString());
		}
		return null;
	}

	// closes the database
	public void close() {

		try {
			pgAppObj.get_pgOpenHelper().close();

		} catch (Exception e) {
			pgAppObj.logMe(TAG, "err: " + e.toString());
		}
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
