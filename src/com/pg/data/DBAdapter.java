package com.pg.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	// public static final String KEY_ROWID = "_id";
	// public static final String KEY_NAME = "name";
	// public static final String KEY_EMAIL = "email";

	private final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "PocketGizmoDB";

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

	public DBAdapter(Context context) { // , String strTableName) {
		Log.i(TAG, "DBAdapter()");
		this.context = context;
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

		DBHelper = new DatabaseHelper(context);
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

	private static class DatabaseHelper extends SQLiteOpenHelper {
		private final String TAG = "DatabaseHelper";

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase sqlDB) {
			Log.i(TAG, "onCreate()");

			try {
				// db.execSQL(DATABASE_CREATE);
				Login_Master.print();

				sqlDB.execSQL(Login_Master.Table_CREATE);
				sqlDB.execSQL(myMoney_Transaction_Master.Table_CREATE);
				
				// db.execSQL(myBank_Master.Table_CREATE);
				// db.execSQL(myBank_Transaction_Master.Table_CREATE);
				// db.execSQL(Category_Master.Table_CREATE);
				// db.execSQL(Product_Master.Table_CREATE);
				// db.execSQL(Store_Master.Table_CREATE);
				// db.execSQL(Stock_Master.Table_CREATE);
				// db.execSQL(Stock_Transaction_Master.Table_CREATE);

			} catch (SQLException e) {
				Log.i(TAG, "Err: " + e.toString());
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion,
				int newVersion) {
			Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			sqlDB.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(sqlDB);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
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
}
