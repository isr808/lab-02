package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.city_list);
        EditText inputCity = findViewById(R.id.inputCity);
        View btnAdd = findViewById(R.id.btnAdd);
        View btnDelete = findViewById(R.id.btnDelete);

        String[] cities = {"Edmonton", "Vancouver", "Berlin"};
        data = new ArrayList<>(Arrays.asList(cities));

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                data
        );

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // select city
        listView.setOnItemClickListener((parent, view, position, id) ->
                selectedIndex = position
        );

        // add city
        btnAdd.setOnClickListener(v -> {
            String city = inputCity.getText().toString().trim();
            if (!city.isEmpty()) {
                data.add(city);
                adapter.notifyDataSetChanged();
                inputCity.setText("");
            }
        });

        // delete city
        btnDelete.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                data.remove(selectedIndex);
                selectedIndex = -1;
                adapter.notifyDataSetChanged();
            }
        });
    }
}
