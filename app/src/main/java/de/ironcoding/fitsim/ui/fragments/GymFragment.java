package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.RepositoryModule;
import de.ironcoding.fitsim.databinding.FragmentGymBinding;
import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.ActivitiesRepository;
import de.ironcoding.fitsim.repository.LevelSpecification;

/**
 * Created by larsl on 27.04.2017.
 */

public class GymFragment extends BaseFragment {

    @Inject
    @Named(RepositoryModule.REPOSITORY_MOCKED)
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
        List<Activity> activities = activitiesRepository.load(new LevelSpecification(athleteModel.get().getAthlete().getLevel()));
        this.activities.addAll(activities);
    }

    public void trainChest() {
        Athlete athlete = getAthlete();
        Activity activity = activities.get(0);
        if(athlete.isAbleToDo(activity)) {
            athlete.doActivity(activity, false);
            athleteRepository.updateAthlete(athlete);
            setAthlete(athlete);
        } else {
            Toast.makeText(getContext(), "I cannot train", Toast.LENGTH_SHORT).show();
        }
    }

    public void trainBack() {
        Athlete athlete = getAthlete();
        Activity activity = activities.get(1);
        if(athlete.isAbleToDo(activity)) {
            athlete.doActivity(activity, false);
            athleteRepository.updateAthlete(athlete);
            setAthlete(athlete);
        } else {
            Toast.makeText(getContext(), "I cannot train", Toast.LENGTH_SHORT).show();
        }
    }

    public void trainBiceps() {
        Athlete athlete = getAthlete();
        Activity activity = activities.get(2);
        if(athlete.isAbleToDo(activity)) {
            athlete.doActivity(activity, false);
            athleteRepository.updateAthlete(athlete);
            setAthlete(athlete);
        } else {
            Toast.makeText(getContext(), "I cannot train", Toast.LENGTH_SHORT).show();
        }
    }

    public void trainTriceps() {
        Athlete athlete = getAthlete();
        Activity activity = activities.get(3);
        if(athlete.isAbleToDo(activity)) {
            athlete.doActivity(activity, false);
            athleteRepository.updateAthlete(athlete);
            setAthlete(athlete);
        } else {
            Toast.makeText(getContext(), "I cannot train", Toast.LENGTH_SHORT).show();
        }
    }

    public void cycle() {
        Athlete athlete = getAthlete();
        Activity activity = activities.get(4);
        if(athlete.isAbleToDo(activity)) {
            athlete.doActivity(activity, false);
            athleteRepository.updateAthlete(athlete);
            setAthlete(athlete);
        } else {
            Toast.makeText(getContext(), "I cannot train", Toast.LENGTH_SHORT).show();
        }
    }

    public void rest() {
        Athlete athlete = getAthlete();
        athlete.refreshBody();
        setAthlete(athlete);
    }
}
