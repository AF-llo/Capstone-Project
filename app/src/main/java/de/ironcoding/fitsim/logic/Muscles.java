package de.ironcoding.fitsim.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by larsl on 15.04.2017.
 */

public class Muscles {

    private Map<Long, Muscle> availableMuscles = new HashMap<>();

    public static Muscles buildUpMuscles(List<Muscle> newMuscles) {
        Muscles muscles = new Muscles();
        for (Muscle newMuscle : newMuscles) {
            if (newMuscle != null && !muscles.has(newMuscle.getId())) {
                muscles.availableMuscles.put(newMuscle.getId(), newMuscle);
            }
        }
        return muscles;
    }

    private boolean has(long muscleId) {
        return availableMuscles != null && availableMuscles.get(muscleId) != null;
    }

    public Muscle getMuscle(long muscleId) {
        return availableMuscles.get(muscleId);
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

    public List<Muscle> getAll() {
        List<Muscle> allMuscles = new ArrayList<>();
        for (Muscle muscle : availableMuscles.values()) {
            allMuscles.add(muscle);
        }
        return allMuscles;
    }

    public Muscles copy(){
        Muscles muscles = new Muscles();
        for (Map.Entry<Long, Muscle> integerMuscleEntry : availableMuscles.entrySet()) {
            muscles.availableMuscles.put(integerMuscleEntry.getKey(), integerMuscleEntry.getValue().copy());
        }
        return muscles;
    }

}
