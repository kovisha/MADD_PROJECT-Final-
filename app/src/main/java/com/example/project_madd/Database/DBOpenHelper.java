package com.example.project_madd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project_madd.Model.PeriodTracker;

/***********************************************************Methods to handle crud operations***********************************************/
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Health_System.db"; //initializing name of database.

    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    /***********************************Period Tracker - IT19080840*********************/


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

    private static final String DROP_PERIOD_TABLE="DROP TABLE IF EXISTS "+DBStructure.PeriodTracker.TABLE_NAME;

/***********************************************End of Period Tracker***************************************************/
/**                                                                                                                   **/
/***********************************************************************************************************************/

    /***********************************Water Tracker - IT19175058*********************/


    /***************************************Create WaterTracker table to store data*********************************************/

    private static final String SQL_CREATE_ENTRIES_3 = "CREATE TABLE " + DBStructure.Water2.TABLE_NAME1 + " (" +
                         DBStructure.Water2._ID + " INTEGER PRIMARY KEY," +
                         DBStructure.Water2.COL1_WATER2+ " INTEGER," +
                         DBStructure.Water2.COL2_WATER2+ " INTEGER," +
                         DBStructure.Water2.COL3_WATER2+ " DOUBLE," +
                         DBStructure.Water2.COL4_WATER2+ " DOUBLE," +
                         DBStructure.Water2.COL5_WATER2+ " DOUBLE)";

    private static final String DROP_WATER_TABLE="DROP TABLE IF EXISTS "+DBStructure.Water2.TABLE_NAME1;

    /***********************************************End of Water Tracker***************************************************/
    /**                                                                                                                  **/
    /**********************************************************************************************************************/


    /***********************************BMI Tracker - IT19162010*********************/


    /***************************************Create user table to maintain BMI tracker****************************************/


    String SQL_QUERY2 = "CREATE TABLE " + DBStructure.BMITracker.TABLE_NAME+ " (" + DBStructure.BMITracker._ID + " INTEGER PRIMARY KEY," +
                         DBStructure.BMITracker.COLUMN_NAME_HEIGHT+ " REAL," +
                         DBStructure.BMITracker.COLUMN_NAME_WEIGHT + " REAL," +
                         DBStructure.BMITracker.COLUMN_NAME_AGE + " INTEGER," +
                         DBStructure.BMITracker.COLUMN_NAME_GENDER + " TEXT," +
                         DBStructure.BMITracker.COLUMN_NAME_WAIST + " REAL," +
                         DBStructure.BMITracker.COLUMN_NAME_BMI + " REAL," +
                         DBStructure.BMITracker.COLUMN_NAME_BMR + " REAL," +
                         DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE + " REAL)";

    private static final String DROP_USER_TABLE="DROP TABLE IF EXISTS "+DBStructure.BMITracker.TABLE_NAME;

    /***********************************************End of BMI Tracker***************************************************/
    /**                                                                                                                **/
    /********************************************************************************************************************/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /****************EXECUTE CREATE QUERY OF EVENTS TABLE********************/
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);


        /****************EXECUTE CREATE QUERY OF PERIOD TRACKER********************/
        sqLiteDatabase.execSQL(CREATE_PERIOD_TABLE);

        /****************EXECUTE CREATE QUERY OF WATER TRACKER********************/
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_3);

        /****************EXECUTE CREATE QUERY OF BMI TRACKER********************/

        sqLiteDatabase.execSQL(SQL_QUERY2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        /*****************UPGRADE EVENTS TABLE***************************************************/
        sqLiteDatabase.execSQL(DROP_EVENTS_TABLE);

        /*****************UPGRADE PERIOD TABLE***************************************************/
        sqLiteDatabase.execSQL(DROP_PERIOD_TABLE);

        /*****************UPGRADE WATER TABLE***************************************************/
        sqLiteDatabase.execSQL(DROP_WATER_TABLE);

        /*****************UPGRADE Users TABLE***************************************************/

        sqLiteDatabase.execSQL(DROP_USER_TABLE);


        onCreate(sqLiteDatabase);
    }

    /******************************************************************************************************/
    /**                      Methods to manage Period date tracker                                       **/
    /**                        IT19080840                                                                **/
    /******************************************************************************************************/



    /**********************************Insert start date method*******************************************/

    public long addMenstrualStartDate(String startDate,Integer periodLength , Integer cycleLength,Integer ovulationLength ){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE, startDate);
        values.put(DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH,periodLength);
        values.put(DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH,cycleLength);
        values.put(DBStructure.PeriodTracker.COLUMN_NAME_O_LENGTH,ovulationLength);

        long newPeriodRowId = db.insert(DBStructure.PeriodTracker.TABLE_NAME,null,values);

        return newPeriodRowId;
    }

    /**********************************Retrieve start date method*******************************************/

    public Cursor readStartDate(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_START_DATE};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }

    /**********************************Update users period End date method*******************************************/

    public long updatePeriodEndDate(String endDate){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_END_DATE,endDate);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};

        long updatedEndDate = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updatedEndDate;
    }

    /**********************************Delete user  period record*******************************************/

    public long deletePeriodRecord(){

        SQLiteDatabase db = getReadableDatabase();

        String selection = DBStructure.PeriodTracker._ID+" LIKE ?";
        String [] selectionArgs = {"12"};

        long deleteRecord = db.delete(DBStructure.PeriodTracker.TABLE_NAME,selection,selectionArgs);

        return deleteRecord;

    }

    /**********************************Update user period length*******************************************/

    public long updateUserPeriodLength(Integer plength){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH,plength);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};


        long updateUserPeriodLength = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updateUserPeriodLength;
    }

    /**********************************Update user cycle length*******************************************/
    public long updateUserCycleLength(Integer cLength){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH,cLength);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};


        long updateUserCycleLength = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updateUserCycleLength;
    }

    /**********************************Update user ovulation length*******************************************/
    public long updateUserOvulationLength(Integer oLength){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_O_LENGTH,oLength);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};


        long updateUserOvuleLength = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updateUserOvuleLength;
    }

    /**********************************Update next Period start day*******************************************/
    public long updateNextPeriodStartDay(String nextDate){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_NEXT_START_DATE,nextDate);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};


        long updateNextStartDate = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updateNextStartDate;
    }

    /**********************************Update Bleeding days*******************************************/
    public long updateBleedingDays(String bdays){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBStructure.PeriodTracker.COLUMN_NAME_BLEEDING_DAYS,bdays);

        String selection = DBStructure.PeriodTracker._ID + " LIKE ?";
        String [] selectionArgs = {"12"};


        long updateBleedingDays = db.update(DBStructure.PeriodTracker.TABLE_NAME,values,selection,selectionArgs);

        return updateBleedingDays;
    }




    /**********************************Retrieve User entered Period Length******************************************/

    public Cursor readPeriodLength(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }

    /**********************************Retrieve User entered CycleLength******************************************/

    public Cursor readCycleLength(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }

    /**********************************Retrieve User entered Ovulation Length******************************************/

    public Cursor readOvulationLength(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_O_LENGTH};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }

    /****************************Retrieve details for the calendar highlight view*******************************/

    public Cursor calendarViewDetails(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_START_DATE,DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }



    /****************************Retrieve details to display Period Records*******************************/

    public Cursor MenstrualRecords(SQLiteDatabase sqLiteDatabase){

        String [] projections={DBStructure.PeriodTracker.COLUMN_NAME_START_DATE,DBStructure.PeriodTracker.COLUMN_NAME_END_DATE,DBStructure.PeriodTracker.COLUMN_NAME_BLEEDING_DAYS,DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH,DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH};
        String selection=DBStructure.PeriodTracker._ID+"=?" ;
        String [] selectionArgs = {"12"};


        return sqLiteDatabase.query(DBStructure.PeriodTracker.TABLE_NAME,projections,selection,selectionArgs,null,null,null);

    }



    /***********************************************************************************************************************/
    /*******************************************Methods to manage the calendar activity in Period Tracker*******************/
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

    /**********************************End of Period Tracker**************************************************************/


    /******************************************************************************************************/
    /**                      Methods to manage Water  tracker                                            **/
    /**                            IT19175058                                                            **/
    /******************************************************************************************************/

    /**************************************** Method to insert Values To water Table *******************************************/
    public long addWater(Integer weight,Integer ExtTime,Double Total){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBStructure.Water2.COL1_WATER2,weight);
        values.put(DBStructure.Water2.COL2_WATER2,ExtTime);
        values.put(DBStructure.Water2.COL3_WATER2,Total);
        values.put(DBStructure.Water2.COL4_WATER2,0.00);
        values.put(DBStructure.Water2.COL5_WATER2,Total);


        long newRowId = db.insert(DBStructure.Water2.TABLE_NAME1,null,values);
        return newRowId;
    }
    //End of method insert


    /**************************************** Method to retrieve Values from water Table *******************************************/
    public Cursor getInfo(SQLiteDatabase sqLiteDatabase){
        //SQLiteDatabase db = getReadableDatabase();


        String[] projection = {
                DBStructure.Water2.COL3_WATER2,
                DBStructure.Water2.COL4_WATER2,
                DBStructure.Water2.COL5_WATER2
        };

        String selection = DBStructure.Water2._ID +"=?";

        String[] selectionArgs = {"2"};

        return sqLiteDatabase.query(
                DBStructure.Water2.TABLE_NAME1,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

    }
    //end of retrieve method


    /**************************************** Method to update after drinking water *******************************************/
    public int updateInfo(Double amount,Double remain){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Water2.COL4_WATER2 , amount);
        contentValues.put(DBStructure.Water2.COL5_WATER2 , remain);

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }
    //end of update method


    /**************************************** Method to delete records *******************************************/
    public void deleteRecord(){
        SQLiteDatabase db = getReadableDatabase();

        String selection = DBStructure.Water2._ID + " LIKE ?";

        String selectionArgs[] = {"2"};
        db.delete(DBStructure.Water2.TABLE_NAME1 , selection , selectionArgs);
    }//End of records deletion


    /**************************************** Method to retrieve drank amount *******************************************/
    public Double getDrank(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL4_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};


        Cursor cursor = db.query(
                DBStructure.Water2.TABLE_NAME1,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Double amountDrank = Double.parseDouble(cursor.getString(0));


        cursor.close();

        return amountDrank;

    }//END of retrieve drank amount



    /**************************************** Method to retrieve remaining amount *******************************************/
    public Double getRemainingAmt(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL5_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};


        Cursor cursor = db.query(
                DBStructure.Water2.TABLE_NAME1,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Double amountDrank = Double.parseDouble(cursor.getString(0));


        cursor.close();

        return amountDrank;
    }//end of method to retrieve drank amount


    /********************************************************* METHOD TO UPDATE TIME *******************************************************************/
    public int updateTime(Integer newVal){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Water2.COL2_WATER2,newVal);


        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of update time method


    /***************************************************** METHOD TO UPDATE WEIGHT ******************************************************************/
    public int updateWgt(Integer weight){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Water2.COL1_WATER2,weight);


        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of method update weight


    /***************************************************** METHOD TO RETRIEVE WEIGHT ******************************************************************/
    public Integer getResetInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL1_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};


        Cursor cursor = db.query(
                DBStructure.Water2.TABLE_NAME1,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Integer weight = Integer.parseInt(cursor.getString(0));
        cursor.close();

        return weight;

    }//End of method


    /***************************************************** METHOD TO RETRIEVE EXERCISE ******************************************************************/
    public Integer getResetExTime(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL2_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};


        Cursor cursor = db.query(
                DBStructure.Water2.TABLE_NAME1,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Integer exTime = Integer.parseInt(cursor.getString(0));
        cursor.close();

        return exTime;

    }//end of method

    /***************************************************** METHOD TO UPDATE WEIGHT ******************************************************************/
    public int resetMyInfo(Double totalAmt){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Water2.COL3_WATER2,totalAmt);
        contentValues.put(DBStructure.Water2.COL4_WATER2,0.00);
        contentValues.put(DBStructure.Water2.COL5_WATER2,totalAmt);


        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"2"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of method update weight


    /*************************************End of water tracker******************************************************************/


    /******************************************************************************************************/
    /**                      Methods to manage BMI tracker                                               **/
    /**                        IT19162010                                                               **/
    /******************************************************************************************************/


    /*****************************************insert details to the BMI tracker*******************************/
    public long addDetails(String height , String weight , String age , String gender , String waist , float bmi, float bmr , float whpercentage){

        float h = Float.parseFloat(height);
        float w = Float.parseFloat(weight);
        int a = Integer.parseInt(age);
        float wa = Float.parseFloat(waist);
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_HEIGHT , h);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_WEIGHT, w);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_AGE, a);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_GENDER , gender);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_WAIST , wa);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_BMI, bmi);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_BMR, bmr);
        contentValues.put( DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE, whpercentage);


        long row = db.insert( DBStructure.BMITracker.TABLE_NAME, null ,contentValues);
        return row;

    }

    /**************************************read BMI from BMI tracker*******************************************/
    public Cursor readBmi(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_BMI,DBStructure.BMITracker.COLUMN_NAME_HEIGHT};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    /*************************************read BMR from BMI tracker************************************************/
    public Cursor readBmr(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_BMR,DBStructure.BMITracker.COLUMN_NAME_BMI};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    /***********************************read WTH from BMI tracker****************************************************/
    public Cursor readWTH(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    /****************************************read ALL from BMI tracker***********************************************/
    public Cursor UserProfile(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_HEIGHT,DBStructure.BMITracker.COLUMN_NAME_WEIGHT,DBStructure.BMITracker.COLUMN_NAME_AGE,
                DBStructure.BMITracker.COLUMN_NAME_GENDER,DBStructure.BMITracker.COLUMN_NAME_WAIST};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    /******************************************update details from BMI tracker*******************************************/
    public int updateValues(String height, String weight , String waist,String bmi , String bmr,String  whpercentage){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBStructure.BMITracker.COLUMN_NAME_HEIGHT,height);
        values.put(DBStructure.BMITracker.COLUMN_NAME_WEIGHT,weight);
        values.put(DBStructure.BMITracker.COLUMN_NAME_WAIST,waist);
        values.put(DBStructure.BMITracker.COLUMN_NAME_BMI,bmi);
        values.put(DBStructure.BMITracker.COLUMN_NAME_BMR,bmr);
        values.put(DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE,whpercentage);


        String  selection = DBStructure.BMITracker._ID+ " LIKE?";
        String[] selectionArgs ={"1"};

        int count = db.update(DBStructure.BMITracker.TABLE_NAME,values,selection,selectionArgs);

        return count ;

    }


    /**************************************************Delete all from BMI tracker**********************************/
    public void deleteInfo(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase() ;
        String selection = DBStructure.BMITracker._ID+ " LIKE?";
        String selectionArgs[] = {"1"};
        sqLiteDatabase.delete(DBStructure.BMITracker.TABLE_NAME,selection,selectionArgs);


    }

    /*************************************End of BMI tracker******************************************************************/

}














