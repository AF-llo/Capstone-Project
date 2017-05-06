package de.ironcoding.fitsim.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import de.ironcoding.fitsim.util.AnalyticsLogger;

/**
 * Created by larsl on 04.05.2017.
 */

public class ProfileFragmentItem implements Parcelable {

    private String title;

    private @AnalyticsLogger.Screen String screenName;

    public ProfileFragmentItem(String title, @AnalyticsLogger.Screen String screenName) {
        this.title = title;
        this.screenName = screenName;
    }

    public String getTitle() {
        return title;
    }

    public @AnalyticsLogger.Screen String getScreenName() {
        return screenName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(screenName);
    }

    protected ProfileFragmentItem(Parcel in) {
        this.title = in.readString();
        @AnalyticsLogger.Screen String screenName = in.readString();
        this.screenName = screenName;
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
