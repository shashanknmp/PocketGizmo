package com.pg.data;

public class myMoney_Transaction_Master {

	public static String TAG = "myMoney_Transaction_Master";
	public static String TableName = "myMoney_Transaction_Master";

	public static String Fields[] = { "transaction_id", "login_id",
			"category_id", "transaction_type", "transaction_date",
			"transaction_time", "transaction_description", "transaction_amount" };

	public static String Table_Creation_Fields2[] = {
			"(transaction_id primary key autoincrement,", "login_id text,",
			"category_id text,", "transaction_type text,",
			"transaction_date datetime,", "transaction_time datetime,",
			"transaction_description text,", "transaction_amount float);" };

	private static String Table_Creation_Fields = "(" + Fields[0]
			+ " text primary key, " + Fields[1] + " text, " + Fields[2]
			+ " text, " + Fields[3] + " text, " + Fields[4] + " text, "
			+ Fields[5] + " text, " + Fields[6] + " datetime, " + Fields[7]
			+ " boolean);";

	// FOREIGN KEY(trackartist) REFERENCES artist(artistid)

	public static String Table_CREATE = "create table " + TableName
			+ (Table_Creation_Fields);

	public myMoney_Transaction_Master() {

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
