package com.example.project_madd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/***********************************************************Methods to handle crud operations***********************************************/
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Health_Tracker.db"; //initializing name of database.

    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    /***************************************Create events table to store moods and symptoms************************************************/

    private final static String CREATE_EVENTS_TABLE="CREATE TABLE "+DBStructure.Events.EVENTS_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBStructure.Events.EVENT+" TEXT,"+DBStructure.Events.TIME+" TEXT,"+DBStructure.Events.DATE+" TEXT,"+DBStructure.Events.MONTH+" TEXT,"+DBStructure.Events.YEAR+" TEXT)";


    private static final String DROP_EVENTS_TABLE="DROP TABLE IF EXISTS "+DBStructure.Events.EVENTS_TABLE_NAME;

/***************************************Create PeriodTracker table to store dates*********************************************/

    private final static String CREATE_PERIOD_TABLE = "CREATE TABLE "+DBStructure.PeriodTracker.TABLE_NAME+ "("+ DBStructure.PeriodTracker._ID+" INTEGER PRIMARY KEY,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_START_DATE+" TEXT,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_END_DATE+" TEXT,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_BLEEDING_DAYS+" INTEGER,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_NEXT_START_DATE+" TEXT,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH+" INTEGER,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_O_LENGTH+" INTEGER,"+
                         DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH+" INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /****************EXECUTE CREATE QUERY OF EVENTS TABLE********************/
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);


        /****************EXECUTE CREATE QUERY OF PERIOD TRACKER********************/
        sqLiteDatabase.execSQL(CREATE_PERIOD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_EVENTS_TABLE);
        onCreate(sqLiteDatabase);
    }

    /******************************************************************************************************/
    /***********************Methods to manage Period date tracker***********************************************/

    /**********************************Insert start date method*******************************************/

    public long addMenstrualStartDate(String startDate ){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE, startDate);


        long newPeriodRowId = db.insert(DBStructure.PeriodTracker.TABLE_NAME,null,values);

        return newPeriodRowId;
    }

    /**********************************Retrieve start date method*******************************************/

    public Cursor readStartDate(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_START_DATE};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"29"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }

    /**********************************Update users period End date method*******************************************/

    public long updatePeriodEndDate(String endDate){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_END_DATE,endDate);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"29"};

        long updatedEndDate = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updatedEndDate;
    }

    /**********************************Delete user  period record*******************************************/

    public long deletePeriodRecord(){

        SQLiteDatabase db = getReadableDatabase();

        String selection = DBStructure.PeriodTracker._ID+" LIKE ?";
        String [] selectionArgs = {"29"};

        long deleteRecord = db.delete(DBStructure.PeriodTracker.TABLE_NAME,selection,selectionArgs);

        return deleteRecord;

    }



    /***********************************************************************************************************************/
    /*******************************************Methods to manage the calendar activity in Period Tracker*************************************/
    public void saveEvent(String event,String time,String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Events.EVENT,event);
        contentValues.put(DBStructure.Events.TIME,time);
        contentValues.put(DBStructure.Events.DATE,date);
        contentValues.put(DBStructure.Events.MONTH,month);
        contentValues.put(DBStructure.Events.YEAR,year);
        database.insert(DBStructure.Events.EVENTS_TABLE_NAME,null,contentValues);
    }

    public Cursor readEvents(String date, SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.Events.EVENT,DBStructure.Events.TIME,DBStructure.Events.DATE,DBStructure.Events.MONTH,DBStructure.Events.YEAR};
        String selection=DBStructure.Events.DATE+"=?";
        String [] selectionArgs = {date};

        return sqLiteDatabase.query(DBStructure.Events.EVENTS_TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }


    public Cursor readEventsPerMonth(String month,String year,SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.Events.EVENT,DBStructure.Events.TIME,DBStructure.Events.DATE,DBStructure.Events.MONTH,DBStructure.Events.YEAR};
        String selection=DBStructure.Events.MONTH+"=? and "+DBStructure.Events.YEAR+"=?" ;
        String [] selectionArgs = {month,year};

        return sqLiteDatabase.query(DBStructure.Events.EVENTS_TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }
}
