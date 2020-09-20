package com.commercial.after_login.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_user;

import java.util.List;
import java.util.Objects;

public class a_collect_inside_aa extends RecyclerView.Adapter<a_collect_inside_aa.myholder> implements View.OnCreateContextMenuListener {

    Context context;
    List<a_collect_inside_user> users;

    public a_collect_inside_aa(Context context, List<a_collect_inside_user> user) {
        this.context = context;
        this.users = user;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_collect_inside_adap, null);
        return new myholder(v);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        a_collect_inside_user user = users.get(position);

        holder.o_item.setText(user.item);
        holder.o_quantity.setText(user.quantity);
        holder.o_rate.setText(user.rate);
        holder.o_amount.setText(user.amount);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }

    public class myholder extends RecyclerView.ViewHolder {
        TextView o_item, o_quantity, o_rate,o_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            o_item = itemView.findViewById(R.id.cia11);
            o_quantity = itemView.findViewById(R.id.cia12);
            o_rate = itemView.findViewById(R.id.cia13);
            o_amount = itemView.findViewById(R.id.cia14);
        }
    }
}





