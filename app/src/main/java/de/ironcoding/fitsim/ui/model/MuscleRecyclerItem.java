package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Muscle;

/**
 * Created by larsl on 04.05.2017.
 */

public class MuscleRecyclerItem extends InfoRecyclerItem {

    private String muscleName;

    private float volume;

    private float maxVolume;

    public MuscleRecyclerItem(Muscle muscle) {
        super();
        muscleName = muscle.getName();
        volume = muscle.getVolume();
        maxVolume = muscle.getLimit();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return muscleName;
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(volume), formatter.format(maxVolume));
    }
}
