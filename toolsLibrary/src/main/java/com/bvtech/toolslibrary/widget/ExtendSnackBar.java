package com.bvtech.toolslibrary.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bvtech.toolslibrary.R;
import com.google.android.material.snackbar.Snackbar;

public class ExtendSnackBar {

    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_SHORT = -1;
    public static final int LENGTH_LONG = 0;

    public static void  makeText(Context context, View v, String msg, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        mImageView.setVisibility(View.GONE);
        mMessageView.setText(msg);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  makeText(Context context, View v, String msg, int resId, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        mImageView.setImageResource(resId);
        mMessageView.setText(msg);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  makeText(Context context, View v, String msg, String buttonText, int resId, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        mImageView.setImageResource(resId);
        mMessageView.setText(msg);
        btn.setText(buttonText);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  makeText(Context context, View v, String msg, Typeface tf, int resId, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        mImageView.setImageResource(resId);
        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  makeText(Context context, View v, String msg, String buttonText,  Typeface tf, int resId, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        mImageView.setImageResource(resId);
        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);

        btn.setText(buttonText);
        btn.setTypeface(tf);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackWarning(Context context, View v, String msg, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackWarning(Context context, View v, String msg, String buttonText, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackWarning(Context context, View v, String msg, Typeface tf,int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackWarning(Context context, View v, String msg, String buttonText, Typeface tf,int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTypeface(tf);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackError(Context context, View v, String msg, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));

        mImageView.setImageResource(R.drawable.tools_library_notification_error);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackError(Context context, View v, String msg, String buttonText, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));

        mImageView.setImageResource(R.drawable.tools_library_notification_error);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackError(Context context, View v, String msg, Typeface tf,int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));

        mImageView.setImageResource(R.drawable.tools_library_notification_error);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackError(Context context, View v, String msg, String buttonText, Typeface tf,int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));

        mImageView.setImageResource(R.drawable.tools_library_notification_error);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTypeface(tf);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackDone(Context context, View v, String msg, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));

        mImageView.setImageResource(R.drawable.tools_library_notification_done);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackDone(Context context, View v, String msg, String buttonText, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));

        mImageView.setImageResource(R.drawable.tools_library_notification_done);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackDone(Context context, View v, String msg,Typeface tf, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));

        mImageView.setImageResource(R.drawable.tools_library_notification_done);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackDone(Context context, View v, String msg, String buttonText, Typeface tf,int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));

        mImageView.setImageResource(R.drawable.tools_library_notification_done);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTypeface(tf);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackNotify(Context context, View v, String msg, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackNotify(Context context, View v, String msg, String buttonText, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackNotify(Context context, View v, String msg, Typeface tf, int length){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);

        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }

    public static void  snackNotify(Context context, View v, String msg, String buttonText, Typeface tf, int length, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(v, "", length);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View view = LayoutInflater.from(context).inflate(R.layout.tools_library_layout_snackbar, null);
        TextView mMessageView = view.findViewById(R.id.bvTechToolsLibraryTextSnackBar);
        ImageView mImageView = view.findViewById(R.id.bvTechToolsLibraryImgSnackBar);
        CardView cardView = view.findViewById(R.id.bvTechToolsLibraryCardViewSnackBar);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));

        mImageView.setImageResource(R.drawable.tools_library_notification_warning);
        mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mMessageView.setText(msg);
        mMessageView.setTypeface(tf);
        mMessageView.setTextColor(Color.WHITE);

        Button btn = view.findViewById(R.id.bvTechToolsLibraryButtonSnackBar);
        btn.setVisibility(View.VISIBLE);

        btn.setText(buttonText);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(onClickListener);

        layout.setPadding(0,0,0,0);
        layout.addView(view, 0);
        snackbar.show();
    }
}
