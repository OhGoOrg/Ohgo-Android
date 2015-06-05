package com.ohgo.ohgo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.Service;

import java.util.ArrayList;

/**
 * Created by Rick on 04/06/15.
 */
public class WorkPlanAdapter extends ArrayAdapter{

    private Context context;
    private int resource;
    private ArrayList<Service> services;
    private LayoutInflater inflater;

    public WorkPlanAdapter(Context context, int resource, ArrayList services) {
        super(context, resource, services);
        this.context = context;
        this.resource = resource;
        this.services = services;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();

            holder.statusColor = (FrameLayout) convertView.findViewById(R.id.statusColor);
            holder.txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
            holder.txtServiceDate = (TextView) convertView.findViewById(R.id.txtServiceDate);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        Service service = services.get(position);
        switch (service.getStatus()){

            case 1:
                holder.statusColor.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                break;
            case 2:
                holder.statusColor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_purple));
                break;
            case 3:
                holder.statusColor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
                break;

        }

        holder.txtUserName.setText("User "+service.getUserId());
        holder.txtServiceDate.setText(service.getServiceDate().toString());


        return convertView;
    }

    class ViewHolder{
        TextView txtUserName;
        TextView txtServiceDate;
        FrameLayout statusColor;
    }
}
