package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableInt;

import javax.inject.Inject;

import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.repository.local.LocalAthleteRepository;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 01.05.2017.
 */

public class OnboardingPresenter extends MVPPresenter {

    @Inject
    AppSettings appSettings;

    @Inject
    LocalAthleteRepository localAthleteRepository;

    public ObservableInt gender = new ObservableInt(Athlete.FEMALE);

    public ObservableString bodyType = new ObservableString(BodyType.ENDOMORPH);

    public ObservableString age = new ObservableString(String.valueOf(Body.DEFAULT_AGE));

    public ObservableString size = new ObservableString(String.valueOf(Body.DEFAULT_SIZE_MALE));

    private OnboardingCallback callback;

    public OnboardingPresenter(OnboardingCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        ((FitSimApp)getContext().getApplicationContext()).getAppComponent().injectOnboardingPresenter(this);
        if (isAthleteCreated()) {
            notifyCallbackFinishOnboarding();
        }
    }

    public void  createAthlete() {
        @BodyType.Name String type = bodyType.get();
        @Athlete.Gender int gender = this.gender.get();
        localAthleteRepository.createInitialAthlete(type, gender, Integer.valueOf(age.get()), Float.valueOf(size.get()));
        notifyCallbackFinishOnboarding();
    }

    private boolean isAthleteCreated() {
        return appSettings.getAthleteIdFromSettings() != -1;
    }

    private void notifyCallbackFinishOnboarding() {
        if (callback != null) {
            callback.finishOnboarding();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callback != null) {
            callback = null;
        }
    }

    public interface OnboardingCallback {
        void finishOnboarding();
    }

}
