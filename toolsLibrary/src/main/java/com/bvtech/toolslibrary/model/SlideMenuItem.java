package com.bvtech.toolslibrary.model;

import android.graphics.Typeface;

import com.bvtech.toolslibrary.interfaces.Resourceble;

/**
 * Created by Konstantin on 23.12.2014.
 */
public class SlideMenuItem implements Resourceble {
    private int index;
    private Typeface typeface;
    private String name;
    private int imageRes;
    private int menuHeight;
    private int menuWidth;
    private int backgroundColor;
    private int imageColor;
    private int textColor;

    public SlideMenuItem(String name, int index, int imageRes, int menuHeight, int menuWidth, int backgroundColor, int imageColor, int textColor, Typeface typeface) {
        this.name = name;
        this.index = index;
        this.imageRes = imageRes;
        this.backgroundColor = backgroundColor;
        this.imageColor = imageColor;
        this.textColor = textColor;
        this.menuHeight = menuHeight;
        this.menuWidth = menuWidth;
        this.typeface = typeface;
    }

    public String getName() {
        return name;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public int getTextColor() {
        return textColor;
    }

    @Override
    public int getImageColor() {
        return imageColor;
    }

    @Override
    public int getMenuHeight() {
        return menuHeight;
    }

    @Override
    public int getMenuWidth() {
        return menuWidth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getIndex() {
        return index;
    }
}
