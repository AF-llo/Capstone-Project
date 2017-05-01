package de.ironcoding.fitsim.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityMainBinding;
import de.ironcoding.fitsim.ui.fragments.ProfileFragment;
import de.ironcoding.fitsim.ui.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainPresenter.MainCallback {

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mPresenter);
        if (savedInstanceState == null) {
            replaceContent(ProfileFragment.getInstance());
        }
    }

    @Override
    public void showFragment(Fragment fragment) {
        if (fragment != null) {
            replaceContent(fragment);
        }
    }
}
