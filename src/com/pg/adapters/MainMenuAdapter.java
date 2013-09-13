package com.pg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pg.R;
import com.pg.animations.Animations;
import com.pg.animations.EFFECTS;

public class MainMenuAdapter extends BaseAdapter {
	private final String TAG = getClass().getName();
	private Context context;

	// references to our menu images
	private Integer[] mainmenuIcons = { R.drawable.pocket_messenger48x48,
			R.drawable.wireless_remote128x128, R.drawable.media_player128x128,
			R.drawable.mymoney48x48, R.drawable.stock_tracker128x128,
			R.drawable.third_eye128x128 };

	// references to our menu item text
	private Integer[] mainmenuItems = { R.string.mmenu_item1,
			R.string.mmenu_item2, R.string.mmenu_item3, R.string.mmenu_item4,
			R.string.mmenu_item5, R.string.mmenu_item6 };

	public MainMenuAdapter(Context context) {
		this.context = context;
	}

	public int getCount() {
		// Log.i(TAG, "getCount() = " + mainmenuIcons.length);
		return mainmenuIcons.length;
	}

	public Object getItem(int position) {
		// Log.i(TAG, "getItem() = " + position);
		return position;
	}

	public long getItemId(int position) {
		// Log.i(TAG, "getItemId() = " + position);
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		// View tmpView = null;
		ImageView imgItemIcon;
		TextView txvItemTitle;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.mainmenu_item, null);

			imgItemIcon = (ImageView) convertView
					.findViewById(R.id.imgItemImage);
			imgItemIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imgItemIcon.setImageResource(mainmenuIcons[position]);

			txvItemTitle = (TextView) convertView
					.findViewById(R.id.txvItemTitle);
			txvItemTitle.setText(mainmenuItems[position]);

			 Animations anim = new Animations();
			 anim.setAnimDuration(300);
			 anim.startAnimation(context, convertView,
			 EFFECTS.ZOOM_IN_EFFECT);

//			CopyOfAnimations.getInstance().setAnimDuration(300);
//			CopyOfAnimations.getInstance().startAnimation(context, convertView,
//					EFFECTS.ZOOM_IN_EFFECT);
		}
		return convertView;
	}
}
