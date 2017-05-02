package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentNutritionBinding;
import de.ironcoding.fitsim.ui.presenter.BasePresenter;
import de.ironcoding.fitsim.ui.presenter.NutritionPresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class NutritionFragment extends BaseFragment<NutritionPresenter> implements BasePresenter.Callback {

    public static NutritionFragment getInstance() {
        return new NutritionFragment();
    }

    @Override
    public NutritionPresenter createPresenter() {
        return new NutritionPresenter(this);
    }

    private BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNutritionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrition, container, false);
        binding.setNutritionPresenter(mPresenter);
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return binding.getRoot();
    }

    @Override
    public void showBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void hideBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void showInterstitial() {
        showInterstitialAd();
    }
}
