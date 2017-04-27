package de.ironcoding.fitsim.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.ui.activities.BaseActivity;

/**
 * Created by larsl on 25.04.2017.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivity)) {
            throw new IllegalStateException("Your Host-Activity is no " + BaseActivity.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectBaseFragment(this);
    }

    protected FitSimApp getFitSimApp() {
        return ((BaseActivity)getActivity()).getFitSimApp();
    }

}
