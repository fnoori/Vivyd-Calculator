package ca.vivyd.vivydcalculator.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.vivyd.vivydcalculator.MainActivity;
import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.in_app_purchase_util.IabHelper;
import ca.vivyd.vivydcalculator.themes.Themer;


/**
 * Created by Amair on 6/15/2016.
 */
public class adfFragment extends Fragment implements View.OnClickListener {

    public adfFragment(){

    }

    public static adfFragment getInstance(int position){
        adfFragment abtFragment = new adfFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        abtFragment.setArguments(args);
        return abtFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View adfView = inflater.inflate(R.layout.fragment_adfree, container, false);
        adfView.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_NUMPAD));


        Button adfreeButton = (Button) adfView.findViewById(R.id.adfButton);
        adfreeButton.setOnClickListener(this);

        return adfView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adfButton:
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.right_out, R.anim.left_in);
                MainActivity.wantToPurchase = 1;

        }
    }
}
