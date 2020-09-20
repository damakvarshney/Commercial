package com.commercial.after_login.customer;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;

import java.util.List;

public class c_order_item_aa extends RecyclerView.Adapter<c_order_item_aa.myholder> {

    Context context;
    List<c_order_item_user> users;

    public c_order_item_aa(Context context, List<c_order_item_user> user) {
        this.context = context;
        this.users = user;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_item_c_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        c_order_item_user user = users.get(position);

        holder.c_item.setText(user.item);
        holder.c_quantity.setText(user.quantity + "");
        holder.c_rate.setText(user.rate + "");
        holder.c_amount.setText(user.amount + "");


        int per_item_amount = (user.rate * user.quantity);

        holder.c_amount.setText(per_item_amount + "");
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_item(position);
                return false;
            }
        });

    }

    private void delete_item(final int position) {
        androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                users.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNeutralButton("Cancel",null);
        builder.setTitle("Confirm Delete?");
        builder.setMessage("Do you really want to delete this");
        builder.create().show();
    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public class myholder extends RecyclerView.ViewHolder {

        TextView c_item, c_quantity;
        TextView  c_rate, c_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            c_item = itemView.findViewById(R.id.oica1);
            c_quantity = itemView.findViewById(R.id.oica2);
            c_rate = itemView.findViewById(R.id.oica3);
            c_amount = itemView.findViewById(R.id.oica4);

        }
    }
}
