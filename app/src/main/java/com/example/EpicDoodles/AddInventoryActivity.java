package com.example.EpicDoodles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddInventoryActivity extends AppCompatActivity {
    EditText purl,pname,ptype,pdescription,quantity;
    Button btnWAdd,btnWBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);

        purl = (EditText)findViewById(R.id.txtWImageUrl);
        pname = (EditText)findViewById(R.id.txtWName);
        ptype = (EditText)findViewById(R.id.txtWType);
        pdescription = (EditText)findViewById(R.id.txtWDescription);
        quantity = (EditText)findViewById(R.id.txtWPrice);

        btnWAdd = (Button)findViewById(R.id.btnWAdd);
        btnWBack = (Button)findViewById(R.id.btnWBack);

        btnWAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnWBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("purl",purl.getText().toString());
        map.put("pname",pname.getText().toString());
        map.put("ptype",ptype.getText().toString());
        map.put("pdescription",pdescription.getText().toString());
        map.put("quantity", quantity.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("inventory").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddInventoryActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddInventoryActivity.this, "Error Occured during insertion!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        purl.setText("");
        pname.setText("");
        ptype.setText("");
        pdescription.setText("");
        quantity.setText("");
    }
}
