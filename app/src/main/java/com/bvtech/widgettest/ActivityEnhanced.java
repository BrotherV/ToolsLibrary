package com.bvtech.widgettest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bvtech.toolslibrary.Locale.LocaleManager;

public class ActivityEnhanced extends AppCompatActivity {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(LocaleManager.setLocale(base));
		Log.d(getPackageName(), "attachBaseContext");
	}

}
