package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Calories;

/**
 * Created by larsl on 04.05.2017.
 */

public class CaloriesRecyclerItem extends InfoRecyclerItem {

    private float consumedCalories;

    private float requiredCalories;

    public CaloriesRecyclerItem(Calories calories) {
        super();
        consumedCalories = calories.getConsumedEnergy();
        requiredCalories = calories.getRequiredEnergy();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.calories);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.required_calories), formatter.format(consumedCalories), formatter.format(requiredCalories));
    }
}
