package com.example.modul2_kel26;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.content.Intent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListUser;
    private List<DataItem> listItem;
    private RecycleAdapter adapter;
    private List<DataItem> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListUser = findViewById(R.id.rv_list_user);
        SearchView searchView = findViewById(R.id.search_view);
        Button buttonFilter = findViewById(R.id.button_filter);
        Button btnHome = findViewById(R.id.btn_home);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        ApiClient.getService().getList().enqueue(new Callback<ListUserResponse>() {
            @Override
            public void onResponse(Call<ListUserResponse> call, Response<ListUserResponse> response) {
                if (response.isSuccessful()) {
                    listItem = response.body().getData();
                    filteredList = new ArrayList<>(listItem); // Initialize filtered list
                    adapter = new RecycleAdapter(filteredList, MainActivity.this);
                    rvListUser.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvListUser.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ListUserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        // Set up button click listener for filter
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });
    }

    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(listItem);
        } else {
            for (DataItem item : listItem) {
                if (item.getFirstName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showFilterDialog() {
        String[] filterOptions = {"A-Z", "Z-A"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by first name")
                .setItems(filterOptions, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            sortAZ();
                            break;
                        case 1:
                            sortZA();
                            break;
                    }
                });
        builder.create().show();
    }

    private void sortAZ() {
        Collections.sort(filteredList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortZA() {
        Collections.sort(filteredList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o2.getFirstName().compareTo(o1.getFirstName());
            }
        });
        adapter.notifyDataSetChanged();
    }
}
