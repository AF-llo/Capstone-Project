package de.ironcoding.fitsim.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityMainBinding;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.repository.InitialAthleteRepository;
import de.ironcoding.fitsim.ui.fragments.ProfileFragment;
import de.ironcoding.fitsim.ui.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainPresenter.MainCallback {

    @Inject
    InitialAthleteRepository athleteRepository;

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectMainActivity(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mPresenter);
        if (savedInstanceState == null) {
            replaceContent(ProfileFragment.getInstance());
        }
        athleteRepository.createInitialAthlete(BodyType.ENDOMORPH, Athlete.MALE, 32, 1.68F);
    }

    @Override
    public void showFragment(Fragment fragment) {
        if (fragment != null) {
            replaceContent(fragment);
        }
    }
}
