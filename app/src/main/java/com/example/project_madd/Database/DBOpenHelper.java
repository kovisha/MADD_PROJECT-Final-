package com.example.project_madd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Health_App.db";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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


    //----------------------------------------------Method to insert Values To water Table----------------------------------------
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


    //----------------------------------------------- Retrieve Method Water ------------------------------------------------------------------------
    public Cursor getInfo(SQLiteDatabase sqLiteDatabase){
        //SQLiteDatabase db = getReadableDatabase();


        String[] projection = {
                DBStructure.Water2.COL3_WATER2,
                DBStructure.Water2.COL4_WATER2,
                DBStructure.Water2.COL5_WATER2
        };

        String selection = DBStructure.Water2._ID +"=?";

        String[] selectionArgs = {"1"};

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


    //------------------------------------------------------- UPDATE AFTER DRINKING ---------------------------------------------------
    public int updateInfo(Double amount,Double remain){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Water2.COL4_WATER2 , amount);
        contentValues.put(DBStructure.Water2.COL5_WATER2 , remain);

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                DBStructure.Water2.TABLE_NAME1,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }
    //end of update method


    //--------------------------------------------- DELETING RECORDS ----------------------------------------------------------------
    public void deleteRecord(){
        SQLiteDatabase db = getReadableDatabase();

        String selection = DBStructure.Water2._ID + " LIKE ?";

        String selectionArgs[] = {"1"};
        db.delete(DBStructure.Water2.TABLE_NAME1 , selection , selectionArgs);
    }//End of records deletion


    //--------------------------------------------- RETRIEVE DRANK AMOUNT--------------------------------------------------------------------
    public Double getDrank(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL4_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"1"};


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



    //------------------------------------- RETRIEVE REAMINING AMOUNT -----------------------------------------------------------------------------
    public Double getRemainingAmt(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                DBStructure.Water2.COL5_WATER2
        };

        String selection = DBStructure.Water2._ID + " LIKE ?";
        String[] selectionArgs = {"1"};


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
    }



}
