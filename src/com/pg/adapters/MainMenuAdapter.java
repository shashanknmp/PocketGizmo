package com.pg.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
	private String[] strLabels;
	private String[] imgIcons;

	public MainMenuAdapter(Context context, String[] strLabels,
			String[] imgIcons) {
		this.context = context;
		this.strLabels = strLabels;
		this.imgIcons = imgIcons;
	}

	public int getCount() {
		// Log.i(TAG, "getCount() = " + mainmenuIcons.length);
		return imgIcons.length;
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
		ImageView imgItemIcon;
		TextView txvItemTitle;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View single_item = inflater.inflate(R.layout.mainmenu_item, null);

		imgItemIcon = (ImageView) single_item.findViewById(R.id.imgItemImage);
		imgItemIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);

		int resource = context.getResources().getIdentifier(imgIcons[position],
				"drawable", context.getPackageName());
		Drawable img = context.getResources().getDrawable(resource);
		imgItemIcon.setImageDrawable(img);

		txvItemTitle = (TextView) single_item.findViewById(R.id.txvItemTitle);
		txvItemTitle.setText(strLabels[position]);

		Animations anim = new Animations();
		anim.setAnimDuration(300);
		anim.startAnimation(context, single_item, EFFECTS.ZOOM_IN_EFFECT);

		// CopyOfAnimations.getInstance().setAnimDuration(300);
		// CopyOfAnimations.getInstance().startAnimation(context,
		// convertView,
		// EFFECTS.ZOOM_IN_EFFECT);
		return single_item;
	}
}
