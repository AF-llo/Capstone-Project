package de.ironcoding.fitsim;

import junit.framework.Assert;

import org.junit.Test;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.logic.Calories;
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

    @Test
    public void calories_proportional_calculations_test() throws Exception {
        Body body = Body.warmUpAverageMale(BodyType.ENDOMORPH);
        Calories calories = body.getCalories();
        float totalCalories = 2200;

        Assert.assertEquals(0.0F, calories.proportionalProteineKalories(-1));
        Assert.assertEquals(880.0F, calories.proportionalProteineKalories(totalCalories));
        Assert.assertEquals(214.63416F, calories.proportionalProteineGram(totalCalories));

        Assert.assertEquals(0.0F, calories.proportionalCarbsKalories(-1));
        Assert.assertEquals(880.0F, calories.proportionalCarbsKalories(totalCalories));
        Assert.assertEquals(214.63416F, calories.proportionalCarbsGram(totalCalories));

        Assert.assertEquals(0.0F, calories.proportionalFatKalories(-1));
        Assert.assertEquals(440.0F, calories.proportionalFatKalories(totalCalories));
        Assert.assertEquals(47.31183F, calories.proportionalFatGram(totalCalories));
    }

    @Test
    public void calories_metabolic_test() throws Exception {
        float totalCalories = Calories.metabolicRatePerDay(Athlete.FEMALE, 70, 172, 29);
        Assert.assertEquals(1500.3999F, totalCalories);

        totalCalories = Calories.metabolicRatePerDay(Athlete.FEMALE, 74, 166.5F, 28);
        Assert.assertEquals(1533.6F, totalCalories);

        totalCalories = Calories.metabolicRatePerDay(Athlete.MALE, 75, 168, 32);
        Assert.assertEquals(1716.37F, totalCalories);


        // pal calories

        float palCaloriesForHours = Calories.energyMetabolismForPal(totalCalories, 1, 24);
        Assert.assertEquals(totalCalories, palCaloriesForHours);

        palCaloriesForHours = Calories.energyMetabolismForPal(totalCalories, 0.95F, 12);
        Assert.assertEquals(815.2757F, palCaloriesForHours);

        palCaloriesForHours = Calories.energyMetabolismForPal(totalCalories, -1, 24);
        Assert.assertEquals(totalCalories, palCaloriesForHours);

        palCaloriesForHours = Calories.energyMetabolismForPal(totalCalories, -1, 27);
        Assert.assertEquals(totalCalories, palCaloriesForHours);

        palCaloriesForHours = Calories.energyMetabolismForPal(totalCalories, 1, -1);
        Assert.assertEquals(0.0F, palCaloriesForHours);

        palCaloriesForHours = Calories.energyMetabolismForPal(-1, -1, -1);
        Assert.assertEquals(0.0F, palCaloriesForHours);

        // increase energy

        Body body = Body.warmUp(
                BodyType.ENDOMORPH,
                new Body.Properties(Athlete.MALE, 168, 32),
                new Body.Stats(Body.MAX_ENERGY, 75),
                new Body.Fitness(Body.INITIAL_FITNESS, Body.INITIAL_FITNESS));
        Calories calories = body.getCalories();
        calories.increaseRequiredEnergy(1.4F, 8);
        Assert.assertEquals(2517.3428F, calories.getRequiredEnergy());

        // BMI
        Assert.assertEquals(0.0F, Calories.getBmi(-1, 168));
        Assert.assertEquals(0.0F, Calories.getBmi(75, -1));
        Assert.assertEquals(0.0F, Calories.getBmi(-1, -1));

        Assert.assertEquals(0.0F, Calories.getFfmi(0, 168, 12));
        Assert.assertEquals(0.0F, Calories.getFfmi(75, 0, 12));
        Assert.assertTrue(Calories.getFfmi(75, 168, 0) > 25);

    }

    @Test
    public void body_stats_test() throws Exception {
        // energy
        Body.Stats stats = new Body.Stats(50, Body.INITIAL_WEIGHT_MALE);
        stats.consumeEnergy(10);
        Assert.assertEquals(40, stats.getEnergy());

        stats.gainEnergy(10);
        Assert.assertEquals(50, stats.getEnergy());

        stats.gainEnergy(51);
        Assert.assertEquals(Body.MAX_ENERGY, stats.getEnergy());

        stats.consumeEnergy(101);
        Assert.assertEquals(Body.MIN_ENERGY, stats.getEnergy());

        // weight

        stats.loseWeight(5);
        Assert.assertEquals(70.0F, stats.getWeight());

        stats.putOnWeight(5);
        Assert.assertEquals(75.0F, stats.getWeight());

        stats.loseWeight(40);
        Assert.assertEquals(Body.MIN_WEIGHT, stats.getWeight());

        stats.putOnWeight(100);
        Assert.assertEquals(Body.MIN_WEIGHT + 100, stats.getWeight());
    }

    @Test
    public void body_fitness_test() throws Exception {
        // strength
        Body.Fitness fitness = new Body.Fitness(Body.INITIAL_FITNESS, Body.INITIAL_FITNESS);
        fitness.improveStrength();
        Assert.assertEquals(62.0F, fitness.getStrength());

        fitness.impairStrength();
        Assert.assertEquals(61.0F, fitness.getStrength());

        fitness = new Body.Fitness(99.5F, Body.INITIAL_FITNESS);
        fitness.improveStrength();
        Assert.assertEquals(Body.MAX_FITNESS, fitness.getStrength());

        fitness = new Body.Fitness(40.5F, Body.INITIAL_FITNESS);
        fitness.impairStrength();
        Assert.assertEquals(Body.MIN_FITNESS, fitness.getStrength());
        // stamina

        fitness.improveStamina();
        Assert.assertEquals(62.0F, fitness.getStamina());

        fitness.impairStamina();
        Assert.assertEquals(61.0F, fitness.getStamina());

        fitness = new Body.Fitness(Body.INITIAL_FITNESS, 99.5F);
        fitness.improveStamina();
        Assert.assertEquals(Body.MAX_FITNESS, fitness.getStamina());

        fitness = new Body.Fitness(Body.INITIAL_FITNESS, 40.5F);
        fitness.impairStamina();
        Assert.assertEquals(Body.MIN_FITNESS, fitness.getStamina());
    }

    @Test
    public void body_test() throws Exception {
        
    }

    @Test
    public void athlete_test() throws Exception {

    }
}