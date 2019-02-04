package com.bvtech.widgettest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.bvtech.toolslibrary.Layouts.ExtendCoordinatorLayout;
import com.bvtech.toolslibrary.Layouts.ExtendFrameLayout;
import com.bvtech.toolslibrary.Locale.LocaleManager;
import com.bvtech.toolslibrary.Utility.Utilities;

import java.lang.reflect.Field;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

public class ActivityEnhanced extends AppCompatActivity {

	public Toolbar searchToolbar;
	public Menu search_menu;
	public MenuItem item_search, buttomSearch;
	public EditText edtSeardch;
	public boolean isKeyboardVisible;
	public InputMethodManager inputMethodManager;
	public ExtendCoordinatorLayout activityRootView;
	private int preXPoint, preYPoint;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		try{
			activityRootView = findViewById(R.id.content);
			activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
					new ViewTreeObserver.OnGlobalLayoutListener() {
						@Override
						public void onGlobalLayout() {
							int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
							if (heightDiff > Utilities.dpToPx(200)) { // if more than 200 dp, it's
								// probably a keyboard...
								// ... do something here
								if(!isKeyboardVisible){
									isKeyboardVisible = true;
								}
							} else {
								if(isKeyboardVisible){
									onBackPressed();
								}
							}
						}
					});
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(LocaleManager.setLocale(base));
		Log.d(getPackageName(), "attachBaseContext");
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void circleReveal(int viewID, final boolean isShow){
		final View myView = findViewById(viewID);
		if(myView != null){
			/*int width = Utilities.getScreenWidth();
			int cy = (int) Utilities.dpToPx(56) / 2;
			int cx = width - cy;*/

			int width = Utilities.getScreenWidth();
			int cy = activityRootView.getYPoint();
			int cx = activityRootView.getXPoint();

			if(isShow){
				preXPoint = cx;
				preYPoint = cy;
			}else{
				cx = preXPoint;
				cy = preYPoint;
			}

			Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy,  isShow ? 0 : width, isShow ? width : 0);
			anim.setDuration((long)220);

			// make the view invisible when the animation is done
			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					if(!isShow){
						super.onAnimationEnd(animation);
						myView.setVisibility(View.INVISIBLE);
					}
				}
			});

			// make the view visible and start the animation
			if(isShow)
				myView.setVisibility(View.VISIBLE);

			// start the animation
			anim.start();
		}
	}

	public void initToolbar(){
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				onBackPressed();
			}
		});
		toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
	}

	public void setSearchToolbar(){
		searchToolbar = (Toolbar) findViewById(R.id.searchToolbar);
		if (searchToolbar != null) {
			searchToolbar.inflateMenu(R.menu.menu_search);
			search_menu = searchToolbar.getMenu();

			final Toolbar finalSearchtollbar = searchToolbar;
			searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
	                    circleReveal(R.id.searchToolbar,false);
                    }else {
	                    finalSearchtollbar.setVisibility(View.GONE);
                    }
					//circleReveal(R.id.searchToolbar,false);
					//showSearchView(false);
				}
			});

			item_search = search_menu.findItem(R.id.action_filter_search);
			buttomSearch = search_menu.findItem(R.id.action_search_button);

			final Toolbar finalSearchtollbar1 = searchToolbar;
			MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
				@Override
				public boolean onMenuItemActionCollapse(MenuItem item) {
					// Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
	                    circleReveal(R.id.searchToolbar,false);
                    }else {
                        finalSearchtollbar1.setVisibility(View.GONE);
                    }
					//circleReveal(R.id.searchToolbar,false);
					//showSearchView(false);
					return true;
				}

				@Override
				public boolean onMenuItemActionExpand(MenuItem item) {
					// Do something when expanded
					return true;
				}
			});

			buttomSearch.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem menuItem) {
					String s = edtSeardch.getText().toString();
					if(s != null && !s.isEmpty()){
						callSearchQuery(s);
						//inputMethodManager.showSoftInput(edtSeardch, InputMethodManager.HIDE_NOT_ALWAYS);
					}
					return false;
				}
			});

			initSearchView(search_menu);
		} else
			Log.d("toolbar", "setSearchtollbar: NULL");
	}

	private String searchQuery;
	public void initSearchView(Menu search_menu){
		final SearchView searchView =
				(SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

		// Enable/Disable Submit button in the keyboard
		//searchView.setSubmitButtonEnabled(false);
		edtSeardch = ((EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text));
		edtSeardch.setHint("جستجو..");
		edtSeardch.setFocusableInTouchMode(true);

		// set the cursor
		AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
		try {
			Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
			mCursorDrawableRes.setAccessible(true);
			mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
		} catch (Exception e) {
			e.printStackTrace();
		}

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				callSearch(query);
				searchView.clearFocus();
				callSearchQuery(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				callSearch(newText);
				searchQuery = newText;
				return true;
			}

			public void callSearch(String query) {
				//Do searching
				Log.i("query", "" + query);
			}
		});
	}

	public void callSearchQuery(String query){}
}
