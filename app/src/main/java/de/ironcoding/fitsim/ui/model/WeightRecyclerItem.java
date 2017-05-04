package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 04.05.2017.
 */

public class WeightRecyclerItem extends InfoRecyclerItem {

    private float weight;

    public WeightRecyclerItem(Athlete athlete) {
        super();
        weight = athlete.getBody().getStats().getWeight();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.weight);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.weight_unit), formatter.format(weight));
    }
}
