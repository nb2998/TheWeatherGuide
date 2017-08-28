package com.apps.nishtha.theweatherguide.Pojo;

import java.util.ArrayList;

/**
 * Created by nishtha on 30/7/17.
 */

public class Forecast {
    private ArrayList<Forecastday> forecastday;

    public ArrayList<Forecastday> getForecastdayArrayList() {
        return forecastday;
    }

    public Forecast(ArrayList<Forecastday> forecastday) {

        this.forecastday = forecastday;
    }
}
