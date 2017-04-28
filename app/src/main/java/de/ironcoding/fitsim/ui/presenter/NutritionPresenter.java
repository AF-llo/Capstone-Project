package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.events.NutritionSelectedEvent;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.repository.NutritionRepository;
import de.ironcoding.fitsim.ui.model.NutritionRecyclerItem;

/**
 * Created by larsl on 28.04.2017.
 */

public class NutritionPresenter extends BasePresenter implements NutritionSelectedEvent {

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    NutritionRepository nutritionRepository;

    public ObservableList<NutritionRecyclerItem> nutritions = new ObservableArrayList<>();

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectNutritionPresenter(this);
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        doInBackground(1, () -> nutritionRepository.loadForLevel(getAthlete().getLevel()))
                .addOnSuccess(this::updateItems)
                .execute();
    }

    private void updateItems(List<Nutrition> items) {
        for (Nutrition item : items) {
            nutritions.add(new NutritionRecyclerItem(item));
        }
    }

    @Override
    public void onNutritionSelected(Nutrition nutrition) {
        if (getContext() == null) {
            return;
        }
        Toast.makeText(getContext(), "Selected " + nutrition.getName(), Toast.LENGTH_SHORT).show();
    }
}
