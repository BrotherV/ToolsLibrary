package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Utility.Utilities;
import com.bvtech.toolslibrary.Utility.ViewUtility;

public class ExtendEditText extends android.support.v7.widget.AppCompatEditText {

    public interface OnKeyboardEventListener {
        void onKeyDown(View v);
        void onKeyUp(View v);
    }

    public interface OnTextChangeListener{
        void onTextChanged(View view, String str);
    }

	public static final int RECTANGLE = 0xA01;
	public static final int OVAL = 0xA02;
	public static final int LTR = 1;
	public static final int RTL = 2;

	private Context context;
	private int mBackgroundColor;
	private int mStrokeColor;
	private float mStrokeSize;
	private float mCornerRadius;
	private int mShapeType;

	private OnKeyboardEventListener listener;
	private OnTextChangeListener onTextChangeListener;

	public ExtendEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	public ExtendEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public ExtendEditText(Context context) {
		super(context);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {
		this.context = context;
		TypedArray ta;
		boolean isRtl = context.getResources().getBoolean(R.bool.is_right_to_left);

		TypedValue typedValue = new TypedValue();
		Resources.Theme theme = context.getTheme();
		theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);

		if(attrs != null){
			ta = context.obtainStyledAttributes(attrs, R.styleable.ExtendSpinner);
		}else {
			ta = context.obtainStyledAttributes(R.styleable.ExtendSpinner);
		}

		int backgroundColorResId = ta.getResourceId(R.styleable.ExtendSpinner_backgroundColor, 0);
		if (backgroundColorResId != 0) {
			mBackgroundColor = context.getResources().getColor(backgroundColorResId);
		}else{
			mBackgroundColor = Color.TRANSPARENT;
		}

		int strokeColorResId = ta.getResourceId(R.styleable.ExtendSpinner_strokeColor, 0);
		if (strokeColorResId != 0) {
			mStrokeColor = context.getResources().getColor(strokeColorResId);
		}else{
			mStrokeColor = typedValue.data;
		}

		float strokeSize = ta.getDimension(R.styleable.ExtendSpinner_strokeSize, 0);
		if (strokeSize != 0) {
			mStrokeSize = strokeSize;
		}else{
			mStrokeSize = Utilities.dpToPx(2);
		}

		float cornerRadius = ta.getDimension(R.styleable.ExtendSpinner_cornerRadius, -1);
		if (cornerRadius != -1) {
			mCornerRadius = cornerRadius;
		}else{
			mCornerRadius = Utilities.dpToPx(2);
		}

		int shapeType = ta.getInt(R.styleable.ExtendSpinner_shapeType, 0);
		if (shapeType != 0) {
			mShapeType = shapeType;
		}

		int layoutDirection = ta.getInt(R.styleable.ExtendSpinner_layoutDirection, 0);
		if (layoutDirection == LTR) {
			isRtl = false;
		}else if (layoutDirection == RTL){
			isRtl = true;
		}

		int fontResId = ta.getResourceId(R.styleable.ExtendSpinner_fontType, 0);
		if(fontResId != 0 && Utilities.hasOreoApi()){
			setTypeface(context.getResources().getFont(fontResId));
		}else{
			String ltrFont = ta.getString(R.styleable.ExtendSpinner_ltrTypeFace);
			String rtlFont = ta.getString(R.styleable.ExtendSpinner_rtlTypeFace);

			if(ltrFont != null && !isRtl){
				try {
					String fileDir = Utilities.isFileExistInAsset(context, "" ,ltrFont + ".ttf");
					if(fileDir != null){
						setTypeface(Typeface.createFromAsset(context.getAssets(), fileDir));
					}else{
						try {
							setTypeface(Typeface.createFromAsset(context.getAssets(), ltrFont));
						}catch (Exception e){
							setTypeface(Typeface.DEFAULT);
							e.printStackTrace();
						}
					}
				}catch (Exception e){
					setTypeface(Typeface.DEFAULT);
					e.printStackTrace();
				}
			}else if(rtlFont != null && isRtl){
				try {
					String fileDir = Utilities.isFileExistInAsset(context, "" ,rtlFont + ".ttf");
					if(fileDir != null){
						setTypeface(Typeface.createFromAsset(context.getAssets(), fileDir));
					}else{
						try {
							setTypeface(Typeface.createFromAsset(context.getAssets(), rtlFont));
						}catch (Exception e){
							setTypeface(Typeface.DEFAULT);
							e.printStackTrace();
						}
					}
				}catch (Exception e){
					setTypeface(Typeface.DEFAULT);
					e.printStackTrace();
				}
			}
		}

		if(mShapeType != 0){
			setBackgroundShape(mShapeType, (int) mCornerRadius, (int) mStrokeSize, mBackgroundColor, mStrokeColor);
		}
	}

	public void setBackgroundShape(int shapeType, int cornerRadius, int strokeSize, int backgroundColor, int borderColor) {
		if(shapeType == RECTANGLE){
			this.setBackgroundDrawable(ViewUtility.setShape(GradientDrawable.RECTANGLE, cornerRadius, strokeSize, backgroundColor, borderColor));
		}else if(shapeType == OVAL){
			this.setBackgroundDrawable(ViewUtility.setShape(GradientDrawable.OVAL, cornerRadius, strokeSize, backgroundColor, borderColor));
		}
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if (listener != null) {
                listener.onKeyUp(ExtendEditText.this);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(onTextChangeListener != null){
            onTextChangeListener.onTextChanged(ExtendEditText.this, text.toString());
        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP) {
            if (listener != null) {
                listener.onKeyDown(ExtendEditText.this);
            }
            //return true; // So it is not propagated.
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setOnKeyboardEventListener(OnKeyboardEventListener l) {
        listener = l;
    }

    public void setOnTextChangeListener(OnTextChangeListener l) {
        onTextChangeListener = l;
    }

    public void performOnKeyboardDownListener() {
        if (listener != null) {
            listener.onKeyDown(ExtendEditText.this);
        }
    }
}
