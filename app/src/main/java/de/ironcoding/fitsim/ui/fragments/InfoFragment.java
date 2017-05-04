package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentInfoBinding;
import de.ironcoding.fitsim.ui.presenter.InfoListPresenter;

/**
 * Created by larsl on 04.05.2017.
 */

public abstract class InfoFragment<T extends InfoListPresenter> extends BaseFragment<T> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        binding.setInfoPresenter(mPresenter);
        return binding.getRoot();
    }
}
