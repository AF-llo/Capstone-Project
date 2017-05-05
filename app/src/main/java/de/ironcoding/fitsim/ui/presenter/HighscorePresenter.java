package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.firebase.model.UserHighscore;
import de.ironcoding.fitsim.util.HighscoreUtil;
import de.ironcoding.fitsim.ui.model.HighscoreRecyclerItem;

/**
 * Created by larsl on 28.04.2017.
 */

public class HighscorePresenter extends BasePresenter implements FirebaseAuth.AuthStateListener {

    public ObservableString username = new ObservableString();

    public ObservableBoolean loggedin = new ObservableBoolean(false);

    public ObservableList<HighscoreRecyclerItem> higscoreItems = new ObservableArrayList<>();

    private LoginCallback loginCallback;

    private ValueEventListener eventListener;

    public HighscorePresenter(LoginCallback callback) {
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
            attachHighscoreListener();
        } else {
            if (getContext() != null) {
                username.set(getContext().getString(R.string.anonymous));
            }
            loggedin.set(false);
            detachhighscoreListener();
        }
    }

    private void attachHighscoreListener() {
        if (eventListener == null) {
            eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (higscoreItems.size() > 0) {
                        higscoreItems.clear();
                    }
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserHighscore userHighscore = snapshot.getValue(UserHighscore.class);
                        if (userHighscore != null) {
                            higscoreItems.add(new HighscoreRecyclerItem(userHighscore));
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            HighscoreUtil.topTenQuery(highscoreDatabaseReference).addValueEventListener(eventListener);
        }
    }

    private void detachhighscoreListener() {
        if (eventListener != null) {
            highscoreDatabaseReference.removeEventListener(eventListener);
            eventListener = null;
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