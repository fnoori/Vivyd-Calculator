package ca.vivyd.vivydcalculator.menu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ca.vivyd.vivydcalculator.R;


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

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);


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
