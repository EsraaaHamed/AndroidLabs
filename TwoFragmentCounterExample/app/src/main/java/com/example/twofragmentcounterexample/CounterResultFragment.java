package com.example.twofragmentcounterexample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CounterResultFragment extends Fragment {
    int count =0;
    TextView counterTxt;

    public CounterResultFragment() {
        // Required empty public constructor
    }
    public void changeData(int data)
    {

        count=data;
        counterTxt.setText(Integer.toString(count));
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Count",count);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_counter_result, container, false);
        counterTxt=(TextView) view.findViewById(R.id.counterTextView);
        if (savedInstanceState != null) {

            count = savedInstanceState.getInt("Count");
            counterTxt.setText(Integer.toString(count));
        }

        return view;
    }

}
