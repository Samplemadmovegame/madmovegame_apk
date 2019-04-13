package com.example.madmovegame.home.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AllSports implements Parcelable {

    private String team1, team2, leagueName;
    private long timeleft;

    public AllSports() {

    }



    public AllSports(String team1, String team2, String leagueName, long timeleft) {
        this.team1 = team1;
        this.team2 = team2;
        this.leagueName = leagueName;
        this.timeleft = timeleft;
    }

    protected AllSports(Parcel in) {
        team1 = in.readString();
        team2 = in.readString();
        leagueName = in.readString();
        timeleft = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(leagueName);
        dest.writeLong(timeleft);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AllSports> CREATOR = new Creator<AllSports>() {
        @Override
        public AllSports createFromParcel(Parcel in) {
            return new AllSports(in);
        }

        @Override
        public AllSports[] newArray(int size) {
            return new AllSports[size];
        }
    };

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public long getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(long timeleft) {
        this.timeleft = timeleft;
    }
}
