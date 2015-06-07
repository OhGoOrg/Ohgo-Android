package com.ohgo.ohgo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.util.RoundedImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Ruben on 6/5/15.
 */
public class EmployeeDetailsFragment extends Fragment {
    public Context CONTEXT;
    public static final String ARG_PARAM_EMPLOY = "paramEmploy";
    Bundle mBundle;
    private Employee employee;



    public EmployeeDetailsFragment() {}

    public static EmployeeDetailsFragment newInstance(Employee employee)
    {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_EMPLOY, employee);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            this.employee = (Employee) getArguments().getSerializable(ARG_PARAM_EMPLOY);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_employe_detail, container, false);

        TextView txtName = (TextView) rootView.findViewById(R.id.employee_detail_name);
        RoundedImageView imgPic = (RoundedImageView) rootView.findViewById(R.id.img_detail_employee);

        Picasso.with(getActivity()).load(employee.getPhotoFile().getUrl()).into(imgPic);
        txtName.setText(employee.getName());

        return rootView;
    }



    @Override
    public void onResume()
    {
        super.onResume();
    }

    public void setRatingStars(Double rating)
    {
        float num_stars = new Float(rating) / 2;
       // ratingBar.setRating(num_stars);
       // ratingBar.setIsIndicator(true);
    }
}