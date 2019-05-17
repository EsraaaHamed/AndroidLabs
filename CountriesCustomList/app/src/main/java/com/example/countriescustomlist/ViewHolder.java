package com.example.countriescustomlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private View currentView;
    private TextView nameTxt;
    private TextView rankTx;
    private ImageView imgIcon;
    private TextView popTxt;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {

        this.currentView = currentView;
    }

    public TextView getNameTxt() {
        if(nameTxt==null)
            nameTxt=currentView.findViewById(R.id.nameTxt);
        return nameTxt;
    }

    public void setNameTxt(TextView nameTxt) {
        this.nameTxt = nameTxt;
    }

    public TextView getRankTx() {
        if(rankTx==null)
            rankTx=currentView.findViewById(R.id.countryRankTxt);
        return rankTx;
    }

    public void setRankTx(TextView rankTx) {
        this.rankTx = rankTx;
    }

}
