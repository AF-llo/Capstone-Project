package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.widget.Toast;

import javax.inject.Inject;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.R;
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

    private static final int MIN_AGE = 1;

    private static final int MAX_AGE = 100;

    @Inject
    AppSettings appSettings;

    @Inject
    LocalAthleteRepository localAthleteRepository;

    @MVPIncludeToState
    public ObservableInt gender = new ObservableInt(Athlete.MALE);

    @MVPIncludeToState
    public ObservableString bodyType = new ObservableString(BodyType.EKTOMORPH);

    @MVPIncludeToState
    public ObservableString age = new ObservableString();

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
        if (getContext() == null) {
            return;
        }
        if (TextUtils.isEmpty(age.get())) {
            Toast.makeText(getContext(), R.string.invalid_age, Toast.LENGTH_LONG).show();
            return;
        }
        int age = Integer.valueOf(this.age.get());
        if (age < MIN_AGE | age > MAX_AGE) {
            Toast.makeText(getContext(), R.string.invalid_age, Toast.LENGTH_LONG).show();
            return;
        }
        @BodyType.Name String type = bodyType.get();
        @Athlete.Gender int gender = this.gender.get();
        localAthleteRepository.createInitialAthlete(type, gender, age, Body.DEFAULT_SIZE_MALE);
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

    public void setBodyType(@BodyType.Name String bodyType) {
        this.bodyType.set(bodyType);
    }

    public void selectGender(@Athlete.Gender int gender) {
        this.gender.set(gender);
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
