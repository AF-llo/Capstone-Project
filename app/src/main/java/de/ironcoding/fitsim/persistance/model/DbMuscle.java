package de.ironcoding.fitsim.persistance.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by larsl on 30.04.2017.
 */
@Entity
public class DbMuscle {

    @Id
    private long muscleId;

    private int volume;

    private int condition;

    private long athleteId;

    @Generated(hash = 1689782767)
    public DbMuscle(long muscleId, int volume, int condition, long athleteId) {
        this.muscleId = muscleId;
        this.volume = volume;
        this.condition = condition;
        this.athleteId = athleteId;
    }

    @Generated(hash = 2055565684)
    public DbMuscle() {
    }

    public long getMuscleId() {
        return this.muscleId;
    }

    public void setMuscleId(long muscleId) {
        this.muscleId = muscleId;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public long getAthleteId() {
        return this.athleteId;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
    }

    public int getCondition() {
        return this.condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

}
