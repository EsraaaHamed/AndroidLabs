package com.example.fragmentbackstackexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button addFragmentOne;
    Button addFragmentTwo;
    Button addFragmentThree;
    Button replaceFragmentFour;
    Button removeBtn;

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStackImmediate();
        }
        else

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentOne=(Button)findViewById(R.id.addFragment1);
        addFragmentTwo=(Button)findViewById(R.id.addFragment2);
        addFragmentThree=(Button)findViewById(R.id.addFragment3);
        replaceFragmentFour=(Button)findViewById(R.id.replaceWith4);
        removeBtn=(Button)findViewById(R.id.removeBtn);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Toast.makeText(getApplicationContext(),""+fragmentManager.getBackStackEntryCount(),Toast.LENGTH_SHORT).show();
                for(int i=fragmentManager.getBackStackEntryCount()-1; i>=0; i--) {
                    FragmentManager.BackStackEntry backStackEntry= fragmentManager.getBackStackEntryAt(i);
                    Toast.makeText(getApplicationContext(), backStackEntry.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        addFragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "This is my Toast message!", Toast.LENGTH_LONG).show();
                FirstFragment firstFragment= new FirstFragment();
                addingFragments(firstFragment,"firstFragment");

            }
        });
        addFragmentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragement2 secondFragment= new Fragement2();
                //Toast.makeText(MainActivity.this, "This is my  message!",Toast.LENGTH_LONG).show();
                addingFragments(secondFragment,"secondFragment");

            }
        });
        addFragmentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdFragment thirdFragment= new ThirdFragment();
                addingFragments(thirdFragment,"thirdFragment");

            }
        });
        replaceFragmentFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FourthFragment fourthFragment= new FourthFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragemntsStage,fourthFragment,"fourthFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = fragmentManager.beginTransaction();

                if (fragmentManager.getBackStackEntryCount() > 0){
                    fragmentManager.popBackStack();
                }

            }
        });


    }
    public void addingFragments(Fragment fragment,String tag)
    {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragemntsStage,fragment,tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
