package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Calories;

/**
 * Created by larsl on 04.05.2017.
 */

public class CaloriesRecyclerItem extends InfoRecyclerItem {

    private float consumedCalories;

    private float requiredCalories;

    public CaloriesRecyclerItem(Calories calories) {
        super();
        consumedCalories = calories.getConsumedEnergy();
        requiredCalories = calories.getRequiredEnergy();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return context.getString(R.string.calories);
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.required_calories), formatter.format(consumedCalories), formatter.format(requiredCalories));
    }

    public CaloriesRecyclerItem(Parcel in) {
        super(in);
        this.consumedCalories = in.readFloat();
        this.requiredCalories = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(consumedCalories);
        dest.writeFloat(requiredCalories);
    }

    public static final Parcelable.Creator<CaloriesRecyclerItem> CREATOR = new Parcelable.Creator<CaloriesRecyclerItem>() {
        @Override
        public CaloriesRecyclerItem createFromParcel(Parcel source) {
            return new CaloriesRecyclerItem(source);
        }

        @Override
        public CaloriesRecyclerItem[] newArray(int size) {
            return new CaloriesRecyclerItem[size];
        }
    };
}
