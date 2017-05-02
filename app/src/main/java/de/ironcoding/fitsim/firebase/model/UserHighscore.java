package de.ironcoding.fitsim.firebase.model;

/**
 * Created by larsl on 02.05.2017.
 */

public class UserHighscore {

    private long points;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
