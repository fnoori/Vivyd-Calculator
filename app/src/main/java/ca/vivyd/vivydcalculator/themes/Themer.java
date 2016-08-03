package ca.vivyd.vivydcalculator.themes;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import ca.vivyd.vivydcalculator.MainActivity;
import ca.vivyd.vivydcalculator.R;

/**
 * Created by AmairJ on 6/20/2016.
 */
public class Themer {

    private static Context context;
    private Button morButton;
    private ArrayList<Button> commonButtons;
    private ArrayList<Button> commonOperands;

    public static ArrayList<Integer> colorArray;

    public static int COLOR_ACCENT = 0;
    public static int COLOR_COMP = 1;
    public static int COLOR_BACKGROUND = 2;
    public static int COLOR_NUMPAD = 3;
    public static int COLOR_TEXT = 4;
    public static int COLOR_TEXT_SCREEN = 5;
    public static int COLOR_OPPTRAY = 6;
    public static int COLOR_NUMPAD_DARK = 7;


    public static int CURRENT_THEME = 1;
    public final static int BLUE_THEME = 1;
    public final static int PINK_THEME = 2;
    public final static int MANGO_THEME = 3;
    public final static int CHOC_THEME = 4;
    public final static int SUND_THEME = 5;
    public final static int WTRM_THEME = 6;
    public final static int RVEL_THEME = 7;
    public final static int PAP_THEME = 8;
    public final static int BLK_THEME = 9;
    public final static int PEP_THEME = 10;
    public final static int PIN_THEME = 11;
    public final static int ELEC_THEME = 12;



    // Basic themes
    public static int BLUE_ACCENT;
    public static int BLUE_COMP;
    public static int PINK_ACCENT;
    public static int PINK_COMP;
    public static int MANGO_ACCENT;
    public static int MANGO_COMP;
    public static int BASIC_BACKGROUND;
    public static int BASIC_NUMPAD;
    public static int BASIC_TEXT;
    public static int BASIC_TEXT_SCREEN;
    public static int BASIC_OPPTRAY;

    // Chocolate Theme
    public static int CHOC_ACCENT;
    public static int CHOC_COMP;
    public static int CHOC_BACKGROUND;
    public static int CHOC_NUMPAD;
    public static int CHOC_TEXT;
    public static int CHOC_TEXT_SCREEN;
    public static int CHOC_OPPTRAY;

    // Sundae Theme
    public static int SUND_ACCENT;
    public static int SUND_COMP;
    public static int SUND_BACKGROUND;
    public static int SUND_NUMPAD;
    public static int SUND_TEXT;
    public static int SUND_TEXT_SCREEN;
    public static int SUND_OPPTRAY;

    // Watermelon Theme
    public static int WTRM_ACCENT;
    public static int WTRM_COMP;
    public static int WTRM_BACKGROUND;
    public static int WTRM_NUMPAD;
    public static int WTRM_TEXT;
    public static int WTRM_TEXT_SCREEN;
    public static int WTRM_OPPTRAY;

    // Red Velvet Theme
    public static int RVEL_ACCENT;
    public static int RVEL_COMP;
    public static int RVEL_BACKGROUND;
    public static int RVEL_NUMPAD;
    public static int RVEL_TEXT;
    public static int RVEL_TEXT_SCREEN;
    public static int RVEL_OPPTRAY;

    // Papaya Theme
    public static int PAP_ACCENT;
    public static int PAP_COMP;
    public static int PAP_BACKGROUND;
    public static int PAP_NUMPAD;
    public static int PAP_TEXT;
    public static int PAP_TEXT_SCREEN;
    public static int PAP_OPPTRAY;

    // Simple Black Theme
    public static int BLK_ACCENT;
    public static int BLK_COMP;
    public static int BLK_BACKGROUND;
    public static int BLK_NUMPAD;
    public static int BLK_TEXT;
    public static int BLK_TEXT_SCREEN;
    public static int BLK_OPPTRAY;

    // Peppermint Theme
    public static int PEP_ACCENT;
    public static int PEP_COMP;
    public static int PEP_BACKGROUND;
    public static int PEP_NUMPAD;
    public static int PEP_TEXT;
    public static int PEP_TEXT_SCREEN;
    public static int PEP_OPPTRAY;
    
    // Pina colada Theme
    public static int PIN_ACCENT;
    public static int PIN_COMP;
    public static int PIN_BACKGROUND;
    public static int PIN_NUMPAD;
    public static int PIN_TEXT;
    public static int PIN_TEXT_SCREEN;
    public static int PIN_OPPTRAY;
    
    // Electrolyte Theme
    public static int ELEC_ACCENT;
    public static int ELEC_COMP;
    public static int ELEC_BACKGROUND;
    public static int ELEC_NUMPAD;
    public static int ELEC_TEXT;
    public static int ELEC_TEXT_SCREEN;
    public static int ELEC_OPPTRAY;

    public Themer(Context inContext,
                  ArrayList<Button> inCommonButtons,
                  ArrayList<Button> inCommonOperands,
                  Button morButton) {

        context = inContext;
        this.commonButtons = inCommonButtons;
        this.commonOperands = inCommonOperands;
        this.morButton = morButton;

        colorArray = new ArrayList<>();
        colorArray.add(COLOR_ACCENT, BLUE_ACCENT);
        colorArray.add(COLOR_COMP, BLUE_COMP);
        colorArray.add(COLOR_BACKGROUND, BASIC_BACKGROUND);
        colorArray.add(COLOR_NUMPAD, BASIC_NUMPAD);
        colorArray.add(COLOR_TEXT, BASIC_TEXT);
        colorArray.add(COLOR_TEXT_SCREEN, BASIC_TEXT_SCREEN);
        colorArray.add(COLOR_OPPTRAY, BASIC_OPPTRAY);
        colorArray.add(COLOR_NUMPAD_DARK, 0);


        // Initialize Basic Themes
        BLUE_ACCENT          = ContextCompat.getColor(context, R.color.BLUE_colorAccent);
        BLUE_COMP            = ContextCompat.getColor(context, R.color.BLUE_colorComp);
        PINK_ACCENT          = ContextCompat.getColor(context, R.color.PINK_colorAccent);
        PINK_COMP            = ContextCompat.getColor(context, R.color.PINK_colorComp);
        MANGO_ACCENT         = ContextCompat.getColor(context, R.color.MANGO_colorAccent);
        MANGO_COMP           = ContextCompat.getColor(context, R.color.MANGO_colorComp);
        BASIC_BACKGROUND     = ContextCompat.getColor(context, R.color.BASIC_background);
        BASIC_NUMPAD         = ContextCompat.getColor(context, R.color.BASIC_numpad);
        BASIC_TEXT           = ContextCompat.getColor(context, R.color.BASIC_text);
        BASIC_TEXT_SCREEN    = ContextCompat.getColor(context, R.color.BASIC_text_screen);
        BASIC_OPPTRAY        = ContextCompat.getColor(context, R.color.BASIC_oppTray);


        // Initialize Chocolate Theme
        CHOC_ACCENT          = ContextCompat.getColor(context, R.color.CHOC_colorAccent);
        CHOC_COMP            = ContextCompat.getColor(context, R.color.CHOC_colorComp);
        CHOC_BACKGROUND      = ContextCompat.getColor(context, R.color.CHOC_background);
        CHOC_NUMPAD          = ContextCompat.getColor(context, R.color.CHOC_numpad);
        CHOC_TEXT            = ContextCompat.getColor(context, R.color.CHOC_text);
        CHOC_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.CHOC_text_screen);
        CHOC_OPPTRAY         = ContextCompat.getColor(context, R.color.CHOC_oppTray);

        // Initialize Sundae Theme
        SUND_ACCENT          = ContextCompat.getColor(context, R.color.SUND_colorAccent);
        SUND_COMP            = ContextCompat.getColor(context, R.color.SUND_colorComp);
        SUND_BACKGROUND      = ContextCompat.getColor(context, R.color.SUND_background);
        SUND_NUMPAD          = ContextCompat.getColor(context, R.color.SUND_numpad);
        SUND_TEXT            = ContextCompat.getColor(context, R.color.SUND_text);
        SUND_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.SUND_text_screen);
        SUND_OPPTRAY         = ContextCompat.getColor(context, R.color.SUND_oppTray);

        // Initialize Watermelon Theme
        WTRM_ACCENT          = ContextCompat.getColor(context, R.color.WTRM_colorAccent);
        WTRM_COMP            = ContextCompat.getColor(context, R.color.WTRM_colorComp);
        WTRM_BACKGROUND      = ContextCompat.getColor(context, R.color.WTRM_background);
        WTRM_NUMPAD          = ContextCompat.getColor(context, R.color.WTRM_numpad);
        WTRM_TEXT            = ContextCompat.getColor(context, R.color.WTRM_text);
        WTRM_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.WTRM_text_screen);
        WTRM_OPPTRAY         = ContextCompat.getColor(context, R.color.WTRM_oppTray);

        //Initialize Red Velvet Theme
        RVEL_ACCENT          = ContextCompat.getColor(context, R.color.RVEL_colorAccent);
        RVEL_COMP            = ContextCompat.getColor(context, R.color.RVEL_colorComp);
        RVEL_BACKGROUND      = ContextCompat.getColor(context, R.color.RVEL_background);
        RVEL_NUMPAD          = ContextCompat.getColor(context, R.color.RVEL_numpad);
        RVEL_TEXT            = ContextCompat.getColor(context, R.color.RVEL_text);
        RVEL_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.RVEL_text_screen);
        RVEL_OPPTRAY         = ContextCompat.getColor(context, R.color.RVEL_oppTray);

        //Initialize Blueberry Theme
        PAP_ACCENT          = ContextCompat.getColor(context, R.color.PAP_colorAccent);
        PAP_COMP            = ContextCompat.getColor(context, R.color.PAP_colorComp);
        PAP_BACKGROUND      = ContextCompat.getColor(context, R.color.PAP_background);
        PAP_NUMPAD          = ContextCompat.getColor(context, R.color.PAP_numpad);
        PAP_TEXT            = ContextCompat.getColor(context, R.color.PAP_text);
        PAP_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.PAP_text_screen);
        PAP_OPPTRAY         = ContextCompat.getColor(context, R.color.PAP_oppTray);

        //Initialize Simple Black Theme
        BLK_ACCENT          = ContextCompat.getColor(context, R.color.BLK_colorAccent);
        BLK_COMP            = ContextCompat.getColor(context, R.color.BLK_colorComp);
        BLK_BACKGROUND      = ContextCompat.getColor(context, R.color.BLK_background);
        BLK_NUMPAD          = ContextCompat.getColor(context, R.color.BLK_numpad);
        BLK_TEXT            = ContextCompat.getColor(context, R.color.BLK_text);
        BLK_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.BLK_text_screen);
        BLK_OPPTRAY         = ContextCompat.getColor(context, R.color.BLK_oppTray);

        //Initialize Peppermint Theme
        PEP_ACCENT          = ContextCompat.getColor(context, R.color.PEP_colorAccent);
        PEP_COMP            = ContextCompat.getColor(context, R.color.PEP_colorComp);
        PEP_BACKGROUND      = ContextCompat.getColor(context, R.color.PEP_background);
        PEP_NUMPAD          = ContextCompat.getColor(context, R.color.PEP_numpad);
        PEP_TEXT            = ContextCompat.getColor(context, R.color.PEP_text);
        PEP_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.PEP_text_screen);
        PEP_OPPTRAY         = ContextCompat.getColor(context, R.color.PEP_oppTray);

        //Initialize Pina Colada Theme
        PIN_ACCENT          = ContextCompat.getColor(context, R.color.PIN_colorAccent);
        PIN_COMP            = ContextCompat.getColor(context, R.color.PIN_colorComp);
        PIN_BACKGROUND      = ContextCompat.getColor(context, R.color.PIN_background);
        PIN_NUMPAD          = ContextCompat.getColor(context, R.color.PIN_numpad);
        PIN_TEXT            = ContextCompat.getColor(context, R.color.PIN_text);
        PIN_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.PIN_text_screen);
        PIN_OPPTRAY         = ContextCompat.getColor(context, R.color.PIN_oppTray);
        
        //Initialize Electrolyte Theme
        ELEC_ACCENT          = ContextCompat.getColor(context, R.color.ELEC_colorAccent);
        ELEC_COMP            = ContextCompat.getColor(context, R.color.ELEC_colorComp);
        ELEC_BACKGROUND      = ContextCompat.getColor(context, R.color.ELEC_background);
        ELEC_NUMPAD          = ContextCompat.getColor(context, R.color.ELEC_numpad);
        ELEC_TEXT            = ContextCompat.getColor(context, R.color.ELEC_text);
        ELEC_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.ELEC_text_screen);
        ELEC_OPPTRAY         = ContextCompat.getColor(context, R.color.ELEC_oppTray);

    }

    // Sets drawable backgrounds to buttons
    public void setButtAnimation() {
        switch (CURRENT_THEME) {
            case (BLUE_THEME):
                colorArray.add(COLOR_ACCENT, BLUE_ACCENT);
                colorArray.add(COLOR_COMP, BLUE_COMP);
                colorArray.add(COLOR_BACKGROUND, BASIC_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BASIC_NUMPAD);
                colorArray.add(COLOR_TEXT, BASIC_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BASIC_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BASIC_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.blue_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.blue_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.blue_numpad_transition);
                break;

            case (PINK_THEME):
                colorArray.add(COLOR_ACCENT, PINK_ACCENT);
                colorArray.add(COLOR_COMP, PINK_COMP);
                colorArray.add(COLOR_BACKGROUND, BASIC_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BASIC_NUMPAD);
                colorArray.add(COLOR_TEXT, BASIC_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BASIC_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BASIC_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pink_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pink_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pink_numpad_transition);
                break;

            case (MANGO_THEME):
                colorArray.add(COLOR_ACCENT, MANGO_ACCENT);
                colorArray.add(COLOR_COMP, MANGO_COMP);
                colorArray.add(COLOR_BACKGROUND, BASIC_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BASIC_NUMPAD);
                colorArray.add(COLOR_TEXT, BASIC_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BASIC_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BASIC_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.mango_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.mango_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.basic_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.mango_numpad_transition);
                break;

            case (CHOC_THEME):
                colorArray.add(COLOR_ACCENT, CHOC_ACCENT);
                colorArray.add(COLOR_COMP, CHOC_COMP);
                colorArray.add(COLOR_BACKGROUND, CHOC_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, CHOC_NUMPAD);
                colorArray.add(COLOR_TEXT, CHOC_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, CHOC_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, CHOC_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.choc_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.choc_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.choc_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.choc_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.choc_numpad_transition);
                break;

            case (SUND_THEME):
                colorArray.add(COLOR_ACCENT, SUND_ACCENT);
                colorArray.add(COLOR_COMP, SUND_COMP);
                colorArray.add(COLOR_BACKGROUND, SUND_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, SUND_NUMPAD);
                colorArray.add(COLOR_TEXT, SUND_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, SUND_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, SUND_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.sund_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.sund_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.sund_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.sund_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.sund_numpad_transition);
                break;

            case (WTRM_THEME):
                colorArray.add(COLOR_ACCENT, WTRM_ACCENT);
                colorArray.add(COLOR_COMP, WTRM_COMP);
                colorArray.add(COLOR_BACKGROUND, WTRM_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, WTRM_NUMPAD);
                colorArray.add(COLOR_TEXT, WTRM_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, WTRM_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, WTRM_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.wtrm_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.wtrm_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.wtrm_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.wtrm_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.wtrm_numpad_transition);
                break;

            case (RVEL_THEME):
                colorArray.add(COLOR_ACCENT, RVEL_ACCENT);
                colorArray.add(COLOR_COMP, RVEL_COMP);
                colorArray.add(COLOR_BACKGROUND, RVEL_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, RVEL_NUMPAD);
                colorArray.add(COLOR_TEXT, RVEL_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, RVEL_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, RVEL_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.rvel_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.rvel_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.rvel_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.rvel_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.rvel_numpad_transition);
                break;

            case (PAP_THEME):
                colorArray.add(COLOR_ACCENT, PAP_ACCENT);
                colorArray.add(COLOR_COMP, PAP_COMP);
                colorArray.add(COLOR_BACKGROUND, PAP_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, PAP_NUMPAD);
                colorArray.add(COLOR_TEXT, PAP_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, PAP_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, PAP_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pap_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pap_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.pap_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.pap_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pap_numpad_transition);
                break;

            case (BLK_THEME):
                colorArray.add(COLOR_ACCENT, BLK_ACCENT);
                colorArray.add(COLOR_COMP, BLK_COMP);
                colorArray.add(COLOR_BACKGROUND, BLK_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BLK_NUMPAD);
                colorArray.add(COLOR_TEXT, BLK_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BLK_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BLK_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.blk_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.blk_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.blk_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.blk_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.blk_numpad_transition);
                break;

            case (PEP_THEME):
                colorArray.add(COLOR_ACCENT, PEP_ACCENT);
                colorArray.add(COLOR_COMP, PEP_COMP);
                colorArray.add(COLOR_BACKGROUND, PEP_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, PEP_NUMPAD);
                colorArray.add(COLOR_TEXT, PEP_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, PEP_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, PEP_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pep_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pep_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.pep_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.pep_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pep_numpad_transition);
                break;
            
            case (PIN_THEME):
                colorArray.add(COLOR_ACCENT, PIN_ACCENT);
                colorArray.add(COLOR_COMP, PIN_COMP);
                colorArray.add(COLOR_BACKGROUND, PIN_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, PIN_NUMPAD);
                colorArray.add(COLOR_TEXT, PIN_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, PIN_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, PIN_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pin_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pin_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.pin_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.pin_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pin_numpad_transition);
                break; 
            
            case (ELEC_THEME):
                colorArray.add(COLOR_ACCENT, ELEC_ACCENT);
                colorArray.add(COLOR_COMP, ELEC_COMP);
                colorArray.add(COLOR_BACKGROUND, ELEC_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, ELEC_NUMPAD);
                colorArray.add(COLOR_TEXT, ELEC_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, ELEC_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, ELEC_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.elec_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.elec_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.elec_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.elec_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.elec_numpad_transition);
                break;
            
        }
        //float[] hsv = new float[3];
        //Color.colorToHSV(colorArray.get(COLOR_ACCENT), hsv);
        //hsv[2] = 0.2f + 0.15f*(hsv[2]);
        //colorArray.add(COLOR_NUMPAD_DARK, Color.HSVToColor(hsv));
        colorArray.add(COLOR_NUMPAD_DARK, Color.GRAY);
    }

    public void setSciButtsAnim(ArrayList<Button> advancedOperands) {
        for(Button curr:advancedOperands) {
            switch (CURRENT_THEME) {
                case (BLUE_THEME):
                    curr.setBackgroundResource(R.drawable.blue_numpad_transition);
                    break;

                case (PINK_THEME):
                    curr.setBackgroundResource(R.drawable.pink_numpad_transition);
                    break;

                case (MANGO_THEME):
                    curr.setBackgroundResource(R.drawable.mango_numpad_transition);
                    break;

                case (CHOC_THEME):
                    curr.setBackgroundResource(R.drawable.choc_numpad_transition);
                    break;

                case (SUND_THEME):
                    curr.setBackgroundResource(R.drawable.sund_numpad_transition);
                    break;

                case (WTRM_THEME):
                    curr.setBackgroundResource(R.drawable.wtrm_numpad_transition);
                    break;

                case (RVEL_THEME):
                    curr.setBackgroundResource(R.drawable.rvel_numpad_transition);
                    break;

                case (PAP_THEME):
                    curr.setBackgroundResource(R.drawable.pap_numpad_transition);
                    break;

                case (BLK_THEME):
                    curr.setBackgroundResource(R.drawable.blk_numpad_transition);
                    break;

                case (PEP_THEME):
                    curr.setBackgroundResource(R.drawable.pep_numpad_transition);
                    break;

                case (PIN_THEME):
                    curr.setBackgroundResource(R.drawable.pin_numpad_transition);
                    break;

                case (ELEC_THEME):
                    curr.setBackgroundResource(R.drawable.elec_numpad_transition);
                    break;

            }
        }

    }
}
