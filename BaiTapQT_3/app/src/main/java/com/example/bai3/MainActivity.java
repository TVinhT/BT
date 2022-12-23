package com.example.bai3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhMuc;
    ArrayList<String> arrayDanhMuc;
    ArrayAdapter adapterDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDanhMuc = (ListView) findViewById(R.id.listviewDanhMuc);
        arrayDanhMuc = new ArrayList<>();

        adapterDanhMuc = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayDanhMuc);
        lvDanhMuc.setAdapter(adapterDanhMuc);
        arrayDanhMuc.add("Proteins");
        arrayDanhMuc.add("Grains and Starches");
        arrayDanhMuc.add("Vitamins");
        arrayDanhMuc.add("d");
        arrayDanhMuc.add("e");
        arrayDanhMuc.add("f");
        arrayDanhMuc.add("g");

        lvDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(MainActivity.this, Proteins.class);
                    startActivity(intent);
                }

                if (i == 1){
                    Intent intent = new Intent(MainActivity.this, GrainsAndStarches.class);
                    startActivity(intent);
                }

                if (i == 2){
                    Intent intent = new Intent(MainActivity.this, Vitamins.class);
                    startActivity(intent);
                }
            }
        });

    }
}