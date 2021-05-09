package com.bvtech.widgettest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bvtech.toolslibrary.layouts.ExtendConstraintLayout;
import com.bvtech.toolslibrary.utility.Utilities;
import com.bvtech.toolslibrary.widget.CircularProgressBar;
import com.bvtech.toolslibrary.widget.DrawerPathView;
import com.bvtech.toolslibrary.widget.ExtendEditText;

import androidx.appcompat.widget.AppCompatImageView;

public class ActivityMain extends ActivityEnhanced implements View.OnClickListener{

	ExtendConstraintLayout layMain;
	AppCompatImageView imgTest;
	AppCompatImageView imgTest2;
	ExtendEditText edtTest;
	DrawerPathView drawerPv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initToolbar();
		setSearchToolbar();

		layMain = findViewById(R.id.layMain);
		drawerPv = findViewById(R.id.drawerPv);

		findViewById(R.id.btnExtendSpinner).setOnClickListener(this);
		findViewById(R.id.btnExtendTextView).setOnClickListener(this);
		findViewById(R.id.btnExtendLayouts).setOnClickListener(this);
		findViewById(R.id.btnFloatLayouts).setOnClickListener(this);
		imgTest = findViewById(R.id.imgTest);
		if(Utilities.hasLollipopApi()){
			Drawable drawable = getResources().getDrawable(R.drawable.avd_delete);
			imgTest.setImageDrawable(drawable);
			imgTest.setOnClickListener(this);

			imgTest2 = findViewById(R.id.imgTest2);
			Drawable drawable2 = getResources().getDrawable(R.drawable.avd_edit);
			imgTest2.setImageDrawable(drawable2);
			imgTest2.setOnClickListener(this);
		}

		edtTest = findViewById(R.id.edtTest);
		edtTest.setOnKeyboardEventListener(new ExtendEditText.OnKeyboardEventListener() {
			@Override
			public void onKeyDown(View v) {
				int a = 2;
			}

			@Override
			public void onKeyUp(View v) {
				int c = 3;
			}
		});

		edtTest.setOnTextChangeListener(new ExtendEditText.OnTextChangeListener() {
			@Override
			public void onTextChanged(View view, String str) {
				String a = str;
				int z = 2;
			}
		});

		CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
		// Set Progress
		//circularProgressBar.setProgress(65f);
		// or with animation
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				circularProgressBar.setProgressWithAnimation(65f, (long) 1000); // =1s
				drawerPv.init(layMain.getWidth(), layMain.getHeight());
			}
		}, 2000);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btnExtendSpinner:
				openActivity(ActivityExtendedSpinner.class);
				break;
			case R.id.btnExtendTextView:
				openActivity(ActivityThird.class);
				break;
			case R.id.btnExtendLayouts:
				openActivity(ActivityExtendedLayouts.class);
				break;
			case R.id.btnFloatLayouts:
				openActivity(ActivityFloatLayouts.class);
				break;
			case R.id.imgTest:
				drawerPv.setPathColor(Color.BLUE);
				Drawable drawable = imgTest.getDrawable();
				if (drawable instanceof Animatable) {
					((Animatable) drawable).start();
				}
				break;
			case R.id.imgTest2:
				Drawable drawable2 = imgTest2.getDrawable();
				if (drawable2 instanceof Animatable) {
					((Animatable) drawable2).start();
				}
				break;
		}
	}

	private void openActivity(Class<?> cls){
		Intent intent = new Intent(ActivityMain.this, cls);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.action_search:
				if (Utilities.hasLollipopApi()) {
					circleReveal(R.id.searchToolbar,true);
				}else {
					searchToolbar.setVisibility(View.VISIBLE);
				}
				item_search.expandActionView();
				if(edtSeardch != null){
					edtSeardch.setText("");
					edtSeardch.requestFocus();
					inputMethodManager.showSoftInput(edtSeardch, InputMethodManager.SHOW_IMPLICIT);
				}
				return true;
			case R.id.action_delete:
				Drawable drawable = item.getIcon();
				if (drawable instanceof Animatable) {
					((Animatable) drawable).start();
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		if(isKeyboardVisible){
			isKeyboardVisible = false;
			if (Utilities.hasLollipopApi()) {
				circleReveal(R.id.searchToolbar,false);
			}else {
				searchToolbar.setVisibility(View.INVISIBLE);
			}
		}else {
			super.onBackPressed();
		}
	}
}
