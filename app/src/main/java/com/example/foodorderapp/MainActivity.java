package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "10", "Veg Pizza with Cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "10", "Veg Pizza with Cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "10", "Veg Pizza with Cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "10", "Veg Pizza with Cheese"));


        MainAdapter adapter=new MainAdapter(list,this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}