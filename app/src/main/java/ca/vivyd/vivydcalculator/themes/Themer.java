package ca.vivyd.vivydcalculator.themes;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import java.util.ArrayList;

import ca.vivyd.vivydcalculator.R;

/**
 * Created by Amair on 6/20/2016.
 */
public class Themer {

    private static Context context;
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

    public Themer(Context inContext,
                  ArrayList<Button> inCommonButtons,
                  ArrayList<Button> inCommonOperands) {

        context = inContext;
        this.commonButtons = inCommonButtons;
        this.commonOperands = inCommonOperands;

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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.blue_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.blue_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.pink_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.pink_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.mango_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.mango_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.choc_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.choc_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.sund_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.sund_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.wtrm_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.wtrm_numpad_transition_top);
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
                    if (curr.getId() != R.id.sevenButton && curr.getId() != R.id.eightButton
                            && curr.getId() != R.id.nineButton)
                        curr.setBackgroundResource(R.drawable.rvel_numpad_transition);
                    else
                        curr.setBackgroundResource(R.drawable.rvel_numpad_transition_top);
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
                break;
        }

        float[] hsv = new float[3];
        Color.colorToHSV(colorArray.get(COLOR_NUMPAD), hsv);
        hsv[2] = 1.0f - 0.8f*(1.0f - hsv[2]);
        colorArray.add(COLOR_NUMPAD_DARK, Color.HSVToColor(hsv));

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

            }
        }

    }
}
