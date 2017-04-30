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

    private Muscles muscles;

    private Body() {}

    public static Body warmUpAverageMale(@BodyType.Name String type, Muscles muscles) {
        Properties properties = new Properties(Athlete.MALE, DEFAULT_SIZE_MALE, DEFAULT_AGE);
        Stats stats = new Stats(MAX_ENERGY, INITIAL_WEIGHT_MALE);
        Fitness fitness = new Fitness(INITIAL_FITNESS, INITIAL_FITNESS);
        BodyType bodyType = BodyType.getType(type);
        Calories calories = Calories.createDefault(bodyType, properties, stats);
        return warmUp(bodyType, properties, stats, fitness, calories, muscles);
    }

    public static Body warmUpAverageFemale(@BodyType.Name String type, Muscles muscles) {
        Properties properties = new Properties(Athlete.FEMALE, DEFAULT_SIZE_FEMALE, DEFAULT_AGE);
        Stats stats = new Stats(MAX_ENERGY, INITIAL_WEIGHT_FEMALE);
        Fitness fitness = new Fitness(INITIAL_FITNESS, INITIAL_FITNESS);
        BodyType bodyType = BodyType.getType(type);
        Calories calories = Calories.createDefault(bodyType, properties, stats);
        return warmUp(bodyType, properties, stats, fitness, calories, muscles);
    }

    public static Body warmUp(BodyType type, Properties properties, Stats stats, Fitness fitness, Calories calories, Muscles muscles) {
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
        if (calories == null) {
            throw new IllegalArgumentException("Without calories you will not be able to train and do activities!");
        }
        if (muscles == null) {
            throw new IllegalArgumentException("No Muscles? You are propably a skelleton?");
        }
        Body body = new Body();
        body.type = type;
        body.stats = stats;
        body.fitness = fitness;
        body.calories = calories;
        body.properties = properties;
        body.muscles = muscles;
        return body;
    }

    boolean isAbleToDo(Activity activity) {
        return activity.getEffort() <= stats.getEnergy() && !activity.isToDemanding(muscles);
    }

    boolean canEat() {
        return !stats.isSaturated;
    }

    void performActivity(Activity activity) {
        stats.consumeEnergy(activity.getEffort());
        calories.increaseRequiredEnergy(activity.getPal(), activity.getDurationInHours());
        activity.perform(fitness, type, muscles);
    }

    void digest(Nutrition nutrition) {
        calories.consume(nutrition);
        nutrition.consume(stats, fitness);
    }

    void refresh() {
        stats.adjustStats(calories.weightForEnergyDifference());
        calories.startNewConsumption();
    }

    void relaxAllMuscles() {
        muscles.relaxAll();
    }

    void breakDown() {
        fitness.impairStrength();
        fitness.impairStamina();
        muscles.breakDown();
    }

    void defecate() {
        stats.setSaturated(false);
    }

    public Body copy() {
        Body body = new Body();
        body.stats = getStats();
        body.fitness = getFitness();
        body.type = BodyType.getType(type.getName());
        body.properties = getProperties();
        body.calories = getCalories();
        body.muscles = getMuscles();
        return body;
    }

    public Stats getStats() {
        return new Stats(stats.energy, stats.weight);
    }

    public Fitness getFitness() {
        return new Fitness(fitness.strength, fitness.stamina);
    }

    public Calories getCalories() {
        return calories.copy();
    }

    public Properties getProperties() {
        return new Properties(properties.gender, properties.size, properties.age);
    }

    public Muscles getMuscles() {
        return muscles.copy();
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

        private boolean isSaturated = false;

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
            energy -= consumedEnergy;
            if (energy < MIN_ENERGY) {
                energy = MIN_ENERGY;
            }
        }

        public void gainEnergy(int gainedEnergy) {
            int obtainedEnergy = energy + gainedEnergy;
            energy = obtainedEnergy > MAX_ENERGY ? MAX_ENERGY : obtainedEnergy;
        }

        public void adjustStats(float changedWeight) {
            energy = MAX_ENERGY;
            weight += changedWeight;
            if (weight < MIN_WEIGHT) {
                weight = MIN_WEIGHT;
            }
        }

        public int getEnergy() {
            return energy;
        }

        public float getWeight() {
            return weight;
        }

        void setSaturated(boolean isSaturated) {
            this.isSaturated = isSaturated;
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

        private float calculateIprovement(float current, float scale) {
            if (current <= MAX_FITNESS - scale) {
                return scale;
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
