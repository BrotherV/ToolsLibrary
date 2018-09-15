package com.bvtech.toolslibrary.FloatViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rey.material.app.ThemeManager;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.RippleManager;

/**
 * Created by Mohsen on 2/21/2017.
 */

public class FloatFrameLayout extends FrameLayout {
	public static final int UP = 1;
	public static final int DOWN = 2;

	public static final float TH = 1f;
	public static final float TM = 0.5f;
	public static final float TL = 0.15f;

	private RippleManager mRippleManager;
	protected int mStyleId;
	protected int mCurrentStyle = ThemeManager.THEME_UNDEFINED;
	protected boolean clickable;
	private boolean isViewIn = true;
	private boolean isViewOut;
	private Animation translateAnimIn;
	private Animation translateAnimOut;
	private int mScrollThreshold = (int) (4 * Resources.getSystem().getDisplayMetrics().density);

	public FloatFrameLayout(Context context) {
		super(context);
		init(context, null, 0, 0);
	}

	public FloatFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0, 0);
	}

	public FloatFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr, 0);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public FloatFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs, defStyleAttr, defStyleRes);
	}

	protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
		setDefaultAnim(UP, TH);
		applyStyle(context, attrs, defStyleAttr, defStyleRes);
		if(!isInEditMode())
			mStyleId = ThemeManager.getStyleId(context, attrs, defStyleAttr, defStyleRes);
	}

	public void applyStyle(int resId){
		ViewUtil.applyStyle(this, resId);
		applyStyle(getContext(), null, 0, resId);
	}

	protected void applyStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
		getRippleManager().onCreate(this, context, attrs, defStyleAttr, defStyleRes);
	}


	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		RippleManager.cancelRipple(this);
	}

	protected RippleManager getRippleManager(){
		if(mRippleManager == null){
			synchronized (RippleManager.class){
				if(mRippleManager == null)
					mRippleManager = new RippleManager();
			}
		}

		return mRippleManager;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		if(clickable){
			RippleManager rippleManager = getRippleManager();
			if (l == rippleManager)
				super.setOnClickListener(l);
			else {
				rippleManager.setOnClickListener(l);
				setOnClickListener(rippleManager);
			}
		}
	}

	@Override
	public boolean onTouchEvent(@NonNull MotionEvent event) {
		boolean result = super.onTouchEvent(event);
		return  getRippleManager().onTouchEvent(this, event) || result;
	}

	@Override
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	/**
	 *
	 */
	public void hide(){
		if(isViewIn && !isViewOut) {
			isViewOut = true;
			isViewIn = false;
			this.startAnimation(translateAnimOut);
		}
	}

	/**
	 *
	 */
	public void show(){
		if(!isViewIn && isViewOut) {
			isViewOut = false;
			isViewIn = true;
			this.startAnimation(translateAnimIn);
		}
	}

	/**
	 *
	 * @param listView
	 */
	public void attachToListView(@NonNull AbsListView listView) {
		attachToListView(listView, null, null);
	}

	/**
	 *
	 * @param listView
	 * @param scrollDirectionListener
	 */
	public void attachToListView(@NonNull AbsListView listView,
	                             ScrollDirectionListener scrollDirectionListener) {
		attachToListView(listView, scrollDirectionListener, null);
	}

	/**
	 *
	 * @param recyclerView
	 */
	public void attachToRecyclerView(@NonNull RecyclerView recyclerView) {
		attachToRecyclerView(recyclerView, null, null);
	}

	/**
	 *
	 * @param recyclerView
	 * @param scrollDirectionListener
	 */
	public void attachToRecyclerView(@NonNull RecyclerView recyclerView,
	                                 ScrollDirectionListener scrollDirectionListener) {
		attachToRecyclerView(recyclerView, scrollDirectionListener, null);
	}

	/**
	 *
	 * @param scrollView
	 */
	public void attachToScrollView(@NonNull ObservableScrollView scrollView) {
		attachToScrollView(scrollView, null, null);
	}

	/**
	 *
	 * @param scrollView
	 * @param scrollDirectionListener
	 */
	public void attachToScrollView(@NonNull ObservableScrollView scrollView,
	                               ScrollDirectionListener scrollDirectionListener) {
		attachToScrollView(scrollView, scrollDirectionListener, null);
	}

	/**
	 *
	 * @param listView
	 * @param scrollDirectionListener
	 * @param onScrollListener
	 */
	public void attachToListView(@NonNull AbsListView listView,
	                             ScrollDirectionListener scrollDirectionListener,
	                             AbsListView.OnScrollListener onScrollListener) {
		AbsListViewScrollDetectorImpl scrollDetector = new AbsListViewScrollDetectorImpl();
		scrollDetector.setScrollDirectionListener(scrollDirectionListener);
		scrollDetector.setOnScrollListener(onScrollListener);
		scrollDetector.setListView(listView);
		scrollDetector.setScrollThreshold(mScrollThreshold);
		listView.setOnScrollListener(scrollDetector);
	}

	/**
	 *
	 * @param recyclerView
	 * @param scrollDirectionlistener
	 * @param onScrollListener
	 */
	public void attachToRecyclerView(@NonNull RecyclerView recyclerView,
	                                 ScrollDirectionListener scrollDirectionlistener,
	                                 RecyclerView.OnScrollListener onScrollListener) {
		RecyclerViewScrollDetectorImpl scrollDetector = new RecyclerViewScrollDetectorImpl();
		scrollDetector.setScrollDirectionListener(scrollDirectionlistener);
		scrollDetector.setOnScrollListener(onScrollListener);
		scrollDetector.setScrollThreshold(mScrollThreshold);
		recyclerView.addOnScrollListener(scrollDetector);
	}

	/**
	 *
	 * @param scrollView
	 * @param scrollDirectionListener
	 * @param onScrollChangedListener
	 */
	public void attachToScrollView(@NonNull ObservableScrollView scrollView,
	                               ScrollDirectionListener scrollDirectionListener,
	                               ObservableScrollView.OnScrollChangedListener onScrollChangedListener) {
		ScrollViewScrollDetectorImpl scrollDetector = new ScrollViewScrollDetectorImpl();
		scrollDetector.setScrollDirectionListener(scrollDirectionListener);
		scrollDetector.setOnScrollChangedListener(onScrollChangedListener);
		scrollDetector.setScrollThreshold(mScrollThreshold);
		scrollView.setOnScrollChangedListener(scrollDetector);
	}

	/**
	 *
	 */
	private class AbsListViewScrollDetectorImpl extends AbsListViewScrollDetector {
		private ScrollDirectionListener mScrollDirectionListener;
		private AbsListView.OnScrollListener mOnScrollListener;

		/**
		 *
		 * @param scrollDirectionListener
		 */
		private void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
			mScrollDirectionListener = scrollDirectionListener;
		}

		/**
		 *
		 * @param onScrollListener
		 */
		public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
			mOnScrollListener = onScrollListener;
		}

		/**
		 *
		 */
		@Override
		public void onScrollDown() {
			show();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollDown();
			}
		}

		/**
		 *
		 */
		@Override
		public void onScrollUp() {
			hide();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollUp();
			}
		}

		/**
		 *
		 * @param view
		 * @param firstVisibleItem
		 * @param visibleItemCount
		 * @param totalItemCount
		 */
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
		                     int totalItemCount) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
			}

			super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}

		/**
		 *
		 * @param view
		 * @param scrollState
		 */
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScrollStateChanged(view, scrollState);
			}

			super.onScrollStateChanged(view, scrollState);
		}
	}

	/**
	 *
	 */
	private class RecyclerViewScrollDetectorImpl extends RecyclerViewScrollDetector {
		private ScrollDirectionListener mScrollDirectionListener;
		private RecyclerView.OnScrollListener mOnScrollListener;

		/**
		 *
		 * @param scrollDirectionListener
		 */
		private void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
			mScrollDirectionListener = scrollDirectionListener;
		}

		/**
		 *
		 * @param onScrollListener
		 */
		public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
			mOnScrollListener = onScrollListener;
		}

		/**
		 *
		 */
		@Override
		public void onScrollDown() {
			show();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollDown();
			}
		}

		/**
		 *
		 */
		@Override
		public void onScrollUp() {
			hide();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollUp();
			}
		}

		/**
		 *
		 * @param recyclerView
		 * @param dx
		 * @param dy
		 */
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScrolled(recyclerView, dx, dy);
			}

			super.onScrolled(recyclerView, dx, dy);
		}

		/**
		 *
		 * @param recyclerView
		 * @param newState
		 */
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScrollStateChanged(recyclerView, newState);
			}

			super.onScrollStateChanged(recyclerView, newState);
		}
	}

	/**
	 *
	 */
	private class ScrollViewScrollDetectorImpl extends ScrollViewScrollDetector {
		private ScrollDirectionListener mScrollDirectionListener;

		private ObservableScrollView.OnScrollChangedListener mOnScrollChangedListener;

		/**
		 *
		 * @param scrollDirectionListener
		 */
		private void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
			mScrollDirectionListener = scrollDirectionListener;
		}

		/**
		 *
		 * @param onScrollChangedListener
		 */
		public void setOnScrollChangedListener(ObservableScrollView.OnScrollChangedListener onScrollChangedListener) {
			mOnScrollChangedListener = onScrollChangedListener;
		}

		/**
		 *
		 */
		@Override
		public void onScrollDown() {
			show();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollDown();
			}
		}

		/**
		 *
		 */
		@Override
		public void onScrollUp() {
			hide();
			if (mScrollDirectionListener != null) {
				mScrollDirectionListener.onScrollUp();
			}
		}

		/**
		 *
		 * @param who
		 * @param l
		 * @param t
		 * @param oldl
		 * @param oldt
		 */
		@Override
		public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
			if (mOnScrollChangedListener != null) {
				mOnScrollChangedListener.onScrollChanged(who, l, t, oldl, oldt);
			}

			super.onScrollChanged(who, l, t, oldl, oldt);
		}
	}

	public void setDefaultAnim(int direction, float th){
		switch(direction){
			case DOWN:
				translateAnimOut = translateAnimationRelativeToParent(0.0f,0.0f,0.0f,1f * th,500,true);
				translateAnimIn = translateAnimationRelativeToParent(0.0f,0.0f,1f * th,0.0f,500,true);
				break;
			default:
				translateAnimOut = translateAnimationRelativeToParent(0.0f,0.0f,0.0f,-1f * th,500,true);
				translateAnimIn = translateAnimationRelativeToParent(0.0f,0.0f,-1f * th,0.0f,500,true);
		}
	}

	private static Animation translateAnimationRelativeToParent(float fromXDelta,
	                                                           float toXDelta, float fromYDelta, float toYDelta, int durationMillis, boolean fill) {
		Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,fromXDelta,
				Animation.RELATIVE_TO_PARENT,toXDelta,
				Animation.RELATIVE_TO_PARENT,fromYDelta,
				Animation.RELATIVE_TO_PARENT, toYDelta);
		// Start and end
		// values for
		// Start and end values for the Y axis scaling
		anim.setInterpolator(new AccelerateDecelerateInterpolator());
		anim.setDuration(durationMillis);
		anim.setFillAfter(fill); // Needed to keep the result of the animation
		return anim;
	}
}
