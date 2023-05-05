package com.example.EpicDoodles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class InventoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InventoryAdapter inventoryAdapter;

    FloatingActionButton floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = (RecyclerView)findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<InventoryModel> options =
                new FirebaseRecyclerOptions.Builder<InventoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("inventory"), InventoryModel.class)
                        .build();

        inventoryAdapter =new InventoryAdapter(options);
        recyclerView.setAdapter(inventoryAdapter);

        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddInventoryActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        inventoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inventoryAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search_btn);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<InventoryModel> options =
                new FirebaseRecyclerOptions.Builder<InventoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("inventory").orderByChild("pname").startAt(str).endAt(str+"~"), InventoryModel.class)
                        .build();

        InventoryAdapter inventoryAdapter = new InventoryAdapter(options);
        inventoryAdapter.startListening();
        recyclerView.setAdapter(inventoryAdapter);
    }
}