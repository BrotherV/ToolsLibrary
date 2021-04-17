package com.bvtech.widgettest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bvtech.toolslibrary.utility.Utilities;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private String[] mDataset;
    private String[] mContryCodes;

    public RecyclerViewAdapter(Context context, String[] dataset, String[] contryCodes) {
        mContext = context;
        mDataset = dataset;
        mContryCodes = contryCodes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String countryName = mDataset[position];
        String values = mContryCodes[position];
        int flagResId = mContext.getResources().getIdentifier(values, "mipmap", mContext.getPackageName());
        viewHolder.mTextView.setText(countryName);
        viewHolder.mImageView.setImageDrawable(Utilities.getRoundedBitmap(mContext, flagResId));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title);
            mImageView = (ImageView) v.findViewById(R.id.imgLst);
        }
    }
}