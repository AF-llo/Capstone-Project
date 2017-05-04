package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentBodyBinding;
import de.ironcoding.fitsim.ui.presenter.BodyPresenter;

/**
 * Created by larsl on 03.05.2017.
 */

public class BodyFragment extends BaseFragment<BodyPresenter> {

    public static BodyFragment getInstance() {
        return new BodyFragment();
    }

    @Override
    public BodyPresenter createPresenter() {
        return new BodyPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBodyBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_body, container, false);
        binding.setBodyPresenter(mPresenter);
        return binding.getRoot();
    }
}
