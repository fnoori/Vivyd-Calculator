package ca.vivyd.vivydcalculator.calc_logic;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Farzam on 2016-07-20.
 *
 * Helper class for shared preferences
 */
public class SharedPreferencesLogic {
    private static final String SHARED_PREF_NAME = "CalcData";

    private Context context;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPreferencesLogic(Context context, SharedPreferences prefs,
                                  SharedPreferences.Editor editor){
        this.context = context;
        this.prefs = prefs;
        this.editor = editor;

        this.prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void variableDataInput(String nameVariable, String nameValue,
                                  String valueVariable, String valueValue){
        editor = prefs.edit();
        editor.putString(nameVariable, nameValue);
        editor.putString(valueVariable, valueValue);
        editor.apply();
    }

    public void generalPurposeDataInput(String variable, String data){
        editor = prefs.edit();
        editor.putString(variable, data);
        editor.apply();
    }

    public String getData(String variable){
        return prefs.getString(variable, "+");
    }
}
