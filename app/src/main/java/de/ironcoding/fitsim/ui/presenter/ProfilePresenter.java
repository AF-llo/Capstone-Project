package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.ui.model.ProfileFragmentItem;
import de.ironcoding.fitsim.util.AnalyticsLogger;

/**
 * Created by larsl on 03.05.2017.
 */

public class ProfilePresenter extends BasePresenter implements TabLayout.OnTabSelectedListener {

    @MVPIncludeToState
    public ObservableArrayList<ProfileFragmentItem> items = new ObservableArrayList<>();

    public ObservableInt selectedItem = new ObservableInt();

    public FragmentManager fragmentManager;

    public ProfilePresenter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getContext() == null) {
            return;
        }
        if (items.size() == 0) {
            items.add(new ProfileFragmentItem(getContext().getString(R.string.highscore), AnalyticsLogger.SCREEN_HIGHSCORE));
            items.add(new ProfileFragmentItem(getContext().getString(R.string.body), AnalyticsLogger.SCREEN_BODY));
            items.add(new ProfileFragmentItem(getContext().getString(R.string.muscles), AnalyticsLogger.SCREEN_MUSCLES));
            items.add(new ProfileFragmentItem(getContext().getString(R.string.nutrition), AnalyticsLogger.SCREEN_NUTRITION));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int newPosition = tab.getPosition();
        if (newPosition != selectedItem.get()) {
            setSelectedItem(tab.getPosition());
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem.set(selectedItem);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentManager = null;
    }

    public interface ProfileItem {
        String getTitle();
    }
}
