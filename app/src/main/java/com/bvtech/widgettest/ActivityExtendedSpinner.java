package com.bvtech.widgettest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;

import com.bvtech.toolslibrary.Layouts.ExtendCoordinatorLayout;
import com.bvtech.toolslibrary.Locale.LocaleManager;
import com.bvtech.toolslibrary.Widget.ExtendSpinner;

public class ActivityExtendedSpinner extends ActivityEnhanced{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extended_spinner);

		final ExtendCoordinatorLayout layout = findViewById(R.id.content);

		final ExtendSpinner spinner2 = findViewById(R.id.extendSpinner2);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				setSnackBarWithNoActionButton(layout, "Name:" +  spinner2.getItemName(position) + "  ,Entry value:" + spinner2.getEntryValue(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});


		final ExtendSpinner spinner = findViewById(R.id.extendSpinner);
		spinner.setSelectionWithEntries(LocaleManager.getContextLanguage(ActivityExtendedSpinner.this));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				LocaleManager.setNewLocale(ActivityExtendedSpinner.this, spinner.getEntryValue(position));

				Intent intent = new Intent(ActivityExtendedSpinner.this, ActivityMain.class);
				ActivityExtendedSpinner.this.startActivity(intent);
				ActivityExtendedSpinner.this.finish();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});


	}

	public static void setSnackBarWithNoActionButton(View view, String title){
		// Changing message text color
		//snackbar.setActionTextColor(Color.RED);

		Snackbar snackbar = Snackbar
				.make(view, title, Snackbar.LENGTH_LONG);
		snackbar.show();
	}
}
