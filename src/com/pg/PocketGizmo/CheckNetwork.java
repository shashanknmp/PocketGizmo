package com.pg.PocketGizmo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class CheckNetwork {

	private Context context;
	private final String TAG = "CheckNetwork";

	public CheckNetwork(Context context) {
		this.context = context;
	}

	/**
	 * This method used for check internet connection
	 * 
	 * @param
	 * @return connected or not as boolean value
	 */
	public boolean checkInternetConnection(int connectionType) {
		boolean bGotHost = false;

		ConnectivityManager cm = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE));

		NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
		for (int i = 0; i < networkInfo.length; i++) {

			Log.i(TAG, "getTypeName = " + networkInfo[i].getTypeName());
			Log.i(TAG, "getType() = " + networkInfo[i].getType());
			Log.i(TAG, "getState() = " + networkInfo[i].getState());
			Log.i(TAG, "getExtraInfo = " + networkInfo[i].getExtraInfo());
			Log.i(TAG, "getReason() = " + networkInfo[i].getReason());
			Log.i(TAG, "=============================================");
			Log.i(TAG, "=============================================");

		}

		// test for connection
		// if (cm.getActiveNetworkInfo() != null
		// && cm.getActiveNetworkInfo().isAvailable()
		// && cm.getActiveNetworkInfo().isConnected()) {
		//
		// return true;
		//
		// } else {
		//
		return false;
		// }
	}

	// if network connection not available show alert dialog
	/**
	 * This method used for if connection is not available then shows error
	 * message as in alert view.
	 * 
	 * @param
	 * @return
	 */
	public void showNWError() {
		Toast toast = Toast.makeText(context, "Please Connect To Internet",
				Toast.LENGTH_LONG);
		toast.show();
	}
}
