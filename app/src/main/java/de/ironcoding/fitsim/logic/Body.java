package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Body {

    /**
     * Min energy -> Less than zero is not possible
     */
    private static final int MIN_ENERGY = 0;

    /**
     * Max energy -> Every one has some limits
     */
    private static final int MAX_ENERGY = 100;

    /**
     * Minimum weight to prepare from anorexia
     */
    private static final float MIN_WEIGHT = 50;

    private BodyType type;

    private Stats stats;

    private Fitness fitness;

    private Body() {}

    public static Body warmUp(@BodyType.Name String type, Stats stats, Fitness fitness) {
        if (type == null) {
            throw new IllegalArgumentException("Every body should have a valid type!");
        }
        if (stats == null) {
            throw new IllegalArgumentException("A body without stats is propably dead!!");
        }
        if (fitness == null) {
            throw new IllegalArgumentException("Without fitness you cant do anything!");
        }
        Body body = new Body();
        body.type = BodyType.getType(type);
        body.stats = stats;
        body.fitness = fitness;
        return body;
    }

    public Body copy() {
        Body body = new Body();
        body.stats = stats;
        body.fitness = fitness;
        body.type = type;
        return body;
    }

    public static class Stats {

        private int energy;

        private float weight;

        public Stats(int energy, float weight) {
            this.energy = energy;
            this.weight = weight < MIN_WEIGHT ? MIN_WEIGHT : weight;
        }

        private void consumeEnergy(int consumedEnergy) {
            int leftEnergy = energy - consumedEnergy;
            energy = leftEnergy < MIN_ENERGY ? MIN_ENERGY : leftEnergy;
        }

        private void gainEnergy(int gainedEnergy) {
            int obtainedEnergy = energy + gainedEnergy;
            energy = obtainedEnergy > MAX_ENERGY ? MAX_ENERGY : obtainedEnergy;
        }

        private void loseWeight(float lostWeight) {
            float leftWeight = weight - lostWeight;
            weight = leftWeight < MIN_WEIGHT ? MIN_WEIGHT : leftWeight;
        }

        private void putOnWeight(float increasedWeight) {
            weight += increasedWeight;
        }

        public int getEnergy() {
            return energy;
        }

        public float getWeight() {
            return weight;
        }
    }

    public static class Fitness {

        private int power;

        private int stamina;

        public Fitness(int power, int stamina) {

        }

        // TODO: 13.04.2017 implement relattion power - muscles, stamina - muscles - power

        public int getPower() {
            return power;
        }

        public int getStamina() {
            return stamina;
        }
    }

}
