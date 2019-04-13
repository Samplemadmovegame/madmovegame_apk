package com.example.madmovegame.contest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contest implements Parcelable {

    private String contestName,description,totalSpot,leftspot,winnigPrice,entryPrice;


    public Contest() {
    }

    public Contest(String contestName, String description, String totalSpot, String leftspot, String winnigPrice, String entryPrice) {
        this.contestName = contestName;
        this.description = description;
        this.totalSpot = totalSpot;
        this.leftspot = leftspot;
        this.winnigPrice = winnigPrice;
        this.entryPrice = entryPrice;
    }

    protected Contest(Parcel in) {
        contestName = in.readString();
        description = in.readString();
        totalSpot = in.readString();
        leftspot = in.readString();
        winnigPrice = in.readString();
        entryPrice = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contestName);
        dest.writeString(description);
        dest.writeString(totalSpot);
        dest.writeString(leftspot);
        dest.writeString(winnigPrice);
        dest.writeString(entryPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contest> CREATOR = new Creator<Contest>() {
        @Override
        public Contest createFromParcel(Parcel in) {
            return new Contest(in);
        }

        @Override
        public Contest[] newArray(int size) {
            return new Contest[size];
        }
    };

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalSpot() {
        return totalSpot;
    }

    public void setTotalSpot(String totalSpot) {
        this.totalSpot = totalSpot;
    }

    public String getLeftspot() {
        return leftspot;
    }

    public void setLeftspot(String leftspot) {
        this.leftspot = leftspot;
    }

    public String getWinnigPrice() {
        return winnigPrice;
    }

    public void setWinnigPrice(String winnigPrice) {
        this.winnigPrice = winnigPrice;
    }

    public String getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(String entryPrice) {
        this.entryPrice = entryPrice;
    }
}
