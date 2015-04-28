package br.ufrn.jardim.adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by wanderson on 25/04/15.
 */
public class NavigationItem {

    private String mText;
    private Drawable mDrawable;

    public NavigationItem(String text, Drawable drawable) {
        mText = text;
        mDrawable = drawable;
    }


    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }
}
