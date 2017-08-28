package com.apps.nishtha.theweatherguide.Pojo;

/**
 * Created by nishtha on 30/7/17.
 */

public class Current {
    private String temp_c;
    private String temp_f;
    private Condition condition;
    private String wind_kph;
    private String humidity;
    private String wind_dir;

    public String getTemp_c() {
        return temp_c;
    }

    public String getTemp_f() {
        return temp_f;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public Current(String temp_c, String temp_f, Condition condition, String wind_kph, String humidity, String wind_dir) {

        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.condition = condition;
        this.wind_kph = wind_kph;
        this.humidity = humidity;
        this.wind_dir = wind_dir;
    }
}
