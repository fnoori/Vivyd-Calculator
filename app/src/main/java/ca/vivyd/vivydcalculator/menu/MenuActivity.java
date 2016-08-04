package ca.vivyd.vivydcalculator.menu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.themes.Themer;


/**
 * Created by Amair on 5/29/2016.
 */
public class MenuActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Themer.colorArray.get(Themer.COLOR_BACKGROUND)));
        getSupportActionBar().setElevation(0);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        assert tabs != null;
        tabs.setDistributeEvenly(true);
        tabs.setSelectedIndicatorColors(Themer.colorArray.get(Themer.COLOR_ACCENT));
        tabs.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_BACKGROUND));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Themer.colorArray.get(Themer.COLOR_BACKGROUND));
        }

        viewPager.setAdapter(new ViewPager_adapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.right_out, R.anim.left_in);
                return true;
        }
        return true;
    }
}
