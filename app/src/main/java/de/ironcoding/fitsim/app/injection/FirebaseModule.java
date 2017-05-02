package de.ironcoding.fitsim.app.injection;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by larsl on 02.05.2017.
 */
@Module
public class FirebaseModule {

    public static final String CHILD_HIGHSCORE = "highscore";

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

}
