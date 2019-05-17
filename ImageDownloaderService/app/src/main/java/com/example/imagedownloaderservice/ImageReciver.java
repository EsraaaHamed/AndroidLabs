package com.example.imagedownloaderservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;

public class ImageReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String fileName=intent.getStringExtra("FileName");

        Intent reciveImageInten=new Intent(context,RecivingImage.class);
        reciveImageInten.putExtra("fileName",fileName);
        reciveImageInten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(reciveImageInten);


    }
}
