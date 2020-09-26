package com.example.project_madd.Database;

/**************************************Define the table names and column names******************/

import android.provider.BaseColumns;

/****Similiar to users master*****/
public final class DBStructure {

    public DBStructure() {
    }



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

    public static class waterTracker implements BaseColumns{

    }
}
