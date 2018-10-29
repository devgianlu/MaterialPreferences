package com.yarolegovich.mp;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

/**
 * Created by yarolegovich on 01.05.2016.
 */
public class MaterialPreferenceCategory extends CardView {
    private LinearLayout container;
    private TextView title;

    public MaterialPreferenceCategory(Context context) {
        super(context);
        init(null);
    }

    public MaterialPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MaterialPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int titleColor = -1;
        String titleText = "";
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MaterialPreferenceCategory);
            try {
                if (ta.hasValue(R.styleable.MaterialPreferenceCategory_mpc_title)) {
                    titleText = ta.getString(R.styleable.MaterialPreferenceCategory_mpc_title);
                }
                titleColor = ta.getColor(R.styleable.MaterialPreferenceCategory_mpc_title_color, -1);
            } finally {
                ta.recycle();
            }
        }

        inflate(getContext(), R.layout.view_preference_category, this);
        setUseCompatPadding(true);
        setRadius(0);

        container = findViewById(R.id.mpc_container);
        title = findViewById(R.id.mpc_title);

        if (!TextUtils.isEmpty(titleText)) {
            title.setVisibility(View.VISIBLE);
            title.setText(titleText);
        }

        if (titleColor != -1) {
            title.setTextColor(titleColor);
        }
    }

    public void setTitle(@StringRes int titleRes) {
        title.setVisibility(View.VISIBLE);
        title.setText(titleRes);
    }

    public void setTitle(String titleText) {
        title.setVisibility(View.VISIBLE);
        title.setText(titleText);
    }

    public void setTitleColor(@ColorInt int color) {
        title.setTextColor(color);
    }

    public void setTitleColorRes(@ColorRes int colorRes) {
        title.setTextColor(ContextCompat.getColor(getContext(), colorRes));
    }

    @Override
    public void addView(View child) {
        if (container != null) {
            container.addView(child);
        } else {
            super.addView(child);
        }
    }

    @Override
    public void addView(View child, int index) {
        if (container != null) {
            container.addView(child, index);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (container != null) {
            container.addView(child, params);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (container != null) {
            container.addView(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }
}
