package com.example.android.cs2table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class timeTableAdapter extends ArrayAdapter<timeTable> {

    List<timeTable> timeTables;
    Context context;
    int resource;

    public timeTableAdapter(Context context, int resource, List<timeTable> timeTables){
        super(context, resource, timeTables);
        this.context = context;
        this.resource = resource;
        this.timeTables = timeTables;
    }



    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        TextView time = view.findViewById(R.id.time);
        TextView subject = view.findViewById(R.id.subject);

        timeTable timeTable= timeTables.get(position);

        time.setText(timeTable.getTime());
        subject.setText(timeTable.getSubject());

        return view;



    }
}
