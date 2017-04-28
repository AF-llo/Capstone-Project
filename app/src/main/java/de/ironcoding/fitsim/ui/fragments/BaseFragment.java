package de.ironcoding.fitsim.ui.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.AthleteRepository;
import de.ironcoding.fitsim.ui.activities.BaseActivity;
import de.ironcoding.fitsim.ui.model.AthleteViewModel;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 25.04.2017.
 */

public class BaseFragment extends Fragment {

    @Inject
    AppSettings appSettings;

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    AthleteRepository athleteRepository;

    public ObservableField<AthleteViewModel> athleteModel = new ObservableField<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivity)) {
            throw new IllegalStateException("Your Host-Activity is no " + BaseActivity.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFitSimApp().getAppComponent().injectBaseFragment(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setAthlete(athleteRepository.loadAthlete());
    }

    protected FitSimApp getFitSimApp() {
        return ((BaseActivity)getActivity()).getFitSimApp();
    }

    protected Athlete getAthlete() {
        return athleteModel.get().getAthlete();
    }

    protected void setAthlete(Athlete athlete) {
        athleteModel.set(new AthleteViewModel(athlete));
    }

}
