package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 04.05.2017.
 */

public class WeightRecyclerItem extends InfoRecyclerItem {

    private float weight;

    public WeightRecyclerItem(Athlete athlete) {
        super();
        weight = athlete.getBody().getStats().getWeight();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.weight);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.weight_unit), formatter.format(weight));
    }

    public WeightRecyclerItem(Parcel in) {
        super(in);
        this.weight = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(weight);
    }

    public static final Parcelable.Creator<WeightRecyclerItem> CREATOR = new Parcelable.Creator<WeightRecyclerItem>() {
        @Override
        public WeightRecyclerItem createFromParcel(Parcel source) {
            return new WeightRecyclerItem(source);
        }

        @Override
        public WeightRecyclerItem[] newArray(int size) {
            return new WeightRecyclerItem[size];
        }
    };
}
