package de.ironcoding.fitsim.ui.fragments;

import android.content.Context;

import de.appsfactory.mvplib.presenter.MVPPresenter;
import de.appsfactory.mvplib.view.MVPFragment;
import de.ironcoding.fitsim.ui.activities.BaseActivity;

/**
 * Created by larsl on 25.04.2017.
 */

public abstract class BaseFragment<T extends MVPPresenter> extends MVPFragment<T> {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivity)) {
            throw new IllegalStateException("Your Host-Activity is no " + BaseActivity.class.getSimpleName());
        }
    }

}
