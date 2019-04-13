package com.example.madmovegame.team.model;

public class Team {
    private String palyerName,point,credits;

    public Team() {
    }

    public Team(String palyerName, String point, String credits) {
        this.palyerName = palyerName;
        this.point = point;
        this.credits = credits;
    }

    public String getPalyerName() {
        return palyerName;
    }

    public void setPalyerName(String palyerName) {
        this.palyerName = palyerName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
