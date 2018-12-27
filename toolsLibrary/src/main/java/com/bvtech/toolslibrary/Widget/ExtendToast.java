package com.bvtech.toolslibrary.Widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bvtech.toolslibrary.R;

public class ExtendToast extends Toast {

	public ExtendToast(Context context) {
		super(context);
	}

	private static ExtendToast makeText(Context context, String s, int lengthLong) {
		return makeText(context, s, lengthLong);
	}

	public static ExtendToast makeText(Context context, String msg, int resId, int lengthLong) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		mImageView.setImageResource(resId);
		mMessageView.setText(msg);
		extendToast.setView(view);
        extendToast.setDuration(lengthLong);
		return extendToast;
	}

	public static ExtendToast makeText(Context context, String msg, int resId, int bc, int tc, int ic, int lengthLong) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(resId);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(bc);
		mImageView.setColorFilter(ic, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(tc);
		extendToast.setView(view);
        extendToast.setDuration(lengthLong);
		return extendToast;
	}

	public static ExtendToast makeText(Context context, String msg, int resId, int bc, int tc, int ic, Typeface tf, int lengthLong) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(resId);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(bc);
		mImageView.setColorFilter(ic, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(tc);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(lengthLong);
		return extendToast;
	}

	public static ExtendToast makeText(Context context, String msg, int resId, int bc, int tc, int ic) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(resId);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(bc);
		mImageView.setColorFilter(ic, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(tc);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast makeText(Context context, String msg, int resId, int bc, int tc, int ic, Typeface tf) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(resId);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(bc);
		mImageView.setColorFilter(ic, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(tc);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastWarning(Context context, String msg, Typeface tf) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastWarning(Context context, String msg) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastWarning(Context context, String msg, Typeface tf, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastWarning(Context context, String msg, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow2Dark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastError(Context context, String msg, Typeface tf) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_error);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastError(Context context, String msg) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_error);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastError(Context context, String msg, Typeface tf, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_error);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastError(Context context, String msg, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_error);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastDone(Context context, String msg, Typeface tf) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_done);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastDone(Context context, String msg) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_done);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastDone(Context context, String msg, Typeface tf, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_done);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastDone(Context context, String msg, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_done);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreenDark));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	public static ExtendToast toastNotify(Context context, String msg, Typeface tf) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastNotify(Context context, String msg) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastNotify(Context context, String msg, Typeface tf, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
        mMessageView.setTypeface(tf);
		extendToast.setView(view);
		extendToast.setGravity(gravity, 0, 0);
        extendToast.setDuration(Toast.LENGTH_LONG);
		return extendToast;
	}

	public static ExtendToast toastNotify(Context context, String msg, int gravity) {
		ExtendToast extendToast = new ExtendToast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView mMessageView = (TextView) view.findViewById(R.id.txtToastText);
		ImageView mImageView = (ImageView) view.findViewById(R.id.imgToastImage);
		CardView cardView = view.findViewById(R.id.cardView);
		mImageView.setImageResource(R.drawable.notification_warning);
		mMessageView.setText(msg);
		cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue));
		mImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		mMessageView.setTextColor(Color.WHITE);
		extendToast.setView(view);
        extendToast.setDuration(Toast.LENGTH_LONG);
        extendToast.setGravity(gravity, 0, 0);
		return extendToast;
	}

	@Override
	public void show() {
		super.show();
	}
}
