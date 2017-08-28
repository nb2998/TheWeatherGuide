package com.apps.nishtha.theweatherguide.Pojo;

import java.io.Serializable;

/**
 * Created by nishtha on 1/8/17.
 */

public class Hour implements Serializable{
    private String time,temp_c,temp_f,will_it_rain,will_it_snow;
    private Condition condition;

    public String getTime() {
        return time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public String getTemp_f() {
        return temp_f;
    }
    //    public String getMaxtemp_c() {
//        return maxtemp_c;
//    }
//
//    public String getMaxtemp_f() {
//        return maxtemp_f;
//    }
//
//    public String getMintemp_c() {
//        return mintemp_c;
//    }
//
//    public String getMintemp_f() {
//        return mintemp_f;
//    }
//
//    public String getAvgtemp_c() {
//        return avgtemp_c;
//    }
//
//    public String getAvgtemp_f() {
//        return avgtemp_f;
//    }

    public String getWill_it_rain() {
        return will_it_rain;
    }

    public String getWill_it_snow() {
        return will_it_snow;
    }

    public Condition getCondition() {
        return condition;
    }

    public Hour(String time,String temp_c,String temp_f, String will_it_rain, String will_it_snow, Condition condition) {
        this.time = time;
//        this.maxtemp_c = maxtemp_c;
//        this.maxtemp_f = maxtemp_f;
//        this.mintemp_c = mintemp_c;
//        this.mintemp_f = mintemp_f;
//        this.avgtemp_c = avgtemp_c;
//        this.avgtemp_f = avgtemp_f;
        this.temp_c=temp_c;
        this.temp_f=temp_f;
        this.will_it_rain = will_it_rain;
        this.will_it_snow = will_it_snow;
        this.condition = condition;
    }
}
