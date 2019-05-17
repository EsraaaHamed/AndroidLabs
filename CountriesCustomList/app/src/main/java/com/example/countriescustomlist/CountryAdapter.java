package com.example.countriescustomlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class CountryAdapter extends ArrayAdapter
{
    Context context;
    List<Country> countries;


    public CountryAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.countries = objects;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        ViewHolder viewHolder;
        View row=convertView;
        if(row==null) {
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.singlecountryrow,parent,false);

            viewHolder= new ViewHolder(row);
            row.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ViewHolder)row.getTag();
        }
        viewHolder.getNameTxt().setText(countries.get(position).getName());
        viewHolder.getRankTx().setText(countries.get(position).getRank());

        return row;
    }
}
