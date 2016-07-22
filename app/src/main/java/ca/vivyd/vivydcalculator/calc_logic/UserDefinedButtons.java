package ca.vivyd.vivydcalculator.calc_logic;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import ca.vivyd.vivydcalculator.R;


/**
 * Created by Farzam on 2016-06-23.
 *
 * Will add code for the custom buttons here
 */
public class UserDefinedButtons {
    private Context context;
    private String valuesToReturn[];
    private Button currentButton;
    private SharedPreferencesLogic sharedPrefs;

    public UserDefinedButtons(Context context, SharedPreferencesLogic sharedPrefs){
        this.context = context;
        valuesToReturn = new String[2];
        this.sharedPrefs = sharedPrefs;
    }

    public void launchUserInputDialog(final CalculatorButtons calcButtons, final Button inButton){
        LayoutInflater li = LayoutInflater.from(context);
        final View theView = li.inflate(R.layout.custom_button_dialog, null);
        final AlertDialog customButtonIputDialog = new AlertDialog.Builder(context).create();

        final EditText varName = (EditText) theView.findViewById(R.id.nameOfVar);
        final EditText varValue = (EditText) theView.findViewById(R.id.valueOfVar);
        final Button ansButton = (Button) theView.findViewById(R.id.userDefAnsButton);
        Button confirmButton = (Button) theView.findViewById(R.id.confirmVarButton);
        Button cancelButton = (Button) theView.findViewById(R.id.cancelVarButton);

        setFieldsValues(inButton, varName, varValue);

        ansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varValue.setText("");
                varValue.setText(sharedPrefs.getData("ANS"));
                varValue.setSelection(varValue.length());
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                varName.setSelection(varName.length());
                varValue.setSelection(varValue.length());

                Log.d("VAR_NAME", varName.getText().toString());
                Log.d("VAR_VALUE", varValue.getText().toString());

                if(!varName.getText().toString().equals("")){
                    calcButtons.setUserDefinedValues(inButton, varName.getText().toString(), varValue.getText().toString());
                    customButtonIputDialog.dismiss();
                }else{
                    Toast.makeText(context, "Please Enter Correct Details", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customButtonIputDialog.dismiss();
            }
        });

        customButtonIputDialog.setView(theView);
        customButtonIputDialog.show();
    }

    public void setFieldsValues(Button inButton, EditText varName, EditText varValue){
        switch (inButton.getId()){
            case R.id.var1Button:
                varName.setText(sharedPrefs.getData("Var1Name"));
                varValue.setText(sharedPrefs.getData("Var1Value"));
                break;
            case R.id.var2Button:
                varName.setText(sharedPrefs.getData("Var2Name"));
                varValue.setText(sharedPrefs.getData("Var2Value"));
                break;
            case R.id.var3Button:
                varName.setText(sharedPrefs.getData("Var3Name"));
                varValue.setText(sharedPrefs.getData("Var3Value"));
                break;
        }
        varName.setSelection(varName.length());
        varValue.setSelection(varValue.length());
    }
}
