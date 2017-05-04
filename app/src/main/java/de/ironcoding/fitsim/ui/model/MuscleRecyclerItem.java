package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Muscle;

/**
 * Created by larsl on 04.05.2017.
 */

public class MuscleRecyclerItem extends InfoRecyclerItem {

    private String muscleName;

    private float volume;

    private float maxVolume;

    public MuscleRecyclerItem(Muscle muscle) {
        super();
        muscleName = muscle.getName();
        volume = muscle.getVolume();
        maxVolume = muscle.getLimit();
    }

    @Override
    public String getFormattedTitle(Context context, String string) {
        return muscleName;
    }

    @Override
    public String getFormattedSubtitle(Context context, String string) {
        return String.format(context.getString(R.string.simple_max_string), formatter.format(volume), formatter.format(maxVolume));
    }

    public MuscleRecyclerItem(Parcel in) {
        super(in);
        this.muscleName = in.readString();
        this.volume = in.readFloat();
        this.maxVolume = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(muscleName);
        dest.writeFloat(volume);
        dest.writeFloat(maxVolume);
    }

    public static final Parcelable.Creator<MuscleRecyclerItem> CREATOR = new Parcelable.Creator<MuscleRecyclerItem>() {
        @Override
        public MuscleRecyclerItem createFromParcel(Parcel source) {
            return new MuscleRecyclerItem(source);
        }

        @Override
        public MuscleRecyclerItem[] newArray(int size) {
            return new MuscleRecyclerItem[size];
        }
    };
}
