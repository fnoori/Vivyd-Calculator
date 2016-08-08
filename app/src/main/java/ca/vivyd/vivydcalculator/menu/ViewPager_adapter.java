package ca.vivyd.vivydcalculator.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Amair on 6/15/2016.
 */
public class ViewPager_adapter extends FragmentPagerAdapter {

    String[] tabNames = new String[3];


    public ViewPager_adapter(FragmentManager fm){
        super(fm);
        tabNames[0] = "Themes";
        //tabNames[1] = "Ad-Free";
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ThemesFragment();
            //case 1:
            //    return new adfFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public int getCount() {
        return 1;
    }
}
