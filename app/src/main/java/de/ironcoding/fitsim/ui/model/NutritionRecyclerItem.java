package de.ironcoding.fitsim.ui.model;

import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem;
import de.ironcoding.fitsim.events.NutritionSelectedEvent;
import de.ironcoding.fitsim.logic.Nutrition;

/**
 * Created by larsl on 28.04.2017.
 */

public class NutritionRecyclerItem extends MVPEventRecyclerItem<NutritionSelectedEvent> {

    private final Nutrition nutrition;

    public NutritionRecyclerItem(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getItemId() {
        return 0;
    }

    public void clicked() {
        getEvents().onNutritionSelected(nutrition);
    }
}
