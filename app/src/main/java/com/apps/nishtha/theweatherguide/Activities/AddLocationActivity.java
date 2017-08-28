package com.apps.nishtha.theweatherguide.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apps.nishtha.theweatherguide.R;

public class AddLocationActivity extends AppCompatActivity {

    EditText etEnter;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        etEnter= (EditText) findViewById(R.id.etEnter);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                i.putExtra("newLocation",etEnter.getText().toString());
                Log.d("TAG", "onClick: "+i.getStringExtra("newLocation"));
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}
