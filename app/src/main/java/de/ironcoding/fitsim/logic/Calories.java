package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 14.04.2017.
 */

public class Calories {

    public static final float KCAL_PER_G_PROTEINE = 4.1F;
    public static final float KCAL_PER_G_CARBS = 4.1F;
    public static final float KCAL_PER_G_FAT = 9.3F;

    private static final float DEFAULT_PROTEINE_PROPORTION = 0.4F;
    private static final float DEFAULT_CARBS_PROPORTION = 0.4F;
    private static final float DEFAULT_FAT_PROPORTION = 0.2F;

    public static final float MIN_KFA = 5.0F;

    private static final float FFMI_FACTOR_ONE = 6.3F;
    private static final float FFMI_FACTOR_TWO = 1.8F;

    // constans for metabolismrate based on harris-benedict-formula
    private static final float BASIC_VALUE_MALE = 66.47F;
    private static final float BASIC_VALUE_FEMALE = 655.1F;

    private static final float WEIGHT_FACTOR_MALE = 13.7F;
    private static final float WEIGHT_FACTOR_FEMALE = 9.6F;

    private static final float SIZE_FACTOR_MALE = 5.0F;
    private static final float SIZE_FACTOR_FEMALE = 1.8F;

    private static final float AGE_FACTOR_MALE = 6.8F;
    private static final float AGE_FACTOR_FEMALE = 4.7F;

    private float proteineProportion;

    private float carbsProportion;

    private float fatProportion;

    private Calories() {}

    public static Calories createDefault() {
        return createWithProportion(DEFAULT_PROTEINE_PROPORTION, DEFAULT_CARBS_PROPORTION, DEFAULT_FAT_PROPORTION);
    }

    /**
     * Creates calories with proportions for macros.
     *
     * @param proteine
     *                  Proportion of Proteine
     * @param carbs
     *                  Proportion of Carbs
     * @param fat
     *                  Proportion of Fat
     * @return
     *                  Calories with passed proportions
     * @throws
     *                  IllegalArgumentException when sum of passed macros is not equal to one
     */
    public static Calories createWithProportion(float proteine, float carbs, float fat) {
        if (!validPropotion(proteine, carbs, fat)) {
            throw new IllegalArgumentException("Invalid proptions for macros. More than 100% is not possible");
        }
        Calories calories = new Calories();
        calories.proteineProportion = proteine;
        calories.carbsProportion = carbs;
        calories.fatProportion = fat;
        return calories;
    }

    public float proportionalProteineKalories(float totalCalories) {
        if (totalCalories < 0) {
            totalCalories = 0;
        }
        return totalCalories * proteineProportion;
    }

    public float proportionalProteineGram(float totalCalories) {
        return proportionalProteineKalories(totalCalories) / KCAL_PER_G_PROTEINE;
    }

    public float proportionalCarbsKalories(float totalCalories) {
        if (totalCalories < 0) {
            totalCalories = 0;
        }
        return totalCalories * carbsProportion;
    }

    public float proportionalCarbsGram(float totalCalories) {
        return proportionalCarbsKalories(totalCalories) / KCAL_PER_G_CARBS;
    }

    public float proportionalFatKalories(float totalCalories) {
        if (totalCalories < 0) {
            totalCalories = 0;
        }
        return totalCalories * fatProportion;
    }

    public float proportionalFatGram(float totalCalories) {
        return proportionalFatKalories(totalCalories) / KCAL_PER_G_FAT;
    }

    public static boolean validPropotion(float proteine, float carbs, float fat) {
        return proteine + carbs + fat == 1;
    }

    /**
     * Calculates the BMI
     * @param weight
     *                  in kg
     * @param size
     *                  in cm
     * @return
     *                  bmi or 0 when invalid weight or size is passed.
     */
    public static float getBmi(float weight, float size) {
        if (weight < 0) {
            weight = 0;
        }
        if (size <= 0) {
            // prevent division with zero
            size = 1;
            weight = 0;
        }
        float cmPerMeter = 100;
        size = size / cmPerMeter;
        return weight / (size * size);
    }

    /**
     * Calculates the ffmi
     * @param weight
     *                  in kg
     * @param size
     *                  in cm
     * @param kfa
     *                  in percent (e.g. 15.0)
     * @return
     *                  ffmi or 0 when invalid weight or size is passed. When kfa < {@link #MIN_KFA},
     *                  the min kfa is used.
     */
    public static float getFfmi(float weight, float size, float kfa) {
        if (weight < weight) {
            weight = 0;
        }
        if (size <= 0) {
            size = 1;
            weight = 0;
        }
        if (kfa < MIN_KFA) {
            kfa = MIN_KFA;
        }
        float cmPerMeter = 100;
        float fatFreeMass = weight * ((100 - kfa) / 100);
        float correctionFactor = FFMI_FACTOR_ONE * (FFMI_FACTOR_TWO - size / cmPerMeter);
        if (weight == 0 | size ==  0) {
            correctionFactor = 0;
        }
        return getBmi(fatFreeMass, size) + correctionFactor;
    }

    /**
     * Calculates the metabolic rate based on formula of harrison-benedict
     * @param gender
     *                  male or female
     * @param weight
     *                  weight in kg. {@link Body#INITIAL_WEIGHT_MALE}/{@link Body#INITIAL_WEIGHT_FEMALE} is
     *                  used when negative weight is passed.
     * @param size
     *                  size in cm. {@link Body#DEFAULT_SIZE_MALE}/{@link Body#DEFAULT_SIZE_FEMALE} is used
     *                  when negative size is passed.
     * @param age
     *                  age in years. {@link Body#DEFAULT_AGE} is used when negative age is passed.
     * @return
     */
    public static float metabolicRatePerDay(@Athlete.Gender int gender, float weight, float size, int age) {
        if (weight < 0) {
            weight = gender == Athlete.MALE ? Body.INITIAL_WEIGHT_MALE : Body.INITIAL_WEIGHT_FEMALE;
        }
        if (size < 0) {
            size = gender == Athlete.MALE ? Body.DEFAULT_SIZE_MALE : Body.DEFAULT_SIZE_FEMALE;
        }
        if (age < 0) {
            age = Body.DEFAULT_AGE;
        }
        float baseValue;
        float factorWeight;
        float factorSize;
        float factorAge;
        if (gender == Athlete.MALE) {
            baseValue = BASIC_VALUE_MALE;
            factorWeight = WEIGHT_FACTOR_MALE;
            factorSize = SIZE_FACTOR_MALE;
            factorAge = AGE_FACTOR_MALE;
        } else {
            baseValue = BASIC_VALUE_FEMALE;
            factorWeight = WEIGHT_FACTOR_FEMALE;
            factorSize = SIZE_FACTOR_FEMALE;
            factorAge = AGE_FACTOR_FEMALE;
        }
        return baseValue + (factorWeight * weight) + (factorSize * size) - (factorAge * age);
    }

    /**
     * Calculates the energy metabolism for hours.
     * @param metabolicRate
     *                          metabolic rate for whole day. 0 is used when negative value is passed.
     * @param pal
     *                          pal of activity. 1 is used when negative value is passed.
     * @param hours
     *                          hours for activity. 0 is used when negative value is passed. 24 is used
     *                          when value greater than 24 is passed.
     */
    public static float energyMetabolismForPal(float metabolicRate, float pal, float hours) {
        if (metabolicRate < 0) {
            metabolicRate = 0;
        }
        if (pal < 0) {
            pal = 1;
        }
        if (hours < 0) {
            hours = 0;
        }
        float hoursPerDay = 24.0F;
        if (hours > hoursPerDay) {
            hours = hoursPerDay;
        }
        return metabolicRate * pal * hours / hoursPerDay;
    }

}
