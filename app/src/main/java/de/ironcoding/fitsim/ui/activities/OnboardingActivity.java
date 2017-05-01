package de.ironcoding.fitsim.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityOnboardingBinding;
import de.ironcoding.fitsim.ui.presenter.OnboardingPresenter;

/**
 * Created by larsl on 01.05.2017.
 */

public class OnboardingActivity extends BaseActivity<OnboardingPresenter> implements OnboardingPresenter.OnboardingCallback {
    @Override
    public OnboardingPresenter createPresenter() {
        return new OnboardingPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOnboardingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding);
        binding.setOnboardingPresenter(mPresenter);
    }

    @Override
    public void finishOnboarding() {
        MainActivity.start(this);
        finish();
    }
}
