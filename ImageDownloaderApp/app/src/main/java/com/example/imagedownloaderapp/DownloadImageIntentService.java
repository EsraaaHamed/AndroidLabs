package com.example.imagedownloaderapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadImageIntentService extends IntentService {
    public DownloadImageIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlImage=intent.getStringExtra("imageUrl");
        URL url= null;
        try
        {
            url = new URL(urlImage);
            HttpURLConnection httpURLConnection;
            if(url.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Please enter a Url", Toast.LENGTH_SHORT).show();

            }
            else
            {

                try
                {
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    Bitmap result= BitmapFactory.decodeStream(inputStream);


                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }




    }

}
