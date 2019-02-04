package com.bvtech.widgettest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bvtech.toolslibrary.FloatViews.FloatLinearLayout;
import com.bvtech.toolslibrary.Layouts.ExtendCoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.SwitchCompat;

public class ActivityExtendedLayouts extends ActivityEnhanced{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extended_layouts);

		final SwitchCompat swich = findViewById(R.id.switchTest);
		swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				swich.setText(b ? "On" : " Off");
			}
		});

		final ExtendCoordinatorLayout layout = findViewById(R.id.content);
		final FloatLinearLayout lay = findViewById(R.id.laySwitch);
		layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				((TextView) findViewById(R.id.txtTouch)).setText("X: " + layout.getXPoint()+
						",  Y: " + layout.getYPoint());
				setSnackBarWithNoActionButton(layout, "X: " + layout.getXPoint()+
						",  Y: " + layout.getYPoint());
				if(lay.isLayoutShow()){
					lay.hide();
				}else if(lay.isLayoutHide()){
					lay.show();
				}
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
