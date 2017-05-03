package de.ironcoding.fitsim.app.injection;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ironcoding.fitsim.util.AnalyticsLogger;

/**
 * Created by larsl on 02.05.2017.
 */
@Module
public class FirebaseModule {

    public static final String CHILD_HIGHSCORE = "highscore";

    public static final String CHILD_POINTS = "points";

    @Provides
    FirebaseDatabase providesFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Named(CHILD_HIGHSCORE)
    DatabaseReference providesHighscoreDbReference(FirebaseDatabase database) {
        return database.getReference().child(CHILD_HIGHSCORE);
    }

    @Provides
    FirebaseAuth providesFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    FirebaseAnalytics providesFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @Singleton
    AnalyticsLogger providesAnalyticsLogger(FirebaseAnalytics analytics) {
        return new AnalyticsLogger(analytics);
    }

}
