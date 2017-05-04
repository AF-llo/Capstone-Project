package de.ironcoding.fitsim.ui.fragments;

import de.ironcoding.fitsim.ui.presenter.NutritionInfoPresenter;

/**
 * Created by larsl on 04.05.2017.
 */

public class NutritionInfoFragment extends InfoFragment<NutritionInfoPresenter> {

    public static NutritionInfoFragment getInstance() {
        return new NutritionInfoFragment();
    }

    @Override
    public NutritionInfoPresenter createPresenter() {
        return new NutritionInfoPresenter();
    }
}
