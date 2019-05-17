package com.example.listviewcustom;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private View currentView;
    private TextView nameTxt;
    private TextView descTx;
    private ImageView imgIcon;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }

    public View getCurrentView() {

        return currentView;
    }

    public TextView getNameTxt() {
        if(nameTxt==null)
        {
            nameTxt=(TextView)currentView.findViewById(R.id.nameTxt);
        }
        return nameTxt;
    }

    public TextView getDescTx() {
        if(descTx==null)
            descTx=(TextView)currentView.findViewById(R.id.descTxt);
        return descTx;
    }

    public ImageView getImgIcon() {
        if(imgIcon==null)
            imgIcon=(ImageView) currentView.findViewById(R.id.imgView);
        return imgIcon;
    }
}
