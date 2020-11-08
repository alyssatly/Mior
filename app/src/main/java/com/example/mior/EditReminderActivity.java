package com.example.mior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

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
        //if (getIntent().hasExtra("Image") && getIntent().hasExtra("Reminder") && getIntent().hasExtra("Description")){
            titleData = getIntent().getStringExtra(MainActivity.REMINDER_TEXT);
            descData = getIntent().getStringExtra(MainActivity.DES_TEXT);
            imgData = getIntent().getIntExtra(MainActivity.IMG_TEXT, 1);
            //minutes = getIntent().getStringExtra(MainActivity.MIN_TEXT);
//        }else{
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
//        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.REMINDER_TEXT,title.getText().toString());
        intent.putExtra(MainActivity.DES_TEXT,description.getText().toString());
        intent.putExtra(MainActivity.IMG_TEXT,1);
        intent.putExtra(MainActivity.POS_TEXT,getIntent().getExtras().getInt(MainActivity.POS_TEXT));
        setResult(RESULT_OK,intent);
        finish();
        //Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
        return true;
    }
}