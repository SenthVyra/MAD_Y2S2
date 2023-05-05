package com.example.EpicDoodles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText ename,age,contact,position;
    Button btnMAdd,btnMBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_employee);


        ename = (EditText)findViewById(R.id.txtMName);
        age = (EditText)findViewById(R.id.txtMType);
        contact = (EditText)findViewById(R.id.txtMDescription);
        position = (EditText)findViewById(R.id.txtMPrice);

        btnMAdd = (Button)findViewById(R.id.btnMAdd);
        btnMBack = (Button)findViewById(R.id.btnMBack);

        btnMAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnMBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("ename",ename.getText().toString());
        map.put("age",age.getText().toString());
        map.put("contact",contact.getText().toString());
        map.put("position", position.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("employee").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddEmployeeActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEmployeeActivity.this, "Error Occured during insertion!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        ename.setText("");
        age.setText("");
        contact.setText("");
        position.setText("");
    }
}
