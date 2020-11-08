package com.example.mior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Button btnAddAlert;
    //RecyclerView recyclerView;
    Game LeagueOfLegends;
    public static final int CREATE_REMINDER_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddAlert = findViewById(R.id.btnAddAlert);
        LeagueOfLegends = new Game("League", 9999999, 2100, R.drawable.blue_mascot);

        LeagueOfLegends.AddNotification("Stretch", "Reminder to stand up and stretch!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
        LeagueOfLegends.AddNotification("Eat", "Reminder to eat!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerViewAdapter myAdapter = new recyclerViewAdapter(this, LeagueOfLegends.GetNotifications());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        btnAddAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bring it to another screen that allows you to create
//                LeagueOfLegends.AddNotification("Drink Water", "Reminder to drink some water!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
//                myAdapter.notifyItemInserted(LeagueOfLegends.GetNotifications().size() - 1);
                Intent i = new Intent(MainActivity.this,CreateReminderActivity.class);
                startActivityForResult(i,CREATE_REMINDER_CODE);
            }
        });
    }
}