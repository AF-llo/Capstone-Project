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

    private static final int MIN_FITNESS = 40;

    private static final int MAX_FITNESS = 100;

    private static final int INITIAL_FITNESS = 60;

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

    public void performActivity(Activity activity) {
        // TODO: 13.04.2017  
    }

    public void digest(Food food) {
        // TODO: 13.04.2017
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

        private float power = INITIAL_FITNESS;

        private float stamina = INITIAL_FITNESS;

        public Fitness(int power, int stamina) {
            if (power < MIN_FITNESS) {
                power = MIN_FITNESS;
            }
            if (power > MAX_FITNESS) {
                power = MAX_FITNESS;
            }
            this.power = power;
            if (stamina < MIN_FITNESS) {
                stamina = MIN_FITNESS;
            }
            if (stamina > MAX_FITNESS) {
                stamina = MAX_FITNESS;
            }
            this.stamina = stamina;
        }

        private void improveStrength() {
            power += calculateIprovement(power);
        }

        private void improveStamina() {
            stamina += calculateIprovement(stamina);
        }

        private float calculateIprovement(float current) {
            // TODO: 14.04.2017  implement not linear
            float baseImprovement = 2;
            if (current <= MAX_FITNESS - baseImprovement) {
                return baseImprovement;
            } else {
                return MAX_FITNESS - current;
            }
        }

        private void impairStrength() {
            power -= calculateDecline(power);
        }

        private void impairStamina() {
            stamina -= calculateDecline(stamina);
        }

        private float calculateDecline(float current) {
            // TODO: 14.04.2017  implement not linear
            float baseDecline = 1;
            if (current >= MIN_FITNESS + baseDecline) {
                return baseDecline;
            } else {
                return current - MIN_FITNESS;
            }
        }

        public float getPower() {
            return power;
        }

        public float getStamina() {
            return stamina;
        }
    }

}
