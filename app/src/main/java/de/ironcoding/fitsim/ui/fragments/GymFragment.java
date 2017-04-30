package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentGymBinding;
import de.ironcoding.fitsim.ui.presenter.BasePresenter;
import de.ironcoding.fitsim.ui.presenter.GymPresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class GymFragment extends BaseFragment<GymPresenter> implements BasePresenter.Callback {

    public static GymFragment getInstance() {
        return new GymFragment();
    }

    @Override
    public GymPresenter createPresenter() {
        return new GymPresenter(this);
    }

    private BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGymBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gym, container, false);
        binding.setGymPresenter(mPresenter);
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
        // TODO: 30.04.2017
        Toast.makeText(getContext(), "Show interstitial", Toast.LENGTH_SHORT).show();
    }
}
