package ca.vivyd.vivydcalculator.calc_logic;

import android.app.AlertDialog;
import android.content.Context;
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

    public UserDefinedButtons(Context context){
        this.context = context;
        valuesToReturn = new String[2];
    }

    public void launchUserInputDialog(final CalculatorButtons calcButtons, final Button inButton){
        LayoutInflater li = LayoutInflater.from(context);
        final View theView = li.inflate(R.layout.custom_button_dialog, null);
        final AlertDialog customButtonIputDialog = new AlertDialog.Builder(context).create();

        final EditText varName = (EditText) theView.findViewById(R.id.nameOfVar);
        final EditText varValue = (EditText) theView.findViewById(R.id.valueOfVar);
        Button confirmButton = (Button) theView.findViewById(R.id.confirmVarButton);
        Button cancelButton = (Button) theView.findViewById(R.id.cancelVarButton);

        currentButton = inButton;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtils.isNumeric(varValue.getText().toString()) && !varName.getText().toString().equals("")){
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

    public String[] getValues(){
        return valuesToReturn;
    }

    public Button getCurrentButton(){
        return currentButton;
    }
}
