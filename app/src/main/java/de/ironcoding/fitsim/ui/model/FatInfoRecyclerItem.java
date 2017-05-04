package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Macros;

/**
 * Created by larsl on 04.05.2017.
 */

public class FatInfoRecyclerItem extends InfoRecyclerItem {

    private float consumedFat;

    public FatInfoRecyclerItem(Macros macros) {
        consumedFat = macros.getConsumedFat();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.fat);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.consumed_gram), formatter.format(consumedFat));
    }

}
