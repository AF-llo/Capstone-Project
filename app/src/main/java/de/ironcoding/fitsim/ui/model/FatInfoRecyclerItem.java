package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Macros;

/**
 * Created by larsl on 04.05.2017.
 */

public class FatInfoRecyclerItem extends InfoRecyclerItem {

    private float consumedFat;

    public FatInfoRecyclerItem(Macros macros) {
        consumedFat = macros.getConsumedFat();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.fat);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.consumed_gram), formatter.format(consumedFat));
    }
    public FatInfoRecyclerItem(Parcel in) {
        super(in);
        this.consumedFat = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(consumedFat);
    }

    public static final Parcelable.Creator<FatInfoRecyclerItem> CREATOR = new Parcelable.Creator<FatInfoRecyclerItem>() {
        @Override
        public FatInfoRecyclerItem createFromParcel(Parcel source) {
            return new FatInfoRecyclerItem(source);
        }

        @Override
        public FatInfoRecyclerItem[] newArray(int size) {
            return new FatInfoRecyclerItem[size];
        }
    };


}
