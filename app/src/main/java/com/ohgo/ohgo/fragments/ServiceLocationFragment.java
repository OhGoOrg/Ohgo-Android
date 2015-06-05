package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.Service;
import com.ohgo.ohgo.models.User;


public class ServiceLocationFragment extends Fragment {

    private static final String ARG_PARAM_SERVICE = "paramService";


    private OnFragmentInteractionListener mListener;

    private SupportMapFragment supportMapFragment;
    private GoogleMap gMap;
    private Service service;

    public static ServiceLocationFragment newInstance(Service service) {
        ServiceLocationFragment fragment = new ServiceLocationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_SERVICE, service);
        fragment.setArguments(args);
        return fragment;
    }

    public ServiceLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.service = (Service) getArguments().getSerializable(ARG_PARAM_SERVICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_location, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        gMap = supportMapFragment.getMap();
        gMap.setMyLocationEnabled(true);
        setLocation();

        fragmentManager.beginTransaction()
                .replace(R.id.containerService, UserInfoFragment.newInstance(new User("Riacrdo Centeno", "sdfghj")))
                .commit();


        return view;
    }


    public void setLocation(){
        gMap.addMarker((new MarkerOptions().position(new LatLng(service.getLatitude(), service.getLongitude()))).title("USER"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(service.getLatitude(),service.getLongitude()), 10));

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


    public interface OnFragmentInteractionListener {

        public void arriveToLocation(int status);
    }

}
