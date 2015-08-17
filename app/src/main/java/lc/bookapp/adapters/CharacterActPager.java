package lc.bookapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lc.bookapp.fragments.CharacterBasicFragment;
import lc.bookapp.fragments.CharacterTagFragment;
import lc.bookapp.fragments.LocationsFragment;
import lc.bookapp.fragments.OverviewFragment;

public class CharacterActPager extends FragmentStatePagerAdapter {
    public CharacterActPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1 ) return new CharacterTagFragment();
        return new CharacterBasicFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    String[] data = {"Basic" , "Tags" , "Relations"};
    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}
