package com.ohgo.ohgo.util;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rick on 05/06/15.
 */
public class LocationUtil {

    public static LatLng midPoint(double lat1, double long1, double lat2,double long2)
    {

        return new LatLng((lat1+lat2)/2, (long1+long2)/2);

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
