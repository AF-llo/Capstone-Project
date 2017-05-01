package de.ironcoding.fitsim.ui.model;

import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.events.NutritionSelectedEvent;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.Nutrition;

/**
 * Created by larsl on 28.04.2017.
 */

public class NutritionRecyclerItem extends MVPEventRecyclerItem<NutritionSelectedEvent> {

    private final Nutrition nutrition;

    private Body body;

    public NutritionRecyclerItem(Nutrition nutrition, Body body) {
        this.nutrition = nutrition;
        this.body = body;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_nutrition_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    public String getName() {
        return nutrition.getName();
    }

    public boolean getCanEat() {
        return nutrition.isAccepted(body);
    }

    public void setBody(Body body) {
        if (body == null) {
            return;
        }
        this.body = body;
        notifyChange();
    }

    public void clicked() {
        if (getCanEat()) {
            getEvents().onNutritionSelected(nutrition);
        } else {
            getEvents().onCanNotEat(nutrition);
        }
    }
}
