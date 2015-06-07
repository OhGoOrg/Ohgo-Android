package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.adapters.GridWorkerAdapter;
import com.ohgo.ohgo.models.Employee;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben on 6/5/15.
 */
public class WorkersGridFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayList<Employee> employ = new ArrayList<>();
    private GridWorkerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grid_workers, container, false);
        GridView gridWorkers = (GridView) view.findViewById(R.id.gridWorkers);

        ParseQuery<Employee> query = ParseQuery.getQuery("Employee");

        query.findInBackground(new FindCallback<Employee>() {
            @Override
            public void done(List<Employee> employees, ParseException e) {

                if (employees == null) {
                    Log.d("score", "The getFirst request failed." );

                } else {
                    Log.d("score", "Retrieved the object."+employees.size());
                    employ = (ArrayList<Employee>) employees;
                    adapter.clear();
                    adapter.addAll(employ);
                    adapter.notifyDataSetChanged();


                }


            }
        });

        //Log.e("EMPLOY", ""+employ.size());
        adapter = new GridWorkerAdapter(getActivity(),R.layout.item_grid_worker,employ);
        gridWorkers.setAdapter(adapter);
        gridWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mListener.onEmployeeSelected(employ.get(position));
                Log.e("NAME",employ.get(position).getName());

            }
        });

        return view;
    }



    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onEmployeeSelected(Employee employee);
    }

}