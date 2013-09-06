package com.pg.pocketgizmo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.pg.data.DBAdapter;

public class PocketGizmoApplication extends Application {

	private static PocketGizmoApplication sInstance;

	private final String TAG = getClass().getName();
	private String strVersionCode;
	private String strAppName;
	private String strSourceDir;
	private String strDataDir;
	private DBAdapter dbAdapter; // = new DBAdapter(this);
	private SQLiteDatabase sqlDB;
	private String IMEI;
	private Typeface fntAppTitle;
	private Typeface fntViewFlipper;
	private Typeface fntFormFields;
	private int deviceWidth, deviceHeight, deviceDensityDpi;
	private float deviceDensity;

	public static PocketGizmoApplication getInstance() {
		return sInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Log.i(TAG, "onCreate()");
		sInstance = this;
		exposeAll();
	}

	private void exposeAll() {
		exposeIMEI();
		exposeDisplayMetrics();

		exposeVersionCode();
		exposeAppName();
		exposeSourceDir();
		exposeDataDir();

		expose_dbAdapter();
		expose_sqlDB();

		exposeFontAppTitle();
		exposeFontFormFields();
		exposeFontViewFlipper();
	}

	/**
	 * @return the strVersionCode
	 */
	public String getVersionCode() {
		Log.i(TAG, "getVersionCode = " + strVersionCode);
		return strVersionCode;
	}

	/**
	 * @param strVersionCode
	 *            the strVersionCode to set
	 */
	private void setVersionCode(String strVersionCode) {
		this.strVersionCode = strVersionCode;
	}

	private void exposeVersionCode() {
		Log.i(TAG, "exposeVersionCode()");
		PackageInfo pInfo;

		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			setVersionCode(pInfo.versionName);
			Log.i(TAG, "VERSION CODE = " + getVersionCode());

		} catch (Exception e) {
			Log.i(TAG, "Err: " + e.toString());
		}
	}

	/**
	 * @return the strAppName
	 */
	public String getAppName() {
		return strAppName;
	}

	/**
	 * @param strAppName
	 *            the strAppName to set
	 */
	private void setAppName(String strAppName) {
		this.strAppName = strAppName;
	}

	private void exposeAppName() {
		Log.i(TAG, "exposeAppName()");
		PackageInfo pInfo;

		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			setAppName(pInfo.applicationInfo.name);
			Log.i(TAG, "APP NAME = " + getAppName());

		} catch (Exception e) {
			Log.i(TAG, "Err: " + e.toString());
		}
	}

	/**
	 * @return the strSourceDir
	 */
	public String getSourceDir() {
		return strSourceDir;
	}

	/**
	 * @param strSourceDir
	 *            the strSourceDir to set
	 */
	private void setSourceDir(String strSourceDir) {
		this.strSourceDir = strSourceDir;
	}

	private void exposeSourceDir() {
		Log.i(TAG, "exposeSourceDir()");
		PackageInfo pInfo;

		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			setSourceDir(pInfo.applicationInfo.sourceDir);
			Log.i(TAG, "SOURCE DIR = " + getSourceDir());

		} catch (Exception e) {
			Log.i(TAG, "Err: " + e.toString());
		}
	}

	/**
	 * @return the strDataDir
	 */
	public String getDataDir() {
		return strDataDir;
	}

	/**
	 * @param strDataDir
	 *            the strDataDir to set
	 */
	private void setDataDir(String strDataDir) {
		this.strDataDir = strDataDir;
	}

	private void exposeDataDir() {
		Log.i(TAG, "exposeDataDir()");
		PackageInfo pInfo;

		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			setDataDir(pInfo.applicationInfo.dataDir);
			Log.i(TAG, "DATA DIR = " + getDataDir());

		} catch (Exception e) {
			Log.i(TAG, "Err: " + e.toString());
		}
	}

	/**
	 * @return the dbAdapater
	 */
	public DBAdapter get_dbAdapter() {
		return dbAdapter;
	}

	/**
	 * @param dbAdapter
	 *            the dbAdapter to set
	 */
	private void set_dbAdapter(DBAdapter dbAdapter) {
		this.dbAdapter = dbAdapter;
	}

	private void expose_dbAdapter() {
		Log.i(TAG, "exposeDB()");

		set_dbAdapter(new DBAdapter(this));
		dbAdapter.open();
		Log.i(TAG, ">>> db.open()");
	}

	/**
	 * @return the iMEI
	 */
	public String getIMEI() {
		return IMEI;
	}

	/**
	 * @param iMEI
	 *            the iMEI to set
	 */
	private void setIMEI(String IMEI) {
		this.IMEI = IMEI;
	}

	private void exposeIMEI() {
		Log.i(TAG, "exposeIMEI()");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		Log.i(TAG, "IMEI --> " + telephonyManager.getDeviceId());
		setIMEI(telephonyManager.getDeviceId());
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

	private void expose_sqlDB() {
		Log.i(TAG, "expose_sqlDB()");

		set_sqlDB(dbAdapter.get_sqlDB());
	}

	/**
	 * @return the fntAppTitle
	 */
	public Typeface get_fntAppTitle() {
		return fntAppTitle;
	}

	/**
	 * @param fntAppTitle
	 *            the fntAppTitle to set
	 */
	private void set_fntAppTitle(Typeface fntAppTitle) {
		this.fntAppTitle = fntAppTitle;
	}

	private void exposeFontAppTitle() {
		Log.i(TAG, "exposeFontAppTitle()");

		// Typeface fntApptitle = Typeface.createFromAsset(getAssets(),
		// "spincd-apptitle.ttf");
		Typeface fntApptitle = Typeface.createFromAsset(getAssets(),
				"computerfont.ttf");
		set_fntAppTitle(fntApptitle);
	}

	/**
	 * @return the fntFormFields
	 */
	public Typeface get_fntFormFields() {
		return fntFormFields;
	}

	/**
	 * @param fntFormFields
	 *            the fntFormFields to set
	 */
	private void set_fntFormFields(Typeface fntFormFields) {
		this.fntFormFields = fntFormFields;
	}

	public void exposeFontFormFields() {
		Log.i(TAG, "exposeFontFormFields()");

		Typeface fntFormFields = Typeface.createFromAsset(getAssets(),
				"ocraextended.ttf");
		set_fntFormFields(fntFormFields);
	}

	/**
	 * @return the fntViewFlipper
	 */
	public Typeface get_fntViewFlipper() {
		return fntViewFlipper;
	}

	/**
	 * @param fntViewFlipper
	 *            the fntViewFlipper to set
	 */
	private void set_fntViewFlipper(Typeface fntViewFlipper) {
		this.fntViewFlipper = fntViewFlipper;
	}

	private void exposeFontViewFlipper() {
		Log.i(TAG, "exposeFontViewFlipper()");

		Typeface fntViewFlipper = Typeface.createFromAsset(getAssets(),
				"brushstr.ttf");
		set_fntViewFlipper(fntViewFlipper);
	}

	/**
	 * @return the deviceWidth
	 */
	public int get_deviceWidth() {
		return deviceWidth;
	}

	/**
	 * @param deviceWidth
	 *            the deviceWidth to set
	 */
	private void set_deviceWidth(int deviceWidth) {
		this.deviceWidth = deviceWidth;
	}

	private void exposeDisplayMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		set_deviceWidth(metrics.widthPixels);
		set_deviceHeight(metrics.heightPixels);
		set_deviceDensityDpi(metrics.densityDpi);
		set_deviceDensity(metrics.density);
	}

	/**
	 * @return the deviceHeight
	 */
	public int get_deviceHeight() {
		return deviceHeight;
	}

	/**
	 * @param deviceHeight
	 *            the deviceHeight to set
	 */
	private void set_deviceHeight(int deviceHeight) {
		this.deviceHeight = deviceHeight;
	}

	/**
	 * @return the deviceDensity
	 */
	public int get_deviceDensityDpi() {
		return deviceDensityDpi;
	}

	/**
	 * @param deviceDensity
	 *            the deviceDensity to set
	 */
	private void set_deviceDensityDpi(int deviceDensityDpi) {
		this.deviceDensityDpi = deviceDensityDpi;
	}

	/**
	 * @return the deviceDensity
	 */
	public float get_deviceDensity() {
		return deviceDensity;
	}

	/**
	 * @param deviceDensity
	 *            the deviceDensity to set
	 */
	private void set_deviceDensity(float deviceDensity) {
		this.deviceDensity = deviceDensity;
	}

	public boolean checkInternetConnection() {
		// boolean bGotHost = false;

		ConnectivityManager cm = ((ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE));
		// test for connection
		if (cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) {
			Log.i(TAG, "ONLINE");

			/*
			 * try { bGotHost=InetAddress.getByName(URL).isReachable(10000); }
			 * catch (UnknownHostException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			return true;

		} else {
			Log.i(TAG, "OFFLINE");

			return false;
		}
	}
}