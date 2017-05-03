package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import javax.inject.Inject;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.ui.fragments.GymFragment;
import de.ironcoding.fitsim.ui.fragments.MoreFragment;
import de.ironcoding.fitsim.ui.fragments.NutritionFragment;
import de.ironcoding.fitsim.ui.fragments.ProfileFragment;
import de.ironcoding.fitsim.util.AnalyticsLogger;
import de.ironcoding.fitsim.util.Jobber;

/**
 * Created by larsl on 30.04.2017.
 */

public class MainPresenter extends BasePresenter implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainCallback mainCallback;

    @Inject
    Jobber jobber;

    @MVPIncludeToState
    private ObservableInt selectedId = new ObservableInt();

    public MainPresenter(MainCallback mainCallback) {
        this.mainCallback = mainCallback;
        selectedId.set(R.id.action_profile);
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectMainPresenter(this);
        jobber.scheduleRefreshBodyJobIfNotScheduled();
        jobber.scheduleRelaxMusclesJobIfNotScheduled();
        jobber.scheduleHighscoreJogEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
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
                notifyMainCallback(ProfileFragment.getInstance(), item);
                analyticsLogger.logScreen(AnalyticsLogger.SCREEN_PROFILE);
                return true;
            case R.id.action_gym:
                notifyMainCallback(GymFragment.getInstance(), item);
                analyticsLogger.logScreen(AnalyticsLogger.SCREEN_GYM);
                return true;
            case R.id.action_nutrition:
                notifyMainCallback(NutritionFragment.getInstance(), item);
                analyticsLogger.logScreen(AnalyticsLogger.SCREEN_NUTRITION);
                return true;
            case R.id.action_more:
                notifyMainCallback(MoreFragment.getInstance(), item);
                analyticsLogger.logScreen(AnalyticsLogger.SCREEN_MORE);
                return true;
        }
        return false;
    }

    private void notifyMainCallback(Fragment fragment, MenuItem item) {
        if (mainCallback != null) {
            mainCallback.itemSelected(fragment, item);
        }
    }

    public interface MainCallback {
        void itemSelected(Fragment fragment, MenuItem item);
    }
}
