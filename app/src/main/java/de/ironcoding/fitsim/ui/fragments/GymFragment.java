package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentGymBinding;
import de.ironcoding.fitsim.ui.presenter.GymPresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class GymFragment extends BaseFragment<GymPresenter> {

    public static GymFragment getInstance() {
        return new GymFragment();
    }

    @Override
    public GymPresenter createPresenter() {
        return new GymPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGymBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gym, container, false);
        binding.setGymPresenter(mPresenter);
        return binding.getRoot();
    }
}
