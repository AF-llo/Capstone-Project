package de.ironcoding.fitsim.ui.fragments;

import de.ironcoding.fitsim.ui.presenter.MuscleInfoPresenter;

/**
 * Created by larsl on 04.05.2017.
 */

public class MuscleInfoFragment extends InfoFragment<MuscleInfoPresenter> {

    public static MuscleInfoFragment getInstance() {
        return new MuscleInfoFragment();
    }

    @Override
    public MuscleInfoPresenter createPresenter() {
        return new MuscleInfoPresenter();
    }
}
