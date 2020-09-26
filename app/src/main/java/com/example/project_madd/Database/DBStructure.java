package com.example.project_madd.Database;

import android.provider.BaseColumns;

public class DBStructure {

    public DBStructure() {
    }


    public  static class BMITracker implements BaseColumns{

        /********CoLumn name declarations******/
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
