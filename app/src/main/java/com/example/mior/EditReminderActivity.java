package com.example.mior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditReminderActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;

    String titleData, descData;
    int imgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

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
}