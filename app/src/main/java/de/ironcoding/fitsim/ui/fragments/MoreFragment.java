package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentMoreBinding;
import de.ironcoding.fitsim.ui.activities.BaseActivity;
import de.ironcoding.fitsim.ui.model.UtilityRecyclerItem;
import de.ironcoding.fitsim.ui.presenter.MorePresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class MoreFragment extends BaseFragment<MorePresenter> implements MorePresenter.MoreCallback {

    public static MoreFragment getInstance() {
        return new MoreFragment();
    }

    @Override
    public MorePresenter createPresenter() {
        return new MorePresenter(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false);
        binding.setMorePresenter(mPresenter);
        ((AppCompatActivity)getContext()).setSupportActionBar(binding.toolbar);
        ActionBar actionBar = ((AppCompatActivity)getContext()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        return binding.getRoot();
    }

    @Override
    public void showUtilityScreen(UtilityRecyclerItem item) {
        ((BaseActivity)getContext()).showUtilityScreen(item);
    }
}
