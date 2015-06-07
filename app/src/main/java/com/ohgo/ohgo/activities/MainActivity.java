package com.ohgo.ohgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.fragments.NavigationDrawerFragment;
import com.ohgo.ohgo.fragments.ServiceLocationFragment;
import com.ohgo.ohgo.fragments.WorkPlanFragment;
import com.ohgo.ohgo.fragments.WorkersGridFragment;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.models.Service;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        WorkPlanFragment.OnFragmentInteractionListener,
        WorkersGridFragment.OnFragmentInteractionListener,
        ServiceLocationFragment.OnFragmentInteractionListener
{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayoutND;
    @InjectView(R.id.navigation_drawer)
    View mFragmentContainerView;

    private static boolean mOwnerView = true; //If Owner = True, If Employee = False
    private static Menu menu;

    private FragmentManager fragmentManager;
    private int currentPosition = -1;


    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(getIntent().getExtras()!= null){
            this.objectId = getIntent().getExtras().getString("employee");
            //this.mOwnerView = false;

        }
        super.onCreate(savedInstanceState);


        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();




        loadSample();


        mNavigationDrawerFragment.setUp(mFragmentContainerView, drawerLayoutND, toolbar);


    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        position=position==0?1:position;
        if (currentPosition!=position)
        {
            currentPosition = position;

            switch (position)
            {
                case 1:
                    Fragment fragment;
                    fragment = new WorkPlanFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                    break;
            }
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.menu = menu;

        setRefreshActionButtonState(false);
        if (!mNavigationDrawerFragment.isDrawerOpen())
        {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.addEmployee)
        {
            Intent intent = new Intent(this, NewEmployeeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceSelected(Service service)
    {
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, ServiceLocationFragment.newInstance(service))
                .commit();
    }

    @Override
    public void arriveToLocation(int status) {

    }

    public static void setRefreshActionButtonState(boolean refresh){
        if(menu!=null)
        {

            MenuItem refreshItem = menu.findItem(R.id.menuRefresh);
            if(refreshItem!=null)
            {
                if(refresh)
                {
                    refreshItem.setVisible(true);
                    refreshItem.setActionView(R.layout.loading);
                }else
                {
                    refreshItem.setVisible(false);
                }
            }
        }
    }


    private void loadSample(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Employee");
        query.whereEqualTo("name", "Jesus");
        query.findInBackground( new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if( parseObjects.size() > 0)
                Log.i("Brand", "Retrieved " + parseObjects.get(0).getString("name") + " Brands");


            }
        });

/*
        ParseObject employee = new ParseObject("Employee");
        //employee.put("userId", "nF6zyt33gl");
        employee.put("name", "Ramon");
        employee.saveInBackground();

*/



        //employee.setUserId("nF6zyt33gl");
        //employee.setName("Ricardo");




/*
        try {
            ParseQuery<ParseUser> query1 = ParseUser.getQuery();
            query1.findInBackground( new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> parseUsers, ParseException e) {
                    ParseUser user = parseUsers.get(0);
                    ParseRelation relation = employee.getRelation(Employee.CLASS_NAME);


                }
            });



            //user.saveInBackground();

        }catch (Exception e){
            e.printStackTrace();
        }
        */


    }

    @Override
    public void onEmployeeSelected(Employee employee) {


        Intent intent = new Intent(getApplicationContext(),EmployeeDetailActivity.class);
        //intent.putExtra("employee", employee);
        intent.putExtra("objectId", employee.getObjectId());

        Log.e("OBJID",intent.getExtras().getString("objectId"));
        //Employee e = (Employee) intent.getExtras().getSerializable("employee");

        startActivity(intent);
    }
}
