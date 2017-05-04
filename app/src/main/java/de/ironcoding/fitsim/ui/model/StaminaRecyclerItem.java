package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import java.text.DecimalFormat;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 04.05.2017.
 */

public class StaminaRecyclerItem extends InfoRecyclerItem {

    private float stamina;

    private float maxStamina;

    private DecimalFormat formatter;

    public StaminaRecyclerItem(Athlete athlete) {
        super();
        stamina = athlete.getBody().getFitness().getStamina();
        maxStamina = Body.MAX_FITNESS;
        formatter = new DecimalFormat(Body.FLOAT_FORMAT_PATTERN);
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.stamina);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(stamina), formatter.format(maxStamina));
    }

}
