package de.ironcoding.fitsim.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityMainBinding;
import de.ironcoding.fitsim.ui.fragments.GymFragment;
import de.ironcoding.fitsim.ui.fragments.MoreFragment;
import de.ironcoding.fitsim.ui.fragments.NutritionFragment;
import de.ironcoding.fitsim.ui.fragments.ProfileFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectMainActivity(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onBottomItemSelected);
        if (savedInstanceState == null) {
            replaceContent(ProfileFragment.getInstance());
        }
    }

    private boolean onBottomItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                replaceContent(ProfileFragment.getInstance());
                return true;
            case R.id.action_gym:
                replaceContent(GymFragment.getInstance());
                return true;
            case R.id.action_nutrition:
                replaceContent(NutritionFragment.getInstance());
                return true;
            case R.id.action_more:
                replaceContent(MoreFragment.getInstance());
                return true;
        }
        return false;
    }
}
