package com.pg.PocketGizmo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.pg.R;
import com.pg.adapters.MainMenuAdapter;
import com.pg.animations.Animations;
import com.pg.animations.EFFECTS;
import com.pg.myMoneyTracker.myMoneyMainMenu;

public class MainMenuActivity extends Activity implements OnItemClickListener {
	private final String TAG = getClass().getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);

		GridView gridMainMenu = (GridView) findViewById(R.id.gridMainMenu);
		gridMainMenu.setAdapter(new MainMenuAdapter(this));
		gridMainMenu.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent startApp;
		// TODO Auto-generated method stub

		// if (parent.equals("gridMainMenu")) {
		// if (view.equals("lnrmainmenu_item")) {
		// Log.i(TAG, "position = " + position);
		// Log.i(TAG, "id = " + id);
		// }
		// }

		Animations anim = new Animations();
		anim.setAnimDuration(200);
		anim.startAnimation(this, view, EFFECTS.ZOOM_OUT_EFFECT);

		switch (position) {
		case 0:
			Toast.makeText(this, "PC", Toast.LENGTH_SHORT).show();
			break;

		case 1:
			Toast.makeText(this, "WR", Toast.LENGTH_SHORT).show();
			break;

		case 2:
			Toast.makeText(this, "MPR", Toast.LENGTH_SHORT).show();
			break;

		case 3:
			Toast.makeText(this, "MYT", Toast.LENGTH_SHORT).show();
			startApp = new Intent(this, myMoneyMainMenu.class);
			startActivity(startApp);
			break;

		case 4:
			Toast.makeText(this, "ST", Toast.LENGTH_SHORT).show();
			break;

		case 5:
			Toast.makeText(this, "TE", Toast.LENGTH_SHORT).show();
			break;
		}
		Log.i(TAG, "position = " + position);
		Log.i(TAG, "id = " + id);

	}
}