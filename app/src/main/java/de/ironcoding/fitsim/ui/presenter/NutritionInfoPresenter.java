package de.ironcoding.fitsim.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.ui.model.CaloriesRecyclerItem;
import de.ironcoding.fitsim.ui.model.CarbsInfoRecyclerItem;
import de.ironcoding.fitsim.ui.model.FatInfoRecyclerItem;
import de.ironcoding.fitsim.ui.model.InfoRecyclerItem;
import de.ironcoding.fitsim.ui.model.ProteintInfoRecyclerItem;

/**
 * Created by larsl on 04.05.2017.
 */

public class NutritionInfoPresenter extends InfoListPresenter {
    @Override
    protected List<InfoRecyclerItem> createItems() {
        Athlete athlete = getAthlete();
        List<InfoRecyclerItem> items = new ArrayList<>();
        items.add(new CaloriesRecyclerItem(athlete.getBody().getCalories()));
        items.add(new ProteintInfoRecyclerItem(athlete.getBody().getCalories().getMacros()));
        items.add(new CarbsInfoRecyclerItem(athlete.getBody().getCalories().getMacros()));
        items.add(new FatInfoRecyclerItem(athlete.getBody().getCalories().getMacros()));
        return items;
    }
}
