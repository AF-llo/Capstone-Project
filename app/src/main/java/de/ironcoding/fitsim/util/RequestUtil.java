package de.ironcoding.fitsim.util;

import android.net.Uri;

import de.ironcoding.fitsim.app.injection.FirebaseModule;

/**
 * Created by larsl on 03.05.2017.
 */

public class RequestUtil {

    private static final String FIREBASE_HIGSCORE_URL = "https:///" + FirebaseModule.CHILD_HIGHSCORE + "/.json";

    public static final String SCHEME_HTTPS = "https";
    public static final String FIREBASE_URL = "fitsim-92a12.firebaseio.com";
    private static final String PATH_HIGHSCORE = FirebaseModule.CHILD_HIGHSCORE;
    private static final String PATH_JSON = ".json";

    public static String highscoreRequestUrl() {
        return new Uri.Builder()
                .scheme(SCHEME_HTTPS)
                .authority(FIREBASE_URL)
                .appendPath(PATH_HIGHSCORE)
                .appendPath(PATH_JSON)
                .build()
                .toString();
    }

}
