package com.example.mior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    String remindersData[], descriptionsData[];
    int images[];
    Context context;

    public recyclerViewAdapter(Context ct, String s1[], String s2[], int img[])
    {
        context = ct;
        remindersData = s1;
        descriptionsData = s2;
        images = img;
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
        holder.reminders_txt.setText(remindersData[position]);
        holder.descriptions_txt.setText(descriptionsData[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return remindersData.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reminders_txt, descriptions_txt;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            reminders_txt = itemView.findViewById(R.id.reminders_txt);
            descriptions_txt = itemView.findViewById(R.id.descriptions_txt);
            myImage = itemView.findViewById(R.id.myImgView);
        }
    }
}
