package de.ironcoding.fitsim.app.injection;

import android.content.res.AssetManager;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.InitialAthleteRepository;
import de.ironcoding.fitsim.repository.initial.InitialAthleteDao;
import de.ironcoding.fitsim.repository.initial.InitialMuscleDao;

/**
 * Created by larsl on 30.04.2017.
 */
@Module(includes = {AppModule.class, DbRepositoryModule.class})
public class OnboardingModule {

    public static final String REPOSITORY_ONBOARDING = "onboarding";

    @Provides
    @Named(REPOSITORY_ONBOARDING)
    InitialMuscleDao providesInitialMuscleDao(AssetManager assetManager, Locale locale) {
        return new InitialMuscleDao(assetManager, locale);
    }

    @Provides
    InitialAthleteDao providesInitialAthleteDao(@Named(DbRepositoryModule.REPOSITORY_DB) IAthleteDao athleteDbDao, @Named(REPOSITORY_ONBOARDING) InitialMuscleDao initialMuscleDao) {
        return new InitialAthleteDao(athleteDbDao, initialMuscleDao);
    }

    @Provides
    @Singleton
    InitialAthleteRepository providesInitialAthleteRepository(InitialAthleteDao initialAthleteDao) {
        return new InitialAthleteRepository(initialAthleteDao);
    }
}
