package de.ironcoding.fitsim.app.injection;

import android.content.res.AssetManager;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.local.LocalAthleteDao;
import de.ironcoding.fitsim.repository.local.LocalAthleteRepository;
import de.ironcoding.fitsim.repository.local.LocalMuscleDao;

import static de.ironcoding.fitsim.app.injection.DbRepositoryModule.REPOSITORY_DB;

/**
 * Created by larsl on 30.04.2017.
 */
@Module(includes = {AppModule.class, DbRepositoryModule.class})
public class OnboardingModule {

    public static final String REPOSITORY_ONBOARDING = "onboarding";

    @Provides
    LocalMuscleDao providesInitialMuscleDao(AssetManager assetManager, Locale locale) {
        return new LocalMuscleDao(assetManager, locale);
    }

    @Provides
    LocalAthleteDao providesInitialAthleteDao(@Named(REPOSITORY_DB) IAthleteDao athleteDbDao, LocalMuscleDao initialMuscleDao) {
        return new LocalAthleteDao(athleteDbDao, initialMuscleDao);
    }

    @Provides
    @Singleton
    LocalAthleteRepository providesLocalAthleteRepository(LocalAthleteDao initialAthleteDao) {
        return new LocalAthleteRepository(initialAthleteDao);
    }
}
