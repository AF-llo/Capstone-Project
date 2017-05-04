package de.ironcoding.fitsim.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import de.ironcoding.fitsim.app.service.JobScheduledIntentService;
import de.ironcoding.fitsim.app.service.JobStoppedIntentService;
import de.ironcoding.fitsim.app.widget.HighscoreWidgetFactory;
import de.ironcoding.fitsim.ui.activities.MainActivity;
import de.ironcoding.fitsim.ui.presenter.BasePresenter;
import de.ironcoding.fitsim.ui.presenter.GymPresenter;
import de.ironcoding.fitsim.ui.presenter.HighscorePresenter;
import de.ironcoding.fitsim.ui.presenter.MainPresenter;
import de.ironcoding.fitsim.ui.presenter.NutritionPresenter;
import de.ironcoding.fitsim.ui.presenter.OnboardingPresenter;

/**
 * Created by larsl on 25.04.2017.
 */
@Singleton
@Component(modules = {AppModule.class, MockRepositoryModule.class, DbRepositoryModule.class, LocalModule.class, FirebaseModule.class})
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);

    void injectBasePresenter(BasePresenter basePresenter);

    void injectMainPresenter(MainPresenter mainActivity);

    void injectOnboardingPresenter(OnboardingPresenter onboardingPresenter);

    void injectGymPresenter(GymPresenter gymPresenter);

    void injectNutritionPresenter(NutritionPresenter nutritionPresenter);

    void injectProfilePresenter(HighscorePresenter profilePresenter);

    void injectJobScheduledService(JobScheduledIntentService intentService);

    void injectJobStoppedIntentService(JobStoppedIntentService intentService);

    void injectHighscoreWidgetFactory(HighscoreWidgetFactory highscoreWidgetFactory);

}
