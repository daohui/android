package com.vitech.sandbox;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vitech.sandbox.activies.AddressLocatorActivity;
import com.vitech.sandbox.activies.DrawActivity;
import com.vitech.sandbox.activies.HelloActivity;
import com.vitech.sandbox.activies.MenuDemoActivity;
import com.vitech.sandbox.activies.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {
    private Demo[] demos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demos = new Demo[]{
                new Demo("Hello Demo", HelloActivity.class),
                new Demo("Address Location (Demo to use available services", AddressLocatorActivity.class),
                new Demo("Drawing Demo", DrawActivity.class),
                new Demo("ViewPager Demo", ViewPagerActivity.class),
                new Demo("Menu Demo", MenuDemoActivity.class)
        };

        ListView listView = (ListView) findViewById(R.id.demo_list);
        listView.setAdapter(
                new ArrayAdapter<Demo>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        demos));
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        demos[position].startActivity();
                    }
                }
        );
    }

    private class Demo {
        String name;
        Class<? extends Activity> cls;

        Demo(String name, Class<? extends Activity> cls) {
            this.name = name;
            this.cls = cls;
        }

        void startActivity() {
            MainActivity.this.startActivity(new Intent(MainActivity.this, cls));
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
