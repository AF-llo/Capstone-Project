package de.ironcoding.fitsim.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import de.appsfactory.mvplib.view.MVPFragmentAdapter;
import de.ironcoding.fitsim.ui.fragments.BodyFragment;
import de.ironcoding.fitsim.ui.fragments.HighscoreFragment;
import de.ironcoding.fitsim.ui.presenter.ProfilePresenter;

/**
 * Created by larsl on 03.05.2017.
 */

public class ProfilePagerAdapter extends MVPFragmentAdapter {
    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(Object o, int i) {
        if (i == 0) {
            return HighscoreFragment.getInstance();
        } else if (i == 1) {
            return BodyFragment.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((ProfilePresenter.ProfileItem)getItems().get(position)).getTitle();
    }
}
