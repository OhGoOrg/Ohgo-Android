package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.activities.MainActivity;
import com.ohgo.ohgo.activities.NewEmployeeActivity;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.models.User;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

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
    Button submitButton;
    @InjectView(R.id.cancel)
    Button cancelButton;

    @InjectView(R.id.imageButton)
    ImageButton photoButton;
    @InjectView(R.id.preview)
    ParseImageView imagePreview;

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
        initViews();
        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    public void startCamera()
    {
        Fragment cameraFragment = new CameraFragment();
        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, cameraFragment);
        transaction.addToBackStack("AddWorkerFragment");
        transaction.commit();
    }
    private void initViews()
    {
        imagePreview.setVisibility(View.INVISIBLE);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                startCamera();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Probar si X Campo esta vacio y si quiza deberia ser obligatorio
                Employee employee = ((NewEmployeeActivity) getActivity()).getEmployee();

                employee.setName(name.getText().toString());
                employee.setPassword(password.getText().toString());
                employee.setBirth(birth.getText().toString());
                employee.setEmail(mail.getText().toString());
                employee.setPhone(phone.getText().toString());

                // Save the meal and return
                employee.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            getActivity().setResult(Activity.RESULT_OK);
                            getActivity().finish();
                        } else {
                            Toast.makeText(
                                    getActivity().getApplicationContext(),
                                    "Error saving: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        });
    }
}