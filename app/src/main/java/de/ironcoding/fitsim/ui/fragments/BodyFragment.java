package de.ironcoding.fitsim.ui.fragments;

import de.ironcoding.fitsim.ui.presenter.BodyPresenter;

/**
 * Created by larsl on 03.05.2017.
 */

public class BodyFragment extends InfoFragment<BodyPresenter> {

    public static BodyFragment getInstance() {
        return new BodyFragment();
    }

    @Override
    public BodyPresenter createPresenter() {
        return new BodyPresenter();
    }
}
