package ca.vivyd.vivydcalculator.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.themes.Themer;


/**
 * Created by Amair on 6/15/2016.
 */
public class ThemesFragment extends Fragment implements View.OnClickListener {

    public ThemesFragment(){

    }

    public static ThemesFragment getInstance(int position){
        ThemesFragment themesFragment = new ThemesFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        themesFragment.setArguments(args);
        return themesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View thmView = inflater.inflate(R.layout.fragment_themes, container, false);

        Button pinkButton = (Button) thmView.findViewById(R.id.pinkButton);
        pinkButton.setOnClickListener(this);
        Button blueButton = (Button) thmView.findViewById(R.id.blueButton);
        blueButton.setOnClickListener(this);
        Button orngButton = (Button) thmView.findViewById(R.id.mangButton);
        orngButton.setOnClickListener(this);
        Button chocButton = (Button) thmView.findViewById(R.id.chocButton);
        chocButton.setOnClickListener(this);
        Button sundaeButton = (Button) thmView.findViewById(R.id.sundaeButton);
        sundaeButton.setOnClickListener(this);
        Button wtrmButton = (Button) thmView.findViewById(R.id.wtrmButton);
        wtrmButton.setOnClickListener(this);
        Button rvelButton = (Button) thmView.findViewById(R.id.rvelButton);
        rvelButton.setOnClickListener(this);
        Button papButton = (Button) thmView.findViewById(R.id.papButton);
        papButton.setOnClickListener(this);
        Button blkButton = (Button) thmView.findViewById(R.id.blkButton);
        blkButton.setOnClickListener(this);

        return thmView;
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.blueButton:
                Themer.CURRENT_THEME = Themer.BLUE_THEME;
                break;
            case R.id.pinkButton:
                Themer.CURRENT_THEME = Themer.PINK_THEME;
                break;
            case R.id.mangButton:
                Themer.CURRENT_THEME = Themer.MANGO_THEME;
                break;
            case R.id.chocButton:
                Themer.CURRENT_THEME = Themer.CHOC_THEME;
                break;
            case R.id.sundaeButton:
                Themer.CURRENT_THEME = Themer.SUND_THEME;
                break;
            case R.id.wtrmButton:
                Themer.CURRENT_THEME = Themer.WTRM_THEME;
                break;
            case R.id.rvelButton:
                Themer.CURRENT_THEME = Themer.RVEL_THEME;
                break;
            case R.id.papButton:
                Themer.CURRENT_THEME = Themer.PAP_THEME;
                break;
            case R.id.blkButton:
                Themer.CURRENT_THEME = Themer.BLK_THEME;
                break;
        }
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

}


