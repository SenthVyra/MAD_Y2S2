package com.example.EpicDoodles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends FirebaseRecyclerAdapter<EmployeeModel,EmployeeAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EmployeeAdapter(@NonNull FirebaseRecyclerOptions<EmployeeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull EmployeeModel mmodel) {
        holder.ename.setText(mmodel.getEname());
        holder.age.setText(mmodel.getAge());
        holder.contact.setText(mmodel.getContact());
        holder.position.setText(mmodel.getPosition());

        holder.btnMEdit.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.ename.getContext())
                        .setContentHolder(new ViewHolder(R.layout.employee_update_popup))
                        .setExpanded(true,1000)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText ename = view.findViewById(R.id.txtMName);
                EditText age = view.findViewById(R.id.txtMType);
                EditText contact = view.findViewById(R.id.txtMDescription);
                EditText position = view.findViewById(R.id.txtMPrice);

                Button btnMUpdate = view.findViewById(R.id.btnMUpdate);

                ename.setText(mmodel.getEname());
                age.setText(mmodel.getAge());
                contact.setText(mmodel.getContact());
                position.setText(mmodel.getPosition());

                dialogPlus.show();

                btnMUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("ename",ename.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("contact",contact.getText().toString());
                        map.put("position", position.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("employee")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.ename.getContext(),"Successfully Updated.",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.ename.getContext(),"Error while Updating.",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnMDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.ename.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undone.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("employee")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.ename.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView ename,age,contact,position;
        Button btnMEdit, btnMDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ename = (TextView)itemView.findViewById(R.id.enametext);
            age = (TextView)itemView.findViewById(R.id.agetext);
            contact = (TextView)itemView.findViewById(R.id.contacttext);
            position = (TextView)itemView.findViewById(R.id.positiontext);

            btnMEdit = (Button)itemView.findViewById(R.id.btnMEdit);
            btnMDelete = (Button)itemView.findViewById(R.id.btnMDelete);
        }
    }
}
