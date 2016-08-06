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
    public final static int BAN_THEME = 13;
    public final static int SUSH_THEME = 14;
    public final static int CLAS_THEME = 15;
    public final static int KIWI_THEME = 16;
    public final static int SNO_THEME = 17;
    public final static int PINE_THEME = 18;
    public final static int STRW_THEME = 19;
    public final static int BDAY_THEME = 20;
    public final static int PECH_THEME = 21;
    public final static int GTEA_THEME = 22;


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
    
    // Banana Theme
    public static int BAN_ACCENT;
    public static int BAN_COMP;
    public static int BAN_BACKGROUND;
    public static int BAN_NUMPAD;
    public static int BAN_TEXT;
    public static int BAN_TEXT_SCREEN;
    public static int BAN_OPPTRAY;
    
    // Sushi Theme
    public static int SUSH_ACCENT;
    public static int SUSH_COMP;
    public static int SUSH_BACKGROUND;
    public static int SUSH_NUMPAD;
    public static int SUSH_TEXT;
    public static int SUSH_TEXT_SCREEN;
    public static int SUSH_OPPTRAY;
    
    // Bold/Classy Theme
    public static int CLAS_ACCENT;
    public static int CLAS_COMP;
    public static int CLAS_BACKGROUND;
    public static int CLAS_NUMPAD;
    public static int CLAS_TEXT;
    public static int CLAS_TEXT_SCREEN;
    public static int CLAS_OPPTRAY;
    
    // Kiwi Theme
    public static int KIWI_ACCENT;
    public static int KIWI_COMP;
    public static int KIWI_BACKGROUND;
    public static int KIWI_NUMPAD;
    public static int KIWI_TEXT;
    public static int KIWI_TEXT_SCREEN;
    public static int KIWI_OPPTRAY;
    
    // Snow Theme
    public static int SNO_ACCENT;
    public static int SNO_COMP;
    public static int SNO_BACKGROUND;
    public static int SNO_NUMPAD;
    public static int SNO_TEXT;
    public static int SNO_TEXT_SCREEN;
    public static int SNO_OPPTRAY;

    // Pine Theme
    public static int PINE_ACCENT;
    public static int PINE_COMP;
    public static int PINE_BACKGROUND;
    public static int PINE_NUMPAD;
    public static int PINE_TEXT;
    public static int PINE_TEXT_SCREEN;
    public static int PINE_OPPTRAY; 
    
    // Strawberry Theme
    public static int STRW_ACCENT;
    public static int STRW_COMP;
    public static int STRW_BACKGROUND;
    public static int STRW_NUMPAD;
    public static int STRW_TEXT;
    public static int STRW_TEXT_SCREEN;
    public static int STRW_OPPTRAY;

    // B-Day Cake Theme
    public static int BDAY_ACCENT;
    public static int BDAY_COMP;
    public static int BDAY_BACKGROUND;
    public static int BDAY_NUMPAD;
    public static int BDAY_TEXT;
    public static int BDAY_TEXT_SCREEN;
    public static int BDAY_OPPTRAY;

    // Peach Cake Theme
    public static int PECH_ACCENT;
    public static int PECH_COMP;
    public static int PECH_BACKGROUND;
    public static int PECH_NUMPAD;
    public static int PECH_TEXT;
    public static int PECH_TEXT_SCREEN;
    public static int PECH_OPPTRAY;
    
    // Green Tea Theme
    public static int GTEA_ACCENT;
    public static int GTEA_COMP;
    public static int GTEA_BACKGROUND;
    public static int GTEA_NUMPAD;
    public static int GTEA_TEXT;
    public static int GTEA_TEXT_SCREEN;
    public static int GTEA_OPPTRAY;

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

        //Initialize Banana Theme
        BAN_ACCENT          = ContextCompat.getColor(context, R.color.BAN_colorAccent);
        BAN_COMP            = ContextCompat.getColor(context, R.color.BAN_colorComp);
        BAN_BACKGROUND      = ContextCompat.getColor(context, R.color.BAN_background);
        BAN_NUMPAD          = ContextCompat.getColor(context, R.color.BAN_numpad);
        BAN_TEXT            = ContextCompat.getColor(context, R.color.BAN_text);
        BAN_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.BAN_text_screen);
        BAN_OPPTRAY         = ContextCompat.getColor(context, R.color.BAN_oppTray);    
        
        //Initialize Sushi Theme
        SUSH_ACCENT          = ContextCompat.getColor(context, R.color.SUSH_colorAccent);
        SUSH_COMP            = ContextCompat.getColor(context, R.color.SUSH_colorComp);
        SUSH_BACKGROUND      = ContextCompat.getColor(context, R.color.SUSH_background);
        SUSH_NUMPAD          = ContextCompat.getColor(context, R.color.SUSH_numpad);
        SUSH_TEXT            = ContextCompat.getColor(context, R.color.SUSH_text);
        SUSH_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.SUSH_text_screen);
        SUSH_OPPTRAY         = ContextCompat.getColor(context, R.color.SUSH_oppTray); 
        
        //Initialize Classy Theme
        CLAS_ACCENT          = ContextCompat.getColor(context, R.color.CLAS_colorAccent);
        CLAS_COMP            = ContextCompat.getColor(context, R.color.CLAS_colorComp);
        CLAS_BACKGROUND      = ContextCompat.getColor(context, R.color.CLAS_background);
        CLAS_NUMPAD          = ContextCompat.getColor(context, R.color.CLAS_numpad);
        CLAS_TEXT            = ContextCompat.getColor(context, R.color.CLAS_text);
        CLAS_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.CLAS_text_screen);
        CLAS_OPPTRAY         = ContextCompat.getColor(context, R.color.CLAS_oppTray);

        //Initialize Kiwi Theme
        KIWI_ACCENT          = ContextCompat.getColor(context, R.color.KIWI_colorAccent);
        KIWI_COMP            = ContextCompat.getColor(context, R.color.KIWI_colorComp);
        KIWI_BACKGROUND      = ContextCompat.getColor(context, R.color.KIWI_background);
        KIWI_NUMPAD          = ContextCompat.getColor(context, R.color.KIWI_numpad);
        KIWI_TEXT            = ContextCompat.getColor(context, R.color.KIWI_text);
        KIWI_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.KIWI_text_screen);
        KIWI_OPPTRAY         = ContextCompat.getColor(context, R.color.KIWI_oppTray);

        //Initialize SnoCone Theme
        SNO_ACCENT          = ContextCompat.getColor(context, R.color.SNO_colorAccent);
        SNO_COMP            = ContextCompat.getColor(context, R.color.SNO_colorComp);
        SNO_BACKGROUND      = ContextCompat.getColor(context, R.color.SNO_background);
        SNO_NUMPAD          = ContextCompat.getColor(context, R.color.SNO_numpad);
        SNO_TEXT            = ContextCompat.getColor(context, R.color.SNO_text);
        SNO_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.SNO_text_screen);
        SNO_OPPTRAY         = ContextCompat.getColor(context, R.color.SNO_oppTray);

        //Initialize Pineapple Theme
        PINE_ACCENT          = ContextCompat.getColor(context, R.color.PINE_colorAccent);
        PINE_COMP            = ContextCompat.getColor(context, R.color.PINE_colorComp);
        PINE_BACKGROUND      = ContextCompat.getColor(context, R.color.PINE_background);
        PINE_NUMPAD          = ContextCompat.getColor(context, R.color.PINE_numpad);
        PINE_TEXT            = ContextCompat.getColor(context, R.color.PINE_text);
        PINE_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.PINE_text_screen);
        PINE_OPPTRAY         = ContextCompat.getColor(context, R.color.PINE_oppTray);
        
        //Initialize Strawberry Theme
        STRW_ACCENT          = ContextCompat.getColor(context, R.color.STRW_colorAccent);
        STRW_COMP            = ContextCompat.getColor(context, R.color.STRW_colorComp);
        STRW_BACKGROUND      = ContextCompat.getColor(context, R.color.STRW_background);
        STRW_NUMPAD          = ContextCompat.getColor(context, R.color.STRW_numpad);
        STRW_TEXT            = ContextCompat.getColor(context, R.color.STRW_text);
        STRW_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.STRW_text_screen);
        STRW_OPPTRAY         = ContextCompat.getColor(context, R.color.STRW_oppTray); 
        
        //Initialize Birthday Theme
        BDAY_ACCENT          = ContextCompat.getColor(context, R.color.BDAY_colorAccent);
        BDAY_COMP            = ContextCompat.getColor(context, R.color.BDAY_colorComp);
        BDAY_BACKGROUND      = ContextCompat.getColor(context, R.color.BDAY_background);
        BDAY_NUMPAD          = ContextCompat.getColor(context, R.color.BDAY_numpad);
        BDAY_TEXT            = ContextCompat.getColor(context, R.color.BDAY_text);
        BDAY_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.BDAY_text_screen);
        BDAY_OPPTRAY         = ContextCompat.getColor(context, R.color.BDAY_oppTray);

        //Initialize Peach Theme
        PECH_ACCENT          = ContextCompat.getColor(context, R.color.PECH_colorAccent);
        PECH_COMP            = ContextCompat.getColor(context, R.color.PECH_colorComp);
        PECH_BACKGROUND      = ContextCompat.getColor(context, R.color.PECH_background);
        PECH_NUMPAD          = ContextCompat.getColor(context, R.color.PECH_numpad);
        PECH_TEXT            = ContextCompat.getColor(context, R.color.PECH_text);
        PECH_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.PECH_text_screen);
        PECH_OPPTRAY         = ContextCompat.getColor(context, R.color.PECH_oppTray);
        
        //Initialize Green Tea Theme
        GTEA_ACCENT          = ContextCompat.getColor(context, R.color.GTEA_colorAccent);
        GTEA_COMP            = ContextCompat.getColor(context, R.color.GTEA_colorComp);
        GTEA_BACKGROUND      = ContextCompat.getColor(context, R.color.GTEA_background);
        GTEA_NUMPAD          = ContextCompat.getColor(context, R.color.GTEA_numpad);
        GTEA_TEXT            = ContextCompat.getColor(context, R.color.GTEA_text);
        GTEA_TEXT_SCREEN     = ContextCompat.getColor(context, R.color.GTEA_text_screen);
        GTEA_OPPTRAY         = ContextCompat.getColor(context, R.color.GTEA_oppTray);
        
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

            case (BAN_THEME):
                colorArray.add(COLOR_ACCENT, BAN_ACCENT);
                colorArray.add(COLOR_COMP, BAN_COMP);
                colorArray.add(COLOR_BACKGROUND, BAN_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BAN_NUMPAD);
                colorArray.add(COLOR_TEXT, BAN_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BAN_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BAN_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.ban_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.ban_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.ban_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.ban_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.ban_numpad_transition);
                break;
            
            case (SUSH_THEME):
                colorArray.add(COLOR_ACCENT, SUSH_ACCENT);
                colorArray.add(COLOR_COMP, SUSH_COMP);
                colorArray.add(COLOR_BACKGROUND, SUSH_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, SUSH_NUMPAD);
                colorArray.add(COLOR_TEXT, SUSH_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, SUSH_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, SUSH_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.sush_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.sush_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.sush_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.sush_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.sush_numpad_transition);
                break;

            case (CLAS_THEME):
                colorArray.add(COLOR_ACCENT, CLAS_ACCENT);
                colorArray.add(COLOR_COMP, CLAS_COMP);
                colorArray.add(COLOR_BACKGROUND, CLAS_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, CLAS_NUMPAD);
                colorArray.add(COLOR_TEXT, CLAS_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, CLAS_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, CLAS_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.clas_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.clas_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.clas_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.clas_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.clas_numpad_transition);
                break;

            case (KIWI_THEME):
                colorArray.add(COLOR_ACCENT, KIWI_ACCENT);
                colorArray.add(COLOR_COMP, KIWI_COMP);
                colorArray.add(COLOR_BACKGROUND, KIWI_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, KIWI_NUMPAD);
                colorArray.add(COLOR_TEXT, KIWI_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, KIWI_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, KIWI_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.kiwi_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.kiwi_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.kiwi_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.kiwi_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.kiwi_numpad_transition);
                break; 
            
            case (SNO_THEME):
                colorArray.add(COLOR_ACCENT, SNO_ACCENT);
                colorArray.add(COLOR_COMP, SNO_COMP);
                colorArray.add(COLOR_BACKGROUND, SNO_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, SNO_NUMPAD);
                colorArray.add(COLOR_TEXT, SNO_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, SNO_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, SNO_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.sno_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.sno_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.sno_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.sno_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.sno_numpad_transition);
                break;

            case (PINE_THEME):
                colorArray.add(COLOR_ACCENT, PINE_ACCENT);
                colorArray.add(COLOR_COMP, PINE_COMP);
                colorArray.add(COLOR_BACKGROUND, PINE_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, PINE_NUMPAD);
                colorArray.add(COLOR_TEXT, PINE_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, PINE_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, PINE_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pine_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pine_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.pine_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.pine_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pine_numpad_transition);
                break; 
            
            case (STRW_THEME):
                colorArray.add(COLOR_ACCENT, STRW_ACCENT);
                colorArray.add(COLOR_COMP, STRW_COMP);
                colorArray.add(COLOR_BACKGROUND, STRW_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, STRW_NUMPAD);
                colorArray.add(COLOR_TEXT, STRW_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, STRW_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, STRW_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.strw_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.strw_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.strw_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.strw_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.strw_numpad_transition);
                break;
            
            case (BDAY_THEME):
                colorArray.add(COLOR_ACCENT, BDAY_ACCENT);
                colorArray.add(COLOR_COMP, BDAY_COMP);
                colorArray.add(COLOR_BACKGROUND, BDAY_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, BDAY_NUMPAD);
                colorArray.add(COLOR_TEXT, BDAY_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, BDAY_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, BDAY_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.bday_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.bday_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.bday_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.bday_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.bday_numpad_transition);
                break;

            case (PECH_THEME):
                colorArray.add(COLOR_ACCENT, PECH_ACCENT);
                colorArray.add(COLOR_COMP, PECH_COMP);
                colorArray.add(COLOR_BACKGROUND, PECH_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, PECH_NUMPAD);
                colorArray.add(COLOR_TEXT, PECH_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, PECH_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, PECH_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.pech_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.pech_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.pech_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.pech_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.pech_numpad_transition);
                break;
            
            case (GTEA_THEME):
                colorArray.add(COLOR_ACCENT, GTEA_ACCENT);
                colorArray.add(COLOR_COMP, GTEA_COMP);
                colorArray.add(COLOR_BACKGROUND, GTEA_BACKGROUND);
                colorArray.add(COLOR_NUMPAD, GTEA_NUMPAD);
                colorArray.add(COLOR_TEXT, GTEA_TEXT);
                colorArray.add(COLOR_TEXT_SCREEN, GTEA_TEXT_SCREEN);
                colorArray.add(COLOR_OPPTRAY, GTEA_OPPTRAY);
                for (Button curr: commonButtons) {
                    curr.setBackgroundResource(R.drawable.gtea_numpad_transition);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                for (Button curr: commonOperands) {
                    if (curr.getId() == R.id.openBrace || curr.getId() == R.id.closeBrace
                            || curr.getId() == R.id.prcntButton || curr.getId() == R.id.delButton
                            || curr.getId() == R.id.clrButton)
                        curr.setBackgroundResource(R.drawable.gtea_numpad_transition_noshad);
                    else if(curr.getId() == R.id.divButton || curr.getId() == R.id.multButton
                            || curr.getId() == R.id.minusButton || curr.getId() == R.id.addButton
                            || curr.getId() == R.id.eqlButton)
                        curr.setBackgroundResource(R.drawable.gtea_main_transition);
                    else if(curr.getId() == R.id.menuButton)
                        curr.setBackgroundResource(R.drawable.gtea_main_transition_top);
                    curr.setTextColor(colorArray.get(COLOR_TEXT));
                }
                if(MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    morButton.setBackgroundResource(R.drawable.gtea_numpad_transition);
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

                case (BAN_THEME):
                    curr.setBackgroundResource(R.drawable.ban_numpad_transition);
                    break;

                case (SUSH_THEME):
                    curr.setBackgroundResource(R.drawable.sush_numpad_transition);
                    break;

                case (CLAS_THEME):
                    curr.setBackgroundResource(R.drawable.clas_numpad_transition);
                    break;

                case (KIWI_THEME):
                    curr.setBackgroundResource(R.drawable.kiwi_numpad_transition);
                    break;

                case (SNO_THEME):
                    curr.setBackgroundResource(R.drawable.sno_numpad_transition);
                    break;
                
                case (PINE_THEME):
                    curr.setBackgroundResource(R.drawable.pine_numpad_transition);
                    break;

                case (STRW_THEME):
                    curr.setBackgroundResource(R.drawable.strw_numpad_transition);
                    break;

                case (BDAY_THEME):
                    curr.setBackgroundResource(R.drawable.bday_numpad_transition);
                    break;

                case (PECH_THEME):
                    curr.setBackgroundResource(R.drawable.pech_numpad_transition);
                    break;

                case (GTEA_THEME):
                    curr.setBackgroundResource(R.drawable.gtea_numpad_transition);
                    break;

            }
        }

    }
}
