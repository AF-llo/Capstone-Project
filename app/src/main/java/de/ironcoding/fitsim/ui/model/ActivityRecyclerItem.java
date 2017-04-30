package de.ironcoding.fitsim.ui.model;

import android.databinding.Bindable;

import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.events.ActivityItemEvent;
import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Muscles;

/**
 * Created by larsl on 28.04.2017.
 */

public class ActivityRecyclerItem extends MVPEventRecyclerItem<ActivityItemEvent> {

    private Activity activity;

    @Bindable
    private Muscles muscles;

    public ActivityRecyclerItem(Activity activity, Muscles muscles) {
        this.activity = activity;
        this.muscles = muscles;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    public String getName() {
        return activity.getName();
    }

    public void clicked() {
        if (getIsAble()) {
            getEvents().onActivitySelected(activity);
        } else {
            getEvents().onActivityToDemanding(activity);
        }
    }

    public void setMuscles(Muscles muscles) {
        if (muscles == null) {
            return;
        }
        this.muscles = muscles;
        notifyChange();
    }

    public boolean getIsAble() {
        return !activity.isToDemanding(muscles);
    }

    public Activity getActivity() {
        return activity;
    }
}
