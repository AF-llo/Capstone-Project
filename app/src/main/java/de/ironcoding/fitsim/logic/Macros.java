package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 17.04.2017.
 */

public class Macros {

    private static final float DEFAULT_PROTEINE_PROPORTION = 0.4F;
    private static final float DEFAULT_CARBS_PROPORTION = 0.4F;
    private static final float DEFAULT_FAT_PROPORTION = 0.2F;

    private final float proteineProportion;

    private final float carbsProportion;

    private final float fatProportion;

    private float consumedProteine = 0;

    private float consumedCarbs = 0;

    private float consumedFat = 0;

    private Macros(float proteineProportion, float carbsProportion, float fatProportion) {
        this.proteineProportion = proteineProportion;
        this.carbsProportion = carbsProportion;
        this.fatProportion = fatProportion;
    }

    public static Macros getDefault() {
        return get(DEFAULT_PROTEINE_PROPORTION, DEFAULT_CARBS_PROPORTION, DEFAULT_FAT_PROPORTION, 0, 0, 0);
    }

    public static Macros get(float proteineProportion, float carbsProportion, float fatProportion, float consumedProteine, float consumedCarbs, float consumedFat) {
        if (!validPropotion(proteineProportion, carbsProportion, fatProportion)) {
            proteineProportion = DEFAULT_PROTEINE_PROPORTION;
            carbsProportion = DEFAULT_CARBS_PROPORTION;
            fatProportion = DEFAULT_FAT_PROPORTION;
        }
        if (consumedProteine < 0) {
            consumedProteine = 0;
        }
        if (consumedCarbs < 0) {
            consumedCarbs = 0;
        }
        if (consumedFat < 0) {
            consumedFat = 0;
        }
        Macros macros = new Macros(proteineProportion, carbsProportion, fatProportion);
        macros.consumedProteine = consumedProteine;
        macros.consumedCarbs = consumedCarbs;
        macros.consumedFat = consumedFat;
        return macros;
    }

    public static boolean validPropotion(float proteine, float carbs, float fat) {
        return proteine + carbs + fat == 1;
    }

    public void consumeMacros(float proteine, float carbs, float fat) {
        consumedProteine += proteine;
        consumedCarbs += carbs;
        consumedFat += fat;
    }

    public float getProteineProportion() {
        return proteineProportion;
    }

    public float getCarbsProportion() {
        return carbsProportion;
    }

    public float getFatProportion() {
        return fatProportion;
    }

    public float getConsumedProteine() {
        return consumedProteine;
    }

    public float getConsumedCarbs() {
        return consumedCarbs;
    }

    public float getConsumedFat() {
        return consumedFat;
    }

    void resetConsumed() {
        consumedProteine = 0;
        consumedCarbs = 0;
        consumedFat = 0;
    }

    public Macros copy() {
        Macros macros = new Macros(proteineProportion, carbsProportion, fatProportion);
        macros.consumedProteine = consumedProteine;
        macros.consumedCarbs = consumedCarbs;
        macros.consumedFat = consumedFat;
        return macros;
    }
}
