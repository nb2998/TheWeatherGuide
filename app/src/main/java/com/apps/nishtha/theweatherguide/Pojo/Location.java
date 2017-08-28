package com.apps.nishtha.theweatherguide.Pojo;

/**
 * Created by nishtha on 18/8/17.
 */

public class Location {
    private String name,region,country;

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public Location(String name, String region, String country) {

        this.name = name;
        this.region = region;
        this.country = country;
    }
}
