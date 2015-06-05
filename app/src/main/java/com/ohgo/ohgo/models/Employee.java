package com.ohgo.ohgo.models;

import com.parse.ParseClassName;

import com.parse.ParseObject;

/**
 * Created by Rick on 05/06/15.
 */

@ParseClassName("Employee")
public class Employee extends  ParseObject{

    private String name;
    private double latitude;
    private double longitude;
    private int customerId;

    public Employee(){}

    public Employee(String name, double latitude, double longitude, int customerId)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.customerId = customerId;
    }

    public String getName()
    {
        return getString("name");
    }
    public void setName(String value)
    {
        put("name", value);
    }

    public double getLatitude()
    {
        return getDouble("latitude");
    }

    public void setLatitude(double value) {
        put("latitude",value);
    }

    public double getLongitude()
    {
        return getDouble("longitude");
    }

    public void setLongitude(double value)
    {
        put("longitude",value);
    }

}
