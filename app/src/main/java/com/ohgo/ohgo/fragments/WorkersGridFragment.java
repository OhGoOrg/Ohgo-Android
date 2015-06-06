package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.adapters.GridWorkerAdapter;
import com.ohgo.ohgo.models.Service;

/**
 * Created by Ruben on 6/5/15.
 */
public class WorkersGridFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_grid_workers, container, false);
        GridView gridWorkers = (GridView) view.findViewById(R.id.gridWorkers);

        GridWorkerAdapter adapter = new GridWorkerAdapter(getActivity(),R.layout.item_grid_worker,Service.getSampleData());
        gridWorkers.setAdapter(adapter);
        gridWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mListener.onServiceSelected(Service.getSampleData().get(position));
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

        public void onServiceSelected(Service service);
    }

}