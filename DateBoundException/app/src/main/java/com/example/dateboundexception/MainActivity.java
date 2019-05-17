package com.example.dateboundexception;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BoundService boundService;
    boolean isBond=false;
    Button getdate;
    TextView dateTxt;

    public MainActivity() {
        boundService= new BoundService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getdate=(Button)findViewById(R.id.getDateBtn);
        dateTxt=(TextView)findViewById(R.id.dateTxt);
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,myConnectionClass, Context.BIND_AUTO_CREATE);
        getdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnedDate=boundService.getCurrentTime();
                dateTxt.setText(returnedDate);
            }
        });

    }
    private ServiceConnection myConnectionClass= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyInnerBinder myInnerBinder =(BoundService.MyInnerBinder)service;
            boundService=myInnerBinder.getService();
            isBond=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBond=false;
        }
    };
}
