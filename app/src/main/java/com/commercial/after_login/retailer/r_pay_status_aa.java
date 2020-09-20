package com.commercial.after_login.retailer;

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

public class r_pay_status_aa extends RecyclerView.Adapter<r_pay_status_aa.myholder> {

    Context context;
    List<r_pay_status_user> users;
    List<r_order_user> all_retailers;

    public r_pay_status_aa(Context context, List<r_pay_status_user> user, List<r_order_user> all_retailers) {
        this.context = context;
        this.users = user;
        this.all_retailers = all_retailers;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.payment_status_r_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        r_pay_status_user user = users.get(position);

        holder.c_order_id.setText(String.valueOf(user.order_id));
        holder.c_date.setText(user.date);
        holder.c_status.setText(user.status);
        holder.c_amount.setText(user.amount+"");

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
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
       /*LayoutInflater in=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View v2=in.inflate(R.layout.a_login_admin,null);
       builder.setView(v2);*/
                builder.create().show();
                return true;
            }
        });
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
            c_order_id = itemView.findViewById(R.id.psr1);
            c_date = itemView.findViewById(R.id.psr2);
            c_amount = itemView.findViewById(R.id.psr4);
            c_status = itemView.findViewById(R.id.psr3);

        }
    }
}
