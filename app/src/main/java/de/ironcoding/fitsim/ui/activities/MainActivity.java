package de.ironcoding.fitsim.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectMainActivity(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onBottomItemSelected);
    }

    private boolean onBottomItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Toast.makeText(MainActivity.this, R.string.profile, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_gym:
                Toast.makeText(MainActivity.this, R.string.gym, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_nutrition:
                Toast.makeText(MainActivity.this, R.string.nutrition, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_more:
                Toast.makeText(MainActivity.this, R.string.more, Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
