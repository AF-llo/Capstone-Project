package de.ironcoding.fitsim.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.appsfactory.mvplib.view.MVPActivity;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.FitSimApp;

/**
 * Created by larsl on 25.04.2017.
 */

public abstract class BaseActivity<T extends MVPPresenter> extends MVPActivity<T> {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_test_addunitid));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }

    protected FitSimApp getFitSimApp() {
        return (FitSimApp) getApplication();
    }

    protected void replaceContent(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit();
    }

    public void showInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    public void showProfile() {
        Intent intent = ProfileActivity.getIntent(this);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

}
