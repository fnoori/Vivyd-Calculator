package ca.vivyd.vivydcalculator;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.themes.Themer;


public class MainActivity extends AppCompatActivity {
    public static int startTime = 0;
    public static int afterInitAllButtonsTime = 0;

    public static String CONTACT_EMAIL = "solutions.teamvivyd@gmail.com";

    private Context context = this;
    EditText equationView;
    private EditText answerView;
    private TextView leftBraceCounter;
    private TextView rightBraceCounter;

    // The following stuff is code related to the popupwindow feature
    private Button morButton;
    private Button menuButton;
    static int popHeight = 0;
    static int popWidth = 0;

    private static int notExited = 0;

    Themer themer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        startTime = (int) System.currentTimeMillis();

        answerView = (EditText) findViewById(R.id.ansView);
        disableSoftKeyboard(answerView);
        answerView.setCursorVisible(true);
        answerView.setSingleLine();

        equationView = (EditText) findViewById(R.id.eqnView);
        disableSoftKeyboard(equationView);
        equationView.setSingleLine();
        leftBraceCounter = (TextView) findViewById(R.id.numLeftBrace);
        rightBraceCounter = (TextView) findViewById(R.id.numRightBrace);

        /* START COMMON BUTTONS */
        final Button zeroButton = (Button) findViewById(R.id.zeroButton);
        final Button oneButton = (Button) findViewById(R.id.oneButton);
        final Button twoButton = (Button) findViewById(R.id.twoButton);
        final Button threeButton = (Button) findViewById(R.id.threeButton);
        final Button fourButton = (Button) findViewById(R.id.fourButton);
        final Button fiveButton = (Button) findViewById(R.id.fiveButton);
        final Button sixButton = (Button) findViewById(R.id.sixButton);
        final Button sevenButton = (Button) findViewById(R.id.sevenButton);
        final Button eightButton = (Button) findViewById(R.id.eightButton);
        final Button nineButton = (Button) findViewById(R.id.nineButton);
        final Button periodButton = (Button) findViewById(R.id.dotButton);
        /* END OF COMMON BUTTONS */

        /* START OF COMMON OPERATOR BUTTONS */
        final Button addButton = (Button) findViewById(R.id.addButton);
        final Button minusButton = (Button) findViewById(R.id.minusButton);
        final Button multiplyButton = (Button) findViewById(R.id.multButton);
        final Button divideButton = (Button) findViewById(R.id.divButton);
        final Button openBracketButton = (Button) findViewById(R.id.openBrace);
        final Button closeBracketButton = (Button) findViewById(R.id.closeBrace);
        final Button percentageButton = (Button) findViewById(R.id.prcntButton);
        final Button degreeRadiaButton = (Button) findViewById(R.id.degRandButton);
        /* END OF COMMON OPERATOR BUTTONS */

        final Button deleteButton = (Button) findViewById(R.id.delButton);
        final Button clearButton = (Button) findViewById(R.id.clrButton);
        final Button equalButton = (Button) findViewById(R.id.eqlButton);
        final Button menuButton = (Button) findViewById(R.id.menuButton);

        ArrayList<Button> commonButtons = new ArrayList<Button>() {{
            add(zeroButton); add(oneButton); add(twoButton); add(threeButton);
            add(fourButton); add(fiveButton); add(sixButton); add(sevenButton);
            add(eightButton); add(nineButton); add(periodButton);
        }};

        ArrayList<Button> commonOperands = new ArrayList<Button>(){{
            add(addButton); add(minusButton); add(multiplyButton); add(divideButton);
            add(openBracketButton); add(closeBracketButton); add(percentageButton);
            add(clearButton); add(deleteButton); add(equalButton); add(menuButton);
        }};

        final CalculatorButtons calcButtons = new CalculatorButtons(context, answerView,
                equationView, commonButtons, commonOperands, leftBraceCounter, rightBraceCounter,
                degreeRadiaButton);

        // For popupMenu
        if (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            morButton = (Button) findViewById(R.id.morButton);
            moreButtonListener(morButton, calcButtons);
        }

        // ad-related stuff
        AdView mAdView = (AdView) findViewById(R.id.adView);
        // We have to use 'test-ads' for developing, lest google overlords think we're cheap chimps
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                // Remember to add a test device ID for each device that should request test ads.
                // Device IDs are written to the system log by the Mobile Ads SDK, so you can find
                // your device's ID by running your app and checking logcat.
                .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
                .addTestDevice("BD18BDB8D3C29637DDA85D96148E76B2")  // My moto G Decice Id, found in logcat
                .addTestDevice("F3F9F302D12D212C9142645902C94D5C")  // Farzam moto E
                .build();
        // To use a real ad, which is discouraged by google when developing, use this:
        // AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(request);

        ImageButton inspButton = (ImageButton) findViewById(R.id.inspireButton);
        assert inspButton != null;
        inspButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder inspBuilder = new AlertDialog.Builder(context);
                inspBuilder.setMessage("Thank you for your support!\n\nWe need your feedback to get rid of all these bugs. " +
                        "Email us and share your suggestions, issues, and/or life problems.");
                inspBuilder.setCancelable(true);

                inspBuilder.setPositiveButton(
                        "Email",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", MainActivity.CONTACT_EMAIL, null));
                                //i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
                                try {
                                    startActivity(Intent.createChooser(i, "Send feedback..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    // Toast.makeText(OptFragment.this, "You don't have an email client!", Toast.LENGTH_LONG).show();
                                }
                                dialog.cancel();
                            }
                        });

                inspBuilder.setNegativeButton(
                        "cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = inspBuilder.create();
                alert.show();
            }
        });

        // For themes. This must come after any button initializations.
        SharedPreferences prefs = getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        themer = new Themer(this, commonButtons, commonOperands);
        Themer.CURRENT_THEME = prefs.getInt("Theme", 1);
        setTheme(themer);

        /** If the current orientation is landscape, initialize advanced buttons
        / Must come after initialization of themer or could crash in odd case where app launches
        / In landscape
        **/
        if (getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            ArrayList<Button> scienceButts = setScienceButts();
            calcButtons.addAdvanceOperands(scienceButts);
            notExited = 1;
        }

        if (notExited == 1){
            calcButtons.countNumberOfBrackets(answerView.getText().toString());
            CalculatorButtons.openBrace = prefs.getInt("openBrace", 0);
            CalculatorButtons.closeBrace = prefs.getInt("closeBrace", 0);
            leftBraceCounter.setText(String.valueOf(CalculatorButtons.openBrace));
            rightBraceCounter.setText(String.valueOf(CalculatorButtons.closeBrace));
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        disableSoftKeyboard(answerView);
        LinearLayout popSpace = (LinearLayout) findViewById(R.id.popSpace);
        assert popSpace != null;
        popSpace.removeView(findViewById(R.id.popMenu));
        setTheme(themer);

        if (answerView.getText().toString().matches("")){
            leftBraceCounter.setText("0");
            rightBraceCounter.setText("0");
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Theme", Themer.CURRENT_THEME);
        editor.putInt("openBrace", CalculatorButtons.openBrace);
        editor.putInt("closeBrace", CalculatorButtons.closeBrace);
        editor.apply();
    }

    // Button listener for "more button"
    public void moreButtonListener(Button moreButton, final CalculatorButtons calcButtons){
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LinearLayout popSpace = (LinearLayout) findViewById(R.id.popSpace);
                LinearLayout bottomRow = (LinearLayout) findViewById(R.id.bottomRow);
                assert bottomRow != null;
                int popHeight =  4*bottomRow.getHeight();
                assert popSpace != null;
                int popWidth = popSpace.getWidth();
                final View popMenu = getLayoutInflater().inflate(R.layout.activity_popup, null);
                popMenu.setMinimumHeight(popHeight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Slide mSlide = new Slide();
                    mSlide.setDuration(200);
                    TransitionManager.beginDelayedTransition(popSpace, mSlide);
                }
                else {
                    LayoutTransition transition = new LayoutTransition();
                    transition.setDuration(100);
                    popSpace.setLayoutTransition(transition);
                }
                popSpace.addView(popMenu);

                ArrayList<Button> advancedOperands = setScienceButts();
                themer.setSciButtsAnim(advancedOperands);
                calcButtons.addAdvanceOperands(advancedOperands);

                //Actual Back button
                Button bakButton = (Button) findViewById(R.id.bakButton);
                assert bakButton != null;
                bakButton.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
                bakButton.setTextColor(Themer.colorArray.get(Themer.COLOR_TEXT_SCREEN));
                bakButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Slide mSlide = new Slide();
                            mSlide.setDuration(200);
                            TransitionManager.beginDelayedTransition(popSpace, mSlide);
                        }
                        popSpace.removeView(popMenu);
                    }
                });
            }
        });
    }

    private ArrayList<Button> setScienceButts() {
        final Button tanButton = (Button) findViewById(R.id.tangentButton);
        final Button sinButton = (Button) findViewById(R.id.sineButton);
        final Button cosButton = (Button) findViewById(R.id.cosineButton);
        final Button logButton = (Button) findViewById(R.id.logButton);
        final Button lnButton = (Button) findViewById(R.id.lnButton);
        final Button piButton = (Button) findViewById(R.id.piButton);
        final Button eButton = (Button) findViewById(R.id.eButton);
        final Button squareRootButton = (Button) findViewById(R.id.sqrtButton);
        final Button powerButton = (Button) findViewById(R.id.pwrButton);
        final Button squaredButton = (Button) findViewById(R.id.sqrButton);
        final Button factorialButton = (Button) findViewById(R.id.facttButton);
        final Button nthRootButton = (Button) findViewById(R.id.usrDefinedRoot);
        final Button cubeButton = (Button) findViewById(R.id.cubButton);
        final Button inverseButton = (Button) findViewById(R.id.oneOverxButton);
        final Button eeButton = (Button) findViewById(R.id.powerOfTenButton);
        final Button ansButton = (Button) findViewById(R.id.ansButton);

        // Variable buttons

        final Button var1Button = (Button) findViewById(R.id.var1Button);
        final Button var2Button = (Button) findViewById(R.id.var2Button);
        final Button var3Button = (Button) findViewById(R.id.var3Button);
        assert var1Button != null;
        var1Button.setTextColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
        assert var2Button != null;
        var2Button.setTextColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
        assert var3Button != null;
        var3Button.setTextColor(Themer.colorArray.get(Themer.COLOR_ACCENT));

        ArrayList<Button> advancedOperands = new ArrayList<Button>(){{
            add(tanButton); add(sinButton); add(cosButton);
            add(logButton); add(lnButton); add(piButton);
            add(eButton); add(squareRootButton);  add(powerButton);
            add(squaredButton); add(factorialButton); add(nthRootButton);
            add(cubeButton); add(inverseButton); add(eeButton);
            add(var1Button); add(var2Button); add(var3Button);
            add(ansButton);
        }};

        return advancedOperands;
    }

    public void disableSoftKeyboard(final EditText v) {
        v.setRawInputType(InputType.TYPE_CLASS_TEXT);
        v.setTextIsSelectable(true);
    }

    public void setTheme(Themer lemur) {

        lemur.setButtAnimation();
        int colorAccent = Themer.colorArray.get(Themer.COLOR_ACCENT);
        int colorTextScreen = Themer.colorArray.get(Themer.COLOR_TEXT_SCREEN);
        int colorOppTray = Themer.colorArray.get(Themer.COLOR_OPPTRAY);
        int colorNumpad = Themer.colorArray.get(Themer.COLOR_NUMPAD);
        int dark_colorNumpad = Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK);


        LinearLayout backgroundView = (LinearLayout) findViewById(R.id.backgroundView);
        assert backgroundView != null;
        TextView eqnView = (TextView) findViewById(R.id.eqnView);
        assert eqnView != null;
        EditText ansView = (EditText) findViewById(R.id.ansView);
        assert ansView != null;
        Button degRandButton = (Button) findViewById(R.id.degRandButton);
        assert degRandButton != null;
        Button var1Button = (Button) findViewById(R.id.var1Button);
        TextView numLeftBrace = (TextView) findViewById(R.id.numLeftBrace);
        assert numLeftBrace != null;
        TextView numRightBrace = (TextView) findViewById(R.id.numRightBrace);
        assert numRightBrace != null;


        backgroundView.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_BACKGROUND));
        eqnView.setBackgroundColor(colorAccent);
        eqnView.setTextColor(colorTextScreen);
        ansView.setBackgroundColor(colorAccent);
        ansView.setTextColor(colorTextScreen);
        degRandButton.setBackgroundColor(colorAccent);
        if (var1Button != null) {
            final Button var2Button = (Button) findViewById(R.id.var2Button);
            final Button var3Button = (Button) findViewById(R.id.var3Button);
            var1Button.setTextColor(colorAccent);
            assert var2Button != null;
            var2Button.setTextColor(colorAccent);
            assert var3Button != null;
            var3Button.setTextColor(colorAccent);
            if (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
                Button bakButton = (Button) findViewById(R.id.bakButton);
                assert bakButton != null;
                bakButton.setBackgroundColor(colorAccent);
                bakButton.setTextColor(colorTextScreen);
            }
        }
        if (numLeftBrace.getText().equals(numRightBrace.getText())) {
            numLeftBrace.setTextColor(dark_colorNumpad);
            numRightBrace.setTextColor(dark_colorNumpad);
        }
        else {
            numLeftBrace.setTextColor(colorAccent);
            numRightBrace.setTextColor(colorAccent);
        }


        // If possible, need to move this to Themer.setButtAnimations() where the rest of the drawables
        // are set
        if (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            ImageView imageMor = (ImageView) findViewById(R.id.imageMor);
            assert imageMor != null;
            imageMor.setColorFilter(colorAccent);

           // morButton.setTextColor(colorAccent);
            switch (Themer.CURRENT_THEME){
                case Themer.BLUE_THEME:
                    morButton.setBackgroundResource(R.drawable.blue_numpad_transition);
                    break;
                case Themer.PINK_THEME:
                    morButton.setBackgroundResource(R.drawable.pink_numpad_transition);
                    break;
                case Themer.MANGO_THEME:
                    morButton.setBackgroundResource(R.drawable.mango_numpad_transition);
                    break;
                case Themer.CHOC_THEME:
                    morButton.setBackgroundResource(R.drawable.choc_numpad_transition);
                    break;
                case Themer.SUND_THEME:
                    morButton.setBackgroundResource(R.drawable.sund_numpad_transition);
                    break;
                case Themer.WTRM_THEME:
                    morButton.setBackgroundResource(R.drawable.wtrm_numpad_transition);
                    break;
                case Themer.RVEL_THEME:
                    morButton.setBackgroundResource(R.drawable.rvel_numpad_transition);
                    break;
            }
        }
        else {
            LinearLayout adLayout = (LinearLayout)findViewById(R.id.adLayout);
            assert adLayout != null;
            adLayout.setBackgroundColor(colorAccent);
            ArrayList<Button> advancedOperands = setScienceButts();
            themer.setSciButtsAnim(advancedOperands);
        }
    }

    public int getScreenOrientation()
    {
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }

}
