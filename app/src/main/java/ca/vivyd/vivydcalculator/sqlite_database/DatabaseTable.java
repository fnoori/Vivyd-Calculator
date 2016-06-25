package ca.vivyd.vivydcalculator.sqlite_database;

import android.provider.BaseColumns;

/**
 * Created by Farzam on 2016-06-10.
 */
public class DatabaseTable {
    public DatabaseTable(){}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_entries";
        public static final String COLUMN_ENTRY_ID = "entry_id";

        public static final String EQUATION = "equation";
        public static final String SOLUTION = "solution";
    }
}
