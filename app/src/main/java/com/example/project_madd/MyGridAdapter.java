package com.example.project_madd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_madd.Model.Events;
import com.example.project_madd.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdapter extends ArrayAdapter {

    List<Date> dates;
    Calendar currentDate;
    List<Events> events;
    LayoutInflater inflater;




    public MyGridAdapter(@NonNull Context context,  List<Date> dates, Calendar currentDate, List<Events> events) {
        super(context, R.layout.single_cell_layout);

        this.dates=dates;
        this.currentDate=currentDate;
        this.events=events;
        inflater=LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int dayNo=dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);



        View view = convertView;
        if(view==null){
            view=inflater.inflate(R.layout.single_cell_layout,parent,false);

        }

        if(displayMonth==currentMonth && displayYear==currentYear){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.popUpColor));
        }


        else{
            view.setBackgroundColor(Color.parseColor("#9dc7c7"));
        }

        TextView Day_Number = view.findViewById(R.id.calendar_days);
        TextView Event_Number = view.findViewById(R.id.events_id);
        Day_Number.setText(String.valueOf(dayNo));
        Calendar eventCalendar = Calendar.getInstance();
        ArrayList<String> arrayList = new ArrayList<>();


        for(int i=0; i< events.size();i++){
            eventCalendar.setTime(convertStringToDate(events.get(i).getDATE()));

            if(dayNo == eventCalendar.get(Calendar.DAY_OF_MONTH)&& displayMonth==eventCalendar.get(Calendar.MONTH)+1 && displayYear==eventCalendar.get(Calendar.YEAR)){
                arrayList.add(events.get(i).getEVENT());
                Event_Number.setText(arrayList.size()+" events");
                view.setBackgroundColor(Color.parseColor("#000000"));
                Toast.makeText(getContext(), "Events highlighted", Toast.LENGTH_SHORT).show();
            }
        }

        return view;
    }

    private Date convertStringToDate(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;

        try{
            date=format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}
