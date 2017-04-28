package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentNutritionBinding;
import de.ironcoding.fitsim.ui.presenter.NutritionPresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class NutritionFragment extends BaseFragment<NutritionPresenter> {

    public static NutritionFragment getInstance() {
        return new NutritionFragment();
    }

    @Override
    public NutritionPresenter createPresenter() {
        return new NutritionPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNutritionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrition, container, false);
        binding.setNutritionPresenter(mPresenter);
        return binding.getRoot();
    }
}
