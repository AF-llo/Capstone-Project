package de.ironcoding.fitsim.logic;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by larsl on 15.04.2017.
 */

public class Muscles {

    private static SparseArray<Muscle> availableMuscles = new SparseArray<>();

    private boolean areBuild = false;

    static void buildUpMuscles(List<Muscle> newMuscles) {
        Muscles muscles = InstanceHolder.INSTANCE;
        muscles.areBuild = true;
        for (Muscle newMuscle : newMuscles) {
            if (newMuscle != null && !muscles.has(newMuscle.getId())) {
                availableMuscles.put(newMuscle.getCondition(), newMuscle);
            }
        }
    }

    static Muscles get() {
        Muscles muscles = InstanceHolder.INSTANCE;
        if (!muscles.areBuild) {
            throw new IllegalArgumentException("Please build your Athlete first!");
        }
        return InstanceHolder.INSTANCE;
    }

    private boolean has(int muscleId) {
        return availableMuscles != null && availableMuscles.get(muscleId) != null;
    }

    public void strain(int musleId, int attraction) {
        Muscle muscle = availableMuscles.get(musleId);
        if (muscle != null) {
            muscle.strain(attraction);
        }
    }

    public boolean isDurable(int muscleId) {
        Muscle muscle = availableMuscles.get(muscleId);
        return muscle != null && muscle.isDurable();
    }

    private static final class InstanceHolder {
        static final Muscles INSTANCE = new Muscles();
    }

}
