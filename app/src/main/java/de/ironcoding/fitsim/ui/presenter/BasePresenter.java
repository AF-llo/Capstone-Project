package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableField;

import javax.inject.Inject;
import javax.inject.Named;

import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.ui.model.AthleteHeaderViewModel;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 28.04.2017.
 */

public class BasePresenter extends MVPPresenter {

    @Inject
    AppSettings appSettings;

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    AthleteRepository athleteRepository;

    private Callback callback;

    public ObservableField<AthleteHeaderViewModel> athleteModel = new ObservableField<>();

    public BasePresenter() {
    }

    public BasePresenter(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectBasePresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (athleteModel.get() == null) {
            doInBackground(0, () -> athleteRepository.loadAthlete())
                    .addOnSuccess(athlete -> {
                        setAthlete(athlete);
                        onAthleteLoaded();
                    })
                    .execute();
        }
    }

    protected void onAthleteLoaded() {
    }

    protected Athlete getAthlete() {
        return athleteModel.get().getAthlete();
    }

    private void setAthlete(Athlete athlete) {
        athleteModel.set(new AthleteHeaderViewModel(athlete));
    }

    protected void updateAthlete(Athlete athlete) {
        if (athlete != null) {
            setAthlete(athlete);
            doInBackground(1, () -> {
                athleteRepository.updateAthlete(athlete);
                return null;
            }).execute();
        }
    }

    protected FitSimApp getFitSimApp() {
        return (FitSimApp) getContext().getApplicationContext();
    }

    protected void notifyCallbackShowBottomSheet() {
        if (callback != null) {
            callback.showBottomSheet();
        }
    }

    protected void notifyCallbackHideBottomSheet() {
        if (callback != null) {
            callback.hideBottomSheet();
        }
    }

    public interface Callback {
        void showBottomSheet();
        void hideBottomSheet();
    }
}
