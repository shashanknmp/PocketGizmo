package com.pg.data;

public class Bank_Master {

	public static String TAG = "myBank_Master";
	public static String TableName = "myBank_Master";
	
	public static String Fields[] = { "login_id", "login_fname", "login_lname",
			"login_username", "login_passwsd", "login_email",
			"login_account_expiry", "login_account_active", "login_image" };

	public static String Table_Creation_Fields[] = {
			"(login_id primary key autoincrement,", "login_fname text,",
			"login_lname text,", "login_username text,", "login_passwsd text,",
			"login_email text,", "login_account_expiry datetime,",
			"login_account_active boolean,", "login_image text);" };

	public static String Table_CREATE = "create table " + TableName
			+ (Table_Creation_Fields);

	public Bank_Master() {

	}

	/**
	 * @return the strTableName
	 */
	public String getTableName() {
		return TableName;
	}

	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return Fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(String[] Fields) {
		this.Fields = Fields;
	}
}
