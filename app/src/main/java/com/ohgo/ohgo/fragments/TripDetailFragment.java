package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


import com.ohgo.ohgo.R;

import butterknife.InjectView;
import butterknife.ButterKnife;

/**
 * Created by Ruben on 6/4/15.
 */
public class TripDetailFragment extends Fragment
{

    public Context CONTEXT;
    Bundle mBundle;

    @InjectView(R.id.img_detail_user)
    ImageView userImage;
    @InjectView(R.id.user_detail_name)
    TextView userName;
    @InjectView(R.id.rating_star)
    RatingBar ratingBar;
    @InjectView(R.id.detail_review)
    TextView review;



    public TripDetailFragment(){}

    public static TripDetailFragment getInstance(Bundle bundle)
    {
        TripDetailFragment frag = new TripDetailFragment();
        frag.setArguments(bundle);
        return frag;
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        CONTEXT = activity;
        mBundle = getArguments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.item_employee_detail_trip, container, false);

        ButterKnife.inject(this, rootView);
        initView();
        return rootView;
    }

    private void initView()
    {
        setRatingStars(4.0d);
        userName.setText(mBundle.getString("name"));
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    public void setRatingStars(Double rating)
    {
        float num_stars = new Float(rating) / 2;
        ratingBar.setRating(num_stars);
    }
}
