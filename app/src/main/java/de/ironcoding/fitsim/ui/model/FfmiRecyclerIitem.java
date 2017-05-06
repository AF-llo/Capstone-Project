package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.content.Intent;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.ui.activities.FfmiActivity;
import de.ironcoding.fitsim.util.AnalyticsLogger;

/**
 * Created by larsl on 05.05.2017.
 */

public class FfmiRecyclerIitem extends UtilityRecyclerItem {
    @Override
    public String getFormattedName(Context context, String title) {
        return context.getString(R.string.ffmi);
    }

    @Override
    public int getIconResId() {
        return R.drawable.ico_ffmi;
    }

    @Override
    public Intent getStartIntent(Context context) {
        return FfmiActivity.getIntent(context);
    }

    @Override
    public String getSreenName() {
        return AnalyticsLogger.SCREEN_FFMI;
    }
}
