package com.bvtech.widgettest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bvtech.toolslibrary.Layouts.ExtendCoordinatorLayout;
import com.bvtech.toolslibrary.Locale.LocaleManager;
import com.bvtech.toolslibrary.Widget.ExtendSpinner;
import com.bvtech.toolslibrary.Widget.ExtendToast;
import com.google.android.material.snackbar.Snackbar;

public class ActivityExtendedSpinner extends ActivityEnhanced{

	private boolean isActivityLinched;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extended_spinner);

		final ExtendCoordinatorLayout layout = findViewById(R.id.content);

		String[] cn = getResources().getStringArray(R.array.countries);
		String[] cc = getResources().getStringArray(R.array.country_codes);

		final ExtendSpinner spinner3 = findViewById(R.id.extendSpinner3);
		final ExtendSpinner spinner2 = findViewById(R.id.extendSpinner2);
		final ExtendSpinner spinner = findViewById(R.id.extendSpinner);

		spinner3.fillSpinner(cn, cc);
		AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				switch (view.getId()){
					case R.id.extendSpinner2:
						String s = "Name:" +  spinner2.getItemName(position) + "  ,Entry value:" + spinner2.getEntryValue(position)
								+ "  ,Selected item position:" + spinner2.getSelectedItemPosition();
						setSnackBarWithNoActionButton(layout, s);
						ExtendToast.toastNotify(ActivityExtendedSpinner.this, s, Gravity.CENTER).show();
						break;
					case R.id.extendSpinner:
						if(isActivityLinched){
							ExtendToast.toastDone(ActivityExtendedSpinner.this, spinner.getEntryValue(position)).show();
							LocaleManager.setNewLocale(ActivityExtendedSpinner.this, spinner.getEntryValue(position));

							Intent intent = new Intent(ActivityExtendedSpinner.this, ActivityMain.class);
							ActivityExtendedSpinner.this.startActivity(intent);
							ActivityExtendedSpinner.this.finishAffinity();
						}else{
							isActivityLinched = true;
						}
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		};

		spinner2.setOnItemSelectedListener(listener);
		spinner.setSelectionWithEntries(LocaleManager.getContextLanguage(ActivityExtendedSpinner.this));
		spinner.setOnItemSelectedListener(listener);
	}

	public static void setSnackBarWithNoActionButton(View view, String title){
		// Changing message text color
		//snackbar.setActionTextColor(Color.RED);

		Snackbar snackbar = Snackbar
				.make(view, title, Snackbar.LENGTH_LONG);
		snackbar.show();
	}
}
