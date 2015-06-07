package com.ohgo.ohgo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.adapters.WorkPlanAdapter;
import com.ohgo.ohgo.models.Employee;
import com.ohgo.ohgo.models.Service;


public class WorkPlanFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM_EMPLOYEE = "paramEmployee";

    private Employee employee;

    public static WorkPlanFragment newInstance(Employee employee) {
        WorkPlanFragment fragment = new WorkPlanFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_EMPLOYEE, employee);

        fragment.setArguments(args);
        return fragment;
    }

    public WorkPlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.employee = (Employee) getArguments().getSerializable(ARG_PARAM_EMPLOYEE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_work_plan, container, false);
        ListView listWorkPlan = (ListView) view.findViewById(R.id.listWorkPlan);
        Toast.makeText(getActivity(), "Size: "+ Service.getSampleData().size(), Toast.LENGTH_SHORT).show();
        WorkPlanAdapter adapter = new WorkPlanAdapter(getActivity(),R.layout.item_list_work_plan,Service.getSampleData());
        listWorkPlan.setAdapter(adapter);

        listWorkPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onServiceSelected(Service.getSampleData().get(position));
            }
        });
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onServiceSelected(Service service);
    }
}
