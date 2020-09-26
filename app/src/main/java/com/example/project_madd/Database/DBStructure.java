package com.example.project_madd.Database;

import android.provider.BaseColumns;

public class DBStructure {

    /***********Class containing table and column names***************************/

    /************* Constructor *****************************/
    public DBStructure() {
    }

    /***************** Table and columns ********************************/
    public static class Water2 implements BaseColumns {
        public static final String TABLE_NAME1 = "waterTracker";
        public static final String COL1_WATER2 = "weight";
        public static final String COL2_WATER2 = "exercise";
        public static final String COL3_WATER2 = "Total";
        public static final String COL4_WATER2 = "Drank";
        public static final String COL5_WATER2 = "Remaining";
    }
}
