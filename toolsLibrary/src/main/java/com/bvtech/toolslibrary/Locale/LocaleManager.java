package com.bvtech.toolslibrary.locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleManager {

    public static final String LANGUAGE_ENGLISH   = "en";
    public static final String LANGUAGE_PERSIAN   = "fa";
    public static final String LANGUAGE_ARABIC   = "ar";
    public static final String LANGUAGE_FRANCE   = "fr";
    public static final String LANGUAGE_GERMAN   = "de";
    public static final String LANGUAGE_AUSTRALIA   = "au";
    public static final String LANGUAGE_AUSTRIA   = "at";
    public static final String LANGUAGE_BRAZIL   = "br";
    public static final String LANGUAGE_CANADA   = "ca";
    public static final String LANGUAGE_CHILIE   = "cl";
    public static final String LANGUAGE_CHINA   = "cn";
    public static final String LANGUAGE_CROATIA   = "hr";
    public static final String LANGUAGE_CzechREPUBLIC   = "cz";
    public static final String LANGUAGE_DENMARK   = "dk";
    public static final String LANGUAGE_ESTONIA   = "ee";
    public static final String LANGUAGE_FINLAND   = "fi";
    public static final String LANGUAGE_GREECE  = "gr";
    public static final String LANGUAGE_HUNGARY   = "hu";
    public static final String LANGUAGE_ISLAND   = "is";
    public static final String LANGUAGE_INDIA   = "in";
    public static final String LANGUAGE_IRLAND   = "ie";
    public static final String LANGUAGE_ITALY   = "it";
    public static final String LANGUAGE_LATVIA   = "lv";
    public static final String LANGUAGE_LITHUANIA   = "lt";
    public static final String LANGUAGE_NETHERLAND  = "nl";
    public static final String LANGUAGE_NewZEALAND   = "nz";
    public static final String LANGUAGE_NORWAY   = "no";
    public static final String LANGUAGE_SWEDEN   = "se";
    public static final String LANGUAGE_SWITZERLAND   = "ch";
    public static final String LANGUAGE_UKRAIN   = "ua";
    public static final String LANGUAGE_US   = "us";
    public static final String LANGUAGE_RUSSIA   = "ru";

    private static final String LANGUAGE_KEY       = "LANGUAGE_KEY";

    public static Context setLocale(Context c) {
        return updateResources(c, getLanguage(c));
    }

    public static Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH);
    }

    @SuppressLint("ApplySharedPref")
    private static void persistLanguage(Context c, String language) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        prefs.edit().putString(LANGUAGE_KEY, language).commit();
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }

    public static String getContextLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0).getLanguage() : config.locale.getLanguage();
    }
}