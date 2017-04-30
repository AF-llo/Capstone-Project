package de.ironcoding.fitsim.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

/**
 * Created by larsl on 25.04.2017.
 */

public class AppSettings {

    private SharedPreferences sharedPreferences;

    public AppSettings(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private static final String PREF_KEY_ANDROID_ID = "de.ironcoding.pref.android_id";

    public String getAndroidIdFromSettings() {
        return sharedPreferences.getString(PREF_KEY_ANDROID_ID, "");
    }

    public void writeAndroidIdToSettings(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        sharedPreferences.edit()
                .putString(PREF_KEY_ANDROID_ID, "")
                .apply();
    }

}
