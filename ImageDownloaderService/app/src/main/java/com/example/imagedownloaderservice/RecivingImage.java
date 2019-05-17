package com.example.imagedownloaderservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RecivingImage extends AppCompatActivity {
    ImageView recivedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciving_image);
        recivedImage=(ImageView)findViewById(R.id.resultImage);
        Intent intent =getIntent();
        String filename=intent.getStringExtra("fileName");
        if(filename!=null) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            recivedImage.setImageBitmap(bitmap);
        }
        else
        {
            String action=intent.getAction();
            String type=intent.getType();
            if(Intent.ACTION_SEND.equals(action)&&type.startsWith("image/"))
            {
                Uri imageUri=(Uri)intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if(imageUri!=null)
                recivedImage.setImageURI(imageUri);
            }
        }

    }
}
