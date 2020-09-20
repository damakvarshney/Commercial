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

public class c_pay_status_aa extends RecyclerView.Adapter<c_pay_status_aa.myholder> {

    Context context;
    List<c_pay_status_user> users;
    List<c_order_user> all_customers;

    public c_pay_status_aa(Context context, List<c_pay_status_user> user, List<c_order_user> all_customers) {
        this.context = context;
        this.users = user;
        this.all_customers = all_customers;
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.payment_status_c_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        c_pay_status_user user = users.get(position);

        holder.c_order_id.setText(String.valueOf(user.order_id));
        holder.c_date.setText(user.date);
        holder.c_status.setText(user.state);
        holder.c_amount.setText(user.amount+"");
    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public class myholder extends RecyclerView.ViewHolder {

        TextView c_order_id,c_date,c_amount,c_status;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.root);
            c_order_id = itemView.findViewById(R.id.psc1);
            c_date = itemView.findViewById(R.id.psc2);
            c_amount = itemView.findViewById(R.id.psc4);
            c_status = itemView.findViewById(R.id.psc3);

        }
    }
}
