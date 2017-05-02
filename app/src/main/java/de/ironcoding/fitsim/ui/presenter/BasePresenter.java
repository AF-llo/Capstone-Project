package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.CallSuper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Named;

import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.DbRepositoryModule;
import de.ironcoding.fitsim.app.injection.FirebaseModule;
import de.ironcoding.fitsim.firebase.model.UserHighscore;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Highscore;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.ui.model.AthleteHeaderViewModel;
import de.ironcoding.fitsim.util.AppSettings;
import timber.log.Timber;

/**
 * Created by larsl on 28.04.2017.
 */

public class BasePresenter extends MVPPresenter {

    protected int loaderId = 0;

    @Inject
    AppSettings appSettings;

    @Inject
    @Named(DbRepositoryModule.REPOSITORY_DB)
    AthleteRepository athleteRepository;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    @Named(FirebaseModule.CHILD_HIGHSCORE)
    DatabaseReference highscoreDatabaseReference;

    private Callback callback;

    public ObservableField<AthleteHeaderViewModel> athleteModel = new ObservableField<>();

    public ObservableBoolean athleteLoaded = new ObservableBoolean(false);

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
            doInBackground(loaderId, () -> athleteRepository.loadAthlete())
                    .addOnSuccess(athlete -> {
                        loaderId++;
                        setAthlete(athlete);
                        onAthleteLoaded();
                    })
                    .execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callback != null) {
            callback = null;
        }
    }

    @CallSuper
    protected void onAthleteLoaded() {
        athleteLoaded.set(true);
    }

    protected Athlete getAthlete() {
        return athleteModel.get().getAthlete();
    }

    protected boolean isAthletLoaded() {
        return athleteLoaded.get();
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

    protected void updateHighscoreIfLoggedIn() {
        if (!isAthletLoaded()) {
            return;
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            return;
        }
        String userName = user.getDisplayName();
        Highscore highscore = new Highscore(getAthlete());
        UserHighscore userHighscore = new UserHighscore(highscore.getPoints(), userName);
        Timber.d("%s has %d highscore points", userName, highscore.getPoints());
        highscoreDatabaseReference.child(user.getUid()).setValue(userHighscore);
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

    protected void notifyCallbackShowInterstitial() {
        if (callback != null) {
            callback.showInterstitial();
        }
    }

    public interface Callback {
        void showBottomSheet();
        void hideBottomSheet();
        void showInterstitial();
    }
}
