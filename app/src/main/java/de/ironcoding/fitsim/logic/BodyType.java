package de.ironcoding.fitsim.logic;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 13.04.2017.
 */

public class BodyType {

    private static final float BASE_SCALE = 1.0F;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef ({EKTOMORPH, MESOMORPH, ENDOMORPH})
    public @interface Name {}

    /**
     * Slim body that has no problems with kfa. But has problems to build up muscles.
     */
    public static final String EKTOMORPH = "EKTOMORPH";

    /**
     * The natural athletic type with good relation to muscles and kfa.
     */
    public static final String MESOMORPH = "MESOMORPH";

    /**
     * Can easilly build up muscles but has to take care of nutrition.
     */
    public static final String ENDOMORPH = "ENDOMORPH";

    private static final float METABOLISM_EKTOMORPH = 1.25F;
    private static final float BUILDUP_EKTOMORPH = 0.8F;

    private static final float METABOLISM_MESOMORPH = 1.15F;
    private static final float BUILDUP_MESOMORPH = 1.2F;

    private static final float METABOLISM_ENDOMORPH = 0.95F;
    private static final float BUILDUP_ENDOMORPH = 1.1F;

    private final @Name String name;

    private final float metabolism;

    private final float buildUp;

    private BodyType(String name, float metabolism, float buildUp) {
        this.name = name;
        this.metabolism = metabolism;
        this.buildUp = buildUp;
    }

    public static BodyType getType(@Name String name) {
        switch (name) {
            case EKTOMORPH:
                return new BodyType(EKTOMORPH, METABOLISM_EKTOMORPH, BUILDUP_EKTOMORPH);
            case ENDOMORPH:
                return new BodyType(ENDOMORPH, METABOLISM_ENDOMORPH, BUILDUP_ENDOMORPH);
            default:
                return new BodyType(MESOMORPH, METABOLISM_MESOMORPH, BUILDUP_MESOMORPH);
        }
    }

    public @Name String getName() {
        return name;
    }

    public float getMetabolism() {
        return metabolism;
    }

    public float getBuildUp() {
        return buildUp;
    }

    public float getEndurance() {
        float deviation = Math.abs(BASE_SCALE - buildUp);
        return buildUp < BASE_SCALE ? BASE_SCALE + deviation : BASE_SCALE - deviation;
    }
}
