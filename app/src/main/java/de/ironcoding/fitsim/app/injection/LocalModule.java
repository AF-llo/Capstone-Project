package de.ironcoding.fitsim.app.injection;

import android.content.res.AssetManager;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.ActivitiesRepository;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.NutritionRepository;
import de.ironcoding.fitsim.repository.local.LocalActivitiesDao;
import de.ironcoding.fitsim.repository.local.LocalAthleteDao;
import de.ironcoding.fitsim.repository.local.LocalAthleteRepository;
import de.ironcoding.fitsim.repository.local.LocalMuscleDao;
import de.ironcoding.fitsim.repository.local.LocalNutritionDao;

import static de.ironcoding.fitsim.app.injection.DbRepositoryModule.REPOSITORY_DB;

/**
 * Created by larsl on 30.04.2017.
 */
@Module(includes = {AppModule.class, DbRepositoryModule.class})
public class LocalModule {

    public static final String REPOSITORY_LOCAL = "local";

    @Provides
    LocalMuscleDao providesLocalMuscleDao(AssetManager assetManager, Locale locale) {
        return new LocalMuscleDao(assetManager, locale);
    }

    @Provides
    LocalActivitiesDao providesLocalActivitiesDao(AssetManager assetManager, Locale locale) {
        return new LocalActivitiesDao(assetManager, locale);
    }

    @Provides
    LocalNutritionDao providesLocalNutritionsDao(AssetManager assetManager, Locale locale) {
        return new LocalNutritionDao(assetManager, locale);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_LOCAL)
    ActivitiesRepository providesLocalActivitiesRepo(LocalActivitiesDao activitiesDao) {
        return new ActivitiesRepository(activitiesDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_LOCAL)
    NutritionRepository providesLocalNutritionRepo(LocalNutritionDao nutritionDao) {
        return new NutritionRepository(nutritionDao);
    }

    @Provides
    LocalAthleteDao providesLocalAthleteDao(@Named(REPOSITORY_DB) IAthleteDao athleteDbDao, LocalMuscleDao initialMuscleDao) {
        return new LocalAthleteDao(athleteDbDao, initialMuscleDao);
    }

    @Provides
    @Singleton
    LocalAthleteRepository providesLocalAthleteRepository(LocalAthleteDao initialAthleteDao) {
        return new LocalAthleteRepository(initialAthleteDao);
    }
}
