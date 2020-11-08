package com.example.mior;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String reminders[], descriptions[];
    int images[] = {R.drawable.blue_mascot, R.drawable.blue_mascot, R.drawable.blue_mascot}; // = {images for each};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        reminders = getResources().getStringArray(R.array.Reminders);
        descriptions = getResources().getStringArray(R.array.Descriptions);

        recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, reminders, descriptions, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}