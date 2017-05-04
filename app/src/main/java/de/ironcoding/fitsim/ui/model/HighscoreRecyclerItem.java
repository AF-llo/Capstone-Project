package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

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

    public HighscoreRecyclerItem(Parcel in) {
        super(in);
        this.name = in.readString();
        this.points = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(name);
        dest.writeLong(points);
    }

    public static final Parcelable.Creator<HighscoreRecyclerItem> CREATOR = new Parcelable.Creator<HighscoreRecyclerItem>() {
        @Override
        public HighscoreRecyclerItem createFromParcel(Parcel source) {
            return new HighscoreRecyclerItem(source);
        }

        @Override
        public HighscoreRecyclerItem[] newArray(int size) {
            return new HighscoreRecyclerItem[size];
        }
    };
}
