package com.ohgo.ohgo.models;


import java.util.Date;

/**
 * Created by Rick on 06/06/15.
 */
public class EmployeeLocation {

    private String employeeLocationId;
    private double latitude;
    private double longitude;
    private Date datetime;
    private String employeeId;

    public EmployeeLocation(double latitude, double longitude, Date datetime, String employeeId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.datetime = datetime;
        this.employeeId = employeeId;
    }


}
