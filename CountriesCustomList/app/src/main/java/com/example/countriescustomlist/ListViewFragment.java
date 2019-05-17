package com.example.countriescustomlist;


import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {
    MyThread myThread ;
    Handler mainHandler;
    int counter=0;
    List<Country> returnCountryList;
    ListView countriesListView;
    View view;
    Communicator communicator;
    Country country;
    public static final String TAG="container";

    public ListViewFragment() {
        // Required empty public constructor
        returnCountryList = new ArrayList<>();

    }

    public List<Country> getReturnCountryList() {
        return returnCountryList;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(country!=null)
        outState.putSerializable("Country",country);

    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator=(Communicator)getActivity();

        myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                returnCountryList = (List<Country>) msg.obj;

                if(country==null&&getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                {
                    communicator.respond(returnCountryList.get(0));
                }
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE&&country!=null)
                {
                    communicator.respond(country);
                }
                CountryAdapter countryAdapter = new CountryAdapter(getActivity().getApplicationContext(), R.layout.singlecountryrow, R.id.nameTxt, returnCountryList);
                countriesListView = (ListView) view.findViewById(R.id.countriesListView);
                countriesListView.setAdapter(countryAdapter);
                countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        country=(Country) parent.getItemAtPosition(position);
                        communicator.respond(country);

                    }
                });


            }

        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_list_view, container, false);
            if(savedInstanceState!=null)
            {
                country=(Country)savedInstanceState.getSerializable("Country");
            }

        return view;
    }
    private class MyThread implements Runnable
    {
        String url="https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        List<Country> countryList= new ArrayList<>();
        Country country ;
        Handler myThreadHandler;
        @Override
        public void run() {

            // Looper.prepare();
            try
            {
                URL urlConnection=new URL(url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) urlConnection.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                String result=convertStreamToString(inputStream);
                // Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();
                try
                {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray worldpopulation=jsonObject.getJSONArray("worldpopulation");
                    for(int i=0; i<worldpopulation.length();i++)
                    {
                        JSONObject eachCountry= worldpopulation.getJSONObject(i);
                        String rank=eachCountry.getString("rank");
                        final String countryName=eachCountry.getString("country");
                        String population=eachCountry.getString("population");
                        String imgUrl=eachCountry.getString("flag").replaceFirst("http","https");
                        //imgUrl.replaceFirst("http","https");
                        country= new Country(rank,countryName,population,imgUrl);
                        countryList.add(country);
                        /*myHandlerThread.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), ""+country.name, Toast.LENGTH_SHORT).show();

                            }
                        });*/

                    }
                    Message msg=new Message();
                    //msg.what=1;
                    msg.obj=countryList;
                    mainHandler.sendMessage(msg);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

        private String convertStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringStream=new StringBuilder();
            String eachLine;
            while((eachLine=bufferedReader.readLine())!=null)
            {
                stringStream.append(eachLine);
                stringStream.append("\n");
            }
            inputStream.close();
            //bufferedReader.close();
            return stringStream.toString();
        }
    }

}
