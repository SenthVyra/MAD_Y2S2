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

public class EmployeeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EmployeeAdapter employeeAdapter;

    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        recyclerView = (RecyclerView)findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<EmployeeModel> options =
                new FirebaseRecyclerOptions.Builder<EmployeeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("employee"), EmployeeModel.class)
                        .build();

        employeeAdapter =new EmployeeAdapter(options);
        recyclerView.setAdapter(employeeAdapter);

        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingActionButton1);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddEmployeeActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        employeeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        employeeAdapter.stopListening();
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
        FirebaseRecyclerOptions<EmployeeModel> options =
                new FirebaseRecyclerOptions.Builder<EmployeeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("employee").orderByChild("ename").startAt(str).endAt(str+"~"), EmployeeModel.class)
                        .build();

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(options);
        employeeAdapter.startListening();
        recyclerView.setAdapter(employeeAdapter);
    }
}