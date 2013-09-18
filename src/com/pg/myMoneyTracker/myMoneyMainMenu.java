/**
 * 
 */
package com.pg.myMoneyTracker;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pg.R;
import com.pg.PocketGizmo.PocketGizmoApplication;

/**
 * @author shashank
 * 
 */
public class myMoneyMainMenu extends ListActivity {
	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;

	/**
	 * 
	 */
	public myMoneyMainMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getApplicationObject();
		pgAppObj.logMe(TAG, "onCreate()");

		String strMainMenu[] = getResources().getStringArray(
				R.array.mymoney_main_menu);

		ArrayAdapter<String> arrStrMenu = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strMainMenu);
		setListAdapter(arrStrMenu);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		pgAppObj.logMe(TAG, "item selected");
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
