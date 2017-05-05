package de.ironcoding.fitsim.ui.presenter;

import de.ironcoding.fitsim.ui.activities.BmiActivity;
import de.ironcoding.fitsim.ui.activities.FfmiActivity;

/**
 * Created by larsl on 28.04.2017.
 */

public class MorePresenter extends BasePresenter {
    public void startBmi() {
        FfmiActivity.start(getContext());
    }
}
