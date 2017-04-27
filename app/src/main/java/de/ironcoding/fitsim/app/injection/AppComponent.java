package de.ironcoding.fitsim.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import de.ironcoding.fitsim.ui.activities.BaseActivity;
import de.ironcoding.fitsim.ui.activities.MainActivity;
import de.ironcoding.fitsim.ui.fragments.BaseFragment;
import de.ironcoding.fitsim.ui.fragments.GymFragment;

/**
 * Created by larsl on 25.04.2017.
 */
@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {

    void injectBaseActivity(BaseActivity baseActivity);

    void injectMainActivity(MainActivity mainActivity);

    void injectBaseFragment(BaseFragment baseFragment);

    void injectGymFragment(GymFragment gymFragment);
}
