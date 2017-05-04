package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Macros;

/**
 * Created by larsl on 04.05.2017.
 */

public class ProteintInfoRecyclerItem extends InfoRecyclerItem {

    private float consumedProteine;

    public ProteintInfoRecyclerItem(Macros macros) {
        consumedProteine = macros.getConsumedProteine();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.proteine);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.consumed_gram), formatter.format(consumedProteine));
    }
}
