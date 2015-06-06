package com.ohgo.ohgo.activities;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.fragments.AddWorkerFragment;
import com.ohgo.ohgo.models.Employee;

/**
 * Created by Ruben on 6/6/15.
 */
public class NewEmployeeActivity extends Activity
{

    private Employee employee;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        employee = new Employee();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_new_employee);
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null)
        {
            fragment = new AddWorkerFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    public Employee getEmployee() {
        return employee;
    }

}