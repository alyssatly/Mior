package com.example.mior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateReminderActivity extends AppCompatActivity {

    ImageView mainImageView;
    EditText title, description,minutes;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Reminder");

        mainImageView = findViewById(R.id.imageView);
        title = findViewById(R.id.notifTitle);
        description = findViewById(R.id.descriptionText);
        minutes = findViewById(R.id.minuteText);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.TITLE_TEXT,title.getText().toString());
                intent.putExtra(MainActivity.DESCP_TEXT,description.getText().toString());
                intent.putExtra(MainActivity.MINUTE_TEXT,minutes.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}