package com.ohgo.ohgo.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.adapters.WorkPlanAdapter;
import com.ohgo.ohgo.fragments.EmployeeDetailsFragment;
import com.ohgo.ohgo.fragments.MapFragment;
import com.ohgo.ohgo.fragments.ServiceLocationFragment;
import com.ohgo.ohgo.fragments.TripDetailFragment;
import com.ohgo.ohgo.fragments.WorkPlanFragment;
import com.ohgo.ohgo.models.Service;
import com.parse.ParseObject;

/**
 * Created by Ruben on 6/5/15.
 */
public class EmployeeDetailActivity extends ActionBarActivity implements MapFragment.OnFragmentInteractionListener,
        WorkPlanFragment.OnFragmentInteractionListener
{
    Bundle mapBundle;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mapBundle = new Bundle();
        setContentView(R.layout.activity_employee_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StartListDetailFragment();
        StartDetailsFragment();
        StartMapFragment();
    }

    void StartMapFragment(Service service)
    {
        MapFragment fragment = MapFragment.newInstance(service);
        getSupportFragmentManager().beginTransaction().replace(R.id.map_detail, fragment).commit();
    }

    void StartMapFragment()
    {
        MapFragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.map_detail, fragment).commit();
    }

    void StartTripDetailsFragment(Bundle employeeBundle)
    {
        Fragment fragment = TripDetailFragment.getInstance(employeeBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.employee_detail_trips, fragment).commit();
    }
    void StartDetailsFragment()
    {
        Bundle b = new Bundle();b.putString("name", "empleado");
        Fragment fragment = EmployeeDetailsFragment.getInstance(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.employee_details, fragment).commit();
    }

    void StartListDetailFragment()
    {
       getSupportFragmentManager().beginTransaction().replace(R.id.employee_detail_list, new WorkPlanFragment()).commit();
    }



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

    @Override
    public void onServiceSelected(Service service)
    {
        StartMapFragment(service); //Service tiene un customer Id
        Bundle employeeBundle = new Bundle();
        employeeBundle.putString("name", "NombreEmpleado"); //DEJAR HARCODE POR EL BIEN DE TODOS
        StartTripDetailsFragment(employeeBundle);
    }
}
