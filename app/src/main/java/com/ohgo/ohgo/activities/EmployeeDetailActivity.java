package com.ohgo.ohgo.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.fragments.ServiceLocationFragment;
import com.ohgo.ohgo.fragments.TripDetailFragment;
import com.ohgo.ohgo.models.Service;

/**
 * Created by Ruben on 6/5/15.
 */
public class EmployeeDetailActivity extends ActionBarActivity implements ServiceLocationFragment.OnFragmentInteractionListener
{
    Bundle mapBundle;
    Bundle employeeBundle;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mapBundle = new Bundle();
        employeeBundle = new Bundle(); employeeBundle.putString("name","OREO");
        setContentView(R.layout.activity_employee_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //StartDetailsFragment();
        //StartListDetailFragment();
        StartMapFragment();
        StartTripDetailsFragment();
    }

    void StartMapFragment()
    {
        ServiceLocationFragment fragment = ServiceLocationFragment.newInstance(Service.getSampleData().get(0));
        getSupportFragmentManager().beginTransaction().replace(R.id.map_detail, fragment).commit();
    }
    void StartTripDetailsFragment()
    {
        Fragment fragment = TripDetailFragment.getInstance(employeeBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.employee_detail_trips, fragment).commit();
    }
    /*
    void StartDetailsFragment()
        {
            SetEmployeBundle();
            Fragment fragment = EmployeDetailsFragment.getInstance(employeeBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.employee_details, fragment).commit();
        }

        void StartListDetailFragment()
        {
            Fragment fragment = ListDetailFragment.getInstance(employeeBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.employee_detail_list, fragment).commit();
        }
        */


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void arriveToLocation(int status)
    {

    }
}
