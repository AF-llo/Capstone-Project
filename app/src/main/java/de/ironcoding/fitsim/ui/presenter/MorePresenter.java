package de.ironcoding.fitsim.ui.presenter;

import android.databinding.ObservableArrayList;

import de.ironcoding.fitsim.events.UtilityEvent;
import de.ironcoding.fitsim.ui.model.BmiRecyclerItem;
import de.ironcoding.fitsim.ui.model.FfmiRecyclerIitem;
import de.ironcoding.fitsim.ui.model.UtilityRecyclerItem;

/**
 * Created by larsl on 28.04.2017.
 */

public class MorePresenter extends BasePresenter implements UtilityEvent {

    public ObservableArrayList<UtilityRecyclerItem> items = new ObservableArrayList<>();

    public MorePresenter(MoreCallback callback) {
        super(callback);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (items.size() == 0) {
            items.add(new BmiRecyclerItem());
            items.add(new FfmiRecyclerIitem());
        }
    }

    @Override
    public void utilitySelected(UtilityRecyclerItem utilityRecyclerItem) {
        analyticsLogger.logScreen(utilityRecyclerItem.getSreenName());
        notifyCallbackShowUtilityScreen(utilityRecyclerItem);
    }

    public void showProfile() {
        notifyCallbackShowProfile();
    }

    private void notifyCallbackShowUtilityScreen(UtilityRecyclerItem item) {
        if (callback != null) {
            ((MoreCallback)callback).showUtilityScreen(item);
        }
    }

    public interface MoreCallback extends BasePresenter.Callback {
        void showUtilityScreen(UtilityRecyclerItem item);
    }
}
