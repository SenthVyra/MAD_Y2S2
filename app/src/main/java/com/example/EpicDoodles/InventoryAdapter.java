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

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class InventoryAdapter extends FirebaseRecyclerAdapter<InventoryModel, InventoryAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public InventoryAdapter(@NonNull FirebaseRecyclerOptions<InventoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull InventoryModel wmodel) {
        holder.pname.setText(wmodel.getPname());
        holder.ptype.setText(wmodel.getPtype());
        holder.pdescription.setText(wmodel.getPdescription());
        holder.quantity.setText(wmodel.getQuantity());

        Glide.with(holder.pimg.getContext())
                .load(wmodel.getPurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.pimg);

        holder.btnWEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.pimg.getContext())
                        .setContentHolder(new ViewHolder(R.layout.inventory_update_popup))
                        .setExpanded(true,1000)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText purl = view.findViewById(R.id.txtWImageUrl);
                EditText pname = view.findViewById(R.id.txtWName);
                EditText ptype = view.findViewById(R.id.txtWType);
                EditText pdescription = view.findViewById(R.id.txtWDescription);
                EditText quantity = view.findViewById(R.id.txtWPrice);

                Button btnWUpdate = view.findViewById(R.id.btnWUpdate);

                purl.setText(wmodel.getPurl());
                pname.setText(wmodel.getPname());
                ptype.setText(wmodel.getPtype());
                pdescription.setText(wmodel.getPdescription());
                quantity.setText(wmodel.getQuantity());

                dialogPlus.show();

                btnWUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("pname",pname.getText().toString());
                        map.put("ptype",ptype.getText().toString());
                        map.put("pdescription",pdescription.getText().toString());
                        map.put("quantity", quantity.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("inventory")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.pname.getContext(),"Successfully Updated.",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.pname.getContext(),"Error while Updating.",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnWDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.pname.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undone.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("inventory")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.pname.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView pimg;
        TextView pname,ptype,pdescription,quantity;
        Button btnWEdit, btnWDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            pimg = (CircleImageView)itemView.findViewById(R.id.pimg);
            pname = (TextView)itemView.findViewById(R.id.pnametext);
            ptype = (TextView)itemView.findViewById(R.id.ptypetext);
            pdescription = (TextView)itemView.findViewById(R.id.pdescriptiontext);
            quantity = (TextView)itemView.findViewById(R.id.quantitytext);

            btnWEdit = (Button)itemView.findViewById(R.id.btnWEdit);
            btnWDelete = (Button)itemView.findViewById(R.id.btnWDelete);
        }
    }
}
