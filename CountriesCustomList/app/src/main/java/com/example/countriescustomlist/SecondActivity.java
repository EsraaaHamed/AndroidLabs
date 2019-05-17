package com.example.countriescustomlist;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    CountryDetailesFragment countryDetailesFragment;
    Country country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fragmentManager=getSupportFragmentManager();
        countryDetailesFragment= new CountryDetailesFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.countryDataLayout,countryDetailesFragment,"countryDetailesFragment");
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        country = (Country) intent.getSerializableExtra("Country");
        countryDetailesFragment.changeData(country);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",country);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",country);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
