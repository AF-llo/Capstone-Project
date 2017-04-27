package de.ironcoding.fitsim.app.injection;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.mock.AthleteMockDao;
import de.ironcoding.fitsim.repository.mock.MusclesMockDao;

/**
 * Created by larsl on 27.04.2017.
 */
@Module
public class RepositoryModule {

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
    @Singleton
    @Named(REPOSITORY_MOCKED)
    AthleteRepository provideMockedAthleteRepo(@Named(REPOSITORY_MOCKED) IAthleteDao athleteDao) {
        return new AthleteRepository(athleteDao);
    }

}
