package de.ironcoding.fitsim.app.injection;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.persistance.FitSimOpenHelper;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.IMusclesDao;
import de.ironcoding.fitsim.repository.db.AthleteDbDao;
import de.ironcoding.fitsim.repository.initial.InitialMuscleDao;

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
    @Named(REPOSITORY_DB)
    IMusclesDao providesInitialMuscleDao(AssetManager assetManager, Locale locale) {
        return new InitialMuscleDao(assetManager, locale);
    }

    @Provides
    @Named(REPOSITORY_DB)
    IAthleteDao providesDbAthleteDao(@Named(REPOSITORY_DB) IMusclesDao dbMuscleFao, DaoMaster daoMaster) {
        return new AthleteDbDao(dbMuscleFao, daoMaster);
    }

}
