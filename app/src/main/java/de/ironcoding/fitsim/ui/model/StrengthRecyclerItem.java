package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import java.text.DecimalFormat;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 04.05.2017.
 */

public class StrengthRecyclerItem extends InfoRecyclerItem {

    private float strength;

    private float maxStrength;

    private DecimalFormat formatter;

    public StrengthRecyclerItem(Athlete athlete) {
        super();
        strength = athlete.getBody().getFitness().getStrength();
        maxStrength = Body.MAX_FITNESS;
        formatter = new DecimalFormat(Body.FLOAT_FORMAT_PATTERN);
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.strength);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(strength), formatter.format(maxStrength));
    }
}
