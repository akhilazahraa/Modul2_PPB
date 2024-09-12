package com.example.modul2_kel26;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    private RecyclerView rvAboutList;
    private AboutAdapter aboutAdapter;
    private List<String> names;
    private List<String> nims;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        rvAboutList = findViewById(R.id.rv_about_list);
        rvAboutList.setLayoutManager(new LinearLayoutManager(this));

        // Initialize names and NIMs lists
        names = new ArrayList<>();
        nims = new ArrayList<>();

        names.add("Muhammad Ahib Ibrilli");
        nims.add("21120122140149");

        names.add("Akhila Zahra");
        nims.add("21120122120005");

        names.add("Reyhan Zidany Jovianto");
        nims.add("21120122130064");

        names.add("Aditya Lutfian Saputra");
        nims.add("21120122140113");

        // Set the adapter
        aboutAdapter = new AboutAdapter(names, nims);
        rvAboutList.setAdapter(aboutAdapter);
    }
}
