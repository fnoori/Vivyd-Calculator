package ca.vivyd.vivydcalculator.sqlite_database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Farzam on 2016-05-08.
 */
public class HistoryData {
    private static final int CURRENT = 0;

    private Context context;

    private String equation;
    private String solution;
    private ArrayList<String> equationList;
    private ArrayList<String> solutionList;

    private Cursor cursor;
    private DatabaseInteractions dbi;

    public HistoryData(Context context){
        this.context = context;
        equation = null;
        solution = null;
        equationList = new ArrayList<>();
        solutionList = new ArrayList<>();

        dbi = new DatabaseInteractions(context);
        cursor = null;
    }

    public ArrayList<String> getData(String type){
        cursor = dbi.getData(dbi, type);
        ArrayList<String> typeOfList = new ArrayList<>();

        typeOfList.clear();
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do{
                typeOfList.add(cursor.getString(CURRENT));
                Log.d("GETTING", cursor.getString(CURRENT));
            }while (cursor.moveToNext());

            dbi.close();
            return typeOfList;
        }else{
            dbi.close();
            Toast.makeText(context, "HISTORY IS EMPTY", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void insertData(String equation, String solution){
        dbi.insertData(dbi, equation, solution);
        dbi.close();
    }

    public void clearDatabase(){
        dbi.clearDatabase(dbi);
        dbi.close();
    }
}

