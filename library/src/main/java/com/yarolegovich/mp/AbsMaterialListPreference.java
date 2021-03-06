package com.yarolegovich.mp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.yarolegovich.mp.io.StorageModule;
import com.yarolegovich.mp.io.UserInputModule;

import static com.yarolegovich.mp.R.styleable.AbsMaterialListPreference;
import static com.yarolegovich.mp.R.styleable.AbsMaterialListPreference_mp_entry_descriptions;
import static com.yarolegovich.mp.R.styleable.AbsMaterialListPreference_mp_entry_values;

/**
 * Created by yarolegovich on 06.05.2016.
 */
abstract class AbsMaterialListPreference<T> extends AbsMaterialTextValuePreference<T> {
    protected CharSequence[] entries;
    protected CharSequence[] entryValues;

    public AbsMaterialListPreference(Context context, String defaultValue, String key, UserInputModule userInputModule, StorageModule storageModule, @ShowValueMode int showValueMode, CharSequence[] entries, CharSequence[] entryValues) {
        super(context, defaultValue, key, userInputModule, storageModule, showValueMode);
        this.entries = entries;
        this.entryValues = entryValues;
    }

    public AbsMaterialListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsMaterialListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbsMaterialListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onCollectAttributes(@NonNull AttributeSet attrs) {
        super.onCollectAttributes(attrs);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, AbsMaterialListPreference);
        try {
            if (ta.hasValue(AbsMaterialListPreference_mp_entry_descriptions)) {
                entries = ta.getTextArray(AbsMaterialListPreference_mp_entry_descriptions);
            }

            if (ta.hasValue(AbsMaterialListPreference_mp_entry_values)) {
                entryValues = ta.getTextArray(AbsMaterialListPreference_mp_entry_values);
            }
        } finally {
            ta.recycle();
        }

        if (entries == null || entryValues == null) {
            if (entries != null) {
                entryValues = entries;
            } else if (entryValues != null) {
                entries = entryValues;
            } else {
                throw new AssertionError("You must provide mp_entry_values or mp_entry_descriptions attribute or both in your XML layout");
            }
        }
    }
}
