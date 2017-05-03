package de.ironcoding.fitsim.firebase.model;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 02.05.2017.
 */

public class UserHighscore implements IHighscore {

    private long points;

    private String name;

    public UserHighscore() {
    }

    public UserHighscore(long points, String name) {
        this.points = points;
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return 0;
    }
}
