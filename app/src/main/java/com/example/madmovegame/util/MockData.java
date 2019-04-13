package com.example.madmovegame.util;

import com.example.madmovegame.constant.Const;
import com.example.madmovegame.contest.model.Contest;
import com.example.madmovegame.contest.model.RankInfo;
import com.example.madmovegame.payment.model.Payment;
import com.example.madmovegame.team.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Contest> getContestList() {
        List<Contest> list = new ArrayList<>();
        list.add(new Contest("Mega Contest", "Get Ready For Mega Winner", "21,60,000", "14,98,000", "8 Crores", "49"));
        list.add(new Contest("Only For Beginners", "Play Your First Constest Now", "21,60,000", "14,98,000", "10 Lacks", "10"));
        return list;
    }

    public static List<RankInfo> getRankInfo() {
        List<RankInfo> list = new ArrayList<>();
        list.add(new RankInfo("#1", "", "20000"));
        list.add(new RankInfo("#2", "- 49,880", "20"));
        return list;
    }


    public static List<Payment> getPaymentInfo() {
        List<Payment> list = new ArrayList<>();
        list.add(new Payment(Const.CARD));
        list.add(new Payment(Const.WALLET));
        list.add(new Payment(Const.UPI));
        list.add(new Payment(Const.NET_BANKING));
        return list;
    }

    public static List<Team> getTeamInfo() {
        List<Team> list = new ArrayList<>();
        list.add(new Team(Const.DHONI, "87.5", "8.0"));
        list.add(new Team(Const.YADAV, "87.5", "8.0"));
        list.add(new Team(Const.POLLARD, "87.5", "8.0"));
        list.add(new Team(Const.KARTIK, "87.5", "8.0"));
        list.add(new Team(Const.MALINGA, "87.5", "8.0"));
        list.add(new Team(Const.QUINTON, "87.5", "8.0"));
        list.add(new Team(Const.WATSON, "87.5", "8.0"));
        list.add(new Team(Const.ROHIT, "87.5", "8.0"));
        list.add(new Team(Const.JADEJA, "87.5", "8.0"));
        return list;
    }
}
