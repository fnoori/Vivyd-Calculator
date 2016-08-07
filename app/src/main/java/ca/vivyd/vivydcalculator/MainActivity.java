package ca.vivyd.vivydcalculator;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.in_app_purchase_util.IabHelper;
import ca.vivyd.vivydcalculator.in_app_purchase_util.IabResult;
import ca.vivyd.vivydcalculator.in_app_purchase_util.Inventory;
import ca.vivyd.vivydcalculator.in_app_purchase_util.Purchase;
import ca.vivyd.vivydcalculator.menu.ThemesFragment;
import ca.vivyd.vivydcalculator.themes.Themer;


public class MainActivity extends AppCompatActivity {
    public static String CONTACT_EMAIL = "solutions.teamvivyd@gmail.com";
    static final String ITEM_SKU = "android.test.purchased";

    private Context context = this;
    private LinearLayout display;
    private EditText answerView;
    private TextView leftBraceCounter;
    private TextView rightBraceCounter;
    private Button morButton;


    // Indicates that only orientation has been changed, and the app has not exited
    private static int notExited = 0;

    public static float defaultTxtSize;
    public static float ansSize;
    public final static float minText_size = 34;
    public static int screenOrientation;
    public static float scale;
    public static float pixelCushion;

    private Themer themer;

    private Button upgradeButton;
    private static boolean mIsPremium;
    private static String base64EncodedPublicKey;
    private IabHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long oldTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        long startTime = System.currentTimeMillis();




        // boolean to check if user is premium
        mIsPremium = false;

        // app's public key
        base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5cF7H6fN2UQduXRx6RZEHue+nYclBlTDXgTHGKHlBHrBAYwcw9IFVJvEX4+HXqrGsHqGy4r975wb9lx3Zxt2PS0e/IwoGmZcN0i2epFB/CCTqC5ZKGTnfBKsGtMM+QYRQN73R83BkiytWW8buRR0Y+Ov8EN3exXgGGi4mRvPgddeMw6ehXqeFWTsbxhENMPT9jlXfeiNm13K/RGDtIUDdLwLDktuUB2VUNAtHtoAHQ6mqp63puVRzdpK8FE3Kq36jMLlbgFQnJaUXQtr4Lxp62Yl0IuO/RgnWyhgUPxqYMprlzkiM/oneeIruNP3Q0V5flbQGHeW9/w/8PJ+2kDuVwIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new
               IabHelper.OnIabSetupFinishedListener() {
                   public void onIabSetupFinished(IabResult result)
                   {
                       if (!result.isSuccess()) {
                           Log.d("IN_APP_PURCHASE", "In-app Billing setup failed: " +
                                   result);
                       } else {
                           Log.d("IN_APP_PURCHASE", "In-app Billing is set up OK");
                       }
                   }
               });


        upgradeButton = (Button) findViewById(R.id.adFreeButton);
        assert upgradeButton != null;
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mHelper.launchPurchaseFlow((Activity) context, ITEM_SKU, 10001,
                            mPurchaseFinishedListener, "mypurchasetoken");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        });





        final AdView mAdView = (AdView) findViewById(R.id.adView);
        assert mAdView != null;
/**
         * Real ad.
         * Uncomment following line for real ads, but make sure to comment out subsequent test ad
         * lines.
         */
         MobileAds.initialize(getApplicationContext(), "ca-app-pub-7966297715259412/8066957483");
         AdRequest request = new AdRequest.Builder().build();
         mAdView.loadAd(request);
        /**
         * Test Ad.
         * Uncomment following line for test ads, but make sure to comment out previous real ad line
         */
        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
           public void run() {
                Log.i("PERFORMANCE", "Ad is occuring here");
                Toast.makeText(MainActivity.this, "ad is occuring", Toast.LENGTH_LONG).show();
                AdRequest request = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                        // Remember to add a test device ID for each device that should request test ads.
                        // Device IDs are written to the system log by the Mobile Ads SDK, so you can find
                        // your device's ID by running your app and checking logcat.
                        .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
                        .addTestDevice("BD18BDB8D3C29637DDA85D96148E76B2")  // My moto G Decice Id, found in logcat
                        .addTestDevice("F3F9F302D12D212C9142645902C94D5C")  // Farzam moto E
                        .build();

                mAdView.loadAd(request);
            }
        }, 2000);
        */

        screenOrientation = getScreenOrientation();
        answerView = (EditText) findViewById(R.id.ansView);
        assert answerView != null;
        answerView.setLongClickable(false);
        answerView.setTextIsSelectable(false);
        answerView.setCursorVisible(true);
        answerView.setSingleLine();
        disableSoftKeyboard(answerView);
        display = (LinearLayout) findViewById(R.id.display);
        scale = this.getResources().getDisplayMetrics().density;
        pixelCushion = (int) (65*MainActivity.scale + 0.5f);

        EditText equationView = (EditText) findViewById(R.id.eqnView);
        assert equationView != null;
        disableSoftKeyboard(equationView);
        equationView.setFocusable(false);
        equationView.setSingleLine();
        leftBraceCounter = (TextView) findViewById(R.id.numLeftBrace);
        rightBraceCounter = (TextView) findViewById(R.id.numRightBrace);

        final Button degRadButton = (Button) findViewById(R.id.degRandButton);
        ArrayList<Button> commonButtons = setCommonButts();
        ArrayList<Button> commonOperands = setCommonOpps();
        final CalculatorButtons calcButtons = new CalculatorButtons(context, display, answerView,
                equationView, commonButtons, commonOperands, leftBraceCounter, rightBraceCounter,
                degRadButton);

        // For popupMenu
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            morButton = (Button) findViewById(R.id.morButton);
            moreButtonListener(morButton, calcButtons);
        }

        // For themes. This must come after any button initializations.
        SharedPreferences prefs = getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        Themer.CURRENT_THEME = prefs.getInt("Theme", Themer.MANGO_THEME);
        themer = new Themer(this, commonButtons, commonOperands, morButton);
        setTheme(themer);


        /** If the current orientation is landscape, initialize advanced buttons
         *  Must come after initialization of themer or could crash in odd case where app launches
         *  in landscape
         */
        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            ArrayList<Button> scienceButts = setScienceButts();
            calcButtons.addAdvanceOperands(scienceButts);
            notExited = 1;
        }


        /**
         *  Values that should persist when the app switches to landscape mode should be placed
         *  and accounted for here.
         */
        if (notExited == 1){
            calcButtons.countNumberOfBrackets(answerView.getText().toString());
            CalculatorButtons.openBracket = prefs.getInt("openBracket", 0);
            CalculatorButtons.closeBracket = prefs.getInt("closeBracket", 0);
            leftBraceCounter.setText(String.valueOf(CalculatorButtons.openBracket));
            rightBraceCounter.setText(String.valueOf(CalculatorButtons.closeBracket));
            if (prefs.getString("deg_rad_state", CalculatorButtons.RADIAN).equals(CalculatorButtons.RADIAN)){
                CalculatorButtons.setRad(degRadButton);
            }
            else if (prefs.getString("deg_rad_state", CalculatorButtons.RADIAN).equals(CalculatorButtons.DEGREE)){
                CalculatorButtons.setDeg(degRadButton);
            }
        }

        if (notExited == 0) {
            defaultTxtSize = pixelsToSp(MainActivity.this, answerView.getTextSize());
        }
        else if (screenOrientation == Configuration.ORIENTATION_PORTRAIT &&
                ansSize != 0) {
            answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, ansSize);
        }

        long endOfOnStartMainActivity = System.currentTimeMillis();
        long total = endOfOnStartMainActivity - startTime;
        //Log.d("TIME_BEFORE_AD_3", total+"");
        Log.d("TIME_AFTER_AD_3", total+"");

        long totTime = System.currentTimeMillis() - oldTime;
        Log.i("PERFORMANCE", " onCreate took this long: " + totTime);
        //Toast.makeText(this, "onCreate took this long: " + totTime, Toast.LENGTH_LONG).show();

    }














    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data){
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                consumeItem();
                upgradeButton.setEnabled(false);
            }

        }
    };

    public void consumeItem() {
        try {
            mHelper.queryInventoryAsync(mReceivedInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {


            if (result.isFailure()) {
                // Handle failure
            } else {
                try {
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                            mConsumeFinishedListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        Log.d("CONSUME", "FINISHED");
                    } else {
                        // handle error
                    }
                }
            };





















    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) try {
            mHelper.dispose();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
    }

    @Override
    public void onResume(){
        long oldTime = System.currentTimeMillis();

        super.onResume();

        disableSoftKeyboard(answerView);
        FrameLayout numArea = (FrameLayout) findViewById(R.id.num_area);
        if (numArea != null)
            numArea.removeView(findViewById(R.id.popMenu));
        if (ThemesFragment.theme_isChanged == 1) {
            setTheme(themer);
            ThemesFragment.theme_isChanged = 0;
        }

        if (answerView.getText().toString().matches("")){
            leftBraceCounter.setText("0");
            rightBraceCounter.setText("0");
        }

        long endTime = System.currentTimeMillis() - oldTime;
        //Toast.makeText(this, "onResume took this long: " + endTime, Toast.LENGTH_LONG).show();
        Log.i("PERFORMANCE", " onResume took this long: " + endTime);
    }

    @Override
    public void onStop(){
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Theme", Themer.CURRENT_THEME);
        editor.putInt("openBracket", CalculatorButtons.openBracket);
        editor.putInt("closeBracket", CalculatorButtons.closeBracket);
        editor.putString("deg_rad_state", CalculatorButtons.DEG_RAND_STATE);
        editor.apply();
    }

    // Button listener for "more button"
    public void moreButtonListener(Button moreButton, final CalculatorButtons calcButtons){
        if (moreButton != null) {
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FrameLayout numArea = (FrameLayout) findViewById(R.id.num_area);
                    assert numArea != null;

                    TableRow bottomRow = (TableRow) findViewById(R.id.bottomRow);
                    assert bottomRow != null;
                    int popHeight = 4 * bottomRow.getHeight();

                    final View popMenu = getLayoutInflater().inflate(R.layout.activity_popup, null);
                    popMenu.setMinimumHeight(popHeight);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Slide mSlide = new Slide();
                        mSlide.setDuration(150);
                        TransitionManager.beginDelayedTransition(numArea, mSlide);
                    } else {
                        LayoutTransition transition = new LayoutTransition();
                        transition.setDuration(100);
                        numArea.setLayoutTransition(transition);
                    }
                    numArea.addView(popMenu);

                    ArrayList<Button> advancedOperands = setScienceButts();
                    themer.setSciButtsAnim(advancedOperands);
                    calcButtons.addAdvanceOperands(advancedOperands);

                    //Actual Back button
                    Button bakButton = (Button) findViewById(R.id.bakButton);
                    assert bakButton != null;
                    ImageView bakImg = (ImageView) findViewById(R.id.imageBak);
                    bakButton.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
                    assert bakImg != null;
                    bakImg.setColorFilter(Themer.colorArray.get(Themer.COLOR_TEXT_SCREEN));
                    bakButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Slide mSlide = new Slide();
                                mSlide.setDuration(200);
                                TransitionManager.beginDelayedTransition(numArea, mSlide);
                            }
                            numArea.removeView(popMenu);
                        }
                    });
                }
            });
        }
    }

    private ArrayList<Button> setCommonButts() {
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

        return new ArrayList<Button>() {{
            add(zeroButton); add(oneButton); add(twoButton); add(threeButton);
            add(fourButton); add(fiveButton); add(sixButton); add(sevenButton);
            add(eightButton); add(nineButton); add(periodButton);
        }};
    }

    private ArrayList<Button> setCommonOpps() {
        final Button addButton = (Button) findViewById(R.id.addButton);
        final Button minusButton = (Button) findViewById(R.id.minusButton);
        final Button multiplyButton = (Button) findViewById(R.id.multButton);
        final Button divideButton = (Button) findViewById(R.id.divButton);
        final Button openBracketButton = (Button) findViewById(R.id.openBrace);
        final Button closeBracketButton = (Button) findViewById(R.id.closeBrace);
        final Button percentageButton = (Button) findViewById(R.id.prcntButton);

        final Button deleteButton = (Button) findViewById(R.id.delButton);
        final Button clearButton = (Button) findViewById(R.id.clrButton);
        final Button equalButton = (Button) findViewById(R.id.eqlButton);
        final Button menuButton = (Button) findViewById(R.id.menuButton);

        return new ArrayList<Button>(){{
            add(addButton); add(minusButton); add(multiplyButton); add(divideButton);
            add(openBracketButton); add(closeBracketButton); add(percentageButton);
            add(clearButton); add(deleteButton); add(equalButton); add(menuButton);
        }};
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

        return new ArrayList<Button>(){{
            add(tanButton); add(sinButton); add(cosButton);
            add(logButton); add(lnButton); add(piButton);
            add(eButton); add(squareRootButton);  add(powerButton);
            add(squaredButton); add(factorialButton); add(nthRootButton);
            add(cubeButton); add(inverseButton); add(eeButton);
            add(var1Button); add(var2Button); add(var3Button);
            add(ansButton);
        }};
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
        int colorComp = Themer.colorArray.get(Themer.COLOR_COMP);
        int colorBG = Themer.colorArray.get(Themer.COLOR_BACKGROUND);

        View wholeView;
        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE)
            wholeView = (RelativeLayout) findViewById(R.id.wholeViewLAND);
        else
            wholeView = (LinearLayout) findViewById(R.id.wholeView);
        assert wholeView != null;
        TextView eqnView = (TextView) findViewById(R.id.eqnView);
        assert eqnView != null;
        Button degRandButton = (Button) findViewById(R.id.degRandButton);
        assert degRandButton != null;
        Button var1Button = (Button) findViewById(R.id.var1Button);
        TextView numLeftBrace = (TextView) findViewById(R.id.numLeftBrace);
        assert numLeftBrace != null;
        TextView numRightBrace = (TextView) findViewById(R.id.numRightBrace);
        assert numRightBrace != null;
        // Warning says that wholeView is always true, but in an odd case it tried to set backgroundcolor
        // to a null reference.
        if (wholeView != null)
            wholeView.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_BACKGROUND));

        display.setBackgroundColor(colorAccent);
        eqnView.setTextColor(colorTextScreen);
        answerView.setTextColor(colorTextScreen);
        if (var1Button != null) {
            final Button var2Button = (Button) findViewById(R.id.var2Button);
            final Button var3Button = (Button) findViewById(R.id.var3Button);
            var1Button.setTextColor(colorAccent);
            assert var2Button != null;
            var2Button.setTextColor(colorAccent);
            assert var3Button != null;
            var3Button.setTextColor(colorAccent);
            if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
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
            numLeftBrace.setTextColor(colorComp);
            numRightBrace.setTextColor(colorComp);
        }

        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            ImageView imageMor = (ImageView) findViewById(R.id.imageMor);
            assert imageMor != null;
            imageMor.setColorFilter(colorAccent);
        } else if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE){
            ArrayList<Button> advancedOperands = setScienceButts();
            themer.setSciButtsAnim(advancedOperands);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorBG);
        }

    }

    public int getScreenOrientation()
    {
        android.graphics.Point size = new android.graphics.Point();
        Display getOrient = getWindowManager().getDefaultDisplay();
        getOrient.getSize(size);
        int width = size.x;
        int height = size.y;
        int orientation;
        if(width < height){
            orientation = Configuration.ORIENTATION_PORTRAIT;
        }else {
            orientation = Configuration.ORIENTATION_LANDSCAPE;
        }
        return orientation;
    }

    public static float pixelsToSp(Context context, float px){
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }
}
