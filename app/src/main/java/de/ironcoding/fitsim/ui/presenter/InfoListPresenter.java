package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;

import java.util.List;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.ironcoding.fitsim.ui.model.InfoRecyclerItem;

/**
 * Created by larsl on 04.05.2017.
 */

public abstract class InfoListPresenter extends BasePresenter {

    @MVPIncludeToState
    public ObservableArrayList<InfoRecyclerItem> items = new ObservableArrayList<>();

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        if (items.size() == 0) {
            List<InfoRecyclerItem> items = createItems();
            if (items != null) {
                this.items.addAll(items);
            }
        }
    }

    protected abstract List<InfoRecyclerItem> createItems() ;

}
