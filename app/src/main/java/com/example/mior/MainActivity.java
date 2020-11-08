package com.example.mior;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Game LeagueOfLegends = new Game("League", 9999999, 2100, R.drawable.blue_mascot);

    String reminders[], descriptions[];
    ArrayList<Integer> images; // = {images for each};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeagueOfLegends.AddNotification("Drink Water", "Reminder to drink some water!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
        LeagueOfLegends.AddNotification("Stretch", "Reminder to stand up and stretch!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
        LeagueOfLegends.AddNotification("Eat", "Reminder to eat!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);

        recyclerView = findViewById(R.id.recyclerView);

        reminders = LeagueOfLegends.DisplayNotifications().toArray(new String[0]);
        descriptions = LeagueOfLegends.DisplayDescriptions().toArray(new String[0]);
        images = LeagueOfLegends.DisplayImages();

        recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, reminders, descriptions, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}