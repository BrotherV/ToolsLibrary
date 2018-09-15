package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.graphics.Typeface;

import com.bvtech.toolslibrary.Adapters.SpinnerAdapter;
import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.Structures.StructSpinner;

import java.util.ArrayList;

/**
 * Created by Mohsen on 9/9/2018.
 */

public class WidgetHelper {

	public static SpinnerAdapter getAdapter(Context mContext, ArrayList<StructSpinner> array, int tColor, int bColor, float tSize, float imgSize,  Typeface tf){
		SpinnerAdapter dataAdapter = new SpinnerAdapter(mContext, R.layout.layout_spinner_items, array,
				tf,
				tColor, bColor, tSize, imgSize);
		return dataAdapter;
	}

	public static SpinnerAdapter getAdapter(Context mContext, String[] array, int tColor, int bColor, float tSize, float imgSize,  Typeface tf){
		SpinnerAdapter dataAdapter = new SpinnerAdapter(mContext, R.layout.layout_spinner_items, getArray(array, null,null),
				tf,
				tColor, bColor, tSize, imgSize);
		return dataAdapter;
	}

	public static SpinnerAdapter getAdapter(Context mContext, String[] array, int[] imageArray, int tColor, int bColor, float tSize, float imgSize, Typeface tf){
		SpinnerAdapter dataAdapter = new SpinnerAdapter(mContext, R.layout.layout_spinner_items, getArray(array, null,imageArray),
				tf,
				tColor, bColor, tSize, imgSize);
		return dataAdapter;
	}

	public static SpinnerAdapter getAdapter(Context mContext, String[] array, String[] entryValues, int[] imageArray, int tColor, int bColor, float tSize, float imgSize, Typeface tf){
		SpinnerAdapter dataAdapter = new SpinnerAdapter(mContext, R.layout.layout_spinner_items, getArray(array, entryValues,imageArray),
				tf,
				tColor, bColor, tSize, imgSize);
		return dataAdapter;
	}

	public static ArrayList<StructSpinner> getArray(String[] array, String[] entryValues, int[] imgArray){
		ArrayList<StructSpinner> list = new ArrayList<>();
		for(int i = 0; i < array.length; i++){
			StructSpinner item = new StructSpinner();
			item.name = array[i];
			if(imgArray != null && imgArray.length == array.length){
				item.imgRes = imgArray[i];
			}else{
				item.imgRes = 0;
			}
			if(entryValues != null && entryValues.length == array.length){
				item.entryValue = entryValues[i];
			}else{
				item.entryValue = "" + i;
			}
			list.add(item);
		}
		return list;
	}
}
