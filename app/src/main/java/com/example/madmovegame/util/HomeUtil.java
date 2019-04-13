package com.example.madmovegame.util;

import com.example.madmovegame.constant.Const;
import com.example.madmovegame.home.model.AllSports;
import com.example.madmovegame.home.model.Sport;

import java.util.ArrayList;
import java.util.List;

public class HomeUtil {

    public static List<Sport> getSortsList() {
        List<Sport> sports = new ArrayList<>();
        sports.add(new Sport(Const.CRICKET));
        sports.add(new Sport(Const.BASEBALL));
        sports.add(new Sport(Const.FOOTBALL));
        sports.add(new Sport(Const.HOCKEY));
        sports.add(new Sport(Const.BASEBALL));
        sports.add(new Sport(Const.BASKETBALL));
        return sports;
    }

    public static List<AllSports> getAllSportsCricketList() {
        List<AllSports> sports = new ArrayList<>();
        sports.add(new AllSports(Const.CSK, Const.MI, "Premier League", 0));
        sports.add(new AllSports(Const.MI, Const.HYD, "Premier League", 0));
        sports.add(new AllSports(Const.HYD, Const.PUNE, "Premier League", 0));
        sports.add(new AllSports(Const.CSK, Const.PUNE, "Premier League", 0));
        sports.add(new AllSports(Const.RCB, Const.MI, "Premier League", 0));
        sports.add(new AllSports(Const.KKR, Const.MI, "Premier League", 0));
        sports.add(new AllSports(Const.CSK, Const.KKR, "Premier League", 0));
        sports.add(new AllSports(Const.CSK, Const.RCB, "Premier League", 0));
        return sports;
    }
}
