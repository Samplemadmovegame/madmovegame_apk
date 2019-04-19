package com.msewa.madmovegame.api;

public class ApiUrl {

    private static final Mode mode = Mode.Test;
    private static final String URL_Live = "";
    private static final String URL_Test = "http://106.51.8.246/";
    private static final String URL_Local = "";


    private static String getURL(Mode mode) {
        switch (String.valueOf(mode)) {
            case "Live":
                return URL_Live;
            case "Test":
                return URL_Test + "madmovegame/";
            default:
                return URL_Local;
        }
    }

    private static final String URL_DOMAIN = getURL(mode) + "Api/";
    private static final String VERSION = "v1";
    private static final String ROLE = "User";
    private static final String DEVICE = "Android";
    private static final String LANGUAGE = "en";

    private static final String SEPARATOR = "/";
    public static final String URL_MAIN = URL_DOMAIN + VERSION + SEPARATOR + ROLE + SEPARATOR + DEVICE + SEPARATOR + LANGUAGE + SEPARATOR;


    enum Mode {

        Live, Test, Local
    }


}


