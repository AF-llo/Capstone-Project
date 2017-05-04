package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Macros;

/**
 * Created by larsl on 04.05.2017.
 */

public class ProteintInfoRecyclerItem extends InfoRecyclerItem {

    private float consumedProteine;

    public ProteintInfoRecyclerItem(Macros macros) {
        consumedProteine = macros.getConsumedProteine();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.proteine);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.consumed_gram), formatter.format(consumedProteine));
    }

    public ProteintInfoRecyclerItem(Parcel in) {
        super(in);
        this.consumedProteine = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(consumedProteine);
    }

    public static final Parcelable.Creator<ProteintInfoRecyclerItem> CREATOR = new Parcelable.Creator<ProteintInfoRecyclerItem>() {
        @Override
        public ProteintInfoRecyclerItem createFromParcel(Parcel source) {
            return new ProteintInfoRecyclerItem(source);
        }

        @Override
        public ProteintInfoRecyclerItem[] newArray(int size) {
            return new ProteintInfoRecyclerItem[size];
        }
    };
}
