package de.ironcoding.fitsim.app.injection;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.ActivitiesRepository;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.repository.IActivitiesDao;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.INutritionDao;
import de.ironcoding.fitsim.repository.NutritionRepository;
import de.ironcoding.fitsim.repository.mock.ActivitiesMockDao;
import de.ironcoding.fitsim.repository.mock.AthleteMockDao;
import de.ironcoding.fitsim.repository.mock.MusclesMockDao;
import de.ironcoding.fitsim.repository.mock.NutritionMockDao;

/**
 * Created by larsl on 27.04.2017.
 */
@Module
public class MockRepositoryModule {

    public static final String REPOSITORY_MOCKED = "mocked";

    @Provides
    MusclesMockDao providesMucleMockDao() {
        return new MusclesMockDao();
    }

    @Provides
    @Named(REPOSITORY_MOCKED)
    IAthleteDao providesAthleteMockDao(MusclesMockDao musclesMockDao) {
        return new AthleteMockDao(musclesMockDao);
    }

    @Provides
    @Named(REPOSITORY_MOCKED)
    IActivitiesDao providesActivitiesMockDao() {
        return new ActivitiesMockDao();
    }

    @Provides
    @Named(REPOSITORY_MOCKED)
    INutritionDao providesNutritionMockDao() {
        return new NutritionMockDao();
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    ActivitiesRepository provideMockedActivitiesRepo(@Named(REPOSITORY_MOCKED) IActivitiesDao activitiesDao) {
        return new ActivitiesRepository(activitiesDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    NutritionRepository provideMockedNutritionRepo(@Named(REPOSITORY_MOCKED) INutritionDao nutritionDao) {
        return new NutritionRepository(nutritionDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    AthleteRepository provideMockedAthleteRepo(@Named(REPOSITORY_MOCKED) IAthleteDao athleteDao) {
        return new AthleteRepository(athleteDao);
    }

}
