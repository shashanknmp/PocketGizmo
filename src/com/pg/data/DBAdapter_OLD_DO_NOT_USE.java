package com.pg.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pg.PocketGizmo.PocketGizmoApplication;

public class DBAdapter_OLD_DO_NOT_USE {
	// public static final String KEY_ROWID = "_id";
	// public static final String KEY_NAME = "name";
	// public static final String KEY_EMAIL = "email";

	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;
	private static final String DATABASE_NAME = "PocketGizmoDB";
	private String DB_PATH;
	// private String Table_Login = "Login_Master";
	// private String Table_myMoney_Transaction = "myMoney_Transaction_Master";
	// private String Table_myBank = "myBank_Master";
	// private String Table_myBank_Transaction = "myBank_Transaction_Master";
	// private String Table_Category = "Category_Master";
	// private String Table_Product = "Product_Master";
	// private String Table_Store = "Store_Master";
	// private String Table_Stock_Transaction = "Stock_Transaction_Master";

	private static int DATABASE_VERSION = 1;

	private final Context context;
	// private final String strTableName;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase sqlDB;

	// private static final String DATABASE_CREATE =
	// "create table contacts (_id integer primary key autoincrement, "
	// + "name text not null, email text not null);";

	public DBAdapter_OLD_DO_NOT_USE(Context context) { // , String strTableName)
														// {
		Log.i(TAG, "DBAdapter()");
		this.context = context;

		getApplicationObject();
		DB_PATH = pgAppObj.getDataDir() + "/" + DATABASE_NAME;

		// this.strTableName = strTableName;
		//
		// if (this.strTableName != null) {
		// if (this.strTableName.equalsIgnoreCase("Login_Master")) {
		//
		// } else if (this.strTableName
		// .equalsIgnoreCase("myMoney_Transaction_Master")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("myBank_Master")) {
		//
		// } else if (this.strTableName
		// .equalsIgnoreCase("myBank_Transaction_Master")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("Category_Master")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("Product_Master")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("Product_Type")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("Store_Master")) {
		//
		// } else if (this.strTableName.equalsIgnoreCase("Stock_Master")) {
		//
		// } else if (this.strTableName
		// .equalsIgnoreCase("Stock_Transaction_Master")) {
		//
		// }
		// }

		DBHelper = new DatabaseHelper(context, pgAppObj);
	}

	/**
	 * @return the databaseVersion
	 */
	public static int getDatabaseVersion() {
		return DATABASE_VERSION;
	}

	/**
	 * @param databaseVersion
	 *            the databaseVersion to set
	 */
	private static void setDatabaseVersion(int databaseVersion) {
		DATABASE_VERSION = databaseVersion;
	}

	/**
	 * @return the sqlDB
	 */
	public SQLiteDatabase get_sqlDB() {
		return sqlDB;
	}

	/**
	 * @param sqlDB
	 *            the sqlDB to set
	 */
	private void set_sqlDB(SQLiteDatabase sqlDB) {
		this.sqlDB = sqlDB;
	}

	public boolean isDBavailable() {
		File fHandle = new File(DB_PATH);
		if (fHandle.exists()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean copyDBfromAssets() {

		pgAppObj.logMe(TAG, "----> copyDBfromAssets()");
		boolean DBcopied = false;

		try {
			if (isDBavailable() == false) {
				startCopyingDB(context.getAssets().open("PocketGizmoDB"),
						new FileOutputStream(DB_PATH));
				DBcopied = true;
			}
		} catch (FileNotFoundException e) {
			DBcopied = false;
			e.printStackTrace();
		} catch (IOException e) {
			DBcopied = false;
			e.printStackTrace();
		}
		// DBAdapter db = new DBAdapter(this);
		//
		// db.open();
		// Cursor c = db.getAllContacts();
		// if (c.moveToFirst())
		// {
		// do {
		// DisplayContact(c);
		// } while (c.moveToNext());
		// }
		// db.close();
		Log.i(TAG, DB_PATH);
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
			OutputStream outputStream) throws IOException {
		// ---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	public boolean deleteDB() {
		boolean status;

		// pgAppObj.get_dbAdapter.close();
		status = context.deleteDatabase(DATABASE_NAME);
		if (status == true) {
			pgAppObj.logMe(TAG, "DB deleted");

		} else {
			pgAppObj.logMe(TAG, "DB deletion failed");

		}
		return status;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		private final String TAG = getClass().getName();
		PocketGizmoApplication pgAppObj;

		DatabaseHelper(Context context, PocketGizmoApplication pgAppObj) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

			this.pgAppObj = pgAppObj;
			pgAppObj.logMe(TAG, "DatabaseHelper()");
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			super.onOpen(db);

			pgAppObj.logMe(TAG, "DB opened successfully !!");

		}

		@Override
		public void onCreate(SQLiteDatabase sqlDB) {

			pgAppObj.logMe(TAG, "onCreate()");
			Log.i(TAG, "onCreate() from log.i");

			// if (pgAppObj.get_dbAdapter().isDBavailable() == false) {
			// pgAppObj.get_dbAdapter().copyDBfromAssets();
			// }
			// try {
			// // db.execSQL(DATABASE_CREATE);
			// Login_Master.print();
			//
			// sqlDB.execSQL(Login_Master.Table_CREATE);
			// sqlDB.execSQL(myMoney_Transaction_Master.Table_CREATE);
			//
			// // db.execSQL(myBank_Master.Table_CREATE);
			// // db.execSQL(myBank_Transaction_Master.Table_CREATE);
			// // db.execSQL(Category_Master.Table_CREATE);
			// // db.execSQL(Product_Master.Table_CREATE);
			// // db.execSQL(Store_Master.Table_CREATE);
			// // db.execSQL(Stock_Master.Table_CREATE);
			// // db.execSQL(Stock_Transaction_Master.Table_CREATE);
			//
			// } catch (SQLException e) {
			// // pgAppObj.logMe(TAG, "Err: " + e.toString());
			// }
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion,
				int newVersion) {
			pgAppObj.logMe(TAG, "Upgrading database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data");
			sqlDB.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(sqlDB);
		}
	}

	// ---opens the database---
	public DBAdapter_OLD_DO_NOT_USE open() throws SQLException {
		sqlDB = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// // ---insert a contact into the database---
	// public long insertContact(String name, String email) {
	// ContentValues initialValues = new ContentValues();
	// initialValues.put(KEY_NAME, name);
	// initialValues.put(KEY_EMAIL, email);
	// return sqlDB.insert(DATABASE_TABLE, null, initialValues);
	// }
	//
	// // ---deletes a particular contact---
	// public boolean deleteContact(long rowId) {
	// return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	// }
	//
	// // ---retrieves all the contacts---
	// public Cursor getAllContacts() {
	// return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
	// KEY_EMAIL }, null, null, null, null, null);
	// }
	//
	// // ---retrieves a particular contact---
	// public Cursor getContact(long rowId) throws SQLException {
	// Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
	// KEY_ROWID, KEY_NAME, KEY_EMAIL }, KEY_ROWID + "=" + rowId,
	// null, null, null, null, null);
	// if (mCursor != null) {
	// mCursor.moveToFirst();
	// }
	// return mCursor;
	// }
	//
	// // ---updates a contact---
	// public boolean updateContact(long rowId, String name, String email) {
	// ContentValues args = new ContentValues();
	// args.put(KEY_NAME, name);
	// args.put(KEY_EMAIL, email);
	// return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) >
	// 0;
	// }
	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}

}
