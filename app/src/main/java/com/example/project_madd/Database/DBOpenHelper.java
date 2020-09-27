package com.example.project_madd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Health_App.db";

    //constructor
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the database
        String SQL_CREATE_ENTRIES_3 =
                "CREATE TABLE " + DBStructure.Water2.TABLE_NAME1 + " (" +
                        DBStructure.Water2._ID + " INTEGER PRIMARY KEY," +
                        DBStructure.Water2.COL1_WATER2+ " INTEGER," +
                        DBStructure.Water2.COL2_WATER2+ " INTEGER," +
                        DBStructure.Water2.COL3_WATER2+ " DOUBLE," +
                        DBStructure.Water2.COL4_WATER2+ " DOUBLE," +
                        DBStructure.Water2.COL5_WATER2+ " DOUBLE)";

        db.execSQL(SQL_CREATE_ENTRIES_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DBStructure.Water2.TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }


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

        String[] selectionArgs = {"4"};

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
        String[] selectionArgs = {"4"};

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

        String selectionArgs[] = {"5"};
        db.delete(DBStructure.Water2.TABLE_NAME1 , selection , selectionArgs);
    }//End of records deletion


    /**************************************** Method to retrieve drank amount *******************************************/
    public Double getDrank(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL4_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"4"};


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
        String[] selectionArgs = {"4"};


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
        String[] selectionArgs = {"5"};

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
        String[] selectionArgs = {"5"};

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
        String[] selectionArgs = {"4"};


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
        String[] selectionArgs = {"4"};


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
        String[] selectionArgs = {"4"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of method update weight





}
