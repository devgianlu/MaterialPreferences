package com.yarolegovich.mp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yarolegovich.mp.io.StorageModule;
import com.yarolegovich.mp.io.UserInputModule;

import java.util.Collection;
import java.util.List;

/**
 * Created by yarolegovich on 01.05.2016.
 */
public class MaterialPreferenceScreen extends ScrollView {
    private ViewGroup container;
    private UserInputModule userInputModule;
    private StorageModule storageModule;

    {
        setFillViewport(true);
    }

    public MaterialPreferenceScreen(Context context) {
        super(context);
    }

    public MaterialPreferenceScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialPreferenceScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialPreferenceScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void useGridLayout() {
        this.container = null;
        removeAllViews();

        GridLayout container = new GridLayout(getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setOrientation(GridLayout.VERTICAL);
        container.setColumnCount(2);
        addView(container);
        this.container = container;
    }

    public void useLinearLayout() {
        this.container = null;
        removeAllViews();

        LinearLayout container = new LinearLayout(getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setOrientation(LinearLayout.VERTICAL);
        addView(container);
        this.container = container;
    }

    public void changeViewsVisibility(List<Integer> viewIds, boolean visible) {
        int visibility = visible ? VISIBLE : GONE;
        changeViewsVisibility(container, viewIds, visibility);
    }

    public void setVisibilityController(int controllerId, List<Integer> controlledIds, boolean showWhenChecked) {
        setVisibilityController(findViewById(controllerId), controlledIds, showWhenChecked);
    }

    public void setVisibilityController(AbsMaterialCheckablePreference controller, AbsMaterialPreference[] controlled, boolean showWhenChecked) {
        boolean shouldShow = showWhenChecked ? controller.getValue() : !controller.getValue();
        int initialVisibility = shouldShow ? View.VISIBLE : View.GONE;
        for (AbsMaterialPreference c : controlled)
            c.setVisibility(initialVisibility);

        controller.addPreferenceValueListener(value -> {
            boolean shouldShow1 = showWhenChecked ? value : !value;
            int visibility = shouldShow1 ? View.VISIBLE : View.GONE;
            for (AbsMaterialPreference c : controlled)
                c.setVisibility(visibility);
        });
    }

    public void setVisibilityController(AbsMaterialCheckablePreference controller, List<Integer> controlledIds, boolean showWhenChecked) {
        boolean shouldShow = showWhenChecked ? controller.getValue() : !controller.getValue();
        int initialVisibility = shouldShow ? View.VISIBLE : View.GONE;
        changeViewsVisibility(this, controlledIds, initialVisibility);
        controller.addPreferenceValueListener(value -> {
            boolean shouldShow1 = showWhenChecked ? value : !value;
            int visibility = shouldShow1 ? View.VISIBLE : View.GONE;
            changeViewsVisibility(this, controlledIds, visibility);
        });
    }

    private void changeViewsVisibility(ViewGroup container, Collection<Integer> viewIds, int visibility) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);
            if (child instanceof ViewGroup) {
                if (viewIds.contains(child.getId())) {
                    child.setVisibility(visibility);
                } else if (!(child instanceof AbsMaterialPreference)) {
                    changeViewsVisibility((ViewGroup) child, viewIds, visibility);
                }
            }

            if (viewIds.contains(child.getId())) {
                child.setVisibility(visibility);
            }
        }
    }

    UserInputModule getUserInputModule() {
        return userInputModule;
    }

    public void setUserInputModule(UserInputModule userInputModule) {
        this.userInputModule = userInputModule;
        setUserInputModuleRecursively(container, userInputModule);
    }

    StorageModule getStorageModule() {
        return storageModule;
    }

    public void setStorageModule(StorageModule storageModule) {
        this.storageModule = storageModule;
        setStorageModuleRecursively(container, storageModule);
    }

    private void setUserInputModuleRecursively(ViewGroup container, UserInputModule module) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);
            if (child instanceof AbsMaterialPreference) {
                ((AbsMaterialPreference) child).setUserInputModule(module);
            } else if (child instanceof ViewGroup) {
                setUserInputModuleRecursively((ViewGroup) child, module);
            }
        }
    }

    private void setStorageModuleRecursively(ViewGroup container, StorageModule module) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);
            if (child instanceof AbsMaterialPreference) {
                ((AbsMaterialPreference) child).setStorageModule(module);
            } else if (child instanceof ViewGroup) {
                setStorageModuleRecursively((ViewGroup) child, module);
            }
        }
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
