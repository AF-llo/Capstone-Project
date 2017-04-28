package de.ironcoding.fitsim.ui.model;

import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.events.ActivitySelectedEvent;
import de.ironcoding.fitsim.logic.Activity;

/**
 * Created by larsl on 28.04.2017.
 */

public class ActivityRecyclerItem extends MVPEventRecyclerItem<ActivitySelectedEvent> {

    private Activity activity;

    public ActivityRecyclerItem(Activity activity) {
        this.activity = activity;
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
        getEvents().onActivitySelected(activity);
    }
}
