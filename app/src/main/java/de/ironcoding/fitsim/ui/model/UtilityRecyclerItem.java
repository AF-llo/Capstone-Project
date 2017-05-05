package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;

import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.events.UtilityEvent;

/**
 * Created by larsl on 05.05.2017.
 */

public abstract class UtilityRecyclerItem extends MVPEventRecyclerItem<UtilityEvent> {

    @Override
    public int getLayoutId() {
        return R.layout.layout_utility_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    @Bindable
    public String getTitle() {
        return "";
    }

    public abstract String getFormattedName(Context context, String title);

    public abstract int getIconResId();

    public void onClicked() {
        getEvents().utilitySelected(this);
    }

    public abstract Intent getStartIntent(Context context);
}
