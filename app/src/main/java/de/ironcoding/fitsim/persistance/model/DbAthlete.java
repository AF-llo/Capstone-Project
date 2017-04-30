package de.ironcoding.fitsim.persistance.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by larsl on 30.04.2017.
 */
@Entity
public class DbAthlete {

    @Id
    private long id;

    private long experience;

    private String bodyType;

    private int gender;

    private float size;

    private int age;

    private int energy;

    private float weight;

    private float strength;

    private float stamina;

    private float requiredEnergy;

    private float proteinProportion;

    private float carbsProportion;

    private float fatProportion;

    private float consumedProteine;

    private float consumedCarbs;

    private float consumedFat;

    @Generated(hash = 889961082)
    public DbAthlete(long id, long experience, String bodyType, int gender,
            float size, int age, int energy, float weight, float strength,
            float stamina, float requiredEnergy, float proteinProportion,
            float carbsProportion, float fatProportion, float consumedProteine,
            float consumedCarbs, float consumedFat) {
        this.id = id;
        this.experience = experience;
        this.bodyType = bodyType;
        this.gender = gender;
        this.size = size;
        this.age = age;
        this.energy = energy;
        this.weight = weight;
        this.strength = strength;
        this.stamina = stamina;
        this.requiredEnergy = requiredEnergy;
        this.proteinProportion = proteinProportion;
        this.carbsProportion = carbsProportion;
        this.fatProportion = fatProportion;
        this.consumedProteine = consumedProteine;
        this.consumedCarbs = consumedCarbs;
        this.consumedFat = consumedFat;
    }

    @Generated(hash = 481205737)
    public DbAthlete() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExperience() {
        return this.experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public String getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getSize() {
        return this.size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getStrength() {
        return this.strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getStamina() {
        return this.stamina;
    }

    public void setStamina(float stamina) {
        this.stamina = stamina;
    }

    public float getRequiredEnergy() {
        return this.requiredEnergy;
    }

    public void setRequiredEnergy(float requiredEnergy) {
        this.requiredEnergy = requiredEnergy;
    }

    public float getProteinProportion() {
        return this.proteinProportion;
    }

    public void setProteinProportion(float proteinProportion) {
        this.proteinProportion = proteinProportion;
    }

    public float getCarbsProportion() {
        return this.carbsProportion;
    }

    public void setCarbsProportion(float carbsProportion) {
        this.carbsProportion = carbsProportion;
    }

    public float getFatProportion() {
        return this.fatProportion;
    }

    public void setFatProportion(float fatProportion) {
        this.fatProportion = fatProportion;
    }

    public float getConsumedProteine() {
        return this.consumedProteine;
    }

    public void setConsumedProteine(float consumedProteine) {
        this.consumedProteine = consumedProteine;
    }

    public float getConsumedCarbs() {
        return this.consumedCarbs;
    }

    public void setConsumedCarbs(float consumedCarbs) {
        this.consumedCarbs = consumedCarbs;
    }

    public float getConsumedFat() {
        return this.consumedFat;
    }

    public void setConsumedFat(float consumedFat) {
        this.consumedFat = consumedFat;
    }
}
