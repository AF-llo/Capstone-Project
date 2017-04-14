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

    public static final float DEFAULT_WEIGHT_MALE = 75.0F;
    public static final float DEFAULT_WEIGHT_FEMALE = 65.0F;
    public static final float DEFAULT_SIZE_MALE = 175.0F;
    public static final float DEFAULT_SIZE_FEMALE = 170.0F;
    public static final int DEFAULT_AGE = 25;

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

    public static float metabolicRatePerDay(@Athlete.Gender int gender, float weight, float size, int age) {
        if (weight < 0) {
            weight = gender == Athlete.MALE ? DEFAULT_WEIGHT_MALE : DEFAULT_WEIGHT_FEMALE;
        }
        if (size < 0) {
            size = gender == Athlete.MALE ? DEFAULT_SIZE_MALE : DEFAULT_SIZE_FEMALE;
        }
        if (age < 0) {
            age = DEFAULT_AGE;
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
