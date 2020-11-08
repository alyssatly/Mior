package com.example.mior;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Button btnAddAlert;
    RecyclerView recyclerView;
    recyclerViewAdapter myAdapter;
    Game LeagueOfLegends;
    public static final String TITLE_TEXT = "title_text";
    public static final String DESCP_TEXT = "descp_text";
    public static final String MINUTE_TEXT = "minute_text";
    public static final String REMINDER_TEXT = "reminder";
    public static final String DES_TEXT = "description";
    public static final String IMG_TEXT = "image";
    public static final String MIN_TEXT = "mins";
    public static final String POS_TEXT = "pos";
    public static final int CREATE_REMINDER_CODE = 20;
    public static final int EDIT_REMINDER_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddAlert = findViewById(R.id.btnAddAlert);
        LeagueOfLegends = new Game("League", 9999999, 2100, R.drawable.blue_mascot);

        LeagueOfLegends.AddNotification("Stretch", "Reminder to stand up and stretch!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
        LeagueOfLegends.AddNotification("Eat", "Reminder to eat!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);

        recyclerViewAdapter.OnClickListener onClickListener = new recyclerViewAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(MainActivity.this, EditReminderActivity.class);
                intent.putExtra(REMINDER_TEXT, LeagueOfLegends.GetNotifications().get(position).GetName());
                intent.putExtra(DES_TEXT, LeagueOfLegends.GetNotifications().get(position).GetMessage());
                intent.putExtra(IMG_TEXT, LeagueOfLegends.GetNotifications().get(position).GetImage());
                intent.putExtra(POS_TEXT,position);
                //intent.putExtra(MIN_TEXT,LeagueOfLegends.GetNotifications().get(position).GetInterval() * 60);
                startActivityForResult(intent,EDIT_REMINDER_CODE);
            }
        };
        recyclerView = findViewById(R.id.recyclerView);

        myAdapter = new recyclerViewAdapter(this, LeagueOfLegends.GetNotifications(),onClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        btnAddAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bring it to another screen that allows you to create
//                LeagueOfLegends.AddNotification("Drink Water", "Reminder to drink some water!", "custom", 15, false, 0, 0, R.drawable.blue_mascot);
//                myAdapter.notifyItemInserted(LeagueOfLegends.GetNotifications().size() - 1);
                Intent i = new Intent(MainActivity.this,CreateReminderActivity.class);
                i.putExtra(TITLE_TEXT,"");
                i.putExtra(DESCP_TEXT,"");
                i.putExtra(MINUTE_TEXT,"");
                startActivityForResult(i,CREATE_REMINDER_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CREATE_REMINDER_CODE) {
            String titleText = data.getStringExtra(TITLE_TEXT);
            String descpText = data.getStringExtra(DESCP_TEXT);
            String minuteText = data.getStringExtra(MINUTE_TEXT);
            LeagueOfLegends.AddNotification(titleText, descpText, "custom", Integer.parseInt(minuteText) * 60, false, 0, 0, R.drawable.blue_mascot);
            myAdapter.notifyItemInserted(LeagueOfLegends.GetNotifications().size() - 1);
        } else if(resultCode == RESULT_OK && requestCode == EDIT_REMINDER_CODE){
            String titleText = data.getStringExtra(REMINDER_TEXT);
            String descpText = data.getStringExtra(DES_TEXT);
            //String minuteText = data.getStringExtra(MIN_TEXT);
            int position = data.getExtras().getInt(POS_TEXT);
            Alert alert = LeagueOfLegends.GetNotifications().get(position);
            alert.Edit(titleText, descpText, "custom", 15, false, 0, 0, R.drawable.blue_mascot);
            myAdapter.notifyDataSetChanged();
        }else {
            Log.w("MainActtivity", "unknown call");
        }
    }
}