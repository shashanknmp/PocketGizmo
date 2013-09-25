/**
 *
 */
package com.pg.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pg.R;
import com.pg.PocketGizmo.PocketGizmoApplication;

/**
 * @author shashank
 * 
 */
public class MMYspinnerAdapter extends ArrayAdapter<String> {

	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;
	private Context context;
	private String[] imgIcons;
	private boolean color_flag;

	public MMYspinnerAdapter(Context context, int resource, String[] strLabels,
			String[] imgIcons) {
		super(context, resource, strLabels);
		// TODO Auto-generated constructor stub
		
		getApplicationObject();
		this.context = context;
		this.imgIcons = imgIcons;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View single_row = inflater.inflate(com.pg.R.layout.spinner_row, parent,
				false);

		TextView txvSpnLabel = (TextView) single_row
				.findViewById(com.pg.R.id.txvSpnLabel);
		txvSpnLabel.setText(getItem(position));
		/*
		 * if (color_flag == true) {
		 * txvLabel.setBackgroundColor(context.getResources().getColor(
		 * android.R.color.tertiary_text_dark)); color_flag = false;
		 * 
		 * } else { color_flag = true; }
		 */
		ImageView imgSpnIcon = (ImageView) single_row
				.findViewById(com.pg.R.id.imgSpnIcon);

		imgSpnIcon.setImageDrawable(context.getResources().getDrawable(
				R.drawable.appicon));

		int resource = context.getResources().getIdentifier(imgIcons[position],
				"drawable", context.getPackageName());
		Drawable img = context.getResources().getDrawable(resource);
		imgSpnIcon.setImageDrawable(img);

		/*
		 * int imageResource = context.getResources().getIdentifier(
		 * imgIcons[position], "drawable", context.getPackageName());
		 * 
		 * Drawable image = context.getResources().getDrawable(imageResource);
		 * imgSpnIcon.setImageDrawable(image);
		 */
		/*
		 * Log.i("img position", "" + imgIcons[position]);
		 * imgSpnIcon.setImageDrawable();
		 */
		/*
		 * String uri = "@drawable/a" + mIcons[position]; int imageResource =
		 * getResources().getIdentifier(uri, null, getPackageName());
		 * icon.setImageResource(imageResource);
		 */
		return single_row;
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
