package com.example.countriescustomlist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryDetailesFragment extends Fragment {
    Country country;
    TextView countryRank;
    TextView countryPopulation;
    TextView countryName;
    ImageView countryImage;
    public static final String TAG="container";
    public CountryDetailesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putSerializable("Country",country);
        Log.e(TAG, "onCreateView on save instance");
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //savedInstanceState.putSerializable("Country",country);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            Log.e(TAG, "onCreateView country deta");
            View view;
            try {
                view = inflater.inflate(R.layout.fragment_country_detailes, container, false);
                countryRank = view.findViewById(R.id.countryRankTxt);
                countryName = view.findViewById(R.id.countryNameTxt);
                countryPopulation = view.findViewById(R.id.countryPopulationTxt);
                countryImage = view.findViewById(R.id.countryImage);
                /*if(savedInstanceState!=null)
                {
                    country=(Country)savedInstanceState.getSerializable("Country");
                    changeData(country);
                }*/


           } catch (Exception e) { Log.e(TAG, "onCreateView", e);
               throw e;
            }

        return view;

    }

    public void changeData(Country country) {
        this.country=country;
        countryRank.setText(country.getRank());
        countryName.setText(country.getName());
        countryPopulation.setText(country.getPopulation());
        countryImage.setImageBitmap(null);
        try
        {
            URL textUrl=new URL(country.getImageurl());
            new DownloadImage().execute(textUrl);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }


    }
    private class DownloadImage extends AsyncTask<URL,Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            countryImage.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            URL url;
            HttpURLConnection httpURLConnection;

            if(urls[0].equals(""))
            {
               // Toast.makeText(getActivity().getApplicationContext(), "Please enter a Url", Toast.LENGTH_SHORT).show();

            }
            else
            {
                url=urls[0];
                try
                {
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    Bitmap result= BitmapFactory.decodeStream(inputStream);
                    return result;

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
