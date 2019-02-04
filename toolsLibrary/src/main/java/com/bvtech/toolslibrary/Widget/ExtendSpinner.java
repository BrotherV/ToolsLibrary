package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bvtech.toolslibrary.Adapters.SpinnerAdapter;
import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Utility.Utilities;
import com.bvtech.toolslibrary.Utility.ViewUtility;

/**
 * Created by Mohsen on 9/10/2018.
 */

public class ExtendSpinner extends LinearLayout {

	public static final int RECTANGLE = 0xA01;
	public static final int OVAL = 0xA02;
	public static final int ICON_SIMPLE = 0xA03;
	public static final int ICON_CORNER = 0xA04;
	public static final int LTR = 1;
	public static final int RTL = 2;
	public static final int TRANSPARENT_COLOR = Color.TRANSPARENT;
	public static final int WHITE_COLOR = Color.WHITE;
	public static final int DEFAULT_COLOR = Color.GRAY;

	private Context mContext;
	private int mBackgroundColor;
	private int mStrokeColor;
	private float mStrokeSize;
	private float mCornerRadius;
	private int mShapeType;
	private int mTextColor;
	private int mIcon;
	private int mIconColor;
	private float mTextSize;
	private float mImageSize;
	private String[] mEntries;
	private String[] mEntryValues;
	private String[] mImageEntries;
	private int[] mImageIdEntries;
	private boolean isSpClicked;
	private Typeface mTypeFace;
	private int mFontId;
	private boolean isRtl;
	private SpinnerAdapter spinnerAdapter;
	public AdapterView.OnItemSelectedListener onItemSelectedListener;
	private LinearLayout lay;
	private Spinner spinner;
	private ImageView img;

	/**
	 *
	 */
	public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
		this.onItemSelectedListener = onItemSelectedListener;
	}

	/**
	 *
	 */
	public ExtendSpinner(Context context) {
		super(context);
		init(context, null);
	}

	public ExtendSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public ExtendSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	/**
	 *
	 */
	private void init(Context context, AttributeSet attrs){
		mContext = context;
		isRtl = context.getResources().getBoolean(R.bool.is_right_to_left);
		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.tools_library_layout_spinner, this, true);
		//View view = inflate(context, R.layout.tools_library_layout_spinner, this);
		TypedValue typedValue = new TypedValue();
		Resources.Theme theme = context.getTheme();
		theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);

		TypedArray ta;
		if(attrs != null){
			ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetAttributes);
		}else {
			ta = context.obtainStyledAttributes(R.styleable.WidgetAttributes);
		}
		int backgroundColorResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_backgroundColor, 0);
		if (backgroundColorResId != 0) {
			mBackgroundColor = context.getResources().getColor(backgroundColorResId);
		}else{
			mBackgroundColor = WHITE_COLOR;
		}

		int strokeColorResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_strokeColor, 0);
		if (strokeColorResId != 0) {
			mStrokeColor = context.getResources().getColor(strokeColorResId);
		}else{
			mStrokeColor = typedValue.data;
		}

		float strokeSize = ta.getDimension(R.styleable.WidgetAttributes_tl_strokeSize, 0);
		if (strokeSize != 0) {
			mStrokeSize = strokeSize;
		}else{
			mStrokeSize = Utilities.dpToPx(2);
		}

		float cornerRadius = ta.getDimension(R.styleable.WidgetAttributes_tl_cornerRadius, -1);
		if (cornerRadius != -1) {
			mCornerRadius = cornerRadius;
		}else{
			mCornerRadius = Utilities.dpToPx(2);
		}

		int shapeType = ta.getInt(R.styleable.WidgetAttributes_tl_shapeType, 0);
		if (shapeType != 0) {
			mShapeType = shapeType;
		}

		int iconType = ta.getInt(R.styleable.WidgetAttributes_tl_dropDownIcon, 0);
		if (iconType != 0) {
			mIcon = iconType;
		}else {
			mIcon = ICON_SIMPLE;
		}

		int layoutDirection = ta.getInt(R.styleable.WidgetAttributes_tl_layoutDirection, 0);
		if (layoutDirection == LTR) {
			isRtl = false;
		}else if (layoutDirection == RTL){
			isRtl = true;
		}

		int iconColorResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_iconColor, 0);
		if (iconColorResId != 0) {
			mIconColor = context.getResources().getColor(iconColorResId);
		}else {
			mIconColor = typedValue.data;
		}

		int textColorResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_textColor, 0);
		if (textColorResId != 0) {
			mTextColor = context.getResources().getColor(textColorResId);
		}else{
			mTextColor = typedValue.data;
		}

		float textSize = ta.getDimension(R.styleable.WidgetAttributes_tl_textSize, 0);
		if (textSize != 0) {
			mTextSize = Utilities.pxToDp(textSize);
		}else {
			mTextSize = 14;
		}

		float imageSize = ta.getDimension(R.styleable.WidgetAttributes_tl_imageSize, 0);
		if (imageSize != 0) {
			mImageSize = imageSize;
		}else {
			mImageSize = Utilities.dpToPx(28);
		}

		int entriesResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_entries, 0);
		if (entriesResId != 0) {
			mEntries = context.getResources().getStringArray(entriesResId);
		}

		int entryValuesResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_entryValues, 0);
		if (entryValuesResId != 0) {
			mEntryValues = context.getResources().getStringArray(entryValuesResId);
		}

		int imageEntriesResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_imageEntries, 0);
		if (imageEntriesResId != 0) {
			mImageEntries = context.getResources().getStringArray(imageEntriesResId);
		}

		int fontResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_fontType, 0);
		if(fontResId != 0 && Utilities.hasOreoApi()){
			mTypeFace = context.getResources().getFont(fontResId);
		}else{
			String ltrFont = ta.getString(R.styleable.WidgetAttributes_tl_ltrTypeFace);
			String rtlFont = ta.getString(R.styleable.WidgetAttributes_tl_rtlTypeFace);

			if(ltrFont != null && !isRtl){
				try {
					mTypeFace = Typeface.createFromAsset(context.getAssets(), ltrFont);
				}catch (Exception e){
					mTypeFace = Typeface.DEFAULT;
					e.printStackTrace();
				}
			}else if(rtlFont != null && isRtl){
				try {
					mTypeFace = Typeface.createFromAsset(context.getAssets(), rtlFont);
				}catch (Exception e){
					mTypeFace = Typeface.DEFAULT;
					e.printStackTrace();
				}
			}

			/*String fileDir = Utilities.isFileExistInAsset(context, "" ,ltrFont + ".ttf");
			if(fileDir != null){
				mTypeFace = Typeface.createFromAsset(context.getAssets(), fileDir);
			}*/
		}

		if(mImageEntries != null && mImageEntries.length > 0){
			mImageIdEntries = new int[mImageEntries.length];
			for(int i = 0; i < mImageEntries.length; i++){
				int id1 = Utilities.getResourceId(mImageEntries[i], "drawable",
						context.getPackageName(), getResources());
				int id2 = Utilities.getResourceId(mImageEntries[i], "mipmap",
						context.getPackageName(), getResources());
				if(id1 != 0 || id2 != 0){
					if(id1 != 0){
						mImageIdEntries[i] = id1;
					}else if(id2 != 0){
						mImageIdEntries[i] = id2;
					}else{
						mImageIdEntries[i] = 0;
					}
				}
			}
		}
		setViews(context, view);
	}

	/**
	 *
	 */
	private void setViews(Context context, View view){
		lay = view.findViewById(R.id.bvTechToolsLibrarySpinnerLayout);
		spinner = view.findViewById(R.id.bvTechToolsLibrarySpinner);
		img = view.findViewById(R.id.bvTechToolsLibrarySpinnerButton);
		img.setVisibility(INVISIBLE);

		if(mIcon == ICON_SIMPLE){
			img.setImageResource(R.drawable.tools_library_arrow_drop_down);
		}else if(mIcon == ICON_CORNER){
			img.setImageResource(isRtl ? R.drawable.tools_library_arrow_drop_down3 : R.drawable.tools_library_arrow_drop_down2);
		}
		spinner.setClickable(false);

		lay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewUtility.shrinkExpandAnimation(img);
				spinner.performClick();
			}
		});

		if(mEntries != null && mEntries.length > 0){
			int color = mTextColor;
			if(mTextColor == Color.WHITE){
				color = DEFAULT_COLOR;
			}
			if(mImageIdEntries!= null && mImageIdEntries.length == mEntries.length){
				fillSpinner(context, mEntries, mEntryValues, mImageIdEntries, color, TRANSPARENT_COLOR, mTextSize, mImageSize, mTypeFace);
			}else{
				fillSpinner(context, mEntries, mEntryValues, null, color, TRANSPARENT_COLOR, mTextSize, mImageSize, mTypeFace);
			}
		}

        if(mShapeType != 0){
            setBackgroundDrawable(mShapeType, (int) mCornerRadius, (int) mStrokeSize, mBackgroundColor, mStrokeColor, mIconColor);
        }
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Log.d("ItemSelectedListener", "Item Selected");
				/*
				if(!isSpClicked){
					isSpClicked = true;
				}else{
					spinner.setSelection(position,false);
					if(onItemSelectedListener != null){
						onItemSelectedListener.onItemSelected(parent,view, position, id);
					}
				}
				*/
				if(onItemSelectedListener != null){
					onItemSelectedListener.onItemSelected(parent, ExtendSpinner.this, position, id);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ItemSelectedListener", "Nothing Selected");
				if(onItemSelectedListener != null){
					onItemSelectedListener.onNothingSelected(parent);
				}
			}
		});

		spinner.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				switch (motionEvent.getAction()){
					case MotionEvent.ACTION_UP:
						if(mShapeType != 0){
							ViewUtility.shrinkExpandAnimation(img);
						}
						break;
				}
				return false;
			}
		});
	}

	/**
	 *
	 */
	public void fillSpinner(Context mContext, String[] mArray, String[] mEntryValues, int[] imageArray, int tColor, int bColor, float tSize, float imgSize,  Typeface tf){
		this.mEntries = mArray;
		this.mEntryValues = mEntryValues;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, mEntryValues, imageArray, tColor, bColor, tSize,  imgSize,  tf);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void fillSpinner(Context mContext, String[] mArray, int[] imageArray){
		this.mEntries = mArray;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, imageArray, mTextColor, TRANSPARENT_COLOR, mTextSize,  mImageSize,  mTypeFace);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void fillSpinner(String[] mArray, String[] mEntryValues, int[] imageArray){
		this.mEntries = mArray;
		this.mEntryValues = mEntryValues;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, mEntryValues, imageArray, mTextColor, TRANSPARENT_COLOR, mTextSize,  mImageSize,  mTypeFace);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void fillSpinner(String[] mArray, int[] imageArray){
		this.mEntries = mArray;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, imageArray, mTextColor, TRANSPARENT_COLOR, mTextSize,  mImageSize,  mTypeFace);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void fillSpinner(String[] mArray, String[] mEntryValues){
		this.mEntries = mArray;
		this.mEntryValues = mEntryValues;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, mEntryValues, null, mTextColor, TRANSPARENT_COLOR, mTextSize,  mImageSize,  mTypeFace);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void fillSpinner(String[] mArray){
		this.mEntries = mArray;
		spinnerAdapter = WidgetHelper.getAdapter(mContext, mArray, null, mTextColor, TRANSPARENT_COLOR, mTextSize,  mImageSize,  mTypeFace);
		spinner.setAdapter(spinnerAdapter);
	}

	/**
	 *
	 */
	public void setBackgroundDrawable(int shapeType, int cornerRadius, int strokeSize, int backgroundColor, int borderColor, int iconColor) {
		if(shapeType == RECTANGLE){
			this.setBackgroundDrawable(ViewUtility.setShape(GradientDrawable.RECTANGLE, cornerRadius, strokeSize, backgroundColor, borderColor));
		}else if(shapeType == OVAL){
            this.setBackgroundDrawable(ViewUtility.setShape(GradientDrawable.OVAL, cornerRadius, strokeSize, backgroundColor, borderColor));
		}
		spinner.setBackgroundColor(TRANSPARENT_COLOR);
		img.setVisibility(VISIBLE);
		img.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP);
	}

	public int getCount() {
		if(spinnerAdapter != null){
			return spinnerAdapter.getCount();
		}
		return 0;
	}

	public String getItemName(int position){
		if(spinnerAdapter != null){
			return spinnerAdapter.getItemName(position);
		}
		return null;
	}

	public String getEntryValue(int position) {
		if(spinnerAdapter != null){
			return spinnerAdapter.getEntryValue(position);
		}
		return null;
	}

	public int getSelectedItemPosition(){
		return spinner.getSelectedItemPosition();
	}

	public void setSelection(int position, boolean b){
	    try {
            spinner.setSelection(position,b);
        }catch (Exception e){
	        e.printStackTrace();
        }
	}

	public void setSelection(int position){
        try {
            spinner.setSelection(position,false);
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public void setSelectionWithEntries(String entry){
		for(int i = 0; i < getCount(); i++){
			if (getEntryValue(i).equals(entry)) {
				spinner.setSelection(i,false);
				break;
			}
		}
	}

	@Override
	public boolean performClick() {
		spinner.performClick();
		return super.performClick();
	}
}
