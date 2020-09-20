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

public class r_order_status_aa extends RecyclerView.Adapter<r_order_status_aa.myholder> {
    Context context;
    List<r_order_status_user> users;
    List<r_order_user> all_retailers;

    public r_order_status_aa(Context context, List<r_order_status_user> users, List<r_order_user> all_retailers) {
        this.context = context;
        this.users = users;
        this.all_retailers = all_retailers;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_status_r_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        r_order_status_user user = users.get(position);

        holder.r_order_id.setText(String.valueOf(user.order_id));
        holder.r_date.setText(user.date);
        holder.r_quantity.setText(user.quantity+"");
        holder.r_amount.setText(user.amount+"");

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

        TextView r_order_id,r_date,r_quantity,r_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.root);
            r_order_id = itemView.findViewById(R.id.osr1);
            r_date = itemView.findViewById(R.id.osr2);
            r_quantity = itemView.findViewById(R.id.osr3);
            r_amount = itemView.findViewById(R.id.osr4);
        }
    }
}
