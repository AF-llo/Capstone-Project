package de.ironcoding.fitsim.events;

import de.appsfactory.mvplib.presenter.MVPEvents;
import de.ironcoding.fitsim.logic.Activity;

/**
 * Created by larsl on 28.04.2017.
 */

public interface ActivityItemEvent extends MVPEvents {
    void onActivitySelected(Activity activity);
    void onActivityToDemanding(Activity activity);
}
