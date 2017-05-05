package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

    private FragmentNutritionBinding binding;

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrition, container, false);
        binding.setNutritionPresenter(mPresenter);
        binding.appbar.addOnOffsetChangedListener(this::onOffsetChanged);
        ((AppCompatActivity)getContext()).setSupportActionBar(binding.toolbar);
        ActionBar actionBar = ((AppCompatActivity)getContext()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        binding.nutritionList.setNestedScrollingEnabled(true);
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return binding.getRoot();
    }

    @Override
    public void showBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxOffset = binding.appbar.getMeasuredHeight() - binding.toolbar.getMeasuredHeight() - binding.header.getMeasuredHeight();
        int absOffset = Math.abs(verticalOffset);
        float perOffset = absOffset / (float) maxOffset;
        mPresenter.setAlpha(perOffset);
    }

    @Override
    public void hideBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
