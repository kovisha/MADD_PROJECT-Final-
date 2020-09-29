package com.example.project_madd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.appcompat.app.AppCompatActivity;


import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;
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
    String startDate;
    Integer periodLength;
    int  firstDay,lastday ;

        /*******************Handle single grid in calendar*************************************************************/

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



        /******************************Retrieve details from database for the calendar view*********************************************************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.calendarViewDetails(database);

        while(cursor.moveToNext()) {
            startDate = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));
            periodLength = cursor.getInt(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH));
        }

        try {
            Date myDate =new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Calendar c = Calendar.getInstance();
            c.setTime(myDate);
            firstDay = c.get(Calendar.DAY_OF_MONTH);

            lastday = firstDay + periodLength;

        } catch (ParseException e) {
            e.printStackTrace();
        }




        /**********************If view is null just display the grids of the calendar********************************/
        View view = convertView;
        if(view==null){
            view=inflater.inflate(R.layout.single_cell_layout,parent,false);

        }


        /****************If day number belongs to month display grid as white in color****************************/
        /*if(displayMonth==currentMonth && displayYear==currentYear){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.popUpColor));
        }*/

        if(displayMonth==currentMonth  && displayYear==currentYear && dayNo >= firstDay && dayNo <= lastday){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.Highlighter));
        }

        /*****************If day number doesn't belong to month grid color becomes light blue  *****************************************/
        else{
            view.setBackgroundColor(Color.parseColor("#fafafa"));
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
