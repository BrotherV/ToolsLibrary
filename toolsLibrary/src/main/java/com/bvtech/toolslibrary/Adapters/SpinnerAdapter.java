package com.bvtech.toolslibrary.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bvtech.toolslibrary.Structures.StructSpinner;
import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Utility.Utilities;

import java.util.ArrayList;

/**
 * Created by Mohsen on 9/6/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<StructSpinner> {

    // (In reality I used a manager which caches the Typeface objects)
    // Typeface farsiFont = FontManager.getInstance().getFont(getContext(), BLAMBOT);
    private Typeface font = Typeface.DEFAULT;
    private Context mContext;
    private ArrayList<StructSpinner> mItems;
    private StructSpinner tempValues=null;
    private LayoutInflater inflater;
    private int resLayout;
    private int mTextColor;
    private int mBackgroundColor;
    private int mPosition;
    private float mImageSize;
    private float mTextSize;

    public SpinnerAdapter(Context context, int resource, ArrayList<StructSpinner> items, Typeface font, int tColor, int bColor, float tSize, float imgSize) {
        super(context, resource, items);
        mItems = items;
        resLayout = resource;
        mBackgroundColor = bColor;
        this.font = font;
        mContext = context;
        mTextColor = tColor;
        mTextSize = tSize;
        mImageSize = imgSize;
        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View view = inflater.inflate(resLayout, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = mItems.get(position);


        LinearLayout l1 = (LinearLayout) view.findViewById(R.id.layCustomSpinner);
        if(l1 != null){
            l1.setBackgroundColor(mBackgroundColor);
        }

        TextView title = (TextView) view.findViewById(R.id.txtCustomSpinner);
        if(title != null){
            title.setText(tempValues.name);
            title.setTextColor(mTextColor);
            title.setTypeface(font);
        }

        ImageView img = (ImageView) view.findViewById(R.id.imgSpinner);
        if(img != null){
            if(tempValues.imgRes == 0){
                img.setVisibility(View.GONE);
            }else{
                if(Utilities.isImageSizeBigger(mContext, tempValues.imgRes, (int) mImageSize * 2, (int) mImageSize * 2)){
                    img.setImageBitmap(Utilities.createScaledBitmap(mContext, tempValues.imgRes, (int) mImageSize * 2, (int) mImageSize * 2));
                }else{
                    img.setImageResource(tempValues.imgRes);
                }
            }
        }

        if(mTextSize != 0){
            title.setTextSize(mTextSize);
        }

        if(mImageSize != 0){
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) img.getLayoutParams();
            params.width = (int) mImageSize;
            img.setLayoutParams(params);
        }
        mPosition = position;
        return view;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mPosition;
    }

    public String getItemName(int position){
        return mItems.get(position).name;
    }

    public int getPosition() {
        return mPosition;
    }

    public String getEntryValue(int position) {
        return mItems.get(position).entryValue;
    }
}
