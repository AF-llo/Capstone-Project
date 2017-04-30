package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.MockRepositoryModule;
import de.ironcoding.fitsim.events.NutritionSelectedEvent;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.repository.NutritionRepository;
import de.ironcoding.fitsim.ui.model.AthleteNutritionPreviewViewModel;
import de.ironcoding.fitsim.ui.model.NutritionRecyclerItem;

/**
 * Created by larsl on 28.04.2017.
 */

public class NutritionPresenter extends BasePresenter implements NutritionSelectedEvent {

    @Inject
    @Named(MockRepositoryModule.REPOSITORY_MOCKED)
    NutritionRepository nutritionRepository;

    public ObservableList<NutritionRecyclerItem> nutritions = new ObservableArrayList<>();

    public ObservableField<AthleteNutritionPreviewViewModel> athletePreview = new ObservableField<>();

    public ObservableField<NutritionRecyclerItem> selectedNutrition = new ObservableField<>();

    public NutritionPresenter(Callback callback) {
        super(callback);
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectNutritionPresenter(this);
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        doInBackground(1, () -> nutritionRepository.loadForLevel(getAthlete().getLevel()))
                .addOnSuccess(loadedNutritions -> {
                    loaderId++;
                    updateItems(loadedNutritions);
                })
                .execute();
    }

    private void updateItems(List<Nutrition> items) {
        if (nutritions.size() > 0) {
            nutritions.clear();
        }
        for (Nutrition item : items) {
            nutritions.add(new NutritionRecyclerItem(item));
        }
    }

    @Override
    public void onNutritionSelected(Nutrition nutrition) {
        if (getContext() == null) {
            return;
        }
        Athlete previewAthlete = getAthlete().copy();
        if (!previewAthlete.canEat(nutrition)) {
            Toast.makeText(getContext(), getContext().getString(R.string.to_satured), Toast.LENGTH_SHORT).show();
            return;
        }
        previewAthlete.eat(nutrition);
        updateAthletePreview(previewAthlete);
        selectedNutrition.set(new NutritionRecyclerItem(nutrition));
        notifyCallbackShowBottomSheet();
    }

    public void eatNutrition() {
        if (selectedNutrition == null) {
            return;
        }
        Athlete updatedAthlete = athletePreview.get().getAthlete();
        athletePreview.set(null);
        athleteRepository.updateAthlete(updatedAthlete);
        updateAthlete(updatedAthlete);
        notifyCallbackHideBottomSheet();
    }

    private void updateAthletePreview(Athlete athlete) {
        athletePreview.set(new AthleteNutritionPreviewViewModel(athlete));
    }
}
