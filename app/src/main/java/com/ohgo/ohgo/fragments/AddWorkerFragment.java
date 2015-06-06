package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.User;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ruben on 6/6/15.
 */
public class AddWorkerFragment extends Fragment
{

    @InjectView(R.id.input_birth)
    EditText birth;
    @InjectView(R.id.input_mail)
    EditText mail;
    @InjectView(R.id.input_name)
    EditText name;
    @InjectView(R.id.input_pass)
    EditText password;
    @InjectView(R.id.input_phone)
    EditText phone;

    @InjectView(R.id.button)
    Button submit;
    @InjectView(R.id.imageButton)
    ImageButton imageButton;

    Context CONTEXT;
    public AddWorkerFragment() {}

    public static AddWorkerFragment getInstance(Bundle bundle)
    {
        AddWorkerFragment mSearchListFragment = new AddWorkerFragment();
        mSearchListFragment.setArguments(bundle);
        return mSearchListFragment;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        CONTEXT = activity;
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
        View rootView = inflater.inflate(R.layout.fragment_add_employee_form, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
}
