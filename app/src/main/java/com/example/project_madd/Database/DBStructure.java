package com.example.project_madd.Database;

/**************************************Define the table names and column names******************/

import android.provider.BaseColumns;

/****Similiar to users master*****/
public final class DBStructure {

    public DBStructure() {
    }


/*****************************Period Tracker - IT19080840***************************************************/
    public static class PeriodTracker implements BaseColumns{

        public static final String TABLE_NAME = "Menstruation";
        public static final String COLUMN_NAME_START_DATE = "startDate";
        public static final String COLUMN_NAME_END_DATE = "endDate";
        public static final String COLUMN_NAME_BLEEDING_DAYS = "bleedDays";
        public static final String COLUMN_NAME_NEXT_START_DATE = "nextStart";
        public static final String COLUMN_NAME_P_LENGTH = "pLength";
        public static final String COLUMN_NAME_C_LENGTH = "cLength";
        public static final String COLUMN_NAME_O_LENGTH = "oLength";
    }

    public static class Events implements BaseColumns{

        public static final String EVENTS_TABLE_NAME="eventstable";
        public static final String EVENT="event";
        public static final String TIME="time";
        public static final String DATE="date";
        public static final String MONTH="month";
        public static final String YEAR="year";
    }

    /*****************************Water Tracker - IT19175058***************************************************/

    public static class Water2 implements BaseColumns {
        public static final String TABLE_NAME1 = "waterTracker";
        public static final String COL1_WATER2 = "weight";
        public static final String COL2_WATER2 = "exercise";
        public static final String COL3_WATER2 = "Total";
        public static final String COL4_WATER2 = "Drank";
        public static final String COL5_WATER2 = "Remaining";
    }


    /**********************************BMI Tracker - IT19162010****************************************************/

    public  static class BMITracker implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_WEIGHT = "weight" ;
        public static final String COLUMN_NAME_AGE = "Age" ;
        public static final String COLUMN_NAME_GENDER = "Gender" ;
        public static final String COLUMN_NAME_WAIST= "waist" ;
        public static final String COLUMN_NAME_BMI = "Bmi" ;
        public static final String COLUMN_NAME_BMR = "Bmr" ;
        public static final String COLUMN_NAME_WHPERCENTAGE = "WHpercentage" ;

    }
}
