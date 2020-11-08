package com.example.mior;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditReminderActivity extends AppCompatActivity {

    ImageView mainImageView;
    EditText title, description,minutes;
    String titleData, descData;
    int imgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        minutes = findViewById(R.id.minText);

        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("Image") && getIntent().hasExtra("Reminder") && getIntent().hasExtra("Description")){

            titleData = getIntent().getStringExtra("Reminder");
            descData = getIntent().getStringExtra("Description");
            imgData = getIntent().getIntExtra("Image", 1);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(titleData);
        description.setText(descData);
        mainImageView.setImageResource(imgData);
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}