/**
 * 
 */
package com.pg.myMoneyTracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pg.R;
import com.pg.PocketGizmo.PocketGizmoApplication;

/**
 * @author shashank
 * 
 */
public class MMY_MainMenu extends ListActivity {
	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;

	/**
	 * 
	 */
	public MMY_MainMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getApplicationObject();
		pgAppObj.logMe(TAG, "onCreate()");

		setTitle("myMoney Tracker - MainMenu");
		setTitleColor(getResources().getColor(R.color.title));

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

		pgAppObj.logMe(TAG, "item selected " + position);

		Intent nextIntent = null;

		switch (position) {
		case 0:
			nextIntent = new Intent(this, MMY_ExpenseIncome.class);
			nextIntent.putExtra("mmy_trans_type", "mmy_expense");
			break;

		case 1:
			nextIntent = new Intent(this, MMY_ExpenseIncome.class);
			nextIntent.putExtra("mmy_trans_type", "mmy_income");
			break;

		case 2:
			break;
		case 3:

			break;

		case 4:
			break;
		}

		if (nextIntent != null) {
			startActivity(nextIntent);

		} else {
			pgAppObj.logMe(TAG, "nextIntent is null");
		}

	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
