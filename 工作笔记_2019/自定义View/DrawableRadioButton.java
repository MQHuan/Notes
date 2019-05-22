package com.kye.view.radiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.kye.view.R;

/**
 * Routing
 * Desc 可设定四周drawable...大小
 * Source
 * Created by zsl on 2018/12/26 9:50.
 * Modify by zsl on 2018/12/26 9:50.
 * Version 1.0
 */
public class DrawableRadioButton extends AppCompatRadioButton {

    private int mDrawableWidth;
    private int mDrawableHeight;

    public DrawableRadioButton(Context context) {
        this(context, null);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableRadioButton);
            mDrawableWidth = (int)t.getDimension(R.styleable.DrawableRadioButton_drawableWidth, 0);
            mDrawableHeight = (int)t.getDimension(R.styleable.DrawableRadioButton_drawableHeight,
                                                  0);
            t.recycle();
            setClickable(true);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final Drawable[] compoundDrawables = getCompoundDrawables();
        Rect rect = new Rect();
        rect.set(0, 0, mDrawableWidth, mDrawableHeight);

        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(rect);
            }
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1],
                             compoundDrawables[2], compoundDrawables[3]);
    }

}
