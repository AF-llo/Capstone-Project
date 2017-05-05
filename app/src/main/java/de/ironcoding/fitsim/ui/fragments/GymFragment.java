package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentGymBinding;
import de.ironcoding.fitsim.ui.presenter.BasePresenter;
import de.ironcoding.fitsim.ui.presenter.GymPresenter;

import static de.ironcoding.fitsim.ui.fragments.NutritionFragment.BACKGROUND_ALPHA_SCALE;

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

    private FragmentGymBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gym, container, false);
        binding.setGymPresenter(mPresenter);
        binding.appbar.addOnOffsetChangedListener(this::onOffsetChanged);
        ((AppCompatActivity)getContext()).setSupportActionBar(binding.toolbar);
        ActionBar actionBar = ((AppCompatActivity)getContext()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        binding.activityList.setNestedScrollingEnabled(true);
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(callback);
        return binding.getRoot();
    }

    private BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            mPresenter.setBackgroundAlpha(slideOffset * BACKGROUND_ALPHA_SCALE);
        }
    };

    @Override
    public void showBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void hideBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxOffset = binding.appbar.getMeasuredHeight() - binding.toolbar.getMeasuredHeight() - binding.header.getMeasuredHeight();
        int absOffset = Math.abs(verticalOffset);
        float perOffset = absOffset / (float) maxOffset;
        mPresenter.setAlpha(perOffset);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
