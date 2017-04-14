package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Body {

    /**
     * Min energy -> Less than zero is not possible
     */
    public static final int MIN_ENERGY = 0;

    /**
     * Max energy -> Every one has some limits
     */
    public static final int MAX_ENERGY = 100;

    /**
     * Minimum weight to prepare from anorexia
     */
    public static final float MIN_WEIGHT = 50;

    public static final float MIN_FITNESS = 40.0F;

    public static final float MAX_FITNESS = 100.0F;

    public static final float INITIAL_FITNESS = 60.0F;

    public static final float INITIAL_WEIGHT_MALE = 75.0F;
    public static final float INITIAL_WEIGHT_FEMALE = 65.0F;
    public static final float DEFAULT_SIZE_MALE = 175.0F;
    public static final float DEFAULT_SIZE_FEMALE = 170.0F;
    public static final int DEFAULT_AGE = 25;

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
            if (energy < MIN_ENERGY) {
                energy = MIN_ENERGY;
            }
            if (energy > MAX_ENERGY) {
                energy = MAX_ENERGY;
            }
            this.energy = energy;
            this.weight = weight < MIN_WEIGHT ? MIN_WEIGHT : weight;
        }

        public void consumeEnergy(int consumedEnergy) {
            int leftEnergy = energy - consumedEnergy;
            energy = leftEnergy < MIN_ENERGY ? MIN_ENERGY : leftEnergy;
        }

        public void gainEnergy(int gainedEnergy) {
            int obtainedEnergy = energy + gainedEnergy;
            energy = obtainedEnergy > MAX_ENERGY ? MAX_ENERGY : obtainedEnergy;
        }

        public void loseWeight(float lostWeight) {
            float leftWeight = weight - lostWeight;
            weight = leftWeight < MIN_WEIGHT ? MIN_WEIGHT : leftWeight;
        }

        public void putOnWeight(float increasedWeight) {
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

        private float strength = INITIAL_FITNESS;

        private float stamina = INITIAL_FITNESS;

        public Fitness(float strength, float stamina) {
            if (strength < MIN_FITNESS) {
                strength = MIN_FITNESS;
            }
            if (strength > MAX_FITNESS) {
                strength = MAX_FITNESS;
            }
            this.strength = strength;
            if (stamina < MIN_FITNESS) {
                stamina = MIN_FITNESS;
            }
            if (stamina > MAX_FITNESS) {
                stamina = MAX_FITNESS;
            }
            this.stamina = stamina;
        }

        public void improveStrength() {
            strength += calculateIprovement(strength);
        }

        public void improveStamina() {
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

        public void impairStrength() {
            strength -= calculateDecline(strength);
        }

        public void impairStamina() {
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

        public float getStrength() {
            return strength;
        }

        public float getStamina() {
            return stamina;
        }
    }

}
