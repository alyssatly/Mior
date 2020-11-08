package com.example.mior;

import android.content.Context;
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
    OnClickListener clickListener;

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public recyclerViewAdapter(Context ct, ArrayList<Alert> notifications, OnClickListener clickListener)
    {
        this.notifications = notifications;
        this.clickListener = clickListener;
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

        ArrayList<Integer> minutes = new ArrayList<Integer>();

        for (int i = 0; i < notifications.size(); i++) {
            minutes.add((notifications.get(i).GetInterval())/60);
        }
        holder.bind(remindersData[position],descriptionsData[position],images.get(position),minutes.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reminders_txt, descriptions_txt,minute_txt;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            reminders_txt = itemView.findViewById(R.id.reminders_txt);
            descriptions_txt = itemView.findViewById(R.id.descriptions_txt);
            myImage = itemView.findViewById(R.id.myImgView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //minute_txt = itemView.findViewById(R.id.minText);
        }

        public void bind(String reminder,String descp,int pic,String min){
            reminders_txt.setText(reminder);
            descriptions_txt.setText(descp);
            myImage.setImageResource(pic);
            //SET MINUTES TEXT FIELD
            //minute_txt.setText(min);

            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
