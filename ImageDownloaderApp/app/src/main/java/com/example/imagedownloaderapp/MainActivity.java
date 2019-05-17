package com.example.imagedownloaderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText urlTxt;
    Button downloadImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlTxt=(EditText)findViewById(R.id.urlTxt);
        downloadImg=(Button)findViewById(R.id.downloadImg);
        downloadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrlStr =urlTxt.getText().toString();
                Intent downloadIntentService= new Intent(MainActivity.this,DownloadImageIntentService.class);
                downloadIntentService.putExtra("imageUrl", imageUrlStr);
                startService(downloadIntentService);
            }
        });
    }
}
