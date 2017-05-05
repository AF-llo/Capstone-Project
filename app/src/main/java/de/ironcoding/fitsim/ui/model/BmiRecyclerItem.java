package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.content.Intent;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.ui.activities.BmiActivity;

/**
 * Created by larsl on 05.05.2017.
 */

public class BmiRecyclerItem extends UtilityRecyclerItem {

    @Override
    public String getFormattedName(Context context, String title) {
        return context.getString(R.string.bmi);
    }

    @Override
    public int getIconResId() {
        return R.drawable.ico_bmi;
    }

    @Override
    public Intent getStartIntent(Context context) {
        return BmiActivity.getIntent(context);
    }
}
