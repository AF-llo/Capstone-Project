package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.R;

/**
 * Created by larsl on 28.04.2017.
 */

public class ProfilePresenter extends BasePresenter implements FirebaseAuth.AuthStateListener {

    public ObservableString username = new ObservableString();

    public ObservableBoolean loggedin = new ObservableBoolean(false);

    private LoginCallback loginCallback;

    public ProfilePresenter(LoginCallback callback) {
        this.loginCallback = callback;
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectProfilePresenter(this);
        if (getContext() != null) {
            username.set(getContext().getString(R.string.anonymous));
        }
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        // TODO: 28.04.2017
    }

    public void login() {
        notifyCallbackLogin();
    }

    public void logout() {
        firebaseAuth.signOut();
    }

    @Override
    public void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            username.set(user.getDisplayName());
            loggedin.set(true);
            updateHighscoreIfLoggedIn();
        } else {
            if (getContext() != null) {
                username.set(getContext().getString(R.string.anonymous));
            }
            loggedin.set(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loginCallback != null) {
            loginCallback = null;
        }
    }

    private void notifyCallbackLogin() {
        if (loginCallback != null) {
            loginCallback.login();
        }
    }

    public interface LoginCallback {
        void login();
    }

}
