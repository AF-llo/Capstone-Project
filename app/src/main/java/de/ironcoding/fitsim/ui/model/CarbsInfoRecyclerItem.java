package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Macros;

/**
 * Created by larsl on 04.05.2017.
 */

public class CarbsInfoRecyclerItem extends InfoRecyclerItem {

    private float consumedCarbs;

    public CarbsInfoRecyclerItem(Macros macros) {
        consumedCarbs = macros.getConsumedCarbs();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.carbs);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.consumed_gram), formatter.format(consumedCarbs));
    }

    public CarbsInfoRecyclerItem(Parcel in) {
        super(in);
        this.consumedCarbs = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(consumedCarbs);
    }

    public static final Parcelable.Creator<CarbsInfoRecyclerItem> CREATOR = new Parcelable.Creator<CarbsInfoRecyclerItem>() {
        @Override
        public CarbsInfoRecyclerItem createFromParcel(Parcel source) {
            return new CarbsInfoRecyclerItem(source);
        }

        @Override
        public CarbsInfoRecyclerItem[] newArray(int size) {
            return new CarbsInfoRecyclerItem[size];
        }
    };

}
