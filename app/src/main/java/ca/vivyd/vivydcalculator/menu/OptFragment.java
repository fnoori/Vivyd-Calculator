package ca.vivyd.vivydcalculator.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.vivyd.vivydcalculator.R;


/**
 * Created by Amair on 6/15/2016.
 */
public class OptFragment extends Fragment {

    public OptFragment(){

    }

    public static OptFragment getInstance(int position){
        OptFragment optFragment = new OptFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        optFragment.setArguments(args);
        return optFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View optView = inflater.inflate(R.layout.fragment_options, container, false);
        return optView;
    }
}
