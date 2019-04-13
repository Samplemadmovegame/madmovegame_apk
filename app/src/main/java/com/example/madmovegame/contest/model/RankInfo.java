package com.example.madmovegame.contest.model;

public class RankInfo {
    private String rankNo,name,prize;


    public RankInfo() {

    }

    public RankInfo(String rankNo, String name, String prize) {
        this.rankNo = rankNo;
        this.name = name;
        this.prize = prize;
    }

    public RankInfo(String name, String prize) {
        this.name = name;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getRankNo() {
        return rankNo;
    }

    public void setRankNo(String rankNo) {
        this.rankNo = rankNo;
    }
}
