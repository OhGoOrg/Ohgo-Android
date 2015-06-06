package com.ohgo.ohgo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.ohgo.ohgo.R;
import com.ohgo.ohgo.fragments.AddWorkerFragment;
import com.ohgo.ohgo.fragments.NavigationDrawerFragment;
import com.ohgo.ohgo.fragments.ServiceLocationFragment;
import com.ohgo.ohgo.fragments.WorkPlanFragment;
import com.ohgo.ohgo.fragments.WorkersGridFragment;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.models.Service;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        WorkPlanFragment.OnFragmentInteractionListener,
        WorkersGridFragment.OnFragmentInteractionListener,
        ServiceLocationFragment.OnFragmentInteractionListener
{
    private FragmentManager fragmentManager;
    private static Menu menu;


    private boolean mOwnerView; //If Owner = True, If Employee = False
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        mOwnerView=false; //FOR NOW,UNTIL WE IMPLEMENT USER TYPE

        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp
                (R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        //GET OWNER VIEW

        switch (position){
            case 0:
                if (mOwnerView)
                 fragmentManager.beginTransaction()
                        .replace(R.id.container, new WorkPlanFragment())
                        .commit();
                else {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new WorkersGridFragment())
                        .commit();}
                break;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity)
        {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
