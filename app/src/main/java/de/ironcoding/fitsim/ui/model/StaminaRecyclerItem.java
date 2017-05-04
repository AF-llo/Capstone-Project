package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 04.05.2017.
 */

public class StaminaRecyclerItem extends InfoRecyclerItem {

    private float stamina;

    private float maxStamina;

    public StaminaRecyclerItem(Athlete athlete) {
        super();
        stamina = athlete.getBody().getFitness().getStamina();
        maxStamina = Body.MAX_FITNESS;
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.stamina);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(stamina), formatter.format(maxStamina));
    }

    public StaminaRecyclerItem(Parcel in) {
        super(in);
        this.stamina = in.readFloat();
        this.maxStamina = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(stamina);
        dest.writeFloat(maxStamina);
    }

    public static final Parcelable.Creator<StaminaRecyclerItem> CREATOR = new Parcelable.Creator<StaminaRecyclerItem>() {
        @Override
        public StaminaRecyclerItem createFromParcel(Parcel source) {
            return new StaminaRecyclerItem(source);
        }

        @Override
        public StaminaRecyclerItem[] newArray(int size) {
            return new StaminaRecyclerItem[size];
        }
    };
}
