package de.ironcoding.fitsim.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityFfmiBinding;
import de.ironcoding.fitsim.ui.presenter.FfmiPresenter;

/**
 * Created by larsl on 05.05.2017.
 */

public class FfmiActivity extends BaseActivity<FfmiPresenter> {

    public static Intent getIntent(Context context) {
        return new Intent(context, FfmiActivity.class);
    }

    @Override
    public FfmiPresenter createPresenter() {
        return new FfmiPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFfmiBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ffmi);
        binding.setFfmiPresenter(mPresenter);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
