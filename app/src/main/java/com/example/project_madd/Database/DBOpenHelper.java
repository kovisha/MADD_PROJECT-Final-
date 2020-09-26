package com.example.project_madd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HealthTracker.db";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_QUERY2 = "CREATE TABLE " + DBStructure.BMITracker.TABLE_NAME+ " (" + DBStructure.BMITracker._ID + " INTEGER PRIMARY KEY," +
                DBStructure.BMITracker.COLUMN_NAME_HEIGHT+ " REAL," +
                DBStructure.BMITracker.COLUMN_NAME_WEIGHT + " REAL," +
                DBStructure.BMITracker.COLUMN_NAME_AGE + " INTEGER," +
                DBStructure.BMITracker.COLUMN_NAME_GENDER + " TEXT," +
                DBStructure.BMITracker.COLUMN_NAME_WAIST + " REAL," +
                DBStructure.BMITracker.COLUMN_NAME_BMI + " REAL," +
                DBStructure.BMITracker.COLUMN_NAME_BMR + " REAL," +
                DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE + " REAL)";

        db.execSQL(SQL_QUERY2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

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

    public Cursor readBmi(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_BMI,DBStructure.BMITracker.COLUMN_NAME_HEIGHT};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    public Cursor readBmr(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_BMR,DBStructure.BMITracker.COLUMN_NAME_BMI};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    public Cursor readWTH(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }

    public Cursor UserProfile(SQLiteDatabase sqLiteDatabase){

        String [] projection = {DBStructure.BMITracker.COLUMN_NAME_HEIGHT,DBStructure.BMITracker.COLUMN_NAME_WEIGHT,DBStructure.BMITracker.COLUMN_NAME_AGE,
                                DBStructure.BMITracker.COLUMN_NAME_GENDER,DBStructure.BMITracker.COLUMN_NAME_WAIST};
        String selection = DBStructure.BMITracker._ID+"=?";
        String [] selectionArgs = {"1"};

        return sqLiteDatabase.query(DBStructure.BMITracker.TABLE_NAME,projection,selection,selectionArgs,null,null,null);


    }


   public int updateValues(String height, String weight , String waist){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBStructure.BMITracker.COLUMN_NAME_HEIGHT,height);
        values.put(DBStructure.BMITracker.COLUMN_NAME_WEIGHT,weight);
        values.put(DBStructure.BMITracker.COLUMN_NAME_WAIST,waist);

        String  selection = DBStructure.BMITracker._ID+ " LIKE?";
        String[] selectionArgs ={"1"};

        int count = db.update(DBStructure.BMITracker.TABLE_NAME,values,selection,selectionArgs);

        return count ;

    }


    public void deleteInfo(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase() ;
        String selection = DBStructure.BMITracker._ID+ " LIKE?";
        String selectionArgs[] = {"1"};
        sqLiteDatabase.delete(DBStructure.BMITracker.TABLE_NAME,selection,selectionArgs);


    }
}
