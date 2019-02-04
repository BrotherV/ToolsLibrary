package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Utility.Utilities;
import com.bvtech.toolslibrary.Utility.ViewUtility;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Mohsen on 9/13/2018.
 */

public class ExtendTextView extends AppCompatTextView {

    public static final int RECTANGLE = 0xA01;
    public static final int OVAL = 0xA02;
    public static final int LTR = 1;
    public static final int RTL = 2;

    private int mBackgroundColor;
    private int mStrokeColor;
    private float mStrokeSize;
    private float mCornerRadius;
    private int mShapeType;

    public ExtendTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ExtendTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExtendTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta;
        boolean isRtl = context.getResources().getBoolean(R.bool.is_right_to_left);

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);

        if(attrs != null){
            ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetAttributes);
        }else {
            ta = context.obtainStyledAttributes(R.styleable.WidgetAttributes);
        }

        int backgroundColorResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_backgroundColor, 0);
        if (backgroundColorResId != 0) {
            mBackgroundColor = context.getResources().getColor(backgroundColorResId);
        }else{
            mBackgroundColor = Color.TRANSPARENT;
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

        int layoutDirection = ta.getInt(R.styleable.WidgetAttributes_tl_layoutDirection, 0);
        if (layoutDirection == LTR) {
            isRtl = false;
        }else if (layoutDirection == RTL){
            isRtl = true;
        }

        int fontResId = ta.getResourceId(R.styleable.WidgetAttributes_tl_fontType, 0);
        if(fontResId != 0 && Utilities.hasOreoApi()){
            setTypeface(context.getResources().getFont(fontResId));
        }else{
            String ltrFont = ta.getString(R.styleable.WidgetAttributes_tl_ltrTypeFace);
            String rtlFont = ta.getString(R.styleable.WidgetAttributes_tl_rtlTypeFace);

            if(ltrFont != null && !isRtl){
                try {
                    setTypeface(Typeface.createFromAsset(context.getAssets(), ltrFont));
                }catch (Exception e){
                    setTypeface(Typeface.DEFAULT);
                    e.printStackTrace();
                }
            }else if(rtlFont != null && isRtl){
                try {
                    setTypeface(Typeface.createFromAsset(context.getAssets(), rtlFont));
                }catch (Exception e){
                    setTypeface(Typeface.DEFAULT);
                    e.printStackTrace();
                }
            }

            //String fileDir = Utilities.isFileExistInAsset(context, "" ,ltrFont + ".ttf");
            //String fileDir = Utilities.isFileExistInAsset(context, "" ,rtlFont + ".ttf");
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
}
