package com.hatn.learnarduino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class Adapter_Achievements extends BaseAdapter {

    private int resource;
    private LinkedList<achievement> m_achievements;

    public Adapter_Achievements(int resource, LinkedList<achievement> m_achievements){
        this.resource=resource;
        this.m_achievements=m_achievements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView_name;
        TextView textView_discription;
        TextView textView_exp;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        }
        textView_name = (TextView) convertView.findViewById(R.id.name_achieve);
        textView_discription = (TextView) convertView.findViewById(R.id.description_achieve);
        textView_exp = (TextView) convertView.findViewById(R.id.exp);

        textView_name.setText(m_achievements.get(position).getName());
        textView_discription.setText(m_achievements.get(position).getDescription());
        //textView_exp.setText(m_achievements.get(position).getExp());

        return convertView;
    }

    @Override
    public int getCount() {
        return m_achievements.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

}
