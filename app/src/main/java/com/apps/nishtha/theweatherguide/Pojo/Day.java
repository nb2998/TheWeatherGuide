package com.apps.nishtha.theweatherguide.Pojo;

/**
 * Created by nishtha on 31/7/17.
 */

public class Day {
    private String maxtemp_c,maxtemp_f,mintemp_c,mintemp_f,avgtemp_c,avgtemp_f;
    private Condition condition;

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public String getMaxtemp_f() {
        return maxtemp_f;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public String getMintemp_f() {
        return mintemp_f;
    }

    public String getAvgtemp_c() {
        return avgtemp_c;
    }

    public String getAvgtemp_f() {
        return avgtemp_f;
    }

//    public String getTime() {
//        return time;
//    }
//
//    public String getWill_it_rain() {
//        return will_it_rain;
//    }
//
//    public String getWill_it_snow() {
//        return will_it_snow;
//    }

    public Condition getCondition() {
        return condition;
    }

    public Day( String maxtemp_c, String maxtemp_f, String mintemp_c, String mintemp_f, String avgtemp_c,
               String avgtemp_f, Condition condition) {
//        this.time = time;
        this.maxtemp_c = maxtemp_c;
        this.maxtemp_f = maxtemp_f;
        this.mintemp_c = mintemp_c;
        this.mintemp_f = mintemp_f;
        this.avgtemp_c = avgtemp_c;
        this.avgtemp_f = avgtemp_f;
//        this.will_it_rain = will_it_rain;
//        this.will_it_snow = will_it_snow;
        this.condition = condition;
    }
}
