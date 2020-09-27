package com.example.project_madd;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;
import com.example.project_madd.Model.Events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomCalendarView extends LinearLayout {

    MyGridAdapter myGridAdapter;
    ImageButton NextButton,PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    AlertDialog alertDialog;

    private static final int MAX_CALENDAR_DAYS=42;

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;

    /**************************Initialising format of calendar dates*****************************************************/

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM ",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);


    /***************************Array Lists to store user entered moods and symptoms and days of entries**********************************/
    List<Date> dates = new ArrayList<>();
    List<Events> events = new ArrayList<>();
    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeLayout();
        setupCalendar();

        /***************************Buttons to navigate back and forth in calendar*********/
        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,-1);
                setupCalendar();
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,1);
                setupCalendar();
            }
        });


/***********************************Method to handle a single grid in calendar*****************************************************/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick( AdapterView<?> adapterView, View view, int position, long id) {

                /*******************************On click o grid add event alert wil appear******************************************/
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);

                final View addView = LayoutInflater.from(getContext()).inflate(R.layout.add_newevent_layout,null);
                final EditText eventName = addView.findViewById(R.id.eventname);
                final TextView eventTime = addView.findViewById(R.id.eventTime);
                ImageButton setTime = addView.findViewById(R.id.setEventTime);
                Button addEvent = addView.findViewById(R.id.addEvent);

                /**************************Setting the time of event*******************************************/
                setTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);

                        /**************************Initialise timepicker*******************************************/
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfday, int minute) {

                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY,hourOfday);
                                c.set(Calendar.MINUTE,minute);
                                c.setTimeZone(TimeZone.getDefault());

                                SimpleDateFormat hformat = new SimpleDateFormat("K:mm a",Locale.ENGLISH);
                                String event_Time = hformat.format(c.getTime());
                                eventTime.setText(event_Time);
                            }
                        },hours,minutes,false);
                        timePickerDialog.show();
                    }
                });

                final String date = eventDateFormat.format(dates.get(position));
                final String month = dateFormat.format(dates.get(position));
                final String year = dateFormat.format(dates.get(position));

                /*****************************Saving the added event**********************************************/
                addEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveEvent(eventName.getText().toString(),eventTime.getText().toString(),date,month,year);
                        setupCalendar();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();

            }


        });
/********************User could view the events if long clicked*********************************************************************/
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                String date = eventDateFormat.format(dates.get(position));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);

/*****************************************Show events layout invoked****************************************************/
                View showView = LayoutInflater.from(getContext()).inflate(R.layout.show_eventd_layout,null);

                RecyclerView recyclerView = showView.findViewById(R.id.eventsRv);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(),collectEventByDate(date));

                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();


                return true;
            }
        });

    }

/**************************Read events on a particular day and add it to an arraylist***********************************/
    private ArrayList<Events> collectEventByDate(String date){
        ArrayList<Events> arrayList = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEvents(date,database);

        while(cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.Events.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.Events.TIME));
            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.Events.DATE));
            String months = cursor.getString(cursor.getColumnIndex(DBStructure.Events.MONTH));
            String years = cursor.getString(cursor.getColumnIndex(DBStructure.Events.YEAR));

            Events events  = new Events(event,time,Date,months,years);
            arrayList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
        return arrayList;
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /***********************Insert event to database*************************************/
    private void saveEvent(String event, String time , String date , String month , String year){

        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.saveEvent(event,time,date,month,year,database);
        dbOpenHelper.close();
        Toast.makeText(context, "Event saved", Toast.LENGTH_SHORT).show();
    }

    private void initializeLayout(){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        NextButton= findViewById(R.id.next_button);
        PreviousButton=findViewById(R.id.previous_button);
        CurrentDate= findViewById(R.id.current_date);
        gridView=findViewById(R.id.gridView);
    }

    /*******************Setup the format o calendar for each month*************************************/
    private void setupCalendar(){
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);

        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-firstDayOfMonth);
        collectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));

        while(dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);

        }

        myGridAdapter = new MyGridAdapter(context,dates,calendar,events);
        gridView.setAdapter(myGridAdapter);
    }

    /****************Retrieve events of a particular day from database and store it in events arraylist*******************/

    private void collectEventsPerMonth(String month,String year){
        events.clear();//clear arraylist so previous events wont add up
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEventsPerMonth(month,year,database);

        while (cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.Events.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.Events.TIME));
            String date = cursor.getString(cursor.getColumnIndex(DBStructure.Events.DATE));
            String months = cursor.getString(cursor.getColumnIndex(DBStructure.Events.MONTH));
            String years = cursor.getString(cursor.getColumnIndex(DBStructure.Events.YEAR));

            Events myevents = new Events(event,time,date,months,years);

            events.add(myevents);
        }
        cursor.close();
        dbOpenHelper.close();
    }
}