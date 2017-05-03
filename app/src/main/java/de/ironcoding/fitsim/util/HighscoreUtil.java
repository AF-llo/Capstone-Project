package de.ironcoding.fitsim.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import de.ironcoding.fitsim.app.injection.FirebaseModule;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Muscles;

/**
 * Created by larsl on 02.05.2017.
 */

public class HighscoreUtil {

    private static final float SCALE_EXPERTISE = 0.001F;
    private static final float SCALE_WEIGHT = 1000F;
    private static final float SCALE_MUSCLES = 1000F;
    private static final float SCALE_FITNESS = 10F;

    public static final int NUMBER_OF_HIGHSCORE_ENTRIES = 10;

    public static long getPoints(Athlete athlete) {
        Level level = athlete.getLevel();
        Body body = athlete.getBody();
        Body.Stats stats = body.getStats();
        Body.Fitness fitness = body.getFitness();
        Muscles muscles = body.getMuscles();
        long experiencePoints = (long) (level.getTotalExperience() * SCALE_EXPERTISE);
        long weightPoints = (long) (stats.getPercentualWeightDiff() * SCALE_WEIGHT);
        long musclePoints = (long) (muscles.getAveragePercentualGrowing() * SCALE_MUSCLES);
        long staminaPoints = (long) (fitness.getGrownStamina() * SCALE_FITNESS);
        long strengthPoints = (long) (fitness.getGrownStrength() * SCALE_FITNESS);
        long points = experiencePoints + weightPoints + musclePoints + staminaPoints + strengthPoints;
        if (points < 0) {
            points = 0;
        }
        return points;
    }

    public static Query topTenQuery(DatabaseReference highscoreReference) {
        return highscoreReference.orderByChild(FirebaseModule.CHILD_POINTS)
                .limitToFirst(NUMBER_OF_HIGHSCORE_ENTRIES);
    }
}
