package de.ironcoding.fitsim.ui.presenter;

/**
 * Created by larsl on 28.04.2017.
 */

public class ProfilePresenter extends BasePresenter {

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectProfilePresenter(this);
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        // TODO: 28.04.2017
    }

}
