package de.ironcoding.fitsim.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.ui.model.InfoRecyclerItem;
import de.ironcoding.fitsim.ui.model.StaminaRecyclerItem;
import de.ironcoding.fitsim.ui.model.StrengthRecyclerItem;
import de.ironcoding.fitsim.ui.model.WeightRecyclerItem;

/**
 * Created by larsl on 03.05.2017.
 */

public class BodyPresenter extends InfoListPresenter {
    @Override
    protected List<InfoRecyclerItem> createItems() {
        List<InfoRecyclerItem> items = new ArrayList<>();
        Athlete athlete = getAthlete();
        items.add(new WeightRecyclerItem(athlete));
        items.add(new StrengthRecyclerItem(athlete));
        items.add(new StaminaRecyclerItem(athlete));
        return items;
    }
}
