package ca.vivyd.vivydcalculator.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
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

        Button revButton = (Button) thmView.findViewById(R.id.revButton);
        revButton.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
        revButton.setTextColor(Themer.colorArray.get(Themer.COLOR_TEXT_SCREEN));
        revButton.setOnClickListener(this);

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
        final Button strwButton = (Button) thmView.findViewById(R.id.strwButton);
        final Button bdayButton = (Button) thmView.findViewById(R.id.bdayButton);
        final Button pechButton = (Button) thmView.findViewById(R.id.pechButton);
        final Button gteaButton = (Button) thmView.findViewById(R.id.gteaButton);
        final Button spicButton = (Button) thmView.findViewById(R.id.spicButton);
        final Button fineButton = (Button) thmView.findViewById(R.id.fineButton);

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
        strwButton.setTag(Themer.STRW_THEME);
        bdayButton.setTag(Themer.BDAY_THEME);
        pechButton.setTag(Themer.PECH_THEME);
        gteaButton.setTag(Themer.GTEA_THEME);
        spicButton.setTag(Themer.SPIC_THEME);
        fineButton.setTag(Themer.FINE_THEME);

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
            add(strwButton);
            add(bdayButton);
            add(pechButton);
            add(gteaButton);
            add(spicButton);
            add(fineButton);
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
        //yPosTheme_changed = themeScroll.getScrollY();
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
        if (v.getId() == R.id.revButton) {
            final String vivydName = getContext().getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + vivydName)));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + vivydName)));
            }
        } else {
            // Id is theme change
            Themer.CURRENT_THEME = (Integer) v.getTag();
            theme_isChanged = 1;
            yPosTheme_changed = themeScroll.getScrollY();
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.right_out, R.anim.left_in);
        }
    }
}


