package com.ohgo.ohgo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.Employee;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ruben on 6/5/15.
 */
public class GridWorkerAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Employee> employees;
    private LayoutInflater inflater;

    public GridWorkerAdapter(Context context, int resource, ArrayList<Employee> employees)
    {
        super(context, resource, employees);
        this.context = context;
        this.resource = resource;
        this.employees = employees;
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
            holder.imgEmployee = (ImageView) convertView.findViewById(R.id.imgEmployee);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        Employee employee = employees.get(position);
        holder.txtUserName.setText(employee.getName());


        Picasso.with(context).load(employee.getParseFile("photo").getUrl()).into(holder.imgEmployee);

        return convertView;
    }

    class ViewHolder
    {
        TextView txtUserName;
        ImageView imgEmployee;
    }
}
