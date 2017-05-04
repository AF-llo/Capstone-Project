package de.ironcoding.fitsim.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityProfileBinding;
import de.ironcoding.fitsim.ui.presenter.ProfilePresenter;

/**
 * Created by larsl on 03.05.2017.
 */

public class ProfileActivity extends BaseActivity<ProfilePresenter> {

    ActivityProfileBinding binding;

    public static Intent getIntent(Context context) {
        Intent starter = new Intent(context, ProfileActivity.class);
        return starter;
    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter(getSupportFragmentManager());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setProfilePresener(mPresenter);
        binding.typeTabs.setupWithViewPager(binding.pager);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
