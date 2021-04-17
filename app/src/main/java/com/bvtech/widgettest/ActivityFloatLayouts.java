package com.bvtech.widgettest;

import android.os.Bundle;

import com.bvtech.toolslibrary.views.FloatLinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityFloatLayouts extends ActivityEnhanced{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_float_layouts);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(ActivityFloatLayouts.this));
		recyclerView.addItemDecoration(new DividerItemDecoration(ActivityFloatLayouts.this, DividerItemDecoration.VERTICAL));

		RecyclerViewAdapter adapter = new RecyclerViewAdapter(ActivityFloatLayouts.this, getResources()
				.getStringArray(R.array.countries), getResources()
				.getStringArray(R.array.country_codes));
		recyclerView.setAdapter(adapter);

		FloatLinearLayout lay = findViewById(R.id.laySwitch);
		lay.attachToRecyclerView(recyclerView);

		final FloatLinearLayout lay2 = findViewById(R.id.laySwitch2);
		lay2.setDefaultAnim(FloatLinearLayout.DOWN, FloatLinearLayout.TH);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (dy > 0 && lay2.isLayoutShow()) {
					lay2.hide();
				} else if (dy < 0 && lay2.isLayoutHide()) {
					lay2.show();
				}
			}
		});
	}
}
