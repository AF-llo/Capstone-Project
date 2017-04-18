package de.ironcoding.fitsim.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by larsl on 15.04.2017.
 */

public class Muscles {

    private static Map<Integer, Muscle> availableMuscles = new HashMap<>();

    private boolean areBuild = false;

    static void buildUpMuscles(List<Muscle> newMuscles) {
        Muscles muscles = InstanceHolder.INSTANCE;
        muscles.areBuild = true;
        for (Muscle newMuscle : newMuscles) {
            if (newMuscle != null && !muscles.has(newMuscle.getId())) {
                availableMuscles.put(newMuscle.getId(), newMuscle);
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

    void strain(int musleId, int attraction) {
        availableMuscles.get(musleId).strain(attraction);
    }

    boolean isDurable(int muscleId) {
        return availableMuscles.get(muscleId).isDurable();
    }

    void relaxAll() {
        for (Muscle muscle : availableMuscles.values()) {
            muscle.relax();
        }
    }

    void breakDown() {
        for (Muscle muscle : availableMuscles.values()) {
            muscle.shrink();
        }
    }

    private static final class InstanceHolder {
        static final Muscles INSTANCE = new Muscles();
    }

    public List<Muscle> getAll() {
        List<Muscle> allMuscles = new ArrayList<>();
        for (Muscle muscle : availableMuscles.values()) {
            allMuscles.add(muscle);
        }
        return allMuscles;
    }

}
