package com.apps.nishtha.theweatherguide.Pojo;


/**
 * Created by nishtha on 30/7/17.
 */

public class Details {
    private Current current;
    private Forecast forecast;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public Details(Current current, Forecast forecast, Location location) {
        this.location=location;
        this.current = current;
        this.forecast = forecast;
    }
}
