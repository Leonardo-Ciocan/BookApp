package lc.bookapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lc.bookapp.fragments.CharacterBasicFragment;
import lc.bookapp.fragments.OverviewFragment;

public class CharacterActPager extends FragmentStatePagerAdapter {
    public CharacterActPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new CharacterBasicFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "Basic" : "Relationships";
    }
}