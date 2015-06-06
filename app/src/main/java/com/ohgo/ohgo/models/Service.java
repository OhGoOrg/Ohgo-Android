package com.ohgo.ohgo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rick on 04/06/15.
 */
public class Service implements Serializable {
    private Date serviceDate;
    private int userId;
    private int status;
    private double latitude;
    private double longitude;
    private Rate rate;

    public Service(Date serviceDate, int userId, int status, double latitude, double longitude) {
        this.serviceDate = serviceDate;
        this.userId = userId;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }


        public static ArrayList<Service> getSampleData()
        {
            ArrayList<Service> services = new ArrayList<>();
            services.add(new Service(Calendar.getInstance().getTime(), 1, 2, 40.717626, -73.997534));
            services.add(new Service(Calendar.getInstance().getTime(), 2, 1, 35.689487, 139.691706));
            services.add(new Service(Calendar.getInstance().getTime(), 4, 2, 19.432608, -99.133208));
            services.add(new Service(Calendar.getInstance().getTime(), 4, 3, 37.774929, -122.419416));
            services.add(new Service(Calendar.getInstance().getTime(), 3, 2, 31.230416, 121.473701));
            return services;
        }


}
