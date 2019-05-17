package com.example.listviewcustom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends ArrayAdapter {
    Context context;
    Food [] foods;
    static int counter=0;
    public static final String TAG="position and Counter";


    public FoodAdapter(Context context, int resource, int textViewResourceId, Food[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        foods=objects;

    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        ViewHolder viewHolder;
        View row=convertView;
        Log.i(TAG,""+position);
        Log.i(TAG,""+counter);
        counter++;
        if(row==null) {
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.customrowlayout,parent,false);

            viewHolder= new ViewHolder(row);
            row.setTag(viewHolder);
            Log.i(TAG,"inside if");

        }
        else
        {
            viewHolder=(ViewHolder)row.getTag();
            Log.i(TAG,"inside else");
        }
        viewHolder.getNameTxt().setText(foods[position].getName());
        viewHolder.getDescTx().setText(foods[position].getDesc());
        viewHolder.getImgIcon().setImageResource(foods[position].getImgIcon());

       // viewHolder.getImgIcon().setImage

        return row;
    }
}
