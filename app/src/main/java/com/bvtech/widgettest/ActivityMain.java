package com.bvtech.widgettest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityMain extends ActivityEnhanced implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btnExtendSpinner).setOnClickListener(this);
		findViewById(R.id.btnExtendTextView).setOnClickListener(this);
		findViewById(R.id.btnExtendLayouts).setOnClickListener(this);
		findViewById(R.id.btnFloatLayouts).setOnClickListener(this);
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
		}
	}

	private void openActivity(Class<?> cls){
		Intent intent = new Intent(ActivityMain.this, cls);
		startActivity(intent);
	}
}
