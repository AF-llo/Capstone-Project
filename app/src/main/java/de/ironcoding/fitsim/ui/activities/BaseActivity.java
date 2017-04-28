package de.ironcoding.fitsim.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.FitSimApp;

/**
 * Created by larsl on 25.04.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FitSimApp getFitSimApp() {
        return (FitSimApp) getApplication();
    }

    protected void replaceContent(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit();
    }
}
