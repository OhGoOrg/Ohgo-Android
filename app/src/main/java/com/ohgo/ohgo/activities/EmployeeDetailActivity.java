package com.ohgo.ohgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.fragments.EmployeeDetailsFragment;
import com.ohgo.ohgo.fragments.MapFragment;
import com.ohgo.ohgo.fragments.TripDetailFragment;
import com.ohgo.ohgo.fragments.WorkPlanFragment;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.models.Service;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Ruben on 6/5/15.
 */
public class EmployeeDetailActivity extends ActionBarActivity implements MapFragment.OnFragmentInteractionListener,
        WorkPlanFragment.OnFragmentInteractionListener
{
    private Bundle mapBundle;
    private FragmentManager manager;
    private RelativeLayout relativeLayout;
    private Employee employ;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        Intent intent = this.getIntent();



        if(intent.getExtras()!=null){
            objectId = intent.getExtras().getString("objectId");

        }

        ParseQuery<Employee> query = ParseQuery.getQuery("Employee");
        query.whereEqualTo("objectId", objectId);
        query.findInBackground(new FindCallback<Employee>() {
            @Override
            public void done(List<Employee> employees, ParseException e) {
                if(employees != null ){
                    employ = employees.get(0);

                    StartListDetailFragment(employ);
                    StartDetailsFragment(employ);
                    StartMapFragment(employ);
                }else {
                    e.printStackTrace();
                }
            }
        });

        mapBundle = new Bundle();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        relativeLayout = (RelativeLayout) findViewById(R.id.employee_detail_trips);





    }

    void StartMapFragment(Employee employee)
    {
        MapFragment fragment = MapFragment.newInstance(employee);
        getSupportFragmentManager().beginTransaction().replace(R.id.map_detail, fragment).commit();
    }

    void StartMapFragment(Service service)
    {
        MapFragment fragment = MapFragment.newInstance(service);
        getSupportFragmentManager().beginTransaction().replace(R.id.map_detail, fragment).commit();
    }



    void StartTripDetailsFragment(Bundle employeeBundle)
    {
        Fragment fragment = TripDetailFragment.getInstance(employeeBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.employee_detail_trips, fragment).commit();
    }
    void StartDetailsFragment(Employee employee){
        getSupportFragmentManager().beginTransaction().
                replace(R.id.employee_details, EmployeeDetailsFragment.newInstance(employee))
                .commit();
    }

    void StartListDetailFragment(Employee employee)
    {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.employee_detail_list, WorkPlanFragment.newInstance(employee))
                .commit();
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
    public void onCameraChange(boolean status)
    {



        if(status)
        relativeLayout.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onServiceSelected(Service service)
    {
        relativeLayout.setVisibility(View.VISIBLE);
        StartMapFragment(service); //Service tiene un customer Id
        Bundle employeeBundle = new Bundle();
        employeeBundle.putString("name", "NombreEmpleado"); //DEJAR HARCODE POR EL BIEN DE TODOS
        StartTripDetailsFragment(employeeBundle);
    }
}
