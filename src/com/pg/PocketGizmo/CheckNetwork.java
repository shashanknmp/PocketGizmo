package com.pg.PocketGizmo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class CheckNetwork {

	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;
	private Context context;

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

			pgAppObj.logMe(TAG, "getTypeName = " + networkInfo[i].getTypeName());
			pgAppObj.logMe(TAG, "getType() = " + networkInfo[i].getType());
			pgAppObj.logMe(TAG, "getState() = " + networkInfo[i].getState());
			pgAppObj.logMe(TAG, "getExtraInfo = " + networkInfo[i].getExtraInfo());
			pgAppObj.logMe(TAG, "getReason() = " + networkInfo[i].getReason());
			pgAppObj.logMe(TAG, "=============================================");
			pgAppObj.logMe(TAG, "=============================================");

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

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}