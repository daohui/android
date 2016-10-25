package com.vitech.sandbox;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddressLocatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_locator);

        final EditText et = (EditText) findViewById(R.id.ad__editText);
        Button button = ((Button) findViewById(R.id.ad__findLocation));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et.getText().toString();
                String uri_string = "geo:0.0?q=" + input;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri_string));
                PackageManager pm = getPackageManager();
                List activities = pm.queryIntentActivities(intent, 0);
                if (activities != null && activities.size() > 0) {
                    startActivity(intent);
                }
            }
        });
    }
}
