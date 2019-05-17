package com.example.listviewcustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Food [] foods;
    ListView foodListView;

    public MainActivity() {
        foods = new Food[]{
                new Food("Pizza", "helwaa ", R.drawable.pizza),
                new Food("Burger", "burger b al gebna  ", R.drawable.burgersand),
                new Food("Shwremaa", "Swrry ", R.drawable.shwrema),
                new Food("Chicken", "Feraa5 mashwyaaa ", R.drawable.chicken),
                new Food("Pizza", "helwaa ", R.drawable.pizza),
                new Food("Burger", "burger b al gebna  ", R.drawable.burgersand),
                new Food("Shwremaa", "Swrry ", R.drawable.shwrema),
                new Food("Chicken", "Feraa5 mashwyaaa ", R.drawable.chicken),
                new Food("Pizza", "helwaa ", R.drawable.pizza),
                new Food("Burger", "burger b al gebna  ", R.drawable.burgersand),
                new Food("Shwremaa", "Swrry ", R.drawable.shwrema),
                new Food("Chicken", "Feraa5 mashwyaaa ", R.drawable.chicken)

        };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FoodAdapter foodAdapter = new FoodAdapter(MainActivity.this,R.layout.customrowlayout,R.id.nameTxt,foods);
        foodListView=(ListView)findViewById(R.id.foodListView);
        foodListView.setAdapter(foodAdapter);
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
