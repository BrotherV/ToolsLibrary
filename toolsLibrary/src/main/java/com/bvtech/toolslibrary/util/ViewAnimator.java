package com.bvtech.toolslibrary.util;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.animation.FlipAnimation;
import com.bvtech.toolslibrary.interfaces.Resourceble;
import com.bvtech.toolslibrary.interfaces.ScreenShotable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Konstantin on 12.01.2015.
 */
public class ViewAnimator<T extends Resourceble> {

    private final int ANIMATION_DURATION = 150;
    private AppCompatActivity appCompatActivity;
    private List<T> list;
    private List<View> viewList = new ArrayList<>();
    private ScreenShotable screenShotable;
    private DrawerLayout drawerLayout;
    private ViewAnimatorListener animatorListener;
    private boolean isRightToLeft;
    private int menuHeight, menuWidth;

    public ViewAnimator(AppCompatActivity activity,
                        List<T> items,
                        ScreenShotable screenShotable,
                        final DrawerLayout drawerLayout,
                        ViewAnimatorListener animatorListener) {
        this.appCompatActivity = activity;

        this.list = items;
        this.screenShotable = screenShotable;
        this.drawerLayout = drawerLayout;
        this.animatorListener = animatorListener;
        isRightToLeft = appCompatActivity.getResources().getBoolean(R.bool.sml_is_right_to_left);
    }

    public void showMenuContent() {
        setViewsClickable(false);
        viewList.clear();
        double size = list.size();
        for (int i = 0; i < size; i++) {
            View viewMenu = appCompatActivity.getLayoutInflater().inflate(R.layout.tools_library_menu_list_item, null);

            menuHeight = list.get(i).getMenuHeight();
            menuWidth = list.get(i).getMenuWidth();
            LinearLayout l = viewMenu.findViewById(R.id.menu_item_container);
            l.setBackgroundColor(list.get(i).getBackgroundColor());
            if(menuHeight != 0){
                changeViewHeight(l, menuHeight);
            }
            if(menuWidth != 0){
                changeViewWidth(l, menuWidth);
            }

            final int finalI = i;
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int[] location = {0, 0};
                    v.getLocationOnScreen(location);
                    switchItem(list.get(finalI), location[1] + v.getHeight() / 2);
                }
            });

            ((ImageView) viewMenu.findViewById(R.id.menu_item_image)).setImageResource(list.get(i).getImageRes());
            ((TextView) viewMenu.findViewById(R.id.menu_item_text)).setTextColor(list.get(i).getTextColor());
            ((TextView) viewMenu.findViewById(R.id.menu_item_text)).setText(list.get(i).getName());
            ((ImageView) viewMenu.findViewById(R.id.menu_item_image)).setColorFilter(list.get(i).getImageColor(),PorterDuff.Mode.SRC_ATOP);
            ((View) viewMenu.findViewById(R.id.menu_item_view)).setBackgroundColor(list.get(i).getImageColor());
            Typeface tf = list.get(i).getTypeface();
            if(tf != null){
                ((TextView) viewMenu.findViewById(R.id.menu_item_text)).setTypeface(tf);
            }
            viewMenu.setVisibility(View.GONE);
            viewMenu.setEnabled(false);
            viewList.add(viewMenu);
            animatorListener.addViewToContainer(viewMenu);
            final double position = i;
            final double delay = 3 * ANIMATION_DURATION * (position / size);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < viewList.size()) {
                        animateView((int) position);
                    }
                    if (position == viewList.size() - 1) {
                        screenShotable.takeScreenShot();
                        setViewsClickable(true);
                    }
                }
            }, (long) delay);
        }

    }

    private void hideMenuContent() {
        setViewsClickable(false);
        double size = list.size();
        for (int i = list.size(); i >= 0; i--) {
            final double position = i;
            final double delay = 3 * ANIMATION_DURATION * (position / size);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < viewList.size()) {
                        animateHideView((int) position);
                    }
                }
            }, (long) delay);
        }

    }

    private void setViewsClickable(boolean clickable) {
        animatorListener.disableHomeButton();
        for (View view : viewList) {
            view.setEnabled(clickable);
        }
    }

    private void animateView(int position) {
        final View view = viewList.get(position);
        view.setVisibility(View.VISIBLE);
        FlipAnimation rotation = new FlipAnimation(isRightToLeft ? 270 : 90, isRightToLeft ? 360 : 0, isRightToLeft ? menuWidth: 0, view.getHeight() / 2f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(rotation);
    }

    private void animateHideView(final int position) {
        final View view = viewList.get(position);
        FlipAnimation rotation = new FlipAnimation(isRightToLeft ? 360 : 0, isRightToLeft ? 270 : 90, isRightToLeft ? menuWidth : 0, view.getHeight() / 2f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
                if (position == viewList.size() - 1) {
                    animatorListener.enableHomeButton();
                    drawerLayout.closeDrawers();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(rotation);
    }

    private void switchItem(Resourceble slideMenuItem, int topPosition) {
        this.screenShotable = animatorListener.onSwitch(slideMenuItem, screenShotable, topPosition);
        hideMenuContent();
    }

    public interface ViewAnimatorListener {

        public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position);

        public void disableHomeButton();

        public void enableHomeButton();

        public void addViewToContainer(View view);

    }

    private void changeViewHeight(View l, int size){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l.getLayoutParams();
        params.height = size;
        l.setLayoutParams(params);
    }

    private void changeViewWidth(View l, int size){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l.getLayoutParams();
        params.width = size;
        l.setLayoutParams(params);
    }
}
