package de.ironcoding.fitsim.app.injection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.persistance.FitSimOpenHelper;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.db.DbAthleteDao;
import de.ironcoding.fitsim.repository.db.DbMuscleDao;
import de.ironcoding.fitsim.repository.local.LocalMuscleDao;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 30.04.2017.
 */
@Module(includes = AppModule.class)
public class DbRepositoryModule {

    public static final String REPOSITORY_DB = "db";

    @Provides
    @Singleton
    DaoMaster.OpenHelper providesDaoMasterOpenHelper(Context context) {
        return new FitSimOpenHelper(context);
    }

    @Provides
    @Singleton
    SQLiteDatabase providesWriteableDataBase(DaoMaster.OpenHelper helper) {
        return helper.getWritableDatabase();
    }

    @Provides
    @Singleton
    DaoMaster providesDaoMaster(SQLiteDatabase database) {
        return new DaoMaster(database);
    }

    @Provides
    @Singleton
    DbMuscleDao providesDbMuscleDao(DaoMaster daoMaster, LocalMuscleDao localMuscleDao) {
        return new DbMuscleDao(daoMaster, localMuscleDao);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_DB)
    IAthleteDao providesDbAthleteDao(DbMuscleDao dbMuscleDao, DaoMaster daoMaster, AppSettings appSettings) {
        return new DbAthleteDao(dbMuscleDao, daoMaster, appSettings);
    }

    @Provides
    @Singleton
    @Named(REPOSITORY_DB)
    AthleteRepository providesDbAthleteRepository(@Named(REPOSITORY_DB) IAthleteDao athleteDbDao) {
        return new AthleteRepository(athleteDbDao);
    }

}
