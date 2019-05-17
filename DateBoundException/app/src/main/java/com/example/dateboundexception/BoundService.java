package com.example.dateboundexception;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {
    private final IBinder iBinder = new MyInnerBinder();
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return iBinder;
    }
    public String getCurrentTime()
    {
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:SS MM/dd/yy", Locale.US);
        return (dateFormat.format(new Date()));
    }
    public class MyInnerBinder extends Binder
    {
        public MyInnerBinder() {
        }
        public BoundService getService ()
        {
            return BoundService.this;
        }
    }
}
