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
import de.ironcoding.fitsim.databinding.FragmentNutritionBinding;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.repository.NutritionRepository;

/**
 * Created by larsl on 27.04.2017.
 */

public class NutritionFragment extends BaseFragment {

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    NutritionRepository nutritionRepository;

    public ObservableList<Nutrition> nutritions = new ObservableArrayList<>();

    public static NutritionFragment getInstance() {
        return new NutritionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectNutritionFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNutritionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrition, container, false);
        binding.setNutritionFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Nutrition> nutritions = nutritionRepository.loadForLevel(getAthlete().getLevel());
        this.nutritions.addAll(nutritions);
        Log.d(NutritionFragment.class.getSimpleName(), "loaded " + this.nutritions.size() + " food");
    }
}
