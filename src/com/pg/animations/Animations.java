package com.pg.animations;

import com.pg.PocketGizmo.PocketGizmoApplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class Animations extends Animation {
	private final String TAG = getClass().getName();
	private PocketGizmoApplication pgAppObj;
	private Context context;
	private View view;
	private int iAnimation_Effect;
	private long lAnimDuration = 500;
	private int iRepeatCount = 0;
	private int iRepeatMode;
	private Animation anim = null;

	public void startAnimation(Context context, View view, int iAnimation_Effect) {

		this.context = context;
		this.view = view;
		this.iAnimation_Effect = iAnimation_Effect;

		getApplicationObject();

		switch (iAnimation_Effect) {
		case EFFECTS.ZOOM_IN_EFFECT:
		case EFFECTS.ZOOM_OUT_EFFECT:
			startScaleAnimation();
			break;

		case EFFECTS.ROTATE_LEFT_EFFECT:
		case EFFECTS.ROTATE_RIGHT_EFFECT:
			startRotateAnimation();
			break;

		case EFFECTS.FADE_IN_EFFECT:
		case EFFECTS.FADE_OUT_EFFECT:
			startAlphaAnimation();
			break;

		case EFFECTS.LEFT_IN_EFFECT:
		case EFFECTS.LEFT_OUT_EFFECT:
			startTranslateAnimation();
			break;

		case EFFECTS.RIGHT_IN_EFFECT:
		case EFFECTS.RIGHT_OUT_EFFECT:
			startTranslateAnimation();
			break;
		}
		anim.setDuration(getAnimDuration());
		// Toast.makeText(context, "" + lAnimDuration,
		// Toast.LENGTH_SHORT).show();
		pgAppObj.logMe(TAG, "" + lAnimDuration);
		anim.setRepeatCount(getRepeatCount());
		view.startAnimation(anim);

	}

	private void startScaleAnimation() {

		if (iAnimation_Effect == EFFECTS.ZOOM_IN_EFFECT) {
			anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);

		} else if (iAnimation_Effect == EFFECTS.ZOOM_OUT_EFFECT) {
			anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
	}

	private void startRotateAnimation() {

		if (iAnimation_Effect == EFFECTS.ROTATE_LEFT_EFFECT) {
			anim = new RotateAnimation(360.0f, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);

		} else if (iAnimation_Effect == EFFECTS.ROTATE_RIGHT_EFFECT) {
			anim = new RotateAnimation(0.0f, 360.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
	}

	private void startAlphaAnimation() {

		if (iAnimation_Effect == EFFECTS.FADE_IN_EFFECT) {
			anim = new AlphaAnimation(0.0f, 1.0f);

		} else if (iAnimation_Effect == EFFECTS.FADE_OUT_EFFECT) {
			anim = new AlphaAnimation(1.0f, 0.0f);
		}
	}

	private void startTranslateAnimation() {

		if (iAnimation_Effect == EFFECTS.LEFT_IN_EFFECT) {
			anim = new TranslateAnimation(100.0f, 0.0f, 0, 0);

		} else if (iAnimation_Effect == EFFECTS.LEFT_OUT_EFFECT) {
			anim = new TranslateAnimation(0.0f, 100.0f, 0, 0);

		} else if (iAnimation_Effect == EFFECTS.RIGHT_IN_EFFECT) {
			anim = new TranslateAnimation(100.0f, 0.0f, 0, 0);

		} else if (iAnimation_Effect == EFFECTS.RIGHT_OUT_EFFECT) {
			anim = new TranslateAnimation(0.0f, 100.0f, 0, 0);
		}
	}

	/**
	 * @return the lAnimDuration
	 */
	public long getAnimDuration() {
		return lAnimDuration;
	}

	/**
	 * @param lAnimDuration
	 *            the lAnimDuration to set
	 */
	public void setAnimDuration(long lAnimDuration) {
		this.lAnimDuration = lAnimDuration;
	}

	/**
	 * @return the iRepeatCount
	 */
	public int getRepeatCount() {
		return iRepeatCount;
	}

	/**
	 * @param iRepeatCount
	 *            the iRepeatCount to set
	 */
	public void setRepeatCount(int iRepeatCount) {
		this.iRepeatCount = iRepeatCount;
	}

	/**
	 * @return the iRepeatMode
	 */
	public int getRepeatMode() {
		return iRepeatMode;
	}

	/**
	 * @param iRepeatMode
	 *            the iRepeatMode to set
	 */
	public void setRepeatMode(int iRepeatMode) {
		this.iRepeatMode = iRepeatMode;
	}

	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	private void getApplicationObject() {
		if (pgAppObj == null) {
			pgAppObj = PocketGizmoApplication.getInstance();
		}
	}
}
