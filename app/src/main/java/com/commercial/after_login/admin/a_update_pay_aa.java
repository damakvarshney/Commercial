package com.commercial.after_login.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_user;

import java.util.List;

public class a_update_pay_aa extends RecyclerView.Adapter<a_update_pay_aa.myholder> {

    Context context;
    List<a_update_pay_user> users;
    List<r_order_user> all_retailers;
    List<v_order_user> all_vendors;
    List<c_order_user> all_customers;
    String type;

    public a_update_pay_aa(Context context, List<a_update_pay_user> users, List<r_order_user> all_retailers, List<v_order_user> all_vendors, List<c_order_user> all_customers, String type) {
        this.context = context;
        this.users = users;
        this.all_retailers = all_retailers;
        this.all_vendors = all_vendors;
        this.all_customers = all_customers;
        this.type = type;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_update_pay_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        a_update_pay_user user = users.get(position);

        holder.date.setText(user.date);
        holder.status.setText(user.status);
        holder.amount.setText(user.amount + "");
        holder.order_id.setText(user.order_id + "");


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

        TextView date, status, order_id, amount;
        View root;


        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            date = itemView.findViewById(R.id.upa2);
            status = itemView.findViewById(R.id.upa4);
            order_id = itemView.findViewById(R.id.upa1);
            amount = itemView.findViewById(R.id.upa3);
        }
    }
}
