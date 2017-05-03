package de.ironcoding.fitsim.ui.model;

import de.appsfactory.mvplib.presenter.MVPRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 02.05.2017.
 */

public class HighscoreRecyclerItem extends MVPRecyclerItem {

    private final String name;

    private final long points;

    public HighscoreRecyclerItem(IHighscore highscore) {
        this.name = highscore.getName();
        this.points = highscore.getPoints();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_highscore_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getPoints() {
        return String.valueOf(points);
    }
}
