package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ohgo.ohgo.R;
import com.ohgo.ohgo.activities.MainActivity;
import com.ohgo.ohgo.models.Service;
import com.ohgo.ohgo.models.User;
import com.ohgo.ohgo.util.JSONParser;
import com.ohgo.ohgo.util.LocationUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruben on 6/5/15.
 */
public class MapFragment extends Fragment {

    private static final String ARG_PARAM_SERVICE = "paramService";

    private OnFragmentInteractionListener mListener;

    private SupportMapFragment supportMapFragment;
    private GoogleMap gMap;
    private Service service;
    private LatLng loc;


    public static MapFragment newInstance(Service service)
    {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_SERVICE, service);
        fragment.setArguments(args);
        return fragment;
    }

    public MapFragment()
    {
        // Required empty public constructor
    }
    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location)
        {
            if (service==null) //WE NEED TO MOVE THIS TO THE WORKER LOCATION; NOT THE OWNER LOCATION; API
                service = new Service(Calendar.getInstance().getTime(),1,1,location.getLatitude(),location.getLongitude());

            double distance = 0.0;
            if(loc != null){
                distance = LocationUtil.distance(loc.latitude, loc.longitude, location.getLatitude(), location.getLongitude());

            }else
                loc = new LatLng(location.getLatitude(), location.getLongitude());

            if(gMap != null && (distance > 10.0 || distance == 0.0) )
            {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(loc);

                builder.include(new LatLng(service.getLatitude(), service.getLongitude()));

                LatLngBounds bounds = builder.build();

                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 30);
                gMap.moveCamera(cu);
                gMap.animateCamera(cu);
                new GetRouteLocation(makeURL(loc.latitude,loc.longitude,service.getLatitude(), service.getLongitude())).execute();
            }
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            this.service = (Service) getArguments().getSerializable(ARG_PARAM_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_location, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        gMap = supportMapFragment.getMap();
        gMap.setMyLocationEnabled(true);
        gMap.setOnMyLocationChangeListener(myLocationChangeListener);
        setLocation();
        return view;
    }


    public void setLocation()
    {
        if (service!=null)
        {
            gMap.addMarker((new MarkerOptions().position(new LatLng(service.getLatitude(), service.getLongitude()))).title("Destiny")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacion)));
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(service.getLatitude(), service.getLongitude()), 10));
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener
    {
        public void arriveToLocation(int status);
    }


    public String makeURL (double sourcelat, double sourcelog, double destlat, double destlog ){
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString
                .append(Double.toString( sourcelog));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString( destlat));
        urlString.append(",");
        urlString.append(Double.toString( destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        return urlString.toString();
    }


    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

    public void drawPath(String  result) {

        try {
            //Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

            for(int z = 0; z<list.size()-1;z++){
                LatLng src= list.get(z);
                LatLng dest= list.get(z+1);
                Polyline line = gMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude,   dest.longitude))
                        .width(4)
                        .color(Color.BLUE).geodesic(true));
            }

        }
        catch (JSONException e) {

        }
    }
    private class GetRouteLocation extends AsyncTask<Void, Void, String> {

        String url;
        GetRouteLocation(String urlPass){
            url = urlPass;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            MainActivity.setRefreshActionButtonState(true);

        }
        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            MainActivity.setRefreshActionButtonState(false);
            if(result!=null){
                drawPath(result);
            }
        }
    }
}