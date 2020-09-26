package com.example.project_madd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_madd.Model.Events;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.myViewHolder> {

    Context context;
    ArrayList<Events> arrayList;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Events events = arrayList.get(position);
        holder.event.setText(events.getEVENT());
        holder.dateText.setText(events.getDATE());
        holder.time.setText(events.getTIME());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends  RecyclerView.ViewHolder{

        TextView dateText , event , time;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dateText = itemView.findViewById(R.id.eventDate);
            event = itemView.findViewById(R.id.eventName);
            time=itemView.findViewById(R.id.eventTime);

        }
    }
}
