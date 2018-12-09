package com.bvtech.toolslibrary.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UnknownFormatConversionException;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Utilities {

	private static class SolarCalendar {

		public static String strWeekDay = "";
		public static String strMonth = "";

		public static int day = 0;
		public static int month = 0;
		public static int year = 0;

		private static void calcSolarCalendar() {

			Calendar MiladiDate = Calendar.getInstance();

			int miladiYear = MiladiDate.get(Calendar.YEAR);
			int miladiMonth = MiladiDate.get(Calendar.MONTH) + 1;
			int miladiDate = MiladiDate.get(Calendar.DATE);
			int WeekDay = MiladiDate.get(Calendar.DAY_OF_MONTH);
			;
			Log.i("Log", "Miladi Year: " + miladiYear + "  Miladi Month: "
					+ miladiMonth + "  Miladi Date: " + miladiDate
					+ "  Week Day: " + WeekDay);

			finalCalculation(miladiYear, miladiMonth, miladiDate, WeekDay);
		}

		public static void finalCalculation(int miladiYear, int miladiMonth,
		                                    int miladiDate, int WeekDay) {

			int ld;

			int[] buf1 = new int[12];
			int[] buf2 = new int[12];

			buf1[0] = 0;
			buf1[1] = 31;
			buf1[2] = 59;
			buf1[3] = 90;
			buf1[4] = 120;
			buf1[5] = 151;
			buf1[6] = 181;
			buf1[7] = 212;
			buf1[8] = 243;
			buf1[9] = 273;
			buf1[10] = 304;
			buf1[11] = 334;

			buf2[0] = 0;
			buf2[1] = 31;
			buf2[2] = 60;
			buf2[3] = 91;
			buf2[4] = 121;
			buf2[5] = 152;
			buf2[6] = 182;
			buf2[7] = 213;
			buf2[8] = 244;
			buf2[9] = 274;
			buf2[10] = 305;
			buf2[11] = 335;

			if ((miladiYear % 4) != 0) {
				day = buf1[miladiMonth - 1] + miladiDate;

				if (day > 79) {
					day = day - 79;
					if (day <= 186) {
						switch (day % 31) {
							case 0:
								month = day / 31;
								day = 31;
								break;
							default:
								month = (day / 31) + 1;
								day = (day % 31);
								break;
						}
						year = miladiYear - 621;
					} else {
						day = day - 186;

						switch (day % 30) {
							case 0:
								month = (day / 30) + 6;
								day = 30;
								break;
							default:
								month = (day / 30) + 7;
								day = (day % 30);
								break;
						}
						year = miladiYear - 621;
					}
				} else {
					if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
						ld = 11;
					} else {
						ld = 10;
					}
					day = day + ld;

					switch (day % 30) {
						case 0:
							month = (day / 30) + 9;
							day = 30;
							break;
						default:
							month = (day / 30) + 10;
							day = (day % 30);
							break;
					}
					year = miladiYear - 622;
				}
			} else {
				day = buf2[miladiMonth - 1] + miladiDate;

				if (miladiYear >= 1996) {
					ld = 79;
				} else {
					ld = 80;
				}
				if (day > ld) {
					day = day - ld;

					if (day <= 186) {
						switch (day % 31) {
							case 0:
								month = (day / 31);
								day = 31;
								break;
							default:
								month = (day / 31) + 1;
								day = (day % 31);
								break;
						}
						year = miladiYear - 621;
					} else {
						day = day - 186;

						switch (day % 30) {
							case 0:
								month = (day / 30) + 6;
								day = 30;
								break;
							default:
								month = (day / 30) + 7;
								day = (day % 30);
								break;
						}
						year = miladiYear - 621;
					}
				} else {
					day = day + 10;

					switch (day % 30) {
						case 0:
							month = (day / 30) + 9;
							day = 30;
							break;
						default:
							month = (day / 30) + 10;
							day = (day % 30);
							break;
					}
					year = miladiYear - 622;
				}

			}

			switch (month) {
				case 1:
					strMonth = "فروردين";
					break;
				case 2:
					strMonth = "ارديبهشت";
					break;
				case 3:
					strMonth = "خرداد";
					break;
				case 4:
					strMonth = "تير";
					break;
				case 5:
					strMonth = "مرداد";
					break;
				case 6:
					strMonth = "شهريور";
					break;
				case 7:
					strMonth = "مهر";
					break;
				case 8:
					strMonth = "آبان";
					break;
				case 9:
					strMonth = "آذر";
					break;
				case 10:
					strMonth = "دي";
					break;
				case 11:
					strMonth = "بهمن";
					break;
				case 12:
					strMonth = "اسفند";
					break;
			}

			switch (WeekDay) {

				case 0:
					strWeekDay = "يکشنبه";
					break;
				case 1:
					strWeekDay = "دوشنبه";
					break;
				case 2:
					strWeekDay = "سه شنبه";
					break;
				case 3:
					strWeekDay = "چهارشنبه";
					break;
				case 4:
					strWeekDay = "پنج شنبه";
					break;
				case 5:
					strWeekDay = "جمعه";
					break;
				case 6:
					strWeekDay = "شنبه";
					break;
			}
		}

	}

	/*
	 *
	 */
	public static String getCurrentShamsidate() {
		Locale loc = new Locale("en_US");
		SolarCalendar.calcSolarCalendar();

		return String.valueOf(SolarCalendar.year) + "/"
				+ String.format(loc, "%02d", SolarCalendar.month) + "/"
				+ String.format(loc, "%02d", SolarCalendar.day);
	}

	/*
	 *
	 */
	public static String getCurrentShamsidateFarsi() {
		SolarCalendar.calcSolarCalendar();

		return toPersianNumber("" + SolarCalendar.year) + "/"
				+ toPersianNumber("" + SolarCalendar.month) + "/"
				+ toPersianNumber("" + SolarCalendar.day);
	}

	/*
	 *
	 */
	public static String getCurrentShamsidateFarsiWithTime() {
		Calendar date = Calendar.getInstance();
		SolarCalendar.calcSolarCalendar();
		return toPersianNumber("" + SolarCalendar.year) + "/"
				+ toPersianNumber("" + SolarCalendar.month) + "/"
				+ toPersianNumber("" + SolarCalendar.day) + " "
				+ toPersianNumber("" + date.getTime().getHours()) + ":"
				+ toPersianNumber("" + date.getTime().getMinutes());
	}

	/*
	 *
	 */
	public static int getCurrentShamsiYear() {
		SolarCalendar.calcSolarCalendar();
		return SolarCalendar.year;
	}

	/*
	 *
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/*
	 *
	 */
	public static int getCurrentShamsiMonth() {
		SolarCalendar.calcSolarCalendar();
		return SolarCalendar.month;
	}

	/*
	 *
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}


	public static int getCurrentShamsiDay() {
		SolarCalendar.calcSolarCalendar();
		return SolarCalendar.day;
	}

	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/*
	 *
	 */
	public static String getInputShamsidate(int miladiYear, int miladiMonth,
	                                        int miladiDate, int WeekDay) {
		SolarCalendar.finalCalculation(miladiYear, miladiMonth, miladiDate,
				WeekDay);
		return toPersianNumber("" + SolarCalendar.year) + "/"
				+ toPersianNumber("" + SolarCalendar.month) + "/"
				+ toPersianNumber("" + SolarCalendar.day);
	}

	public static String getDate() {
		Calendar MiladiDate = Calendar.getInstance();
		int miladiYear = MiladiDate.get(Calendar.YEAR);
		int miladiMonth = MiladiDate.get(Calendar.MONTH) + 1;
		int weekDay = MiladiDate.get(Calendar.DAY_OF_MONTH);

		return miladiYear + "/" + miladiMonth + "/" + weekDay + " "
				+ MiladiDate.getTime().getHours() + ":"
				+ MiladiDate.getTime().getMinutes() + ":"
				+ MiladiDate.getTime().getSeconds();
	}

	public static String getDateWithTime() {
		Calendar MiladiDate = Calendar.getInstance();
		int miladiYear = MiladiDate.get(Calendar.YEAR);
		int miladiMonth = MiladiDate.get(Calendar.MONTH) + 1;
		int weekDay = MiladiDate.get(Calendar.DAY_OF_MONTH);

		return miladiYear + "/" + miladiMonth + "/" + weekDay + " "
				+ MiladiDate.getTime().getHours() + ":"
				+ MiladiDate.getTime().getMinutes();
	}

	public static String getCurrentDate() {
		Calendar MiladiDate = Calendar.getInstance();
		int miladiYear = MiladiDate.get(Calendar.YEAR);
		int miladiMonth = MiladiDate.get(Calendar.MONTH) + 1;
		int weekDay = MiladiDate.get(Calendar.DAY_OF_MONTH);

		return miladiYear + "/" + miladiMonth + "/" + weekDay;
	}

	public static Calendar toCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		return cal;
	}
	/*
	 *
	 */
	public static byte[] bigIntToByteArray(final int i) {
		BigInteger bigInt = BigInteger.valueOf(i);
		byte[] bytes = bigInt.toByteArray();
		for (byte b : bytes) {
			Log.i("Hex format:  ", String.format("0x%20x", b));
		}
		return bytes;
	}

	/*
	 *
	 */
	public static void fontChanger(ViewGroup viewGroup, Typeface tFace) {
		int childCount = viewGroup.getChildCount();
		// Log.i("Log", "chil Count = " + chilCount);
		if (tFace != null) {
			for (int i = 0; i < childCount; i++) {
				View child = viewGroup.getChildAt(i);

				if (child instanceof ViewGroup) {
					fontChanger((ViewGroup) child, tFace);
					continue;
				}

				if (child instanceof TextView) {
					((TextView) child).setTypeface(tFace);
				}
			}
		}
	}

	/*
	 *
	 */
	public static void colorChanger(ViewGroup viewGroup, int color) {
		int childCount = viewGroup.getChildCount();
		// Log.i("Log", "chil Count = " + chilCount);
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);

			if (child instanceof ViewGroup) {
				if (child instanceof CardView) {
					((CardView) child).setCardBackgroundColor(color);
				}else if (child instanceof LinearLayout) {
					((LinearLayout) child).setBackgroundColor(color);
				}else{
					colorChanger((ViewGroup) child, color);
				}
				continue;
			}
		}
	}
	/*
	 *
	 */
	public static void colorChanger(ViewGroup viewGroup, int color, float alpha) {
		int childCount = viewGroup.getChildCount();
		// Log.i("Log", "chil Count = " + chilCount);
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);

			if (child instanceof ViewGroup) {
				if (child instanceof CardView) {
					((CardView) child).setCardBackgroundColor(color);
					child.setAlpha(alpha);
				}else if (child instanceof LinearLayout) {
					child.setBackgroundColor(color);
					child.setAlpha(alpha);
				}else{
					colorChanger((ViewGroup) child, color);
				}
				continue;
			}
		}
	}

	/**
	 *
	 * @param viewGroup
	 * @param obj
	 * @return
	 */
	public static View getChild(ViewGroup viewGroup, Object obj) {
		int childCount = viewGroup.getChildCount();
		// Log.i("Log", "chil Count = " + chilCount);
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);

			if (child instanceof ViewGroup) {
				View v = getChild((ViewGroup) child, obj);
				if(v != null){
					return v;
				}
				continue;
			}

			String clsName = child.getClass().getName();
			String objName = obj.toString();
			objName = objName.substring(6 , objName.length());
			boolean b = clsName.equals(objName);

			if (b){
				return child;
			}
		}
		return null;
	}


	public static void viewTextChanger(ViewGroup viewGroup) {
		int childCount = viewGroup.getChildCount();
		// Log.i("Log", "chil Count = " + chilCount);
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);

			if (child instanceof ViewGroup) {
				viewTextChanger((ViewGroup) child);
				continue;
			}

			if (child instanceof TextView) {
				String s = ((TextView) child).getText().toString();
				((TextView) child).setText(toPersianNumber(s));
			}
		}
	}

	/**
	 *
	 */
	private static String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};

	public static String toPersianNumber(String text) {
		if (text.length() == 0) {
			return "";
		}
		String out = "";
		int length = text.length();
		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);
			if ('0' <= c && c <= '9') {
				int number = Integer.parseInt(String.valueOf(c));
				out += persianNumbers[number];
			} else if (c == '٫') {
				out += '،';
			} else {
				out += c;
			}
		}
		return out;
	}


	/*
	 *
	 */
	public static int convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int px = (int) (dp * (metrics.densityDpi / 160f));
		return px;
	}

	/**
	 *
	 * @return
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return "0";
		}
		return tManager.getDeviceId();
	}

	/**
	 *
	 * @param context
	 * @return system mac address
	 */
	public static String getDeviceMacId(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return requestAddressMAC(wifiManager);
	}


	/**
	 *
	 * @return Deice name
	 */
	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}
	/**
	 *
	 * @return system android version
	 */
	public static String getDeviceAndroidVersion() {
		String s = " lower than 4.0";
		switch (Build.VERSION.SDK_INT){
			case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
				s = "4.0.1 - 4.0.2";
				break;
			case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
				s = "4.1.3 - 4.0.4";
				break;
			case Build.VERSION_CODES.JELLY_BEAN:
				s = "4.1.x";
				break;
			case Build.VERSION_CODES.JELLY_BEAN_MR1:
				s = "4.2.x";
				break;
			case Build.VERSION_CODES.JELLY_BEAN_MR2:
				s = "4.3.x";
				break;
			case Build.VERSION_CODES.KITKAT:
				s = "4.4 - 4.4.4";
				break;
			case Build.VERSION_CODES.LOLLIPOP:
				s = "5.0";
				break;
			case Build.VERSION_CODES.LOLLIPOP_MR1:
				s = "5.1";
				break;
			case Build.VERSION_CODES.M:
				s = "6.0";
				break;
			case Build.VERSION_CODES.N:
				s = "7.0";
				break;
			case Build.VERSION_CODES.N_MR1:
				s = "7.1";
				break;
			case Build.VERSION_CODES.O:
				s = "8";
				break;
			case Build.VERSION_CODES.O_MR1:
				s = "8.1";
				break;
			default:
				s = "P";
		}
		return "Android v" + s;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	/**
	 *
	 */
	/*
	 *
	 */
	public static String stringSeparetor(String str, int order,char ser) {
		float ret = 0;
		String val = "";
		char[] buf = str.toCharArray();
		int len = str.length();
		int j ,k;
		j = k = 0;
		if(str.contains(""+ser)){
			try {
				while (j < len) {
					if (buf[j] == ser) {
						k++;
						j++;
						if(k > order){
							break;
						}
					}
					if(k == order && buf[j] != (char)0x20){
						val += buf[j];
					}
					j++;
				}
				if (!val.isEmpty() && val != "") {
					ret = Float.parseFloat(val);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 *
	 * @param dp
	 * @return
	 */
	public static float dpToPx(float dp) {
		return dp * Resources.getSystem().getDisplayMetrics().density;
	}

	/**
	 *
	 * @param px
	 * @return
	 */
	public static float pxToDp(float px) {
		return px / Resources.getSystem().getDisplayMetrics().density;
	}

	/**
	 *
	 * @param context
	 * @param id
	 * @return
	 */
	public static RoundedBitmapDrawable getRoundedBitmap(Context context, @DrawableRes int id) {
		Resources res = context.getResources();
		Bitmap src = BitmapFactory.decodeResource(res, id);

		int size = Math.max(src.getHeight(), src.getWidth());
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(size/4.0f);
		return dr;
	}

	/**
	 *
	 * @param context
	 * @param src
	 * @return
	 */
	public static RoundedBitmapDrawable getRoundedBitmap(Context context, Bitmap src) {
		Resources res = context.getResources();
		int size = Math.max(src.getHeight(), src.getWidth());
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(size/4.0f);
		return dr;
	}

	/**
	 *
	 * @param context
	 * @param id
	 * @return
	 */
	public static RoundedBitmapDrawable getRoundedBitmap(Context context, int id, float r) {
		Resources res = context.getResources();
		Bitmap src = BitmapFactory.decodeResource(res, id);

		int size = Math.max(src.getHeight(), src.getWidth());
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(size/r);
		return dr;
	}

	/**
	 *
	 * @param context
	 * @param src
	 * @return
	 */
	public static RoundedBitmapDrawable getRoundedBitmap(Context context, Bitmap src, float r) {
		Resources res = context.getResources();
		int size = Math.max(src.getHeight(), src.getWidth());
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(size/r);
		return dr;
	}

	/**
	 *
	 */
	public static Bitmap scaleBitmapDown(Bitmap bitmap, float imageSize,boolean filter) {
		int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
		if(size > imageSize){
			size = (int) imageSize;
		}else{
			size = (int) dpToPx(56);
		}

		Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, size, size, filter);
		return newBitmap;
	}

    /**
     *
     */
	public static Bitmap scaleBitmapDown(String path, float imageSize, boolean filter) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(path, option);
		int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
		if(size > imageSize){
			size = (int) imageSize;
		}else{
			size = (int) dpToPx(56);
		}

		Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, size, size, filter);
		return newBitmap;
	}

	/**
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeigth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		return metrics.heightPixels;
	}
	/**
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		return metrics.widthPixels;
	}

	public static int getScreenHeigth(){
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}

	public static int getScreenWidth(){
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	public static float getScreenHeigthDpi(){
		return Resources.getSystem().getDisplayMetrics().ydpi;
	}

	public static float getScreenWidthDpi(){
		return Resources.getSystem().getDisplayMetrics().xdpi;
	}
	/**
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeigthDp(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		return (int) pxToDp(metrics.heightPixels);
	}
	/**
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidthDp(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		return (int) pxToDp(metrics.widthPixels);
	}

	public static boolean hasHoneyCombApi() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}

	public static boolean hasLollipopApi() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	public static boolean hasLollipopMr1Api() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
	}

	public static boolean hasMarshmallowApi() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
	}

	public static boolean hasOreoApi() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
	}

	public static boolean hasJellyBean() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
	}

	//Android 6.0 : Access to mac address from WifiManager forbidden
	private static final String marshmallowMacAddress = "02:00:00:00:00:00";
	private static final String fileAddressMac = "/sys/class/net/wlan0/address";

	public static String requestAddressMAC(WifiManager wifiMan) {
		WifiInfo wifiInf = wifiMan.getConnectionInfo();

		if(wifiInf.getMacAddress().equals(marshmallowMacAddress)){
			String ret = null;
			try {
				ret= getAdressMacByInterface();
				if (ret != null){
					return ret;
				} else {
					ret = getAddressMacByFile(wifiMan);
					return ret;
				}
			} catch (IOException e) {
				Log.e("MobileAccess", "Error looking Adresse MAC");
			} catch (Exception e) {
				Log.e("MobileAcces", "Error looking Adresse MAC ");
			}
		} else{
			return wifiInf.getMacAddress();
		}
		return marshmallowMacAddress;
	}

	private static String getAdressMacByInterface(){
		try {
			List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface nif : all) {
				if (nif.getName().equalsIgnoreCase("wlan0")) {
					byte[] macBytes = nif.getHardwareAddress();
					if (macBytes == null) {
						return "";
					}

					StringBuilder res1 = new StringBuilder();
					for (byte b : macBytes) {
						res1.append(String.format("%02X:",b));
					}

					if (res1.length() > 0) {
						res1.deleteCharAt(res1.length() - 1);
					}
					return res1.toString();
				}
			}

		} catch (Exception e) {
			Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
		}
		return null;
	}

	private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
		String ret;
		int wifiState = wifiMan.getWifiState();

		wifiMan.setWifiEnabled(true);
		File fl = new File(fileAddressMac);
		FileInputStream fin = new FileInputStream(fl);
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = fin.read()) != -1){
			builder.append((char)ch);
		}

		ret = builder.toString();
		fin.close();

		boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
		wifiMan.setWifiEnabled(enabled);
		return ret;
	}

	public static int getSoftwareVersion(Context context) {
		PackageInfo pInfo;
		int version = 0;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pInfo.versionCode;
			Log.i("Log", "Build Version = " + version);
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}

	public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	public static String getDefaultSmsApp(Context context){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return Telephony.Sms.getDefaultSmsPackage(context);
		}
		return "0";
	}

	public static void setDefaultSmsApp(Context context, Activity activity, String app){
		Intent intent = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			try {
				intent = new Intent(context, Class.forName(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT));
				intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, app);
				activity.startActivity(intent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 */
	public static boolean isImageSizeBigger(Context context, int id, int sX, int sY) {
		Resources res = context.getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, id);

		int min = Math.min(sX,sY);
		int bMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
		if(bMin > min){
			return true;
		}
		return false;
	}

	/**
	 *
	 */
	public static Bitmap createScaledBitmap(Context context, int id, int sX, int sY) {
		Resources res = context.getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, id);

		int min = Math.min(sX,sY);
		int bMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
		if(bMin < min){
			min = bMin;
		}
		return  Bitmap.createScaledBitmap(bitmap, min, min, true);
	}

	/**
	 *
	 */
	public static Bitmap createScaledBitmap(Bitmap bitmap, int sX, int sY){
		int min = Math.min(sX,sY);
		int bMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
		if(bMin < min){
			min = bMin;
		}
		return  Bitmap.createScaledBitmap(bitmap, min, min, true);
	}

	/**
	 *
	 */
	public static byte[] convetIntToByte(final int i) {
		BigInteger bigInt = BigInteger.valueOf(i);
		byte[] bytes = bigInt.toByteArray();
		for(byte b: bytes){
			Log.i("Hex format:  ", String.format("0x%20x", b));
		}
		return bytes;
	}

	/**
	 *
	 */
	public static void setText(TextView textView, String str){
		if(textView != null){
			textView.setText(str);
		}
	}

	/**
	 *
	 */
	public static byte getBit(byte id, int position){
		return (byte) ((id >> position) & 1);
	}

	/**
	 *
	 */
	public static boolean isBitVluable(byte id, int position){
		return ((id >> position) & 1) != 0;
	}

	/**
	 *
	 */
	public static void saveData(Context c, String key, Object value){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
		SharedPreferences.Editor edt = prefs.edit();
		if(value instanceof Boolean){
			edt.putBoolean(key, (Boolean) value);
		}else if (value instanceof  Integer){
			edt.putInt(key, (Integer) value);
		}else if (value instanceof  String){
			edt.putString(key, (String) value);
		}else if (value instanceof  Long){
			edt.putLong(key, (Long) value);
		}else if (value instanceof Float){
			edt.putFloat(key, (Float) value);
		}
		edt.commit();
	}

	public static final int OBJECT_INTEGER = 0;
	public static final int OBJECT_LONG = 1;
	public static final int OBJECT_FLOAT = 2;
	public static final int OBJECT_STRING = 3;
	public static final int OBJECT_BOOLEAN = 4;
	public static Object readData(Context c, String key, int type){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
		switch (type){
			case OBJECT_INTEGER:
				return prefs.getInt(key, 0);
			case OBJECT_LONG:
				return prefs.getLong(key, 0);
			case OBJECT_FLOAT:
				return prefs.getFloat(key, 0);
			case OBJECT_STRING:
				return prefs.getString(key, null);
			case OBJECT_BOOLEAN:
				return prefs.getBoolean(key, false);
		}
		return null;
	}


	/**
	 *
	 */
	public static int getId(String resourceName, Class<?> c) {
		try {
			Field idField = c.getDeclaredField(resourceName);
			return idField.getInt(idField);
		} catch (Exception e) {
			throw new RuntimeException("No resource ID found for: "
					+ resourceName + " / " + c, e);
		}
	}

	/**
	 *
	 */
	public static int getResourceId(String pVariableName, String pResourceName, String pPackageName, Resources res){
		try {
			return res.getIdentifier(pVariableName, pResourceName, pPackageName);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 *
	 */
	public static Intent openBrowser(String url){
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setPackage("com.android.chrome");
		try {
			return i;
		} catch (ActivityNotFoundException e) {
			// Chrome is probably not installed
			// Try with the default browser
			i.setPackage(null);
			return i;
		}
	}

	/**
	 *
	 */
	public static void changeStatusBarColor(Activity activity, int color){
		Window window = activity.getWindow();

		// clear FLAG_TRANSLUCENT_STATUS flag:
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

		// finally change the color
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(color);
		}
	}

	/**
	 *
	 */
	//If the root folder is the asset folder then call it with
	//listAssetFiles("");
	//
	//Call the listAssetFiles with the root folder name of your asset folder.
	//listAssetFiles("root_folder_name_in_assets");
	public static boolean listAssetFiles(Context context, String path) {

		String [] list;
		try {
			list = context.getAssets().list(path);
			if (list.length > 0) {
				// This is a folder
				for (String file : list){
					String pathDir = path + "/" + file;
					if(path.isEmpty()){
						pathDir = file;
					}
					if (!listAssetFiles(context, pathDir))
						return false;
					else {
						// This is a file
						// TODO: add file name to an array list
					}
				}
			}
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public static String isFileExistInAsset(Context context, String path, String fileName) {
		String [] list;
		try {
			list = context.getAssets().list(path);
			if (list.length > 0) {
				// This is a folder
				for (String fileDir : list) {
					String pathDir = path + "/" + fileDir;
					if(path.isEmpty()){
						pathDir = fileDir;
					}

					if (fileDir != null){
						InputStream is = null;
						try {
							is = context.getAssets().open(pathDir + "/" + fileName);
							//File exists so do something with it
						} catch (IOException ex) {
							//file does not exist
							ex.printStackTrace();
							String dir = isFileExistInAsset(context, pathDir, fileName);
							if(dir != null){
								if(!dir.contains(fileName)){
									return dir + "/" + fileName;
								}else{
									return dir;
								}
							}
						}

						if (is != null) {
							is.close();
							return  pathDir;
						}
					}
				}
			}
		} catch (IOException e) {
			return null;
		}

		return null;
	}

	public static String readTextFromClipboard(Context context) {
		ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard.hasPrimaryClip()) {
			ClipDescription description = clipboard.getPrimaryClipDescription();
			ClipData data = clipboard.getPrimaryClip();
			if (data != null && description != null && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
				return String.valueOf(data.getItemAt(0).getText());
		}
		return null;
	}

	public static void copyTextToClipboard(Context context, String text, String label) {
		ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText(label, text);
		clipboard.setPrimaryClip(clip);
	}

	public void copyFromView(View v, String label) {
		if(v != null && v instanceof TextView){
			TextView txtNotes = (TextView) v;
			int startSelection = txtNotes.getSelectionStart();
			int endSelection = txtNotes.getSelectionEnd();
			if ((txtNotes.getText() != null) && (endSelection > startSelection )){
				String selectedText = txtNotes.getText().toString().substring(startSelection, endSelection);
				copyTextToClipboard(v.getContext(), selectedText, label);
			}
		}
	}
}
