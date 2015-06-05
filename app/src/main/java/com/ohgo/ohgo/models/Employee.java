package com.ohgo.ohgo.models;

/**
 * Created by Rick on 05/06/15.
 */
public class Employee {

    private String name;
    private double latitude;
    private double longitude;
    private int customerId;

    public Employee(String name, double latitude, double longitude, int customerId) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
