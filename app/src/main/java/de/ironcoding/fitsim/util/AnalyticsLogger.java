package de.ironcoding.fitsim.util;

import android.os.Bundle;
import android.support.annotation.StringDef;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 03.05.2017.
 */

public class AnalyticsLogger {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SCREEN_PROFILE, SCREEN_GYM, SCREEN_NUTRITION, SCREEN_MORE, SCREEN_HIGHSCORE, SCREEN_MUSCLES, SCREEN_BODY, SCREEN_CALORIES, SCREEN_BMI, SCREEN_FFMI})
    public @interface Screen {}
    public static final String SCREEN_PROFILE ="screen_profile";
    public static final String SCREEN_GYM ="screen_gym";
    public static final String SCREEN_NUTRITION ="screen_nutrition";
    public static final String SCREEN_MORE ="screen_more";
    public static final String SCREEN_HIGHSCORE ="screen_highscore";
    public static final String SCREEN_MUSCLES ="screen_muscles";
    public static final String SCREEN_BODY ="screen_body";
    public static final String SCREEN_CALORIES ="screen_calories";
    public static final String SCREEN_BMI ="screen_calories";
    public static final String SCREEN_FFMI ="screen_calories";

    private static final String SCREEN_VIEW_EVENT = "screen_view";
    private static final String SCREEN_PARAM_NAME = "screen_name";

    private static final String ACTIVITY_EVENT = "activity_executed";
    private static final String ACTIVITY_PARAM_NAME = "activity_name";

    private static final String NUTRITION_EVENT = "nutrition_eaten";
    private static final String NUTRITION_PARAM_NAME = "nutrition_name";

    private FirebaseAnalytics firebaseAnalytics;

    public AnalyticsLogger(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    public void logScreen(@Screen String screen) {
        Bundle bundle = new Bundle();
        bundle.putString(SCREEN_PARAM_NAME, screen);
        firebaseAnalytics.logEvent(SCREEN_VIEW_EVENT, bundle);
    }

    public void logActivityDone(String activityName) {
        Bundle bundle = new Bundle();
        bundle.putString(ACTIVITY_PARAM_NAME, activityName);
        firebaseAnalytics.logEvent(ACTIVITY_EVENT, bundle);
    }

    public void logNutritionEaten(String nutrtionName) {
        Bundle bundle = new Bundle();
        bundle.putString(NUTRITION_PARAM_NAME, nutrtionName);
        firebaseAnalytics.logEvent(NUTRITION_EVENT, bundle);
    }
}
