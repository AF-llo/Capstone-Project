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

    private Calories calories;

    private Properties properties;

    // TODO: 15.04.2017 create list of muscles

    private Body() {}

    public static Body warmUpAverageMale(@BodyType.Name String type) {
        Properties properties = new Properties(Athlete.MALE, DEFAULT_SIZE_MALE, DEFAULT_AGE);
        Stats stats = new Stats(MAX_ENERGY, INITIAL_WEIGHT_MALE);
        Fitness fitness = new Fitness(INITIAL_FITNESS, INITIAL_FITNESS);
        return warmUp(type, properties, stats, fitness);
    }

    public static Body warmUpAverageFemale(@BodyType.Name String type) {
        Properties properties = new Properties(Athlete.FEMALE, DEFAULT_SIZE_FEMALE, DEFAULT_AGE);
        Stats stats = new Stats(MAX_ENERGY, INITIAL_WEIGHT_FEMALE);
        Fitness fitness = new Fitness(INITIAL_FITNESS, INITIAL_FITNESS);
        return warmUp(type, properties, stats, fitness);
    }

    public static Body warmUp(@BodyType.Name String type, Properties properties, Stats stats, Fitness fitness) {
        if (type == null) {
            throw new IllegalArgumentException("Every body should have a valid type!");
        }
        if (stats == null) {
            throw new IllegalArgumentException("A body without stats is propably dead!!");
        }
        if (fitness == null) {
            throw new IllegalArgumentException("Without fitness you cant do anything!");
        }
        if (properties == null) {
            throw new IllegalArgumentException("When you have been born, you got some properties!");
        }
        BodyType bodyType = BodyType.getType(type);
        Body body = new Body();
        body.type = bodyType;
        body.stats = stats;
        body.fitness = fitness;
        body.calories = Calories.createWithDefaultProportion(body.type , properties, stats);
        body.properties = properties;
        return body;
    }

    boolean isAbleToDo(Activity activity) {
        return activity.getEffort() <= stats.getEnergy() && !activity.isToDemanding();
    }

    void performActivity(Activity activity) {
        stats.consumeEnergy(activity.getEffort());
        calories.increaseRequiredEnergy(activity.getPal(), activity.getDuration());
        activity.perform(fitness, type);
    }

    void digest(Nutrition nutrition) {
        calories.consume(nutrition);
    }

    public Body copy() {
        Body body = new Body();
        body.stats = stats;
        body.fitness = fitness;
        body.type = type;
        body.properties = properties;
        return body;
    }

    public Stats getStats() {
        return stats;
    }

    public Fitness getFitness() {
        return fitness;
    }

    public Calories getCalories() {
        return calories;
    }

    public Properties getProperties() {
        return properties;
    }

    public static class Properties {
        private final @Athlete.Gender int gender;

        private final float size;

        private final int age;

        public Properties(int gender, float size, int age) {
            this.gender = gender;
            if (size < 0 ) {
                size = gender == Athlete.MALE ? DEFAULT_SIZE_MALE : DEFAULT_SIZE_FEMALE;
            }
            this.size = size;
            if (age < 0) {
                age = DEFAULT_AGE;
            }
            this.age = age;
        }

        public @Athlete.Gender int getGender() {
            return gender;
        }

        public float getSize() {
            return size;
        }

        public int getAge() {
            return age;
        }
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

        public void improveStrength(float buildUp) {
            strength += calculateIprovement(strength, buildUp);
        }

        public void improveStamina(float endurance) {
            stamina += calculateIprovement(stamina, endurance);
        }

        private float calculateIprovement(float current, float metabolism) {
            // TODO: 14.04.2017  implement not linear
            if (current <= MAX_FITNESS - metabolism) {
                return metabolism;
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
