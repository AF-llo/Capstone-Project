package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.databinding.FragmentGymBinding;
import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.repository.ActivitiesRepository;

/**
 * Created by larsl on 27.04.2017.
 */

public class GymFragment extends BaseFragment {

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    ActivitiesRepository activitiesRepository;

    public ObservableList<Activity> activities = new ObservableArrayList<>();

    public static GymFragment getInstance() {
        return new GymFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectGymFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGymBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gym, container, false);
        binding.setGymFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Activity> activities = activitiesRepository.loadForLevel(getAthlete().getLevel());
        this.activities.addAll(activities);
        Log.d(NutritionFragment.class.getSimpleName(), "loaded " + this.activities.size() + " activities");
    }
}
