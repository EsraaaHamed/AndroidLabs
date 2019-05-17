package com.example.twofragmentcounterexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CounterBtnFragment counterBtnFragment;
        fragmentManager=getSupportFragmentManager();
        if(savedInstanceState==null) {
            counterBtnFragment= new CounterBtnFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.firstfragmentArea, counterBtnFragment, "counterBtnFragment");
            fragmentTransaction.commit();
        }
        else
        {
            counterBtnFragment=(CounterBtnFragment) fragmentManager.findFragmentByTag("counterBtnFragment");
        }

    }

    @Override
    public void respond(int data) {
        CounterResultFragment countResult =(CounterResultFragment)fragmentManager.findFragmentById(R.id.fragment);

        countResult.changeData(data);
    }
}
