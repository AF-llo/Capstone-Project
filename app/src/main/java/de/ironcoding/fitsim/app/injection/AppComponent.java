package de.ironcoding.fitsim.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import de.ironcoding.fitsim.ui.activities.BaseActivity;
import de.ironcoding.fitsim.ui.activities.MainActivity;
import de.ironcoding.fitsim.ui.presenter.BasePresenter;
import de.ironcoding.fitsim.ui.presenter.GymPresenter;
import de.ironcoding.fitsim.ui.presenter.NutritionPresenter;
import de.ironcoding.fitsim.ui.presenter.ProfilePresenter;

/**
 * Created by larsl on 25.04.2017.
 */
@Singleton
@Component(modules = {AppModule.class, MockRepositoryModule.class})
public interface AppComponent {

    void injectBaseActivity(BaseActivity baseActivity);

    void injectMainActivity(MainActivity mainActivity);

    void injectBasePresenter(BasePresenter basePresenter);

    void injectGymPresenter(GymPresenter gymPresenter);

    void injectNutritionPresenter(NutritionPresenter nutritionPresenter);

    void injectProfilePresenter(ProfilePresenter profilePresenter);

}
