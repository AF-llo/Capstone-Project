package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableList;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.injection.LocalModule;
import de.ironcoding.fitsim.events.ActivityItemEvent;
import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.repository.ActivitiesRepository;
import de.ironcoding.fitsim.ui.model.ActivityRecyclerItem;
import de.ironcoding.fitsim.ui.model.AthleteActivityPreviewViewModel;

/**
 * Created by larsl on 28.04.2017.
 */

public class GymPresenter extends BasePresenter implements ActivityItemEvent {

    @Inject
    @Named(LocalModule.REPOSITORY_LOCAL)
    ActivitiesRepository activitiesRepository;

    public ObservableFloat backgroundAlpha = new ObservableFloat();

    public ObservableFloat alpha = new ObservableFloat();

    public ObservableList<ActivityRecyclerItem> activities = new ObservableArrayList<>();

    public ObservableField<AthleteActivityPreviewViewModel> athletePreview = new ObservableField<>();

    public ObservableField<ActivityRecyclerItem> selectedActivity = new ObservableField<>();

    public ObservableBoolean activitySelected = new ObservableBoolean(false);

    public GymPresenter(Callback callback) {
        super(callback);
    }

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        getFitSimApp().getAppComponent().injectGymPresenter(this);
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        loadActivities();
    }

    private void loadActivities() {
        doInBackground(loaderId, () -> activitiesRepository.loadForLevel(getAthlete().getLevel()))
                .addOnSuccess(loadedActivities -> {
                    loaderId++;
                    updateItems(loadedActivities);
                } )
                .execute();
    }

    private void updateItems(List<Activity> items) {
        Body body = getAthlete().getBody();
        if (items.size() == activities.size()) {
            return;
        }
        if (activities.size() > 0) {
            activities.clear();
        }
        for (Activity item : items) {
            activities.add(new ActivityRecyclerItem(item, body.getMuscles()));
        }
    }

    /**
     * This will only force all activities to update its muscles to show new state e.g. when
     * muscle is now to demanding.
     * @param muscles
     *                      Changed muscles
     */
    private void musclesChanged(Muscles muscles) {
        for (ActivityRecyclerItem activity : activities) {
            activity.setMuscles(muscles);
        }
    }

    @Override
    public void onActivitySelected(Activity activity) {
        if (getContext() == null) {
            return;
        }
        Athlete currentAthlete = getAthlete().copy();
        Athlete previewAthlete = currentAthlete.copy();
        if (!currentAthlete.isAbleToDo(activity)) {
            Toast.makeText(getContext(), getContext().getString(R.string.to_tired), Toast.LENGTH_SHORT).show();
            return;
        }
        previewAthlete.doActivity(activity);
        updateAthletePreview(currentAthlete, previewAthlete, activity);
        selectedActivity.set(new ActivityRecyclerItem(activity, currentAthlete.getBody().getMuscles()));
        notifyCallbackShowBottomSheet();
        analyticsLogger.logActivityDone(activity.getName());
        activitySelected.set(true);
    }

    @Override
    public void onActivityToDemanding(Activity activity) {
        if (getContext() == null) {
            return;
        }
        Toast.makeText(getContext(), getContext().getString(R.string.activity_to_demanding, activity.getName()), Toast.LENGTH_SHORT).show();
    }

    public void startActivity() {
        if (getContext() == null) {
            return;
        }
        if (selectedActivity == null) {
            return;
        }
        Athlete currentAthlete = getAthlete();
        Athlete updatedAthlete = athletePreview.get().getAthlete();
        boolean leveledUp = currentAthlete.getLevel().getValue() < updatedAthlete.getLevel().getValue();
        athletePreview.set(null);
        athleteRepository.updateAthlete(updatedAthlete);
        musclesChanged(updatedAthlete.getBody().getMuscles());
        updateAthlete(updatedAthlete);
        notifyCallbackHideBottomSheet();
        if (leveledUp) {
            loadActivities();
            notifyCallbackShowInterstitial();
        }
        updateHighscoreIfLoggedIn();
        activitySelected.set(false);
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

    private void updateAthletePreview(Athlete current, Athlete preview, Activity activity) {
        athletePreview.set(new AthleteActivityPreviewViewModel(current, preview, activity.getId()));
    }

    public void setBackgroundAlpha(float backgroundAlpha) {
        this.backgroundAlpha.set(backgroundAlpha);
    }
}
