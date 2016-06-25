package ca.vivyd.vivydcalculator.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Farzam on 2016-06-10.
 */
public class DatabaseInteractions extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserInputCalculations.db";

    private static final String TEXT_KIND = " TEXT,";
    private static final String JUST_TEXT = " TEXT";
    private static final String TEXT_KIND_END = " TEXT)";
    private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY,";
    private static final String STARTING_BRACKET = " (";
    private static final String IS_QUESTION = "=?";
    private static final String AND = " AND ";

    public static final String CREATE_SQL_ENTRIES =
            "CREATE TABLE " + DatabaseTable.FeedEntry.TABLE_NAME + STARTING_BRACKET +
                    DatabaseTable.FeedEntry._ID + PRIMARY_KEY +

                    DatabaseTable.FeedEntry.EQUATION + TEXT_KIND +
                    DatabaseTable.FeedEntry.SOLUTION + TEXT_KIND_END;

    public DatabaseInteractions(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_ENTRIES);
        Log.d("DATABASE_CREATED", "SUCCESS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(DatabaseInteractions dbi, String equation, String solution){
        SQLiteDatabase SQ = dbi.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseTable.FeedEntry.EQUATION, equation);
        cv.put(DatabaseTable.FeedEntry.SOLUTION, solution);

        long k = SQ.insert(DatabaseTable.FeedEntry.TABLE_NAME, null, cv);
        Log.d("DATABASE_OPERATION", "ONE ROW INSERTED " + k);
        return k > -1;
    }

    public Cursor getData(DatabaseInteractions dbi, String type){
        SQLiteDatabase SQ = dbi.getWritableDatabase();

        String[] columnsToReturn = {type};

        return SQ.query(DatabaseTable.FeedEntry.TABLE_NAME, columnsToReturn, null,
                null, null, null, null);
    }

    public long getRowCount(DatabaseInteractions dbi){
        SQLiteDatabase SQ = dbi.getWritableDatabase();

        return DatabaseUtils.queryNumEntries(SQ, DatabaseTable.FeedEntry.TABLE_NAME);
    }

    public void clearDatabase(DatabaseInteractions dbi){
        SQLiteDatabase SQ = dbi.getWritableDatabase();

        SQ.execSQL("DELETE FROM " + DatabaseTable.FeedEntry.TABLE_NAME);
    }

    public void closeDatabase(DatabaseInteractions dbi){
        dbi.close();
    }
}
