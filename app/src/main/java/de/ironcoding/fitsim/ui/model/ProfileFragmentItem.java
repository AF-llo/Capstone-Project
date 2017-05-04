package de.ironcoding.fitsim.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by larsl on 04.05.2017.
 */

public class ProfileFragmentItem implements Parcelable {

    private String title;

    public ProfileFragmentItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    protected ProfileFragmentItem(Parcel in) {
        this.title = in.readString();
    }

    public static final Parcelable.Creator<ProfileFragmentItem> CREATOR = new Parcelable.Creator<ProfileFragmentItem>() {
        @Override
        public ProfileFragmentItem createFromParcel(Parcel source) {
            return new ProfileFragmentItem(source);
        }

        @Override
        public ProfileFragmentItem[] newArray(int size) {
            return new ProfileFragmentItem[size];
        }
    };
}
