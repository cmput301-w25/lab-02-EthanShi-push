package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityButton, deleteCityButton, confirmButton;
    EditText cityInput;
    String currentAction = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all components from xml files
        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);
        confirmButton = findViewById(R.id.confirm_button);
        cityInput = findViewById(R.id.city_input);

        // initialize visibility of the prompt and confirm button
        cityInput.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);

        // Initial cities with predefined content textview layout
        String[] cities = {"Edmonton", "Montréal"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Add City Button
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = "add";
                showInput();
            }
        });

        // Delete City Button
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = "delete";
                showInput();
            }
        });

        // Confirm Button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                if (cityName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "City name cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (currentAction.equals("add")) {
                    // do the action of adding
                    if (!dataList.contains(cityName)) {
                        dataList.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, cityName + " added!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, cityName + " already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else if (currentAction.equals("delete")) {
                    // do the action of deleting
                    if (dataList.contains(cityName)) {
                        dataList.remove(cityName);
                        cityAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, cityName + " deleted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, cityName + " not found!", Toast.LENGTH_SHORT).show();
                    }
                }

                hideInput();
            }
        });
    }

    // Show input field and confirm button
    private void showInput() {
        cityInput.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.VISIBLE);
    }

    // Hide input field and confirm button
    private void hideInput() {
        cityInput.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        cityInput.setText(""); // Clear the input field
    }
}
