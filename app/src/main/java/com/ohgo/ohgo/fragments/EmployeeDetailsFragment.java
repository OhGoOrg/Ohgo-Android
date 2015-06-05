package com.ohgo.ohgo.fragments;

import android.support.v4.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ohgo.ohgo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ruben on 6/5/15.
 */
public class EmployeeDetailsFragment extends Fragment {
    public Context CONTEXT;
    Bundle mBundle;

    @InjectView(R.id.img_detail_employee)
    ImageView userImage;
    @InjectView(R.id.employee_detail_name)
    TextView employeeName;
    @InjectView(R.id.rating_star)
    RatingBar ratingBar;


    public EmployeeDetailsFragment() {}

    public static EmployeeDetailsFragment getInstance(Bundle bundle)
    {
        EmployeeDetailsFragment frag = new EmployeeDetailsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CONTEXT = activity;
        mBundle = getArguments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_employe_detail, container, false);

        ButterKnife.inject(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        setRatingStars(9.0d);
        employeeName.setText(mBundle.getString("name"));
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