package de.ironcoding.fitsim.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityBmiBinding;
import de.ironcoding.fitsim.ui.presenter.BmiPresenter;

/**
 * Created by larsl on 05.05.2017.
 */

public class BmiActivity extends BaseActivity<BmiPresenter> {

    public static Intent getIntent(Context context) {
        return new Intent(context, BmiActivity.class);
    }

    @Override
    public BmiPresenter createPresenter() {
        return new BmiPresenter();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBmiBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bmi);
        binding.setBmiPresenter(mPresenter);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
