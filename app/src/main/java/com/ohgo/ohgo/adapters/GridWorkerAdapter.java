package com.ohgo.ohgo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.activities.EmployeeDetailActivity;
import com.ohgo.ohgo.models.Service;

import java.util.ArrayList;

/**
 * Created by Ruben on 6/5/15.
 */
public class GridWorkerAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Service> services;
    private LayoutInflater inflater;

    public GridWorkerAdapter(Context context, int resource, ArrayList services)
    {
        super(context, resource, services);
        this.context = context;
        this.resource = resource;
        this.services = services;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView == null)
        {
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();

            holder.txtUserName = (TextView) convertView.findViewById(R.id.employee_name);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        holder.txtUserName.setText("User "+"dfxhgjk");
        holder.txtUserName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, EmployeeDetailActivity.class);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder
    {
        TextView txtUserName;
    }
}
