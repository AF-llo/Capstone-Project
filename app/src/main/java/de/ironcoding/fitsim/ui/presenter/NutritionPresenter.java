package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableList;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.LocalModule;
import de.ironcoding.fitsim.events.NutritionSelectedEvent;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.repository.NutritionRepository;
import de.ironcoding.fitsim.ui.model.AthleteNutritionPreviewViewModel;
import de.ironcoding.fitsim.ui.model.NutritionRecyclerItem;

/**
 * Created by larsl on 28.04.2017.
 */

public class NutritionPresenter extends BasePresenter implements NutritionSelectedEvent {

    @Inject
    @Named(LocalModule.REPOSITORY_LOCAL)
    NutritionRepository nutritionRepository;

    public ObservableFloat alpha = new ObservableFloat();

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
            nutritions.add(new NutritionRecyclerItem(item, getAthlete().getBody()));
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
        selectedNutrition.set(new NutritionRecyclerItem(nutrition, previewAthlete.getBody()));
        notifyCallbackShowBottomSheet();
        analyticsLogger.logNutritionEaten(nutrition.getName());
    }

    /**
     * This will only force all nutrition to update its body to show new state e.g. when
     * body is now to saturated.
     * @param body
     *                     Changed body
     */
    private void bodyChanged(Body body) {
        for (NutritionRecyclerItem nutrition : nutritions) {
            nutrition.setBody(body);
        }
    }

    @Override
    public void onCanNotEat(Nutrition nutrition) {
        if (getContext() == null) {
            return;
        }
        Toast.makeText(getContext(), getContext().getString(R.string.cant_eat_nutrition, nutrition.getName()), Toast.LENGTH_SHORT).show();
    }

    public void eatNutrition() {
        if (selectedNutrition == null) {
            return;
        }
        Athlete updatedAthlete = athletePreview.get().getAthlete();
        athletePreview.set(null);
        athleteRepository.updateAthlete(updatedAthlete);
        bodyChanged(updatedAthlete.getBody());
        updateAthlete(updatedAthlete);
        notifyCallbackHideBottomSheet();
    }

    public void showProfile() {
        notifyCallbackShowProfile();
    }

    public void setAlpha(float alpha) {
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 1) {
            alpha = 1;
        }
        this.alpha.set(alpha);
    }

    private void updateAthletePreview(Athlete athlete) {
        athletePreview.set(new AthleteNutritionPreviewViewModel(athlete));
    }
}
