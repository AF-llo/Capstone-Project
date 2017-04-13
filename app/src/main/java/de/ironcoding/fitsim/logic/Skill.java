package de.ironcoding.fitsim.logic;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 12.04.2017.
 */

public class Skill {

    private static final int LEVEL_INTERVAL = 25;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({})
    public @interface Name {}
    public static final String BEGINNER = "BEGINNER";
    public static final String ADVANCED = "ADVANCED";
    public static final String EXPERT = "EXPERT";

    private @Name String name;

    private Skill() {}

    /**
     * Factory method to create a skill based on the passed level.
     *
     * @param level
     *                  If negative value  is passed, 0 will be used with {@link Level#START_LEVEL}
     * @return
     *                  The skill related to the passed level.
     */
    public static Skill achieve(int level) {
        if (level < Level.START_LEVEL) {
            level = Level.START_LEVEL;
        }
        Skill skill = new Skill();
        skill.improveForLevel(level);
        return skill;
    }

    public void improveForLevel(int level) {
        int skillCount = level / LEVEL_INTERVAL;
        switch (skillCount) {
            case 0:
                this.name = BEGINNER;
                return;
            case 1:
                this.name = ADVANCED;
                return;
            default:
                this.name = EXPERT;
        }
    }

    public @Name String getName() {
        return name;
    }
}
