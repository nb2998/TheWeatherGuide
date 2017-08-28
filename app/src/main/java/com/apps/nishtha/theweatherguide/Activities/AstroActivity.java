package com.apps.nishtha.theweatherguide.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apps.nishtha.theweatherguide.R;

public class AstroActivity extends AppCompatActivity {

    TextView textSunrise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro);
        textSunrise= (TextView) findViewById(R.id.textSunrise);
        Intent i=getIntent();
        textSunrise.setText("Sunrise at : " + i.getStringExtra("sunrise"));

    }
}
