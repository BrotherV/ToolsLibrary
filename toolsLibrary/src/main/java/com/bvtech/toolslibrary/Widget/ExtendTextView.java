package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Utility.Utilities;

/**
 * Created by Mohsen on 9/13/2018.
 */

public class ExtendTextView extends android.support.v7.widget.AppCompatTextView {

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
        if(attrs != null){
            ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetAttributes);
        }else {
            ta = context.obtainStyledAttributes(R.styleable.WidgetAttributes);
        }

        int fontResId = ta.getResourceId(R.styleable.WidgetAttributes_fontType, 0);
        if(fontResId != 0 && Utilities.hasOreoApi()){
            setTypeface(context.getResources().getFont(fontResId));
        }else{
            String ltrFont = ta.getString(R.styleable.WidgetAttributes_ltrTypeFace);
            String rtlFont = ta.getString(R.styleable.WidgetAttributes_rtlTypeFace);

            if(ltrFont != null && rtlFont != null){
                try {
                    String fileDir = Utilities.isFileExistInAsset(context, "" ,(isRtl ? ltrFont : rtlFont) + ".ttf");
                    if(fileDir != null){
                        setTypeface(Typeface.createFromAsset(context.getAssets(), fileDir));
                    }else{
                        setTypeface(Typeface.DEFAULT);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
