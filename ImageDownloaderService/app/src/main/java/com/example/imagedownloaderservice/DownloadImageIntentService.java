package com.example.imagedownloaderservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownloadImageIntentService extends IntentService {
    ImageReciver imageReciver;
    public DownloadImageIntentService() {
        super("");
        imageReciver=new ImageReciver();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlImage = intent.getStringExtra("imageUrl");
        URL url = null;
        try {
            url = new URL(urlImage);
            HttpURLConnection httpURLConnection;
            if (url.equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter a Url", Toast.LENGTH_SHORT).show();

            } else {

                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap result = BitmapFactory.decodeStream(inputStream);
                    saveInFile(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void saveInFile(Bitmap resultImg) {
        FileOutputStream fileOutputStream = null;
        String fileName="F:\\imageFile.txt";
        try
        {

            fileOutputStream = openFileOutput(fileName,MODE_PRIVATE);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try ( DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream))
        {
            resultImg.compress(Bitmap.CompressFormat.PNG,85,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            //dataOutputStream.close();
            ///////////////adding intent filter/////////////
            IntentFilter imageReciveIntentFilter= new IntentFilter("passFileName");
            //imageReciver = new ImageReciver();
            registerReceiver(imageReciver,imageReciveIntentFilter);
            /////////////////broadCast based on An action///////////////////////
            Intent passFileNameIntent= new Intent();
            passFileNameIntent.setAction("passFileName");
            // passFileNameIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            passFileNameIntent.putExtra("FileName",fileName);
            sendBroadcast(passFileNameIntent);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(imageReciver);

    }
}
