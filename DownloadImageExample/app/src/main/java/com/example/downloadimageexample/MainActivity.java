package com.example.downloadimageexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    EditText imgUrl;
    Button downloadImg;
    private LruCache<String, Bitmap> memoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.img);
        imgUrl=(EditText)findViewById(R.id.urlText);
        downloadImg=(Button)findViewById(R.id.downloadImg);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        downloadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                { if(getBitmapFromMemCache(imgUrl.getText().toString())!=null)
                {
                    img.setImageBitmap(getBitmapFromMemCache(imgUrl.getText().toString()));
                }
                else {
                    URL textUrl = new URL(imgUrl.getText().toString());
                    new DownloadImage().execute(textUrl);
                }
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }
    private class DownloadImage extends AsyncTask<URL,Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
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
                Toast.makeText(getApplicationContext(), "Please enter a Url", Toast.LENGTH_SHORT).show();

            }
            else
            {
                    url=urls[0];
                try
                {
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    Bitmap result=BitmapFactory.decodeStream(inputStream);
                    addBitmapToMemoryCache(imgUrl.getText().toString(),result);
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
