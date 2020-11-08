package com.example.mior;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    ArrayList<Alert> notifications;
    String remindersData[], descriptionsData[];
    ArrayList<Integer> images;
    Context context;


    public recyclerViewAdapter(Context ct, ArrayList<Alert> notifications)
    {
        this.notifications = notifications;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { // sets the item shown in position to its values
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < notifications.size(); i++) {
            temp.add(notifications.get(i).GetName());
        }
        remindersData = temp.toArray(new String[0]);

        temp = new ArrayList<String>();
        for (int i = 0; i < notifications.size(); i++){
            temp.add(notifications.get(i).GetMessage());
        }
        descriptionsData = temp.toArray(new String[0]);

        images = new ArrayList<Integer>();

        for (int i = 0; i < notifications.size(); i++) {
            images.add(notifications.get(i).GetImage());
        }
        holder.reminders_txt.setText(remindersData[position]);
        holder.descriptions_txt.setText(descriptionsData[position]);
        holder.myImage.setImageResource(images.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditReminderActivity.class);
                intent.putExtra("Reminder", remindersData[position]);
                intent.putExtra("Description", descriptionsData[position]);
                intent.putExtra("Image", images.get(position));
                System.out.println("HI");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reminders_txt, descriptions_txt;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            reminders_txt = itemView.findViewById(R.id.reminders_txt);
            descriptions_txt = itemView.findViewById(R.id.descriptions_txt);
            myImage = itemView.findViewById(R.id.myImgView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
