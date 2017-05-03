package de.ironcoding.fitsim.persistance.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 03.05.2017.
 */
@Entity
public class DbHighscore implements IHighscore {

    @Id(autoincrement = true)
    private long id;

    private String name;

    private long points;

    @Generated(hash = 389957534)
    public DbHighscore(long id, String name, long points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    @Generated(hash = 1789873693)
    public DbHighscore() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return this.points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
