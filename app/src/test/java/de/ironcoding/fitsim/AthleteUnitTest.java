package de.ironcoding.fitsim;

import junit.framework.Assert;

import org.junit.Test;

import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Skill;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AthleteUnitTest {

    @Test
    public void create_level_test() throws Exception {
        Level level = Level.create(-1);
        Assert.assertEquals(1, level.getValue());

        level = Level.create(0);
        Assert.assertEquals(1, level.getValue());

        level = Level.create(500);
        Assert.assertEquals(2, level.getValue());

        level = Level.create(2340);
        Assert.assertEquals(5, level.getValue());

        level = Level.create(7000);
        Assert.assertEquals(10, level.getValue());

        level = Level.create(30000);
        Assert.assertEquals(20, level.getValue());

        level = Level.create(50000);
        Assert.assertEquals(25, level.getValue());

        level = Level.create(490);
        level.gainExperience(50);
        Assert.assertEquals(2, level.getValue());
        Assert.assertEquals(40, level.getReachedExperience());
    }

    @Test
    public void create_skill_test() throws Exception {
        Skill skill = Skill.achieve(-1);
        Assert.assertEquals(Skill.BEGINNER, skill.getName());

        skill = Skill.achieve(1);
        Assert.assertEquals(Skill.BEGINNER, skill.getName());

        skill = Skill.achieve(25);
        Assert.assertEquals(Skill.ADVANCED, skill.getName());

        skill = Skill.achieve(50);
        Assert.assertEquals(Skill.EXPERT, skill.getName());
    }
}