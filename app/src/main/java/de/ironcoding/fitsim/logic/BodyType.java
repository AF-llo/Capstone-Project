package de.ironcoding.fitsim.logic;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 13.04.2017.
 */

public class BodyType {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef ({EKTOMORPH, MESOMORPH, ENDOMORPH})
    public @interface Name {}
    public static final String EKTOMORPH = "EKTOMORPH";
    public static final String MESOMORPH = "MESOMORPH";
    public static final String ENDOMORPH = "ENDOMORPH";

    // TODO: 13.04.2017 adjust values
    private static final float METABOLISM_EKTOMORPH = 0.7F;
    private static final float BUILDUP_EKTOMORPH = 0.8F;

    private static final float METABOLISM_MESOMORPH = 0.85F;
    private static final float BUILDUP_MESOMORPH = 1.2F;

    private static final float METABOLISM_ENDOMORPH = 1.0F;
    private static final float BUILDUP_ENDOMORPH = 1.1F;

    private final @Name String name;

    private final float metabolism;

    private final float buildUp;

    private BodyType(String name, float metabolism, float buildUp) {
        this.name = name;
        this.metabolism = metabolism;
        this.buildUp = buildUp;
    }

    public static BodyType getType(@Skill.Name String name) {
        switch (name) {
            case EKTOMORPH:
                return new BodyType(EKTOMORPH, METABOLISM_EKTOMORPH, BUILDUP_EKTOMORPH);
            case ENDOMORPH:
                return new BodyType(ENDOMORPH, METABOLISM_ENDOMORPH, BUILDUP_ENDOMORPH);
            default:
                return new BodyType(MESOMORPH, METABOLISM_MESOMORPH, BUILDUP_MESOMORPH);
        }
    }

    public String getName() {
        return name;
    }

    public float getMetabolism() {
        return metabolism;
    }

    public float getBuildUp() {
        return buildUp;
    }
}
