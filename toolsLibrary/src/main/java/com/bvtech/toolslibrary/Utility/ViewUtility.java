package com.bvtech.toolslibrary.Utility;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.RequiresApi;

public class ViewUtility {

	public static final int UP = 1;
	public static final int DOWN = 2;

	public interface OnAnimationEndListener{
		public void animDone();
	}

	/**
	 *
	 * @param xStart
	 * @param xEnd
	 * @param yStart
	 * @param yEnd
	 * @param pivotX
	 * @param pivotY
	 * @param durationMillis
	 * @param fill
	 * @return
	 */
	public static Animation scaleAnimationRelativeToSelf(float xStart,
	                                                     float xEnd, float yStart, float yEnd, float pivotX, float pivotY,
	                                                     int durationMillis, boolean fill) {
		Animation anim = new ScaleAnimation(xStart, xEnd, // Start and end
				// values for
				// the X axis scaling
				yStart, yEnd, // Start and end values for the Y axis scaling
				Animation.RELATIVE_TO_SELF, pivotX, // Pivot point of X scaling
				Animation.RELATIVE_TO_SELF, pivotY); // Pivot point of Y scaling
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}

	/**
	 *
	 * @param xStart
	 * @param xEnd
	 * @param yStart
	 * @param yEnd
	 * @param pivotXType
	 * @param pivotYType
	 * @param pivotX
	 * @param pivotY
	 * @param durationMillis
	 * @param fill
	 * @return
	 */
	public static Animation scaleAnimation(float xStart, float xEnd,
	                                       float yStart, float yEnd, int pivotXType, int pivotYType,
	                                       float pivotX, float pivotY, int durationMillis, boolean fill) {
		Animation anim = new ScaleAnimation(xStart, xEnd, // Start and end
				// values for
				// the X axis scaling
				yStart, yEnd, // Start and end values for the Y axis scaling
				pivotXType, pivotX, // Pivot point of X scaling
				pivotYType, pivotY); // Pivot point of Y scaling
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}

	/**
	 *
	 * @param fromXDelta
	 * @param toXDelta
	 * @param fromYDelta
	 * @param toYDelta
	 * @param interpolator
	 * @param durationMillis
	 * @param fill
	 * @return
	 */
	public static Animation translateAnimationRelativeToParent(float fromXDelta,
	                                                           float toXDelta, float fromYDelta, float toYDelta,
	                                                           Interpolator interpolator, int durationMillis, boolean fill) {
		Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,fromXDelta,
				Animation.RELATIVE_TO_PARENT,toXDelta,
				Animation.RELATIVE_TO_PARENT,fromYDelta,
				Animation.RELATIVE_TO_PARENT, toYDelta);
		// Start and end
		// values for
		// Start and end values for the Y axis scaling
		anim.setInterpolator(interpolator);
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}

	/**
	 *
	 * @param relativeType
	 * @param fromXDelta
	 * @param toXDelta
	 * @param fromYDelta
	 * @param toYDelta
	 * @param interpolator
	 * @param durationMillis
	 * @param fill
	 * @return
	 */
	public static Animation translateAnimation(int relativeType,float fromXDelta,
	                                           float toXDelta, float fromYDelta, float toYDelta,
	                                           Interpolator interpolator, int durationMillis , boolean fill) {
		Animation anim = new TranslateAnimation(relativeType,fromXDelta,
				relativeType,toXDelta,
				relativeType,fromYDelta,
				relativeType, toYDelta);
		// Start and end
		// values for
		// Start and end values for the Y axis scaling
		anim.setInterpolator(interpolator);
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}

	/**
	 *
	 * @param fromDeg
	 * @param toDeg
	 * @param pivotX
	 * @param pivotY
	 * @param durationMillis
	 * @param fill
	 * @return
	 */
	public static Animation rotationAnimationRelativeToSelf(float fromDeg,
	                                                        float toDeg, float pivotX, float pivotY,
	                                                        int durationMillis,boolean fill) {
		Animation anim = new RotateAnimation(fromDeg,toDeg, // Start and end values for the Y axis scaling
				Animation.RELATIVE_TO_SELF, pivotX, // Pivot point of X scaling
				Animation.RELATIVE_TO_SELF, pivotY); // Pivot point of Y scaling
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}

	public static Animation rotatenAnimation(int delay) {
		RotateAnimation rotate = new RotateAnimation(
				0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f
		);
		rotate.setDuration(delay);
		rotate.setRepeatCount(Animation.INFINITE);
		rotate.setRepeatMode(Animation.RESTART);
		rotate.setStartOffset(0);
		return rotate;
	}

	/**
	 *
	 * @param myView
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public  static void exitReveal(final View myView) {
		if(myView != null){
			// get the center for the clipping circle
			//int cx = myView.getMeasuredWidth() / 2;
			int cx = 0;
			int cy = myView.getMeasuredHeight();

			// get the initial radius for the clipping circle
			int initialRadius = Math.max(myView.getWidth(),myView.getHeight());

			// create the animation (the final radius is zero)
			Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

			// make the view invisible when the animation is done
			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					myView.setVisibility(View.INVISIBLE);
				}
			});

			// start the animation
			anim.start();
		}
	}

	/**
	 *
	 * @param myView
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public static void enterReveal(View myView) {
		if(myView != null){
			// get the center for the clipping circle
			int cx = myView.getMeasuredWidth();
			//int cy = myView.getMeasuredHeight() / 2;
			int cy = 0;

			// get the final radius for the clipping circle
			int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

			// create the animator_persian for this view (the start radius is zero)
			Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

			// make the view visible and start the animation
			myView.setVisibility(View.VISIBLE);
			anim.start();
		}
	}

	public static final int CENTER_POINT = 0;
	public static final int CENTER_RIGHT_POINT = 1;
	public static final int CENTER_LEFT_POINT = 2;
	public static final int CENTER_TOP_POINT = 3;
	public static final int CENTER_BOTTOM_POINT = 4;
	public static final int TOP_RIGHT_POINT = 5;
	public static final int TOP_LEFT_POINT = 6;
	public static final int BOTTOM_RIGHT_POINT = 7;
	public static final int BOTTOM_LEFT_POINT = 8;
	/**
	 *
	 * @param myView
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public  static void exitReveal(final View myView, int point) {
		if(myView != null){
			int[] c = getPoint(myView, point) ;

			// get the initial radius for the clipping circle
			int initialRadius = Math.max(myView.getWidth(),myView.getHeight());

			// create the animation (the final radius is zero)
			Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, c[0], c[1], initialRadius, 0);

			// make the view invisible when the animation is done
			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					myView.setVisibility(View.INVISIBLE);
				}
			});

			// start the animation
			anim.start();
		}
	}

	/**
	 *
	 * @param myView
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public static void enterReveal(View myView, int point) {
		if(myView != null){
			int[] c = getPoint(myView, point) ;

			// get the final radius for the clipping circle
			int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

			// create the animator_persian for this view (the start radius is zero)
			Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, c[0], c[1], 0, finalRadius);

			// make the view visible and start the animation
			myView.setVisibility(View.VISIBLE);
			anim.start();
		}
	}

	/**
	 *
	 * @param myView
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public static void enterReveal(View myView, int cx, int cy) {
		if(myView != null){

			// get the final radius for the clipping circle
			int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

			// create the animator_persian for this view (the start radius is zero)
			Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

			// make the view visible and start the animation
			myView.setVisibility(View.VISIBLE);
			anim.start();
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public  static void exitReveal(final View myView, int cx, int cy, final Animator.AnimatorListener listener) {
		if(myView != null){

			// get the initial radius for the clipping circle
			int initialRadius = Math.max(myView.getWidth(),myView.getHeight());

			// create the animation (the final radius is zero)
			final Animator anim =
					ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

			anim.setDuration(400);
			// make the view invisible when the animation is done
			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					myView.setVisibility(View.INVISIBLE);
					listener.onAnimationEnd(anim);
				}
			});

			// start the animation
			anim.start();
		}
	}

	private static int[] getPoint(final View myView, int point){
		int[] d = new int[2];
		switch (point){
			case CENTER_POINT:
				d[0] = myView.getMeasuredWidth()/2;
				d[1] = myView.getMeasuredHeight()/2;
				break;
			case CENTER_RIGHT_POINT:
				d[0] = myView.getMeasuredWidth();
				d[1] = myView.getMeasuredHeight()/2;
				break;
			case CENTER_LEFT_POINT:
				d[0] = 0;
				d[1] = myView.getMeasuredHeight()/2;
				break;
			case CENTER_TOP_POINT:
				d[0] = myView.getMeasuredWidth()/2;
				d[1] = 0;
				break;
			case CENTER_BOTTOM_POINT:
				d[0] = myView.getMeasuredWidth()/2;
				d[1] = myView.getMeasuredHeight();
				break;
			case TOP_RIGHT_POINT:
				d[0] = myView.getMeasuredWidth();
				d[1] = 0;
				break;
			case TOP_LEFT_POINT:
				d[0] = 0;
				d[1] = 0;
				break;
			case BOTTOM_RIGHT_POINT:
				d[0] = myView.getMeasuredWidth();
				d[1] = myView.getMeasuredHeight();
				break;
			case BOTTOM_LEFT_POINT:
				d[0] = 0;
				d[1] = myView.getMeasuredHeight();
				break;
		}

		return d;
	}

	/**
	 *
	 * @param myView
	 */
	public  static void rotateView(final View myView, boolean infinite, int delay, String rotType, float from, float to) {
		if(myView != null){
			ObjectAnimator animation = ObjectAnimator.ofFloat(myView, rotType, from, to);
			animation.setDuration(delay);
			if(infinite){
				animation.setRepeatCount(ObjectAnimator.INFINITE);
			}
			animation.setInterpolator(new AccelerateDecelerateInterpolator());
			animation.start();
		}
	}

	public static void shrinkExpandAnimation(final View myView, final View.OnClickListener listener) {
		if(myView != null){
			final Animation animClickIn = scaleAnimationRelativeToSelf(1.0f, 0.85f,
					1.0f, 0.85f, 0.5f, 0.5f, 80,false);
			animClickIn.setRepeatMode(Animation.REVERSE);
			animClickIn.setInterpolator(new AccelerateDecelerateInterpolator());

			myView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					myView.startAnimation(animClickIn);
				}
			});

			animClickIn.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (listener != null){
						listener.onClick(myView);
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
		}
	}

	public static void shrinkExpandAnimation(final View myView, final float shrinkPercent, final View.OnClickListener listener) {
		if(myView != null){
			final Animation animClickIn = scaleAnimationRelativeToSelf(1.0f, shrinkPercent,
					1.0f, shrinkPercent, 0.5f, 0.5f, 80,false);
			animClickIn.setRepeatMode(Animation.REVERSE);
			animClickIn.setInterpolator(new AccelerateDecelerateInterpolator());

			myView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					myView.startAnimation(animClickIn);
				}
			});

			animClickIn.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (listener != null){
						listener.onClick(myView);
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
		}
	}

	public static void shrinkExpandAnimation(final View myView, final float shrinkPercent, int delay, final View.OnClickListener listener) {
		if(myView != null){
			final Animation animClickIn = scaleAnimationRelativeToSelf(1.0f, shrinkPercent,
					1.0f, shrinkPercent, 0.5f, 0.5f, delay,false);
			animClickIn.setRepeatMode(Animation.REVERSE);
			animClickIn.setInterpolator(new AccelerateDecelerateInterpolator());

			myView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					myView.startAnimation(animClickIn);
				}
			});

			animClickIn.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (listener != null){
						listener.onClick(myView);
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
		}
	}

	public static void shrinkExpandAnimation(final View targetView, final View expandShrinker, final View.OnClickListener listener) {
		if(targetView != null){
			final Animation animClickIn = scaleAnimationRelativeToSelf(1.0f, 0.85f,
					1.0f, 0.85f, 0.5f, 0.5f, 80,false);
			animClickIn.setRepeatMode(Animation.REVERSE);
			animClickIn.setInterpolator(new AccelerateDecelerateInterpolator());

			targetView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					expandShrinker.startAnimation(animClickIn);
				}
			});

			animClickIn.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (listener != null){
						listener.onClick(targetView);
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
		}
	}

	public static void shrinkExpandAnimation(final View targetView) {
		if(targetView != null){
			final Animation animClickIn = scaleAnimationRelativeToSelf(1.0f, 0.75f,
					1.0f, 0.75f, 0.5f, 0.5f, 75,false);
			animClickIn.setRepeatMode(Animation.REVERSE);
			animClickIn.setInterpolator(new BounceInterpolator());
			targetView.startAnimation(animClickIn);
		}
	}

	public static void byPassOnclickListener(final View targetView, final View.OnClickListener listener) {
		if(targetView != null) {

			targetView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) {
						listener.onClick(targetView);
					}
				}
			});
		}
	}

	public static void hideView(final View myView, final OnAnimationEndListener listener, int direction) {
		if(myView != null){
			float dir = -1f;
			switch (direction){
				case UP:
					dir = -1f;
					break;
				case DOWN:
					dir = 1f;
					break;
			}
			final Animation anim = translateAnimationRelativeToParent(0f, 0f, 0f, dir,
					new AccelerateDecelerateInterpolator(), 300,false);
			anim.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if(listener != null){
						listener.animDone();
					}
					myView.setVisibility(View.GONE);
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
			myView.startAnimation(anim);
		}
	}

	public static void showView(final View myView, final OnAnimationEndListener listener, int direction) {
		if(myView != null){
			float dir = -1f;
			switch (direction){
				case UP:
					dir = -1f;
					break;
				case DOWN:
					dir = 1f;
					break;
			}
			myView.setVisibility(View.VISIBLE);
			final Animation anim = translateAnimationRelativeToParent(0f, 0f, dir, 0f,
					new AccelerateDecelerateInterpolator(), 300,false);
			anim.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					if(listener != null){
						listener.animDone();
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}
			});
			myView.startAnimation(anim);
		}
	}

	public static void hide(final View myView) {
		if(myView != null){
			final Animation anim = scaleAnimationRelativeToSelf(1, 0, 1, 0, 0.5f, 0.5f, 300, true);
			myView.startAnimation(anim);
		}
	}

	public static void show(final View myView) {
		if(myView != null){
			final Animation anim = scaleAnimationRelativeToSelf(0, 1, 0, 1, 0.5f, 0.5f, 300, true);
			myView.startAnimation(anim);
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setShape(View v, int backgroundColor, int borderColor) {
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(GradientDrawable.RECTANGLE);
		shape.setCornerRadii(new float[] { 8, 8, 8, 8, 0, 0, 0, 0 });
		shape.setColor(backgroundColor);
		shape.setStroke(3, borderColor);
		v.setBackground(shape);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setShape(View v, int shapeType, int cornerRadius, int strokeSize, int backgroundColor, int borderColor) {
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(shapeType);
		shape.setCornerRadius(cornerRadius);
		shape.setColor(backgroundColor);
		shape.setStroke(strokeSize, borderColor);
		v.setBackgroundDrawable(shape);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setShape(Context context, View v, int shapeType, int cornerRadius, int strokeSize, int backgroundColor, int borderColor) {
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(shapeType);
		shape.setCornerRadius(cornerRadius);
		shape.setColor(backgroundColor);
		shape.setStroke(strokeSize, borderColor);
		//v.setBackgroundDrawable(shape);
		//v.setBackgroundDrawable(ContextCompat.getDrawable(context, shape));
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static GradientDrawable setShape(int shapeType, int cornerRadius, int strokeSize, int backgroundColor, int borderColor) {
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(shapeType);
		shape.setCornerRadius(cornerRadius);
		shape.setColor(backgroundColor);
		shape.setStroke(strokeSize, borderColor);
		return shape;
	}
}
