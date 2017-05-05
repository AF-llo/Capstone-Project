package de.ironcoding.fitsim.events;

import de.appsfactory.mvplib.presenter.MVPEvents;
import de.ironcoding.fitsim.ui.model.UtilityRecyclerItem;

/**
 * Created by larsl on 05.05.2017.
 */

public interface UtilityEvent extends MVPEvents{
    void utilitySelected(UtilityRecyclerItem utilityRecyclerItem);
}
