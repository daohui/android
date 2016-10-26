package com.vitech.sandbox.activies;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vitech.sandbox.R;

public class MenuDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_demo);

        setSupportActionBar((Toolbar) findViewById(R.id.menu_toolbar_id));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Up button enabled
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);

        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;  // to expand the action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true; // to collapse the action view
            }
        };

        MenuItem actionMenuItem = menu.findItem(R.id.action_settings);
        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);

        return true;
    }
}
