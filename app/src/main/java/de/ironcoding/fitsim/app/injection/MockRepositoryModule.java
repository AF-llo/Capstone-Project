package de.ironcoding.fitsim.app.injection;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.ActivitiesRepository;
import de.ironcoding.fitsim.repository.AthleteRepository;
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
    AthleteMockDao providesAthleteMockDao(MusclesMockDao musclesMockDao) {
        return new AthleteMockDao(musclesMockDao);
    }

    @Provides
    ActivitiesMockDao providesActivitiesMockDao() {
        return new ActivitiesMockDao();
    }

    @Provides
    NutritionMockDao providesNutritionMockDao() {
        return new NutritionMockDao();
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    ActivitiesRepository provideMockedActivitiesRepo(ActivitiesMockDao activitiesDao) {
        return new ActivitiesRepository(activitiesDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    NutritionRepository provideMockedNutritionRepo(NutritionMockDao nutritionDao) {
        return new NutritionRepository(nutritionDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_MOCKED)
    AthleteRepository provideMockedAthleteRepo(AthleteMockDao athleteDao) {
        return new AthleteRepository(athleteDao);
    }

}
