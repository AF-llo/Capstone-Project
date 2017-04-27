package de.ironcoding.fitsim.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.RepositoryModule;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 25.04.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    AppSettings appSettings;

    @Inject
    @Named(RepositoryModule.REPOSITORY_MOCKED)
    AthleteRepository athleteRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectBaseActivity(this);
    }

    public FitSimApp getFitSimApp() {
        return (FitSimApp) getApplication();
    }
}
