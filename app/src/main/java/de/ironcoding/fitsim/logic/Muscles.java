package de.ironcoding.fitsim.logic;

import android.util.SparseArray;

import java.util.ArrayList;
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

    void strain(int musleId, int attraction) {
        availableMuscles.get(musleId).strain(attraction);
    }

    boolean isDurable(int muscleId) {
        return availableMuscles.get(muscleId).isDurable();
    }

    void relaxAll() {
        for (int i = 0; i < availableMuscles.size(); i++) {
            availableMuscles.get(availableMuscles.keyAt(i)).relax();
        }
    }

    private static final class InstanceHolder {
        static final Muscles INSTANCE = new Muscles();
    }

    public List<Muscle> getAllCopied() {
        List<Muscle> allMuscles = new ArrayList<>();
        for (int i = 0; i < availableMuscles.size(); i++) {
            allMuscles.add(availableMuscles.get(availableMuscles.keyAt(i)).copy());
        }
        return allMuscles;
    }

}
