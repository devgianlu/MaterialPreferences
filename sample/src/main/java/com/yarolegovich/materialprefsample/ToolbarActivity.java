package com.yarolegovich.materialprefsample;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by yarolegovich on 16.05.2016.
 */
public class ToolbarActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
