package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentMoreBinding;
import de.ironcoding.fitsim.ui.presenter.MorePresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class MoreFragment extends BaseFragment<MorePresenter> {

    public static MoreFragment getInstance() {
        return new MoreFragment();
    }

    @Override
    public MorePresenter createPresenter() {
        return new MorePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false);
        return binding.getRoot();
    }
}
