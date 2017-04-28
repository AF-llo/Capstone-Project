package de.ironcoding.fitsim.app.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 20.04.2017.
 */
@Module
public class AppModule {

    private final FitSimApp application;

    public AppModule(FitSimApp application) {
        this.application = application;
    }

    @Provides
    Context provideAppContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    AppSettings provideAppSettings() {
        return new AppSettings();
    }
}