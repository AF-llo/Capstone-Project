package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.util.FormatUtil;

/**
 * Created by larsl on 04.05.2017.
 */

public class StrengthRecyclerItem extends InfoRecyclerItem {

    private float strength;

    private float maxStrength;

    private DecimalFormat formatter;

    public StrengthRecyclerItem(Athlete athlete) {
        super();
        strength = athlete.getBody().getFitness().getStrength();
        maxStrength = Body.MAX_FITNESS;
        formatter = FormatUtil.baseFloatForatter();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.strength);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(strength), formatter.format(maxStrength));
    }

    public StrengthRecyclerItem(Parcel in) {
        super(in);
        this.strength = in.readFloat();
        this.maxStrength = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(strength);
        dest.writeFloat(maxStrength);
    }

    public static final Parcelable.Creator<StrengthRecyclerItem> CREATOR = new Parcelable.Creator<StrengthRecyclerItem>() {
        @Override
        public StrengthRecyclerItem createFromParcel(Parcel source) {
            return new StrengthRecyclerItem(source);
        }

        @Override
        public StrengthRecyclerItem[] newArray(int size) {
            return new StrengthRecyclerItem[size];
        }
    };
}
