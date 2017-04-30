package de.ironcoding.fitsim.app;

import android.app.Application;

import de.ironcoding.fitsim.app.injection.AppComponent;
import de.ironcoding.fitsim.app.injection.AppModule;
import de.ironcoding.fitsim.app.injection.DaggerAppComponent;
import de.ironcoding.fitsim.app.injection.DbRepositoryModule;
import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.app.injection.OnboardingModule;

/**
 * Created by larsl on 20.04.2017.
 */

public class FitSimApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjection();
    }

    private void initInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dbRepositoryModule(new DbRepositoryModule())
                .onboardingModule(new OnboardingModule())
                .mockRepositoryModule(new MockRepositoryModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
