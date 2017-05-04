package de.ironcoding.fitsim.ui.model;

import android.content.Context;

import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 02.05.2017.
 */

public class HighscoreRecyclerItem extends InfoRecyclerItem {

    private final String name;

    private final long points;

    public HighscoreRecyclerItem(IHighscore highscore) {
        super();
        this.name = highscore.getName();
        this.points = highscore.getPoints();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_info_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return name == null ? "" : name;
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.points), String.valueOf(points));
    }
}
