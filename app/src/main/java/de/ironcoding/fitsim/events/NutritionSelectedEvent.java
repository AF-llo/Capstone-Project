package de.ironcoding.fitsim.events;

import de.appsfactory.mvplib.presenter.MVPEvents;
import de.ironcoding.fitsim.logic.Nutrition;

/**
 * Created by larsl on 28.04.2017.
 */

public interface NutritionSelectedEvent extends MVPEvents {
    void onNutritionSelected(Nutrition nutrition);
    void onCanNotEat(Nutrition nutrition);
}
