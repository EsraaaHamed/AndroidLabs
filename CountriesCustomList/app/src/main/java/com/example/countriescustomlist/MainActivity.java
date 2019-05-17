package com.example.countriescustomlist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Communicator {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CountryDetailesFragment countryDetailesFragment;
    ListViewFragment listViewFragment;
    public static final String TAG="test";
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           // if(savedInstanceState==null) {
                fragmentManager = getSupportFragmentManager();
                listViewFragment = new ListViewFragment();
                if(fragmentManager.findFragmentByTag("listViewFragment")==null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.listViewLayout, listViewFragment, "listViewFragment");
                    fragmentTransaction.commit();
                }

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    countryDetailesFragment = new CountryDetailesFragment();
                    //fragmentManager = getSupportFragmentManager();
                    if(fragmentManager.findFragmentByTag("countryDetailesFragment")==null) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.countryDetailesLayout, countryDetailesFragment, "countryDetailesFragment");
                        fragmentTransaction.commit();
                    }

                    //countryDetailesFragment.changeData(listViewFragment.getReturnCountryList().get(0));
                }


            //}
           /* else
            {
                listViewFragment=(ListViewFragment)fragmentManager.findFragmentByTag("listViewFragment");
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                    countryDetailesFragment=(CountryDetailesFragment)fragmentManager.findFragmentByTag("countryDetailesFragment");

            }*/
    }
    @Override
    public void respond(Country country) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            countryDetailesFragment = new CountryDetailesFragment();
            countryDetailesFragment = (CountryDetailesFragment) fragmentManager.findFragmentByTag("countryDetailesFragment");
            countryDetailesFragment.changeData(country);
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("Country", country);
            startActivityForResult(intent,1);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1)
        {
            Country returnedCountry=(Country)data.getSerializableExtra("result");
             countryDetailesFragment = new CountryDetailesFragment();
            countryDetailesFragment = (CountryDetailesFragment) fragmentManager.findFragmentByTag("countryDetailesFragment");

            countryDetailesFragment.changeData(returnedCountry);
        }
    }
    @Override
    public void setFirstCountry(Country country)
    {
    }
}
