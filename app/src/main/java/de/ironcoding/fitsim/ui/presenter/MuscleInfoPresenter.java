package de.ironcoding.fitsim.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.ui.model.InfoRecyclerItem;
import de.ironcoding.fitsim.ui.model.MuscleRecyclerItem;

/**
 * Created by larsl on 04.05.2017.
 */

public class MuscleInfoPresenter extends InfoListPresenter {

    @Override
    protected List<InfoRecyclerItem> createItems() {
        List<InfoRecyclerItem> items = new ArrayList<>();
        Athlete athlete = getAthlete();
        List<Muscle> muscles = athlete.getBody().getMuscles().getAll();
        for (Muscle muscle : muscles) {
            items.add(new MuscleRecyclerItem(muscle));
        }
        return items;
    }
}
