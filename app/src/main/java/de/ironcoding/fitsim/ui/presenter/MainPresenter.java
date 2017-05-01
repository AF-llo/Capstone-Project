package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.ui.fragments.GymFragment;
import de.ironcoding.fitsim.ui.fragments.MoreFragment;
import de.ironcoding.fitsim.ui.fragments.NutritionFragment;
import de.ironcoding.fitsim.ui.fragments.ProfileFragment;

/**
 * Created by larsl on 30.04.2017.
 */

public class MainPresenter extends BasePresenter implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainCallback mainCallback;

    @MVPIncludeToState
    private ObservableInt selectedId = new ObservableInt();

    public MainPresenter(MainCallback mainCallback) {
        this.mainCallback = mainCallback;
        selectedId.set(R.id.action_profile);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public interface MainCallback {
        void showFragment(Fragment fragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mainCallback != null) {
            mainCallback = null;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int newSelectedId = item.getItemId();
        if (newSelectedId == selectedId.get()) {
            return false;
        }
        selectedId.set(newSelectedId);
        switch (item.getItemId()) {
            case R.id.action_profile:
                notifyMainCallback(ProfileFragment.getInstance());
                return true;
            case R.id.action_gym:
                notifyMainCallback(GymFragment.getInstance());
                return true;
            case R.id.action_nutrition:
                notifyMainCallback(NutritionFragment.getInstance());
                return true;
            case R.id.action_more:
                notifyMainCallback(MoreFragment.getInstance());
                return true;
        }
        return false;
    }

    private void notifyMainCallback(Fragment fragment) {
        if (mainCallback != null) {
            mainCallback.showFragment(fragment);
        }
    }
}
