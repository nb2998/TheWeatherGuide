package com.apps.nishtha.theweatherguide.Pojo;

/**
 * Created by nishtha on 31/7/17.
 */

public class Forecastday {
    String date;
    Day day;
//    Hour hour;
    Astro astro;

    public String getDate() {
        return date;
    }

    public Day getDay() {
        return day;
    }

//    public Hour getHour() {
//        return hour;
//    }

    public Astro getAstro() {
        return astro;
    }

    public Forecastday(String date, Day day, Hour hour, Astro astro) {
        this.date = date;
        this.day = day;
//        this.hour = hour;
        this.astro = astro;
    }
}
