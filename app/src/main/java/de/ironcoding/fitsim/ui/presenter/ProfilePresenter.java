package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Named;

import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.FirebaseModule;

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

    @Inject
    @Named(FirebaseModule.CHILD_HIGHSCORE)
    DatabaseReference highscoreDatabaseReference;

    @Inject
    FirebaseAuth firebaseAuth;

    ChildEventListener childEventListener;

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

    private void attachChildEventListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            highscoreDatabaseReference.addChildEventListener(childEventListener);
        }
    }

    public void login() {
        notifyCallbackLogin();
    }

    private void detachChildEventListener() {
        if (childEventListener != null) {
            highscoreDatabaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
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
            // TODO: 02.05.2017 logged in
            username.set(user.getDisplayName());
            attachChildEventListener();
            loggedin.set(true);
        } else {
            // TODO: 02.05.2017 logged out
            if (getContext() != null) {
                username.set(getContext().getString(R.string.anonymous));
            }
            detachChildEventListener();
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
