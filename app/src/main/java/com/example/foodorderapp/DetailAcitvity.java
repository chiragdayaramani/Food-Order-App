package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailAcitvityBinding;

public class DetailAcitvity extends AppCompatActivity {

    ActivityDetailAcitvityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailAcitvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        binding.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(binding.quantity.getText().toString());
                value+=1;
                binding.quantity.setText(value+"");
                Toast.makeText(DetailAcitvity.this, "Quantity Increased by 1", Toast.LENGTH_SHORT).show();
            }
        });

        binding.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(binding.quantity.getText().toString());
                if(value==1){
                    Toast.makeText(DetailAcitvity.this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                }else{
                    value-=1;
                    binding.quantity.setText(value+"");
                    Toast.makeText(DetailAcitvity.this, "Quantity Decreased by 1", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (getIntent().getIntExtra("type", 0) == 1) {
            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");


            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );

                    if (isInserted)
                        Toast.makeText(DetailAcitvity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailAcitvity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
//            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();

            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.foodName.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(6));
            binding.quantity.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            binding.foodName.getText().toString(),
                            binding.detailDescription.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id
                    );
                    
                    if(isUpdated)
                        Toast.makeText(DetailAcitvity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailAcitvity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}