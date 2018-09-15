package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class HoloButton extends android.support.v7.widget.AppCompatImageView implements GestureDetector.OnGestureListener {

	private static final long DRAW_OFFSET = 1;
	private static final String CIRCLE_FILL_COLOR  = "#009688";
	private static final String CIRCLE_ROUND_COLOR = "#f7f7f7";
	private static final String CIRCLE_HOVER_COLOR = "#000000";

	private OnClickListener     clickEndlistener;
	private OnClickListener     clickStartlistener;
	private OnLongClickListener longClickListener;

	private GestureDetector detector;
	private MotionEvent event;
	private Paint textPaint;
	private Paint	       circlePaintHover;
	private Paint	       circlePaintUnder;
	private Paint[]	     circlePaintOver    = new Paint[20];
	private int	      initialFillColor   = Color.parseColor(CIRCLE_FILL_COLOR);
	private int	      initialRoundColor  = Color.parseColor(CIRCLE_ROUND_COLOR);
	private int		 initialAlpha       = 100;
	private int textSize = 20;

	private float	       radius;
	private float	       xCenter;
	private float	       yCenter;
	private float	       xClickPosition;
	private float	       yClickPosition;
	private float	       stepRaduis;

	private float	       circleRaduisUnder;
	private float[]	     circleRaduis       = new float[20];

	private boolean	     clickable	  = true;
	private boolean	     canDrawCircle;
	private boolean	     colorInverse;
	private boolean	     initializeCircleDone;
	private boolean	     firstRun	   = false;
	private boolean	     longClickable	  = false;
	private boolean	     longClick	  = false;

	/*
	 *
	 */
	public HoloButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initial(context);
	}

	public HoloButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initial(context);
	}

	public HoloButton(Context context) {
		super(context);
		initial(context);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
	                        int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		radius = (float) (getWidth() / 2.5);
		stepRaduis = radius / 5;
		xCenter = getWidth() / 2;
		yCenter = getHeight() / 2;
	}

	/*
	 *
	 */
	public void setOnClickListener(OnClickListener l) {
		clickEndlistener = l;
	}

	/*
	 *
	 */
	public void setOnClickStartListener(OnClickListener l) {
		clickStartlistener = l;
	}

	/*
	 *
	 */
	public void setOnLongClickListener(OnLongClickListener l) {
		longClickListener = l;
	}

	/**
	 *
	 * @param longClickable
	 */
	@Override
	public void setLongClickable(boolean longClickable) {
		this.longClickable = longClickable;
	}


	@Override
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
		postInvalidate();
	}

	/**
	 *
	 * @param l
	 */
	public void setColorInverse(boolean l) {
		colorInverse = l;
	}

	public void setColor(int iColor, int sColor) {
		initializeCircleDone = false;
		initialFillColor = iColor;
		initialRoundColor = sColor;
		initializeCirclePaint();
	}

	/**
	 *
	 */
	private void initial(Context context) {
		detector = new GestureDetector(context, this); // "this" refers to the OnGestureListener
		circlePaintHover = new Paint();
		circlePaintHover.setColor(Color.GRAY);
		circlePaintHover.setAntiAlias(true);
		circlePaintHover.setStyle(Style.FILL_AND_STROKE);
		circlePaintHover.setAlpha(15);
	}

	/**
	 *
	 */
	private void initializeCirclePaint() {
		if (!initializeCircleDone) {
			circlePaintUnder = new Paint();
			circlePaintUnder.setColor(initialFillColor);
			circlePaintUnder.setAntiAlias(true);
			circlePaintUnder.setStyle(Style.FILL_AND_STROKE);
			circlePaintUnder.setAlpha(initialAlpha);

			for (int i = 0; i < circlePaintOver.length; i++) {
				circlePaintOver[i] = new Paint();
				circlePaintOver[i]
						.setColor(initialRoundColor);
				circlePaintOver[i].setAntiAlias(true);
				circlePaintOver[i].setStyle(Style.FILL_AND_STROKE);
				circlePaintOver[i].setAlpha(5 + i);
			}
			initializeCircleDone = true;
		}
	}

	/**
	 *
	 * @param canvas
	 */
	@Override
	protected void onDraw(final Canvas canvas) {
		super.onDraw(canvas);

		if (!clickable) {
			circlePaintHover.setAlpha(25);
			canvas.drawCircle(xCenter, yCenter, radius * 2.5f, circlePaintHover);

			invalidate();
		} else {
			circlePaintHover.setAlpha(0);
			canvas.drawCircle(xCenter, yCenter, radius * 2.5f, circlePaintHover);
			invalidate();
		}

		if (canDrawCircle && !longClick) {
			if (!firstRun) {
				callStartListener();
				firstRun = true;
			}
			if (circleRaduis[circleRaduis.length - 1] < (0.75 * radius)) {
				// ||(circleRaduis3 < radius)

				circleRaduisUnder += getWidth() / 6;
				if (circleRaduisUnder > getWidth())
					circleRaduisUnder = getWidth();

				for (int i = 0; i < circleRaduis.length; i++) {
					float nR = (float) (radius - i * 0.012);
					if (circleRaduis[i] < nR)
						circleRaduis[i] += stepRaduis / (1.1f + i * 0.03);
				}

				canvas.drawCircle(xClickPosition, yClickPosition,
						circleRaduisUnder, circlePaintUnder);
				for (int i = 0; i < circleRaduis.length; i++) {
					canvas.drawCircle(xClickPosition, yClickPosition,
							circleRaduis[i], circlePaintOver[i]);
				}
				invalidate();
			} else {
				canDrawCircle = false;
				circleRaduisUnder = 0;
				for (int i = 0; i < circleRaduis.length; i++) {
					circleRaduis[i] = 0;
				}
				callClickListener();
			}
		} else if (canDrawCircle && longClick) {
			canDrawCircle = false;
			longClick = false;
			callLongClickListener();
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean performClick() {
		extClick();
		return super.performClick();
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean performLongClick() {
		extClick();
		return super.performLongClick();
	}

	/**
	 *
	 */
	private void extClick(){
		if (clickable && longClickable) {
			if (!canDrawCircle && event != null) {
				xClickPosition = event.getX();
				yClickPosition = event.getY();
				clickEvent();
			}
		}
	}

	/**
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		boolean handled = detector.onTouchEvent(event);
		if (clickable) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					if (!canDrawCircle) {
						xClickPosition = event.getX();
						yClickPosition = event.getY();
						clickEvent();
					}
					break;
				case MotionEvent.ACTION_DOWN:
					break;
			}
		}
		return true;
	}

	/**
	 *
	 * @param motionEvent
	 * @return
	 */
	@Override
	public boolean onDown(MotionEvent motionEvent) {
		return false;
	}

	/**
	 *
	 * @param motionEvent
	 */
	@Override
	public void onShowPress(MotionEvent motionEvent) {

	}

	/**
	 *
	 * @param motionEvent
	 * @return
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent motionEvent) {
		return false;
	}

	/**
	 *
	 * @param motionEvent
	 * @param motionEvent1
	 * @param v
	 * @param v1
	 * @return
	 */
	@Override
	public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
		return false;
	}

	/**
	 *
	 * @param motionEvent
	 */
	@Override
	public void onLongPress(MotionEvent motionEvent) {
		if(longClickable){
			Log.i("Long Click", "Done");
			longClick = true;
			clickEvent();
		}
	}

	/**
	 *
	 * @param motionEvent
	 * @param motionEvent1
	 * @param v
	 * @param v1
	 * @return
	 */
	@Override
	public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
		return false;
	}

	/**
	 *
	 */
	private void clickEvent() {
		firstRun = false;
		canDrawCircle = true;
		initializeCirclePaint();
		postInvalidate();
	}

	/**
	 *
	 */
	public void callClickListener() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (clickEndlistener != null) {
					clickEndlistener.onClick(HoloButton.this);
				}
			}
		}, DRAW_OFFSET);
	}

	/**
	 *
	 */
	public void callLongClickListener() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (longClickListener != null) {
					longClickListener.onLongClick(HoloButton.this);
				}
			}
		}, DRAW_OFFSET);
	}

	/**
	 *
	 */
	public void callStartListener() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (clickStartlistener != null) {
					clickStartlistener.onClick(HoloButton.this);
				}
			}
		}, DRAW_OFFSET);
	}
}