package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 02.05.2017.
 */

public class Highscore {

    private static final float SCALE_EXPERTISE = 0.001F;
    private static final float SCALE_WEIGHT = 1000F;
    private static final float SCALE_MUSCLES = 1000F;
    private static final float SCALE_FITNESS = 10F;

    private Level level;

    private Body.Stats stats;

    private Body.Fitness fitness;

    private Muscles muscles;

    public Highscore(Athlete athlete) {
        level = athlete.getLevel();
        Body body = athlete.getBody();
        stats = body.getStats();
        fitness = body.getFitness();
        muscles = body.getMuscles();
    }

    public long getPoints() {
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
}
