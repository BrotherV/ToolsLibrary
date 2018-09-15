package com.bvtech.widgettest;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.bvtech.toolslibrary.Locale.LocaleManager;

public class G extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LocaleManager.setLocale(getApplicationContext());
		Log.d(getPackageName(), "onConfigurationChanged: " + newConfig.locale.getLanguage());
	}
}
