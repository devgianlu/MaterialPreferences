package com.yarolegovich.mp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import com.yarolegovich.mp.io.StorageModule;
import com.yarolegovich.mp.io.UserInputModule;

/**
 * Created by yarolegovich on 04.05.2016.
 */
public abstract class AbsMaterialCheckablePreference extends AbsMaterialPreference<Boolean> implements
        View.OnClickListener {

    protected Checkable checkableWidget;

    public AbsMaterialCheckablePreference(Context context, boolean defaultValue, String key, UserInputModule userInputModule, StorageModule storageModule) {
        super(context, Boolean.toString(defaultValue), key, userInputModule, storageModule);
    }

    public AbsMaterialCheckablePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsMaterialCheckablePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbsMaterialCheckablePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onViewCreated() {
        checkableWidget = findViewById(R.id.mp_checkable);
        boolean isChecked = getValue();
        checkableWidget.setChecked(isChecked);
        addPreferenceClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean newValue = !getValue();
        checkableWidget.setChecked(newValue);
        setValue(newValue);
    }

    @Override
    public Boolean getValue() {
        return storageModule.getBoolean(key, Boolean.parseBoolean(defaultValue));
    }

    @Override
    public void setStorageModule(StorageModule storageModule) {
        super.setStorageModule(storageModule);
        checkableWidget.setChecked(getValue());
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
        storageModule.saveBoolean(key, value);
    }
}
