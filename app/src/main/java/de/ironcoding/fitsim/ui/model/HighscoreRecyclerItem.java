package de.ironcoding.fitsim.ui.model;

import de.appsfactory.mvplib.presenter.MVPRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.firebase.model.UserHighscore;

/**
 * Created by larsl on 02.05.2017.
 */

public class HighscoreRecyclerItem extends MVPRecyclerItem {

    private UserHighscore highscore;

    public HighscoreRecyclerItem(UserHighscore highscore) {
        this.highscore = highscore;
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
        return highscore.getName();
    }

    public String getPoints() {
        return String.valueOf(highscore.getPoints());
    }
}
