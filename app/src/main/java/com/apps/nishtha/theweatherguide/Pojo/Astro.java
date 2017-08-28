package com.apps.nishtha.theweatherguide.Pojo;

/**
 * Created by nishtha on 1/8/17.
 */

public class Astro {
    private String sunrise,sunset,moonrise,moonset;

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public Astro(String sunrise, String sunset, String moonrise, String moonset) {

        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
    }
}
