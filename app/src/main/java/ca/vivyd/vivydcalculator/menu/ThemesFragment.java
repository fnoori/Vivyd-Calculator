package ca.vivyd.vivydcalculator.menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.ArrayList;

import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.themes.Themer;


/**
 * Created by Amair on 6/15/2016.
 */
public class ThemesFragment extends Fragment implements View.OnClickListener {

    public static int theme_isChanged = 0;
    public static int yPosTheme_changed = 0;

    private ArrayList<Button> buttList;
    private ScrollView themeScroll;

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

        final Button blueButton = (Button) thmView.findViewById(R.id.blueButton);
        final Button pinkButton = (Button) thmView.findViewById(R.id.pinkButton);
        final Button mangButton = (Button) thmView.findViewById(R.id.mangButton);
        final Button chocButton = (Button) thmView.findViewById(R.id.chocButton);
        final Button sundaeButton = (Button) thmView.findViewById(R.id.sundaeButton);
        final Button wtrmButton = (Button) thmView.findViewById(R.id.wtrmButton);
        final Button rvelButton = (Button) thmView.findViewById(R.id.rvelButton);
        final Button papButton = (Button) thmView.findViewById(R.id.papButton);
        final Button blkButton = (Button) thmView.findViewById(R.id.blkButton);
        final Button pepButton = (Button) thmView.findViewById(R.id.pepButton);
        final Button pinButton = (Button) thmView.findViewById(R.id.pinButton);
        final Button elecButton = (Button) thmView.findViewById(R.id.elecButton);
        final Button banButton = (Button) thmView.findViewById(R.id.banButton);
        final Button sushButton = (Button) thmView.findViewById(R.id.sushButton);
        final Button clasButton = (Button) thmView.findViewById(R.id.clasButton);
        final Button kiwiButton = (Button) thmView.findViewById(R.id.kiwiButton);
        final Button snoButton = (Button) thmView.findViewById(R.id.snoButton);
        final Button pineButton = (Button) thmView.findViewById(R.id.pineButton);

        blueButton.setTag(Themer.BLUE_THEME);
        pinkButton.setTag(Themer.PINK_THEME);
        mangButton.setTag(Themer.MANGO_THEME);
        chocButton.setTag(Themer.CHOC_THEME);
        sundaeButton.setTag(Themer.SUND_THEME);
        wtrmButton.setTag(Themer.WTRM_THEME);
        rvelButton.setTag(Themer.RVEL_THEME);
        papButton.setTag(Themer.PAP_THEME);
        blkButton.setTag(Themer.BLK_THEME);
        pepButton.setTag(Themer.PEP_THEME);
        pinButton.setTag(Themer.PIN_THEME);
        elecButton.setTag(Themer.ELEC_THEME);
        banButton.setTag(Themer.BAN_THEME);
        sushButton.setTag(Themer.SUSH_THEME);
        clasButton.setTag(Themer.CLAS_THEME);
        kiwiButton.setTag(Themer.KIWI_THEME);
        snoButton.setTag(Themer.SNO_THEME);
        pineButton.setTag(Themer.PINE_THEME);

        buttList = new ArrayList<Button>(){{
            add(blueButton);
            add(pinkButton);
            add(mangButton);
            add(chocButton);
            add(sundaeButton);
            add(wtrmButton);
            add(rvelButton);
            add(papButton);
            add(blkButton);
            add(pepButton);
            add(pinButton);
            add(elecButton);
            add(banButton);
            add(sushButton);
            add(clasButton);
            add(kiwiButton);
            add(snoButton);
            add(pineButton);
        }};


        // Set Current Theme drawables
        Drawable pLeft = ResourcesCompat.getDrawable(getResources(), R.drawable.poinleft_icon, null);
        Drawable pRight = ResourcesCompat.getDrawable(getResources(), R.drawable.poinright_icon, null);
        for (Button curr: buttList){
            curr.setOnClickListener(this);
            if (Themer.CURRENT_THEME == (Integer) curr.getTag()){
                Drawable[] pDrawables = curr.getCompoundDrawables();
                curr.setCompoundDrawablesWithIntrinsicBounds(pLeft, pDrawables[2], pRight, null);
            }
        }

        themeScroll = (ScrollView) thmView.findViewById(R.id.themeScroll);
        return thmView;
    }

    @Override
    public void onPause(){
        super.onPause();
        yPosTheme_changed = themeScroll.getScrollY();
    }

    @Override
    public void onResume(){
        super.onResume();
        themeScroll.post(new Runnable() {
            @Override
            public void run() {
                themeScroll.scrollTo(0, yPosTheme_changed);
            }
        });
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
            case R.id.pepButton:
                Themer.CURRENT_THEME = Themer.PEP_THEME;
                break;
            case R.id.pinButton:
                Themer.CURRENT_THEME = Themer.PIN_THEME;
                break;
            case R.id.elecButton:
                Themer.CURRENT_THEME = Themer.ELEC_THEME;
                break; 
            case R.id.banButton:
                Themer.CURRENT_THEME = Themer.BAN_THEME;
                break;
            case R.id.sushButton:
                Themer.CURRENT_THEME = Themer.SUSH_THEME;
                break;
            case R.id.clasButton:
                Themer.CURRENT_THEME = Themer.CLAS_THEME;
                break;
            case R.id.kiwiButton:
                Themer.CURRENT_THEME = Themer.KIWI_THEME;
                break;
            case R.id.snoButton:
                Themer.CURRENT_THEME = Themer.SNO_THEME;
                break;
            case R.id.pineButton:
                Themer.CURRENT_THEME = Themer.PINE_THEME;
                break;
        }
        theme_isChanged  = 1;
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }
}


